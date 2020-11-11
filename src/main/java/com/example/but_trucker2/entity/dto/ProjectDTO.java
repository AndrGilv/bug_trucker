package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.UserEntity;

import java.util.List;

public interface ProjectDTO extends SimpleProjectDTO {

    public UserDTO getManager();
    //public void setManager(UserEntity manager);
}
