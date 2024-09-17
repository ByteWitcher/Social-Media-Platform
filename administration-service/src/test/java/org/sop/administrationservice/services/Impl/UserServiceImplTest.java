package org.sop.administrationservice.services.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sop.administrationservice.feignclients.ApiGatewayUserClient;
import org.sop.administrationservice.models.User;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private ApiGatewayUserClient apiGatewayUserClient;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void banUserUserExists() {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setEnabled(true);
        when(apiGatewayUserClient.findById(userId)).thenReturn(user);

        userServiceImpl.banUser(userId);

        verify(apiGatewayUserClient, times(1)).update(user);
    }

    @Test
    void banUserUserDoesNotExist() {
        Long userId = 1L;

        when(apiGatewayUserClient.findById(userId)).thenReturn(null);

        userServiceImpl.banUser(userId);

        verify(apiGatewayUserClient, never()).update(any());
    }

    @Test
    void unbanUserUserExists() {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);
        user.setEnabled(false);
        when(apiGatewayUserClient.findById(userId)).thenReturn(user);

        userServiceImpl.unbanUser(userId);

        verify(apiGatewayUserClient, times(1)).update(user);
    }

    @Test
    void unbanUserUserDoesNotExist() {
        Long userId = 1L;

        when(apiGatewayUserClient.findById(userId)).thenReturn(null);

        userServiceImpl.unbanUser(userId);

        verify(apiGatewayUserClient, never()).update(any());
    }
}
