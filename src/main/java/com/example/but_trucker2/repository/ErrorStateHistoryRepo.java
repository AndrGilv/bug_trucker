package com.example.but_trucker2.repository;

import com.example.but_trucker2.entity.CommentEntity;
import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ErrorStateChangingHistoryEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorState;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ErrorStateHistoryRepo extends CrudRepository<ErrorStateChangingHistoryEntity, Long> {
    List<ErrorStateChangingHistoryEntity> findAll();

    @Query(
            nativeQuery = true,
            value = "select * from error_histories " +
                    "where state = :state and error_id = :errorId and developer_id = :developerId")
    Optional<ErrorStateChangingHistoryEntity> findByDeveloperIdAndStateKeyAndErrorId(Long developerId, ErrorState state, Long errorId);
}
