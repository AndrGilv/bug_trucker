package com.example.but_trucker2.controller.modelAssembler;


import com.example.but_trucker2.controller.CommentsController;
import com.example.but_trucker2.controller.ProjectController;
import com.example.but_trucker2.controller.response.CommentResponse;
import com.example.but_trucker2.controller.response.extended.ExtendedProjectResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public
class CommentModelAssembler implements RepresentationModelAssembler<CommentResponse, EntityModel<CommentResponse>> {

    @Override
    public EntityModel<CommentResponse> toModel(CommentResponse response) {

        return EntityModel.of(response,
                linkTo(methodOn(CommentsController.class).one(response.getId())).withSelfRel(),
                linkTo(methodOn(CommentsController.class).all()).withRel("projects"));
    }
}
