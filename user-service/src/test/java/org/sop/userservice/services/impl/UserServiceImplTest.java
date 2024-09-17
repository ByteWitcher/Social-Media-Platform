package org.sop.userservice.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sop.userservice.feignclients.*;
import org.sop.userservice.services.facade.FriendRequestService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private FriendRequestService friendRequestService;
    @Mock
    private ApiGatewayUserClient apiGatewayUserClient;
    @Mock
    private PostServicePostClient postServicePostClient;
    @Mock
    private PostServiceCommentClient postServiceCommentClient;
    @Mock
    private PostServiceReactionClient postServiceReactionClient;
    @Mock
    private PostServiceViewedPostClient postServiceViewedPostClient;
    @Mock
    private ChatServiceMessageClient chatServiceMessageClient;

    @InjectMocks
    private UserServiceImpl userServiceImpl;


    @Test
    void deleteUser() {
        Long userId = 1L;

        userServiceImpl.deleteUser(userId);

        verify(friendRequestService, times(1)).deleteUserFriendRequests(userId);
        verify(postServicePostClient, times(1)).deleteByUserId(userId);
        verify(postServiceCommentClient, times(1)).deleteByUserId(userId);
        verify(postServiceReactionClient, times(1)).deleteByUserId(userId);
        verify(postServiceViewedPostClient, times(1)).deleteByUserId(userId);
        verify(chatServiceMessageClient, times(1)).deleteUserChats(userId);
        verify(apiGatewayUserClient, times(1)).deleteById(userId);
    }
}
