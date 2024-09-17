package org.sop.userservice.services.impl;

import org.sop.userservice.feignclients.ApiGatewayUserClient;
import org.sop.userservice.models.FriendRequest;
import org.sop.userservice.models.User;
import org.sop.userservice.repositories.FriendRequestRepository;
import org.sop.userservice.services.facade.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
    @Autowired
    private FriendRequestRepository friendRequestRepository;
    @Autowired
    private ApiGatewayUserClient apiGatewayUserClient;

    public List<FriendRequest> findBySenderId(Long senderId) {
        return friendRequestRepository.findBySenderIdOrderBySentAtDesc(senderId);
    }

    public List<FriendRequest> findByReceiverId(Long receiverId) {
        return friendRequestRepository.findByReceiverIdOrderBySentAtDesc(receiverId);
    }

    @Transactional
    public void deleteUserFriendRequests(Long id) {
        friendRequestRepository.deleteBySenderId(id);
        friendRequestRepository.deleteByReceiverId(id);
    }

    public FriendRequest sendRequest(Long senderId, Long receiverId) {
        User user = apiGatewayUserClient.findById(receiverId);
        if (user == null) return null;
        FriendRequest friendRequest = new FriendRequest(senderId, receiverId);
        friendRequest.setSentAt(LocalDate.now());
        return friendRequestRepository.save(friendRequest);
    }

    @Transactional
    public User acceptRequest(Long senderId, Long receiverId) {
        FriendRequest friendRequest = friendRequestRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        User user = apiGatewayUserClient.findById(senderId);
        if (friendRequest == null || user == null) return null;
        friendRequestRepository.deleteBySenderIdAndReceiverId(senderId, receiverId);
        apiGatewayUserClient.addFriend(senderId, receiverId);
        return user;
    }

    @Transactional
    public void rejectRequest(Long senderId, Long receiverId) {
        friendRequestRepository.deleteBySenderIdAndReceiverId(senderId, receiverId);
    }
}
