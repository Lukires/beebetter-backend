package com.lukire.activityapp.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user")
    public boolean registerUser(@RequestParam(value="name") String name, @RequestParam(value="password") String password) {
        return User.registerUser(name, password);
    }

    @GetMapping("/user")
    public String user(@RequestParam(value="name") String name) {
        return name;
    }
}
