package com.example.but_trucker2.repository;

import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.dto.UserDTOWithRelations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findAll();

    //Optional<UserDTOWithRelations> getUserEntityById(Long id);

   // List<UserDTOWithRelations> findAllBy();

    //UserEntity save(UserEntity user);


//    @Query(nativeQuery = true,
//            value = "select user.email, user.password, user.firstname, user.lastname, user.middle_name,  " +
//                    "user.specialisation, user.username, " +
//            "from users " +
//                    "inner join user_entity_roles on user_entity_roles.user_entity_id = users.id " +
//                    "left join error_histories on error_histories.developer_id = users.id" +
//                    "left join comments on comments.developer_id = users.id" +
//                    "left join error_resposibles on error_resposibles.developer_id = users.id" +
//                    "left join errors on errors.error_founder_user_id = users.id" +
//                    "left join projects on projects.manager_id = users.id" +
//                    "left join projects_developers_group on projects_developers_group.developers_group_id = users.id")
//    Optional<UserDTOWithRelations> getUserInfoWithRelationsByID(Long id);

    //Optional<TestUserDTO> findById(Long id);

    /*@Query(nativeQuery = true, value = "" +
            "select * " +
            "from user inner join ")*/
    /*@Transactional(readOnly = true)
    List<UserDTOWithRelations> findAllUserDTO();*/


}
