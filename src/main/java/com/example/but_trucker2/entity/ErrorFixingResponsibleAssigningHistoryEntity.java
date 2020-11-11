package com.example.but_trucker2.entity;

import com.example.but_trucker2.entity.dto.ErrorStateChangingHistoryDTO;
import com.example.but_trucker2.entity.dto.ResponsibleAssigningHistoryDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "error_resposibles", schema = "bug_trucker")
public class ErrorFixingResponsibleAssigningHistoryEntity {

    public ErrorFixingResponsibleAssigningHistoryEntity(){

    }

    public ErrorFixingResponsibleAssigningHistoryEntity(Date date, UserEntity developer, ErrorEntity error) {
        this.date = date;
        this.developer = developer;
        this.error = error;

        developer.getResponsibleErrorsList().add(this);
        error.getResponsibleHistoryList().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column()
    private Date date;

    @ManyToOne
    private UserEntity developer;

    @ManyToOne
    private ErrorEntity error;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserEntity getDeveloper() {
        return developer;
    }

    public void setDeveloper(UserEntity developer) {
        this.developer = developer;
    }

    public ErrorEntity getError() {
        return error;
    }

    public void setError(ErrorEntity error) {
        this.error = error;
    }

    public void updateAllFieldsFrom(ErrorFixingResponsibleAssigningHistoryEntity entity){
        date = entity.getDate();
        developer = entity.getDeveloper();
        error = entity.getError();
    }
}
