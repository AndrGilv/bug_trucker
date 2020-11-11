package com.example.but_trucker2.repository;

import com.example.but_trucker2.entity.CommentEntity;
import com.example.but_trucker2.entity.ErrorFixingResponsibleAssigningHistoryEntity;
import com.example.but_trucker2.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsiblesRepo extends CrudRepository<ErrorFixingResponsibleAssigningHistoryEntity, Long> {
    List<ErrorFixingResponsibleAssigningHistoryEntity> findAll();
}
