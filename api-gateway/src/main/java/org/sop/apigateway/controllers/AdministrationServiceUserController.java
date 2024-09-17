package org.sop.apigateway.controllers;

import org.sop.apigateway.feignclients.AdministrationServiceUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administration-service/api/user")
public class AdministrationServiceUserController {
    @Autowired
    private AdministrationServiceUserClient administrationServiceUserClient;

    @GetMapping("/ban/{id}")
    @PreAuthorize(" hasRole('ADMIN')")
    public void banUser(@PathVariable Long id) {
        administrationServiceUserClient.banUser(id);
    }

    @GetMapping("/unban/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void unbanUser(@PathVariable Long id) {
        administrationServiceUserClient.unbanUser(id);
    }
}
