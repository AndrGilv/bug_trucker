package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.ProjectEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorCriticality;

public interface SimpleErrorDTO {

    public Long getId();
    //public void setId(Long id);

    public String getName();
    //public void setName(String name);

    public String getDescription();
    //public void setDescription(String description);

    public String getOccurrenceAre();
    //public void setOccurrenceAre(String occurrenceAre);

    public ErrorCriticality getCriticality();
    //public void setCriticality(ErrorCriticality criticality);


}
