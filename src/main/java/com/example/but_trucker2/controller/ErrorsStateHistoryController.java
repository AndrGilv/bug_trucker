package com.example.but_trucker2.controller;

import com.example.but_trucker2.controller.modelAssembler.ErrorModelAssembler;
import com.example.but_trucker2.controller.modelAssembler.ErrorStateHistoryModelAssembler;
import com.example.but_trucker2.controller.response.ErrorStateChangingHistoryResponse;
import com.example.but_trucker2.controller.response.extended.ExtendedErrorResponse;
import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ErrorStateChangingHistoryEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorState;
import com.example.but_trucker2.exception.ErrorNotFoundException;
import com.example.but_trucker2.exception.ErrorStateHistoryNotFoundException;
import com.example.but_trucker2.exception.UserNotFoundException;
import com.example.but_trucker2.repository.ErrorRepo;
import com.example.but_trucker2.repository.ErrorStateHistoryRepo;
import com.example.but_trucker2.repository.ProjectRepo;
import com.example.but_trucker2.repository.UserRepo;
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
@RequestMapping(path = "/errorStateHistory")
public class ErrorsStateHistoryController {

    private final UserRepo developerRepo;
    private final ErrorRepo errorRepo;

    private final ErrorStateHistoryRepo repository;
    private final ErrorStateHistoryModelAssembler assembler;

    public ErrorsStateHistoryController(ErrorStateHistoryRepo repository, ErrorStateHistoryModelAssembler assembler, UserRepo developerRepo, ErrorRepo errorRepo){
        this.repository = repository;
        this.assembler = assembler;
        this.developerRepo = developerRepo;
        this.errorRepo = errorRepo;
    }

    // Aggregate root

    @GetMapping("/all")
    public CollectionModel<EntityModel<ErrorStateChangingHistoryResponse>> all() {
        List<EntityModel<ErrorStateChangingHistoryResponse>> historyItems = repository.findAll()
                .stream()
                .map( historyItem -> assembler.toModel(new ErrorStateChangingHistoryResponse(historyItem)))
                .collect(Collectors.toList());

        return CollectionModel.of(historyItems, linkTo(methodOn(ErrorsStateHistoryController.class).all()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<EntityModel<ErrorStateChangingHistoryResponse>> newHistoryItem(@RequestBody ErrorStateChangingHistoryEntity newHistoryItem) {
        EntityModel<ErrorStateChangingHistoryResponse> historyItemModel = assembler.toModel( new ErrorStateChangingHistoryResponse(repository.save(newHistoryItem)));
        return ResponseEntity
                .created(historyItemModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(historyItemModel);
    }

    // Single item

    @GetMapping("/{id}")
    public EntityModel<ErrorStateChangingHistoryResponse> one(@PathVariable Long id) {
        ErrorStateChangingHistoryEntity historyItem = repository.findById(id)
                .orElseThrow(() -> new ErrorStateHistoryNotFoundException(id));
        return assembler.toModel(new ErrorStateChangingHistoryResponse(historyItem));
    }

    @PutMapping("/{errorId}/{state}/{developerId}")
    public ResponseEntity<EntityModel<ErrorStateChangingHistoryResponse>> replaceErrorHistoryItem(@RequestBody ErrorStateChangingHistoryEntity newHistoryItem, @PathVariable Long errorId, @PathVariable ErrorState state, @PathVariable Long developerId) {
        ErrorStateChangingHistoryEntity updatedErrorHistoryItem = repository.findByDeveloperIdAndStateKeyAndErrorId(errorId, state, developerId)
                .map(item -> {
                    item.updateAllFieldsFrom(newHistoryItem);
                    return repository.save(item);
                })
                .orElseGet(() -> {
                    //newHistoryItem.setDeveloper(developerRepo.findById(developerId).orElseThrow(() -> new UserNotFoundException(developerId)));
                    return repository.save(newHistoryItem);
                });
        EntityModel<ErrorStateChangingHistoryResponse> errorHistoryItemModel = assembler.toModel(new ErrorStateChangingHistoryResponse(updatedErrorHistoryItem));
        return ResponseEntity
                .created(errorHistoryItemModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(errorHistoryItemModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
