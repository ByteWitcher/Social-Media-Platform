package org.sop.administrationservice.services.Impl;

import org.sop.administrationservice.feignclients.ApiGatewayUserClient;
import org.sop.administrationservice.models.User;
import org.sop.administrationservice.services.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ApiGatewayUserClient apiGatewayUserClient;

    public void banUser(Long id) {
        User user = apiGatewayUserClient.findById(id);
        if (user == null) return;
        user.setEnabled(false);
        apiGatewayUserClient.update(user);
    }

    public void unbanUser(Long id) {
        User user = apiGatewayUserClient.findById(id);
        if (user == null) return;
        user.setEnabled(true);
        apiGatewayUserClient.update(user);
    }
}
