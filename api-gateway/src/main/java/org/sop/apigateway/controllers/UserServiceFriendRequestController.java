package org.sop.apigateway.controllers;

import org.sop.apigateway.dtos.FriendDto;
import org.sop.apigateway.feignclients.UserServiceFriendRequestClient;
import org.sop.apigateway.models.FriendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service/api/friend-request")
public class UserServiceFriendRequestController {
    @Autowired
    private UserServiceFriendRequestClient userServiceFriendRequestClient;

    @GetMapping("/sent-friend-requests/{senderId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<FriendRequest> findBySenderId(@PathVariable Long senderId) {
        return userServiceFriendRequestClient.findBySenderId(senderId);
    }


    @GetMapping("/received-friend-requests/{receiverId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<FriendRequest> findByReceiverId(@PathVariable Long receiverId) {
        return userServiceFriendRequestClient.findByReceiverId(receiverId);
    }

    @PostMapping("/{senderId}/{receiverId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public FriendRequest sendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return userServiceFriendRequestClient.sendRequest(senderId, receiverId);
    }

    @DeleteMapping("/accept/{senderId}/{receiverId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public FriendDto acceptRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return userServiceFriendRequestClient.acceptRequest(senderId, receiverId);
    }

    @DeleteMapping("/reject/{senderId}/{receiverId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void rejectRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        userServiceFriendRequestClient.rejectRequest(senderId, receiverId);
    }
}
