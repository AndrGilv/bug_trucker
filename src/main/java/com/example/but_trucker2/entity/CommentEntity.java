package com.example.but_trucker2.entity;


import com.example.but_trucker2.entity.dto.CommentsDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments", schema = "bug_trucker")
public class CommentEntity{

    public CommentEntity(){

    }

    public CommentEntity(String text, Date date, ErrorEntity error, UserEntity developer) {
        this.text = text;
        this.date = date;
        this.error = error;
        this.developer = developer;

        error.getCommentsList().add(this);
        developer.getCommentsList().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column()
    private String text;

    @Column()
    private Date date;

    @ManyToOne
    private ErrorEntity error;

    @ManyToOne
    private UserEntity developer;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public ErrorEntity getError() {
        return error;
    }
    public void setError(ErrorEntity error) {
        this.error = error;
    }

    public UserEntity getDeveloper() {
        return developer;
    }
    public void setDeveloper(UserEntity developer) {
        this.developer = developer;
    }

    public void updateAllFieldsFrom(CommentEntity entity){
        text = entity.getText();
        date = entity.getDate();
        error = entity.getError();
        developer = entity.getDeveloper();
    }
}
