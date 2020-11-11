package com.example.but_trucker2.controller;

import com.example.but_trucker2.controller.modelAssembler.ErrorModelAssembler;
import com.example.but_trucker2.controller.modelAssembler.ResponsibleModelAssembler;
import com.example.but_trucker2.controller.response.ErrorFixingResponsibleAssigningHistoryResponse;
import com.example.but_trucker2.controller.response.extended.ExtendedErrorResponse;
import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ErrorFixingResponsibleAssigningHistoryEntity;
import com.example.but_trucker2.exception.ErrorNotFoundException;
import com.example.but_trucker2.exception.ResponsibleNotFoundException;
import com.example.but_trucker2.repository.ErrorRepo;
import com.example.but_trucker2.repository.ResponsiblesRepo;
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
@RequestMapping(path = "/responsibleDeveloper")
public class ResponsibleController {

    private final ResponsiblesRepo repository;
    private final ResponsibleModelAssembler assembler;

    public ResponsibleController(ResponsiblesRepo repository, ResponsibleModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/all")
    public CollectionModel<EntityModel<ErrorFixingResponsibleAssigningHistoryResponse>> all() {
        List<EntityModel<ErrorFixingResponsibleAssigningHistoryResponse>> responsibleDevelopers = repository.findAll()
                .stream()
                .map( developer -> assembler.toModel(new ErrorFixingResponsibleAssigningHistoryResponse(developer)))
                .collect(Collectors.toList());

        return CollectionModel.of(responsibleDevelopers, linkTo(methodOn(ResponsibleController.class).all()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<EntityModel<ErrorFixingResponsibleAssigningHistoryResponse>> newResponsible(@RequestBody ErrorFixingResponsibleAssigningHistoryEntity newResponsible) {
        EntityModel<ErrorFixingResponsibleAssigningHistoryResponse> responsibleDeveloperModel = assembler.toModel( new ErrorFixingResponsibleAssigningHistoryResponse(repository.save(newResponsible)));
        return ResponseEntity
                .created(responsibleDeveloperModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(responsibleDeveloperModel);
    }

    // Single item

    @GetMapping("/{id}")
    public EntityModel<ErrorFixingResponsibleAssigningHistoryResponse> one(@PathVariable Long id) {
        ErrorFixingResponsibleAssigningHistoryEntity responsibleDeveloper = repository.findById(id)
                .orElseThrow(() -> new ResponsibleNotFoundException(id));
        return assembler.toModel(new ErrorFixingResponsibleAssigningHistoryResponse(responsibleDeveloper));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ErrorFixingResponsibleAssigningHistoryResponse>> replaceResponsible(@RequestBody ErrorFixingResponsibleAssigningHistoryEntity newResponsible, @PathVariable Long id) {
        ErrorFixingResponsibleAssigningHistoryEntity updatedResponsibleDeveloper = repository.findById(id)
                .map(developer -> {
                    developer.updateAllFieldsFrom(newResponsible);
                    return repository.save(developer);
                })
                .orElseGet(() -> {
                    newResponsible.setId(id);
                    return repository.save(newResponsible);
                });
        EntityModel<ErrorFixingResponsibleAssigningHistoryResponse> responsibleDeveloperModel = assembler.toModel(new ErrorFixingResponsibleAssigningHistoryResponse(updatedResponsibleDeveloper));
        return ResponseEntity
                .created(responsibleDeveloperModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(responsibleDeveloperModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResponsible(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
