package com.revature.Controllers;

import com.revature.aspects.AdminOnly;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AdminOnly
@RequestMapping("/admin")
public class AdminController {



    @GetMapping("/test")
    public void testMethod(){
        System.out.println("Accessed successfully");
    }


}
