package com.example.but_trucker2.controller.request;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRequest {

    //private Long id;
    private String name;
    private Long managerId;
    //private List<Long> errorsIdList = new ArrayList<>();
    //private List<Long> developersId = new ArrayList<>();

    /*public Long getId() {
        return id;
    }*/

    public String getName() {
        return name;
    }

    public Long getManagerId() {
        return managerId;
    }

    /*public List<Long> getErrorsIdList() {
        return errorsIdList;
    }

    public List<Long> getDevelopersId() {
        return developersId;
    }*/
}
