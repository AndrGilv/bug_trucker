package com.example.but_trucker2.entity;


import com.example.but_trucker2.entity.dto.ErrorDTOWithRelations;
import com.example.but_trucker2.entity.dto.ErrorStateChangingHistoryDTO;
import com.example.but_trucker2.entity.termsDirectories.ErrorCriticality;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "errors", schema = "bug_trucker")
public class ErrorEntity{
    public ErrorEntity() {

    }

    public ErrorEntity(String name, String description, String occurrenceAre, ErrorCriticality criticality, ProjectEntity project, UserEntity errorFounderUser) {
        this.name = name;
        this.description = description;
        this.occurrenceAre = occurrenceAre;
        this.criticality = criticality;
        this.project = project;
        this.errorFounderUser = errorFounderUser;

        project.getErrors().add(this);
        errorFounderUser.getErrorsFoundList().add(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column(name = "occurrence_are")
    private String occurrenceAre;


    @Column(name = "criticality")
    private ErrorCriticality criticality;

    @ManyToOne
    private ProjectEntity project;

    //@Column(name = "error_founder_user")
    @ManyToOne
    private UserEntity errorFounderUser; // пользователь который нашёл ошибку

    @OneToMany(mappedBy = "error", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorStateChangingHistoryEntity> errorHistory = new ArrayList<>();

    @OneToMany(mappedBy = "error", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> commentsList = new ArrayList<>();

    @OneToMany(mappedBy = "error", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorFixingResponsibleAssigningHistoryEntity> responsibleHistoryList = new ArrayList<>();



    public ProjectEntity getProject() {
        return project;
    }
    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public UserEntity getErrorFounderUser() {
        return errorFounderUser;
    }
    public void setErrorFounderUser(UserEntity errorFounderUser) {
        this.errorFounderUser = errorFounderUser;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getOccurrenceAre() {
        return occurrenceAre;
    }
    public void setOccurrenceAre(String occurrenceAre) {
        this.occurrenceAre = occurrenceAre;
    }

    @Enumerated(EnumType.STRING)
    public ErrorCriticality getCriticality() {
        return criticality;
    }
    public void setCriticality(ErrorCriticality criticality) {
        this.criticality = criticality;
    }

    public List<ErrorStateChangingHistoryEntity> getErrorHistory() {
        return errorHistory;
    }
    public void setErrorHistory(List<ErrorStateChangingHistoryEntity> errorHistory) {
        this.errorHistory = errorHistory;
    }

    public List<CommentEntity> getCommentsList() {
        return commentsList;
    }
    public void setCommentsList(List<CommentEntity> commentsList) {
        this.commentsList = commentsList;
    }

    public List<ErrorFixingResponsibleAssigningHistoryEntity> getResponsibleHistoryList() {
        return responsibleHistoryList;
    }
    public void setResponsibleHistoryList(List<ErrorFixingResponsibleAssigningHistoryEntity> responsibleHistoryList) {
        this.responsibleHistoryList = responsibleHistoryList;
    }

    public void updateAllFieldsFrom(ErrorEntity entity){

        name = entity.getName();
        description = entity.getDescription();
        occurrenceAre = entity.getOccurrenceAre();
        criticality = entity.getCriticality();
        project = entity.getProject();
        errorFounderUser = entity.getErrorFounderUser();
        errorHistory = entity.getErrorHistory();
        commentsList = entity.getCommentsList();
        responsibleHistoryList = entity.getResponsibleHistoryList();
    }
}
