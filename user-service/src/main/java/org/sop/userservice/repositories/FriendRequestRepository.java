package org.sop.userservice.repositories;

import org.sop.userservice.models.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    FriendRequest findBySenderIdAndReceiverId(Long senderId, Long receiverId);

    List<FriendRequest> findBySenderIdOrderBySentAtDesc(Long senderId);

    List<FriendRequest> findByReceiverIdOrderBySentAtDesc(Long receiverId);

    int deleteBySenderIdAndReceiverId(Long senderId, Long receiverId);

    int deleteBySenderId(Long senderId);

    int deleteByReceiverId(Long receiverId);

}
