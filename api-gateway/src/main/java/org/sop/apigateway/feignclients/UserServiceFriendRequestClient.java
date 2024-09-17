package org.sop.apigateway.feignclients;

import org.sop.apigateway.dtos.FriendDto;
import org.sop.apigateway.models.FriendRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "USER-SERVICE", path = "/api/friend-request", contextId = "friend-request")
public interface UserServiceFriendRequestClient {
    @GetMapping("/sent-friend-requests/{senderId}")
    List<FriendRequest> findBySenderId(@PathVariable Long senderId);


    @GetMapping("/received-friend-requests/{receiverId}")
    List<FriendRequest> findByReceiverId(@PathVariable Long receiverId);

    @PostMapping("/{senderId}/{receiverId}")
    FriendRequest sendRequest(@PathVariable Long senderId, @PathVariable Long receiverId);

    @DeleteMapping("/accept/{senderId}/{receiverId}")
    FriendDto acceptRequest(@PathVariable Long senderId, @PathVariable Long receiverId);

    @DeleteMapping("/reject/{senderId}/{receiverId}")
    void rejectRequest(@PathVariable Long senderId, @PathVariable Long receiverId);
}
