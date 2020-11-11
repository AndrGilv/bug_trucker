package com.example.but_trucker2.entity;

import com.example.but_trucker2.entity.dto.ProjectDTO;
import com.example.but_trucker2.entity.dto.UserDTOWithRelations;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "users", schema = "bug_trucker")
public class UserEntity implements UserDetails, Serializable{

    public UserEntity(){

    }

    public UserEntity(@NotEmpty String firstname, @NotEmpty String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public UserEntity(
            String firstname,
            String lastname,
            String middleName,
            String email,
            String specialisation,
            String username,
            String password,
            List<String> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middleName = middleName;
        this.email = email;
        this.specialisation = specialisation;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    //@NotEmpty
    @Column()
    private String firstname;

    //@NotEmpty
    @Column()
    private String lastname;


    @Column(name = "middle_name")
    private String middleName;

    //@Email
    @Column()
    private String email;

    @Column(name = "specialisation")
    private String specialisation;

    //@NotNull(message = "The value must be not null")
    //@NotEmpty
    private String username;

   // @NotEmpty
    //@NotNull(message = "The value must be not null")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorStateChangingHistoryEntity> errorHistory = new ArrayList<>();

    @ManyToMany(mappedBy = "developersGroup")
    private List<ProjectEntity> projects = new ArrayList<>();

    @OneToMany(mappedBy = "errorFounderUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorEntity> errorsFoundList = new ArrayList<>();

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectEntity> projectsManagedList = new ArrayList<>();

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> commentsList = new ArrayList<>();

    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorFixingResponsibleAssigningHistoryEntity> responsibleErrorsList = new ArrayList<>();

    public List<ErrorStateChangingHistoryEntity> getErrorHistory() {
        return errorHistory;
    }
    public void setErrorHistory(List<ErrorStateChangingHistoryEntity> errorHistory) {
        this.errorHistory = errorHistory;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }
    public void setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialisation() {
        return specialisation;
    }
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public List<ErrorEntity> getErrorsFoundList() {
        return errorsFoundList;
    }
    public void setErrorsFoundList(List<ErrorEntity> errorsFoundList) {
        this.errorsFoundList = errorsFoundList;
    }


    public List<ProjectEntity> getProjectsManagedList() {
        return projectsManagedList;
    }
    public void setProjectsManagedList(List<ProjectEntity> projectsManagedList) {
        this.projectsManagedList = projectsManagedList;
    }

    public List<CommentEntity> getCommentsList() {
        return commentsList;
    }
    public void setCommentsList(List<CommentEntity> commentsList) {
        this.commentsList = commentsList;
    }

    public List<ErrorFixingResponsibleAssigningHistoryEntity> getResponsibleErrorsList() {
        return responsibleErrorsList;
    }
    public void setResponsibleErrorsList(List<ErrorFixingResponsibleAssigningHistoryEntity> responsibleErrorsList) {
        this.responsibleErrorsList = responsibleErrorsList;
    }

    /*public String getUsername() {
        return username;
    }*/
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void updateAllFieldsFrom(UserEntity user){
        //this.commentsList = user.commentsList;
        this.email = user.email;
        this.password = user.password;
        this.username = user.username;
        //this.errorHistory = user.errorHistory;
        //this.commentsList = user.commentsList;
        //this.errorsFoundList = user.errorsFoundList;
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.middleName = user.middleName;
        //this.projects = user.projects;
        this.specialisation = user.specialisation;
        //this.responsibleErrorsList = user.responsibleErrorsList;
        this.roles = user.roles;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof UserEntity))
            return false;
        UserEntity user = (UserEntity) o;
        return user.id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.username, this.roles);
    }

    @Override
    public String toString() {
        return "User { " + username + " }";
    }
}
