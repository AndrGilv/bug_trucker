package com.example.but_trucker2.controller;

import com.example.but_trucker2.controller.modelAssembler.UserModelAssembler;
import com.example.but_trucker2.controller.request.UserRequest;
import com.example.but_trucker2.controller.response.extended.ExtendedUserResponse;
import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.exception.UserNotFoundException;
import com.example.but_trucker2.repository.ErrorRepo;
import com.example.but_trucker2.repository.ProjectRepo;
import com.example.but_trucker2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final PasswordEncoder encoder;

    private final UserRepo repository;
    private final UserModelAssembler assembler;

    public UserController(PasswordEncoder encoder, UserRepo repository, UserModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
        this.encoder = encoder;
    }

    @GetMapping("/test")
    public EntityModel<ExtendedUserResponse> test() {
        UserEntity user = repository.findById(2L)
                .orElseThrow(() -> new UserNotFoundException(2L));
        return assembler.toModel(new ExtendedUserResponse(user));
    }

    @GetMapping("/me")
    public ResponseEntity<Map<Object, Object>> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
        );
        return ResponseEntity.ok(model);
    }

    // Aggregate root

    @GetMapping("/all")
    public CollectionModel<EntityModel<ExtendedUserResponse>> all() {
        List<EntityModel<ExtendedUserResponse>> users = repository.findAll()//((List<UserEntity>) repository.findAll())
                .stream()
                .map(user -> assembler.toModel(new ExtendedUserResponse(user)))
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping(
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<EntityModel<ExtendedUserResponse>> newUser(@RequestBody UserRequest request) {

        UserEntity newUser = new UserEntity(request.getFirstname(),
                request.getLastname(),
                request.getMiddleName(),
                request.getEmail(),
                request.getSpecialisation(),
                request.getUsername(),
                encoder.encode(request.getPassword()),
                request.getRoles());

        UserEntity user = repository.save(newUser);

        EntityModel<ExtendedUserResponse> userModel = assembler.toModel(new ExtendedUserResponse(user));

        return ResponseEntity
                .created(userModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(userModel);
    }

    // Single item
    @GetMapping("/{id}")
    public EntityModel<ExtendedUserResponse> one(@PathVariable Long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(new ExtendedUserResponse(user));
    }

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public EntityModel<ExtendedUserResponse> replace(@RequestBody UserRequest request, @PathVariable Long id) {
        UserEntity newUser = new UserEntity(request.getFirstname(),
                request.getLastname(),
                request.getMiddleName(),
                request.getEmail(),
                request.getSpecialisation(),
                request.getUsername(),
                encoder.encode(request.getPassword()),
                request.getRoles());

        UserEntity updatedUser = repository.findById(id)
                .map(user -> {
                    user.updateAllFieldsFrom(newUser);
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
        return assembler.toModel(new ExtendedUserResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
