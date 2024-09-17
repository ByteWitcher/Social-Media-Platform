package org.sop.userservice.services.impl;

import org.sop.userservice.feignclients.*;
import org.sop.userservice.services.facade.FriendRequestService;
import org.sop.userservice.services.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private FriendRequestService friendRequestService;
    @Autowired
    private ApiGatewayUserClient apiGatewayUserClient;
    @Autowired
    private PostServicePostClient postServicePostClient;
    @Autowired
    private PostServiceCommentClient postServiceCommentClient;
    @Autowired
    private PostServiceReactionClient postServiceReactionClient;
    @Autowired
    private PostServiceViewedPostClient postServiceViewedPostClient;
    @Autowired
    private ChatServiceMessageClient chatServiceMessageClient;

    public void deleteUser(Long id) {
        friendRequestService.deleteUserFriendRequests(id);
        postServicePostClient.deleteByUserId(id);
        postServiceCommentClient.deleteByUserId(id);
        postServiceReactionClient.deleteByUserId(id);
        postServiceViewedPostClient.deleteByUserId(id);
        chatServiceMessageClient.deleteUserChats(id);
        apiGatewayUserClient.deleteById(id);
    }
}
