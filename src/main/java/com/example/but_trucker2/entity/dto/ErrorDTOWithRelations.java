package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.*;

import java.util.List;

public interface ErrorDTOWithRelations extends ErrorDTO {

    public List<ErrorStateChangingHistoryDTO> getErrorHistory();
    //public void setErrorHistory(List<ErrorStateChangingHistoryEntity> errorHistory);

    public List<CommentsDTO> getCommentsList();
    //public void setCommentsList(List<CommentEntity> commentsList);

    public List<ResponsibleAssigningHistoryDTO> getResponsibleHistoryList();
    //public void setResponsibleHistoryList(List<ErrorFixingResponsibleAssigningHistoryEntity> responsibleHistoryList);
}
