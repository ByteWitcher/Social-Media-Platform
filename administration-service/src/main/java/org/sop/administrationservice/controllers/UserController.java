package org.sop.administrationservice.controllers;

import org.sop.administrationservice.services.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/ban/{id}")
    public void banUser(@PathVariable Long id) {
        userService.banUser(id);
    }

    @GetMapping("/unban/{id}")
    public void unbanUser(@PathVariable Long id) {
        userService.unbanUser(id);
    }
}

