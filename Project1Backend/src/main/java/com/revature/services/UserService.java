package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.DTOs.UserDTO;
import com.revature.aspects.AdminOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.revature.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//this service handles stuff to do with the users
@Service //Beanify me!
public class UserService {

    //need the DAO
    private final UserDAO userDAO;

    //autowire the DAO
    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    //get all users from DB
    //we'll control access to this in the controller
    @AdminOnly
    public List<UserDTO> getAllUsers(){
        //JPArepository method for grabbing users
        List<User> allUsers = userDAO.findAll();

        //list for holding DTOs
        //dont want even admins to have password access
        List<UserDTO> outgoingUsers = new ArrayList<>();

        //use a for each loop
        for (User user : allUsers){
            outgoingUsers.add(new UserDTO(user));
        }

        //return the dtos
        return outgoingUsers;
    }

    @AdminOnly
    public List<UserDTO> getUsersByLastName(String lastName) {
        Optional<List<User>> optionalUserList = userDAO.findUsersByLastName(lastName);

        List userList = new ArrayList<UserDTO>();


        if (optionalUserList.isEmpty()){
            return userList;
        } else {
            userList.addAll(optionalUserList.get());
        }

        return userList;
    }

    @AdminOnly
    public List<UserDTO> getUsersByFirstName(String firstName) {
        Optional<List<User>> optionalUserList = userDAO.findUsersByFirstName(firstName);

        List userList = new ArrayList<UserDTO>();


        if (optionalUserList.isEmpty()){
            return userList;
        } else {
            userList.addAll(optionalUserList.get());
        }

        return userList;
    }


    //TODO: Make sure roles are stored in uppercase
    @AdminOnly
    public List<UserDTO> getUsersByRole(String role) {
        role = role.toUpperCase();
        Optional<List<User>> optionalUserList = userDAO.findUsersByRole(role);

        List userList = new ArrayList<UserDTO>();


        if (optionalUserList.isEmpty()){
            return userList;
        } else {
            userList.addAll(optionalUserList.get());
        }

        return userList;
    }

    @AdminOnly
    public ResponseEntity deleteUserById(int userId) {
        userDAO.deleteById(userId);

        //best practice is to return no content after a successful deletion
        return ResponseEntity.noContent().build();
    }

    @AdminOnly
    public ResponseEntity updateUserRoleById(int userId, String role) {
        userDAO.updateUserRoleByUserId(userId, role);
    }







}
