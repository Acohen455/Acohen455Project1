package com.revature.aspects;


import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthAspect {

    @Order(1)
    @Before("within(com.revature.Controllers.*) && !within(com.revature.Controllers.AuthController)")
    public void checkLoggedIn(){
        System.out.println("AuthAspect: Checking if user is logged in...");

        //get access to the session (or lack thereof)
        //start by grabbing attributes from the session
        ServletRequestAttributes sessionAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession currSession = sessionAttributes.getRequest().getSession(false); //dont create session

        //check if session is null
        if(currSession == null || currSession.getAttribute("userId") == null){
            throw new IllegalArgumentException("User is not logged in");
        }
    }

    @Order(2)
    @Before("@annotation(com.revature.aspects.AdminOnly)")
    public void checkAdmin() {


        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession(false);

        //check for session
        //if the session is null, the user is not logged in and we can throw an exception
        if(session == null || session.getAttribute("userId") == null) {
            throw new IllegalArgumentException("User must be logged in to do this!");
        }


        session.getAttribute("role");
        String role = session.getAttribute("role").toString();

        //just to see:
        System.out.println(session.getAttribute("role").toString());


        //If the User's role != "admin", throw an exception
        if(!role.equalsIgnoreCase("admin")) {
            throw new IllegalArgumentException("You must be an admin to do this!");
        }
    }

}
