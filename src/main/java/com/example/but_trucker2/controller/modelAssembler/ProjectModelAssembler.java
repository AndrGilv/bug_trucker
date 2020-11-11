package com.example.but_trucker2.controller.modelAssembler;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.but_trucker2.controller.ProjectController;
import com.example.but_trucker2.controller.response.extended.ExtendedProjectResponse;
import com.example.but_trucker2.entity.ProjectEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public
class ProjectModelAssembler implements RepresentationModelAssembler<ExtendedProjectResponse, EntityModel<ExtendedProjectResponse>> {

    @Override
    public EntityModel<ExtendedProjectResponse> toModel(ExtendedProjectResponse project) {

        return EntityModel.of(project,
                linkTo(methodOn(ProjectController.class).one(project.getId())).withSelfRel(),
                linkTo(methodOn(ProjectController.class).all()).withRel("projects"));
    }
}
