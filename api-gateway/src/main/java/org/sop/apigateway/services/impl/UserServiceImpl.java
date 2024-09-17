package org.sop.apigateway.services.impl;

import org.sop.apigateway.security.models.User;
import org.sop.apigateway.security.repositories.UserRepository;
import org.sop.apigateway.services.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findFriends(Long id) {
        User user = findById(id);
        if (user == null || user.getFriends() == null || user.getFriends().isEmpty()) return new ArrayList<>();
        return user.getFriends();
    }

    @Transactional
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) userRepository.deleteById(id);
    }

    public User update(User user) {
        User foundUser = findById(user.getId());
        if ((!foundUser.getUsername().equals(user.getUsername()) && userRepository.existsByUsername(user.getUsername())) || (!foundUser.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())))
            return null;
        applyChanges(user, foundUser);
        return userRepository.save(foundUser);
    }

    public boolean addFriend(Long id1, Long id2) {
        User user1 = findById(id1);
        User user2 = findById(id2);
        if (user1 == null || user2 == null) return false;
        if (user1.getFriends() == null) user1.setFriends(new ArrayList<>());
        if (user2.getFriends() == null) user2.setFriends(new ArrayList<>());
        user1.getFriends().add(user2);
        user2.getFriends().add(user1);
        userRepository.save(user1);
        userRepository.save(user2);
        return true;
    }

    public boolean removeFriend(Long id1, Long id2) {
        User user1 = findById(id1);
        User user2 = findById(id2);
        if (user1 == null || user2 == null) return false;
        user1.getFriends().remove(user2);
        user2.getFriends().remove(user1);
        userRepository.save(user1);
        userRepository.save(user2);
        return true;
    }

    private void applyChanges(User user, User foundUser) {
        foundUser.setUsername(user.getUsername());
        foundUser.setEmail(user.getEmail());
        foundUser.setFirstname(user.getFirstname());
        foundUser.setLastname(user.getLastname());
        foundUser.setBirthdate(user.getBirthdate());
        foundUser.setPhoneNumber(user.getPhoneNumber());
        foundUser.setBio(user.getBio());
        foundUser.setImage(user.getImage());
        foundUser.setEnabled(user.isEnabled());
    }

}
