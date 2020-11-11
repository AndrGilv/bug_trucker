package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.*;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface UserDTOWithRelations extends UserDTO {

    public List<ErrorStateChangingHistoryDTO> getErrorHistory();
    //public void setErrorHistory(List<ErrorStateChangingHistoryEntity> errorHistory);

    public List<ProjectDTO> getProjects();
    //public void setProjects(List<ProjectEntity> projects);

    public List<ErrorDTO> getErrorsFoundList();
    //public void setErrorsFoundList(List<ErrorEntity> errorsFoundList);

    public List<ProjectDTO> getProjectsManagedList();
    //public void setProjectsManagedList(List<ProjectEntity> projectsManagedList);

    public List<CommentsDTO> getCommentsList();
    //public void setCommentsList(List<CommentEntity> commentsList);

    public List<ResponsibleAssigningHistoryDTO> getResponsibleErrorsList();
    //public void setResponsibleErrorsList(List<ErrorFixingResponsibleAssigningHistoryEntity> responsibleErrorsList);

}
