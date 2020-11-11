package com.example.but_trucker2.controller.modelAssembler;


import com.example.but_trucker2.controller.ErrorsStateHistoryController;
import com.example.but_trucker2.controller.ProjectController;
import com.example.but_trucker2.controller.response.ErrorStateChangingHistoryResponse;
import com.example.but_trucker2.controller.response.extended.ExtendedErrorResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public
class ErrorStateHistoryModelAssembler implements RepresentationModelAssembler<ErrorStateChangingHistoryResponse, EntityModel<ErrorStateChangingHistoryResponse>> {

    @Override
    public EntityModel<ErrorStateChangingHistoryResponse> toModel(ErrorStateChangingHistoryResponse response) {

        return EntityModel.of(response,
                linkTo(methodOn(ErrorsStateHistoryController.class).one(response.getId())).withSelfRel(),
                linkTo(methodOn(ErrorsStateHistoryController.class).all()).withRel("projects"));
    }
}
