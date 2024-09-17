package org.sop.apigateway.services.facade;

import org.sop.apigateway.security.models.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    List<User> findFriends(Long id);

    void deleteById(Long id);

    User update(User user);

    boolean addFriend(Long id1, Long id2);

    boolean removeFriend(Long id1, Long id2);
}
