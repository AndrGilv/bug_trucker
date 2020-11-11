package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ProjectEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorCriticality;

import java.util.Date;

public interface ErrorDTO extends SimpleErrorDTO {


    public SimpleProjectDTO getProject();
    //public void setProject(ProjectEntity project);

    public UserDTO getErrorFounderUser();
    //public void setErrorFounderUser(UserEntity errorFounderUser);

}
