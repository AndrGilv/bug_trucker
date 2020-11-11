package com.example.but_trucker2.controller.modelAssembler;


import com.example.but_trucker2.controller.ErrorsController;
import com.example.but_trucker2.controller.ProjectController;
import com.example.but_trucker2.controller.response.CommentResponse;
import com.example.but_trucker2.controller.response.extended.ExtendedErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public
class ErrorModelAssembler implements RepresentationModelAssembler<ExtendedErrorResponse, EntityModel<ExtendedErrorResponse>> {

    @Override
    public EntityModel<ExtendedErrorResponse> toModel(ExtendedErrorResponse response) {

        return EntityModel.of(response,
                linkTo(methodOn(ErrorsController.class).one(response.getId())).withSelfRel(),
                linkTo(methodOn(ErrorsController.class).all()).withRel("projects"));
    }
}
