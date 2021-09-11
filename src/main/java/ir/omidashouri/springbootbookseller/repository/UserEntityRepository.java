package ir.omidashouri.springbootbookseller.repository;

import ir.omidashouri.springbootbookseller.model.Role;
import ir.omidashouri.springbootbookseller.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String username);


    @Modifying
    @Query("update UserEntity u set u.role=:role where u.username=:username")
    void updateUserRole(@Param("username")String username,@Param("role") Role role);
}
