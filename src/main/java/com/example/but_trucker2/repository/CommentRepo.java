package com.example.but_trucker2.repository;

import com.example.but_trucker2.entity.CommentEntity;
import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<CommentEntity, Long> {
    List<CommentEntity> findAll();
}
