package com.reason.controller;

import com.reason.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Reason.H.Duan
 * @Date: 7/10/2020
 */

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    // GET http://localhost:8080/user
    @GetMapping("/user")
    @ResponseBody
    public String runServerJob(){
        userService.userMigration();
        return "success";
    }
}
