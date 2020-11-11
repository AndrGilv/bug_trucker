package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;

import java.util.List;

public interface ProjectDTOWithRelations extends ProjectDTO {

    public List<ErrorDTO> getErrors();
    //public void setErrors(List<ErrorEntity> errors);

    public List<UserDTO> getDevelopersGroup();
    //public void setDevelopersGroup(List<UserEntity> developersGroup);


}
