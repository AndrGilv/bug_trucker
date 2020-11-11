package com.example.but_trucker2.entity;

import com.example.but_trucker2.entity.dto.ProjectDTOWithRelations;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects", schema = "bug_trucker")
public class ProjectEntity{

    public ProjectEntity(){

    }

    public ProjectEntity(String name, UserEntity manager) {
        this.name = name;
        this.manager = manager;
        manager.getProjectsManagedList().add(this);
    }

    public ProjectEntity(String name, UserEntity manager, List<ErrorEntity> errors, List<UserEntity> developersGroup) {
        this.name = name;
        this.manager = manager;
        this.errors.addAll(errors);
        this.developersGroup.addAll(developersGroup);
    }

    @Id
    //@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "clothes_seq")
    //@SequenceGenerator(name="clothes_seq", schema = "clothes_shop",sequenceName="clothes_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column()
    private String name;

    @ManyToOne
    private UserEntity manager;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorEntity> errors = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<UserEntity> developersGroup = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void addDeveloper(UserEntity developer) {
        developersGroup.add(developer);
        developer.getProjects().add(this);
    }
    public void removeDeveloper(UserEntity developer) {
        developersGroup.remove(developer);
        developer.getProjects().remove(this);
    }

    public UserEntity getManager() {
        return manager;
    }
    public void setManager(UserEntity manager) {
        this.manager = manager;
    }

    public List<ErrorEntity> getErrors() {
        return errors;
    }
    public void setErrors(List<ErrorEntity> errors) {
        this.errors = errors;
    }

    public List<UserEntity> getDevelopersGroup() {
        return developersGroup;
    }
    public void setDevelopersGroup(List<UserEntity> developersGroup) {
        this.developersGroup = developersGroup;
    }

    public void updateAllFieldsFrom(ProjectEntity project){
        this.developersGroup = project.developersGroup;
        this.errors = project.errors;
        this.manager = project.manager;
        this.name = project.name;
    }

}
