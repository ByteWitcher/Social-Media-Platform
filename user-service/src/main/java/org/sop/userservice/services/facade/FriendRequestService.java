package org.sop.userservice.services.facade;

import org.sop.userservice.models.FriendRequest;
import org.sop.userservice.models.User;

import java.util.List;

public interface FriendRequestService {

    List<FriendRequest> findBySenderId(Long senderId);

    List<FriendRequest> findByReceiverId(Long receiverId);

    void deleteUserFriendRequests(Long id);

    FriendRequest sendRequest(Long senderId, Long receiverId);

    User acceptRequest(Long senderId, Long receiverId);

    void rejectRequest(Long senderId, Long receiverId);
}
