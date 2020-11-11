package com.example.but_trucker2.controller.modelAssembler;


import com.example.but_trucker2.controller.ProjectController;
import com.example.but_trucker2.controller.ResponsibleController;
import com.example.but_trucker2.controller.response.CommentResponse;
import com.example.but_trucker2.controller.response.ErrorFixingResponsibleAssigningHistoryResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public
class ResponsibleModelAssembler implements RepresentationModelAssembler<ErrorFixingResponsibleAssigningHistoryResponse, EntityModel<ErrorFixingResponsibleAssigningHistoryResponse>> {

    @Override
    public EntityModel<ErrorFixingResponsibleAssigningHistoryResponse> toModel(ErrorFixingResponsibleAssigningHistoryResponse response) {

        return EntityModel.of(response,
                linkTo(methodOn(ResponsibleController.class).one(response.getId())).withSelfRel(),
                linkTo(methodOn(ResponsibleController.class).all()).withRel("projects"));
    }
}
