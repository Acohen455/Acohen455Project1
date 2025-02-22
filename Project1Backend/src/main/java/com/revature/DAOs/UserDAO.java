package com.revature.DAOs;

import com.revature.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Extending JpaRepository gives us access to a bunch of basic CRUD methods. We don't have to write them!
//This includes find all, find by id, insert, update, delete

//JpaRepository takes 2 generics:
//1) The entity we're working with (User in this case)
//2) The type of the primary key (Integer in this case)
@Repository //1 of the 4 stereotype annotations (make a class a bean)
public interface UserDAO extends JpaRepository<User, Integer> {

    //attempting to implement spring security user auth
    Optional<User> findByUsername(String username);

    Optional<User> findByUserId(Integer userId);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<List<User>> findUsersByLastName(String lastName);

    Optional<List<User>> findUsersByFirstName(String firstName);

    Optional<List<User>> findUsersByRole(String role);

    //this has to be done manually, as spring can only autowire retrievals
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.role = :role WHERE u.userId = :userId")
    void updateUserRoleByUserId(@Param("userId") Integer userId, @Param("role") String role);

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.userId = :userId")
    void deleteUserById(@Param("userId") Integer userId);

}
