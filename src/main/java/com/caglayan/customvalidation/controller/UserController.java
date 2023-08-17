package com.caglayan.customvalidation.controller;

import com.caglayan.customvalidation.model.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/save")
    public User test(@Valid @RequestBody User user) {
        return new User(
                user.id(),
                user.email(),
                user.password(),
                user.phoneNumber()
        );
    }
}
