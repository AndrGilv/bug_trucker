package com.example.but_trucker2.repository;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ErrorStateChangingHistoryEntity;
import com.example.but_trucker2.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends CrudRepository<ProjectEntity, Long> {
    List<ProjectEntity> findAll();
}
