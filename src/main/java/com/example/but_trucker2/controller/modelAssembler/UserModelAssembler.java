package com.example.but_trucker2.controller.modelAssembler;


import com.example.but_trucker2.controller.ProjectController;
import com.example.but_trucker2.controller.UserController;
import com.example.but_trucker2.controller.response.UserResponse;
import com.example.but_trucker2.controller.response.extended.ExtendedUserResponse;
import com.example.but_trucker2.entity.ProjectEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.dto.UserDTOWithRelations;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public
class UserModelAssembler implements RepresentationModelAssembler<ExtendedUserResponse, EntityModel<ExtendedUserResponse>> {


   /* public EntityModel<UserEntity> toModel(UserEntity user) {

        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }*/

   /* @Override
    public EntityModel<UserResponse> toModel(UserResponse user) {

        return EntityModel.of( user,
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }*/
    @Override
    public EntityModel<ExtendedUserResponse> toModel(ExtendedUserResponse user) {

        return EntityModel.of( user,
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}
