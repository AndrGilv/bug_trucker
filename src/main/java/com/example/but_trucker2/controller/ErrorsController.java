package com.example.but_trucker2.controller;

import com.example.but_trucker2.controller.modelAssembler.CommentModelAssembler;
import com.example.but_trucker2.controller.modelAssembler.ErrorModelAssembler;
import com.example.but_trucker2.controller.response.CommentResponse;
import com.example.but_trucker2.controller.response.extended.ExtendedErrorResponse;
import com.example.but_trucker2.entity.CommentEntity;
import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.exception.CommentNotFoundException;
import com.example.but_trucker2.exception.ErrorNotFoundException;
import com.example.but_trucker2.repository.CommentRepo;
import com.example.but_trucker2.repository.ErrorRepo;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/errors")
public class ErrorsController {

    private final ErrorRepo repository;
    private final ErrorModelAssembler assembler;

    public ErrorsController(ErrorRepo repository, ErrorModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/all")
    public CollectionModel<EntityModel<ExtendedErrorResponse>> all() {
        List<EntityModel<ExtendedErrorResponse>> comments = repository.findAll()
                .stream()
                .map( comment -> assembler.toModel(new ExtendedErrorResponse(comment)))
                .collect(Collectors.toList());

        return CollectionModel.of(comments, linkTo(methodOn(ErrorsController.class).all()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<EntityModel<ExtendedErrorResponse>> newError(@RequestBody ErrorEntity newError) {
        EntityModel<ExtendedErrorResponse> commentModel = assembler.toModel( new ExtendedErrorResponse(repository.save(newError)));
        return ResponseEntity
                .created(commentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(commentModel);
    }

    // Single item

    @GetMapping("/{id}")
    public EntityModel<ExtendedErrorResponse> one(@PathVariable Long id) {
        ErrorEntity comment = repository.findById(id)
                .orElseThrow(() -> new ErrorNotFoundException(id));
        return assembler.toModel(new ExtendedErrorResponse(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ExtendedErrorResponse>> replaceError(@RequestBody ErrorEntity newError, @PathVariable Long id) {
        ErrorEntity updatedError = repository.findById(id)
                .map(error -> {
                    error.updateAllFieldsFrom(newError);
                    return repository.save(error);
                })
                .orElseGet(() -> {
                    newError.setId(id);
                    return repository.save(newError);
                });
        EntityModel<ExtendedErrorResponse> errorModel = assembler.toModel(new ExtendedErrorResponse(updatedError));
        return ResponseEntity
                .created(errorModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(errorModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteError(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
