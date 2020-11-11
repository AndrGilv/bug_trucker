package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;

import java.util.Date;
import java.util.List;

public interface CommentsDTO extends SimpleCommentsDTO{

    public SimpleErrorDTO getError();
    //public void setError(ErrorEntity error);

    public UserDTO getDeveloper();
    //public void setDeveloper(UserEntity developer);

}
