package com.revature.services;

import com.revature.DAOs.UserDAO;
import com.revature.DTOs.UserDTO;
import com.revature.aspects.AdminOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.models.User;

import java.util.ArrayList;
import java.util.List;

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




}
