package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.UserEntity;
import lombok.Builder;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public interface UserDTO {

    public Long getId();
    //public void setId(Long id);

    public String getFirstname();
    //public void setFirstname(String firstname);

    public String getLastname();
    //public void setLastname(String lastname);

    public String getMiddleName();
    //public void setMiddleName(String middleName);

    public String getEmail();
    //public void setEmail(String email);

    public String getSpecialisation();
    //public void setSpecialisation(String specialisation);

    public List<String> getRoles();
    //public void setRoles(List<String> roles);

}
