package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;
import java.util.List;

public interface ResponsibleAssigningHistoryDTO extends SimpleResponsibleAssigningHistoryDTO {

    public UserDTO getDeveloper();
    //public void setDeveloper(UserEntity developer);

    public SimpleErrorDTO getError();
    //public void setError(ErrorEntity error);

}
