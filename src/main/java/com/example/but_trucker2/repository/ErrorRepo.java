package com.example.but_trucker2.repository;

import com.example.but_trucker2.entity.CommentEntity;
import com.example.but_trucker2.entity.ErrorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErrorRepo extends CrudRepository<ErrorEntity, Long> {
    List<ErrorEntity> findAll();
}
