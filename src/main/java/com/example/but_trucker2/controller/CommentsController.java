package com.example.but_trucker2.controller;

import com.example.but_trucker2.controller.modelAssembler.CommentModelAssembler;
import com.example.but_trucker2.controller.modelAssembler.ProjectModelAssembler;
import com.example.but_trucker2.controller.response.CommentResponse;
import com.example.but_trucker2.controller.response.extended.ExtendedProjectResponse;
import com.example.but_trucker2.entity.CommentEntity;
import com.example.but_trucker2.entity.ProjectEntity;
import com.example.but_trucker2.exception.CommentNotFoundException;
import com.example.but_trucker2.exception.ProjectNotFoundException;
import com.example.but_trucker2.repository.CommentRepo;
import com.example.but_trucker2.repository.ProjectRepo;
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
@RequestMapping(path = "/comments")
public class CommentsController {

    private final CommentRepo repository;
    private final CommentModelAssembler assembler;

    public CommentsController(CommentRepo repository, CommentModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/all")
    public CollectionModel<EntityModel<CommentResponse>> all() {
        List<EntityModel<CommentResponse>> comments = repository.findAll()
                .stream()
                .map( comment -> assembler.toModel(new CommentResponse(comment)))
                .collect(Collectors.toList());

        return CollectionModel.of(comments, linkTo(methodOn(CommentsController.class).all()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<EntityModel<CommentResponse>> newComment(@RequestBody CommentEntity newEntity) {
        EntityModel<CommentResponse> commentModel = assembler.toModel( new CommentResponse(repository.save(newEntity)));
        return ResponseEntity
                .created(commentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(commentModel);
    }

    // Single item

    @GetMapping("/{id}")
    public EntityModel<CommentResponse> one(@PathVariable Long id) {
        CommentEntity comment = repository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        return assembler.toModel(new CommentResponse(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CommentResponse>> replaceComment(@RequestBody CommentEntity newComment, @PathVariable Long id) {
        CommentEntity updatedComment = repository.findById(id)
                .map(comment -> {
                    comment.updateAllFieldsFrom(newComment);
                    return repository.save(comment);
                })
                .orElseGet(() -> {
                    newComment.setId(id);
                    return repository.save(newComment);
                });
        EntityModel<CommentResponse> commentModel = assembler.toModel(new CommentResponse(updatedComment));
        return ResponseEntity //
                .created(commentModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(commentModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
