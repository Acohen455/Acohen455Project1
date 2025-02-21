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
    @Before("within(com.revature.Controllers.*)" + "&& !within(com.revature.Controllers.AuthController.*)")
    public void checkLoggedIn(){
        //get access to the session (or lack thereof)
        //start by grabbing attributes from the session
        ServletRequestAttributes sessionAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession currSession = sessionAttributes.getRequest().getSession(false); //dont create session

        //check if session is null
        if(currSession == null || currSession.getAttribute("userId") == null){
            throw new IllegalArgumentException("User is not logged in");
        }





    }

}
