package com.example.but_trucker2.entity;



import com.example.but_trucker2.entity.dto.ErrorStateChangingHistoryDTO;
import com.example.but_trucker2.entity.termsDirectories.ErrorState;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "error_histories", schema = "bug_trucker")
public class ErrorStateChangingHistoryEntity{

    public ErrorStateChangingHistoryEntity() {

    }

    public ErrorStateChangingHistoryEntity(UserEntity developer, ErrorState state, ErrorEntity error, Date changeDate) {
        this.developer = developer;
        this.state = state;
        this.error = error;
        this.changeDate = changeDate;

        developer.getErrorHistory().add(this);
        error.getErrorHistory().add(this);
    }

    /*@EmbeddedId
    IdKey compositeId;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @ManyToOne
    private UserEntity developer;

    private ErrorState state;

    @ManyToOne
    private ErrorEntity error;

    @Column(name = "change_date")
    private Date changeDate;

    public Date getChangeDate() {
        return changeDate;
    }
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public void updateAllFieldsFrom(ErrorStateChangingHistoryEntity entity){
        this.developer = entity.getDeveloper();
        this.state = entity.getState();
        this.error = entity.getError();
        changeDate = entity.getChangeDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getDeveloper() {
        return developer;
    }

    public void setDeveloper(UserEntity developer) {
        this.developer = developer;
    }

    public ErrorState getState() {
        return state;
    }

    public void setState(ErrorState state) {
        this.state = state;
    }

    public ErrorEntity getError() {
        return error;
    }

    public void setError(ErrorEntity error) {
        this.error = error;
    }
}
