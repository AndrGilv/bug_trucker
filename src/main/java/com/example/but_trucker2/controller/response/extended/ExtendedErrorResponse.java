package com.example.but_trucker2.controller.response.extended;

import com.example.but_trucker2.controller.response.*;
import com.example.but_trucker2.entity.*;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExtendedErrorResponse extends ErrorResponse {


    private List<ErrorStateChangingHistoryResponse> errorHistory;
    private List<CommentResponse> commentsList;
    private List<ErrorFixingResponsibleAssigningHistoryResponse> responsibleHistoryList;

    public ExtendedErrorResponse(ErrorEntity entity) {
        super(entity);
        this.errorHistory = entity.getErrorHistory().stream().map(ErrorStateChangingHistoryResponse::new).collect(Collectors.toList());;
        this.commentsList = entity.getCommentsList().stream().map(CommentResponse::new).collect(Collectors.toList());
        this.responsibleHistoryList = entity.getResponsibleHistoryList().stream().map(ErrorFixingResponsibleAssigningHistoryResponse::new).collect(Collectors.toList());
    }

    public List<ErrorStateChangingHistoryResponse> getErrorHistory() {
        return errorHistory;
    }

    public List<CommentResponse> getCommentsList() {
        return commentsList;
    }

    public List<ErrorFixingResponsibleAssigningHistoryResponse> getResponsibleHistoryList() {
        return responsibleHistoryList;
    }
}
