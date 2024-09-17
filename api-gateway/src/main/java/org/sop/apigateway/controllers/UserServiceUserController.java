package org.sop.apigateway.controllers;

import org.sop.apigateway.feignclients.UserServiceUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service/api/user")
public class UserServiceUserController {
    @Autowired
    private UserServiceUserClient userServiceUserClient;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userServiceUserClient.deleteUser(id);
    }
}
