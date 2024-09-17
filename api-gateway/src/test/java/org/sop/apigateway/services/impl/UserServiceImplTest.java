package org.sop.apigateway.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sop.apigateway.security.models.User;
import org.sop.apigateway.security.repositories.UserRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void findById() {
        Long userId = 1L;
        User expectedUser = new User();
        expectedUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        User actualUser = userServiceImpl.findById(userId);

        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void findByIdUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User actualUser = userServiceImpl.findById(userId);

        assertNull(actualUser);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void findFriendsExistingUser() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        User friend1 = new User();
        friend1.setId(2L);

        User friend2 = new User();
        friend2.setId(3L);

        user.setFriends(Arrays.asList(friend1, friend2));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        List<User> friends = userServiceImpl.findFriends(userId);

        assertNotNull(friends);
        assertEquals(2, friends.size());
        assertTrue(friends.contains(friend1));
        assertTrue(friends.contains(friend2));
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void findFriendsUserNotFound() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        List<User> friends = userServiceImpl.findFriends(userId);

        assertNotNull(friends);
        assertTrue(friends.isEmpty());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void findFriendsUserHasNoFriends() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setFriends(Collections.emptyList());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        List<User> friends = userServiceImpl.findFriends(userId);

        assertNotNull(friends);
        assertTrue(friends.isEmpty());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void deleteByIdUserExists() {
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(true);

        userServiceImpl.deleteById(userId);

        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void deleteByIdUserDoesNotExist() {
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(false);

        userServiceImpl.deleteById(userId);

        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, never()).deleteById(userId);
    }

    @Test
    void update() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("oldUsername");
        existingUser.setEmail("oldEmail@example.com");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("newUsername");
        updatedUser.setEmail("newEmail@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.existsByUsername("newUsername")).thenReturn(false);
        when(userRepository.existsByEmail("newEmail@example.com")).thenReturn(false);
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userServiceImpl.update(updatedUser);

        assertNotNull(result);
        assertEquals("newUsername", result.getUsername());
        assertEquals("newEmail@example.com", result.getEmail());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).existsByUsername("newUsername");
        verify(userRepository, times(1)).existsByEmail("newEmail@example.com");
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void updateUsernameExists() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("oldUsername");
        existingUser.setEmail("oldEmail@example.com");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("newUsername");
        updatedUser.setEmail("newEmail@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.existsByUsername("newUsername")).thenReturn(true);

        User result = userServiceImpl.update(updatedUser);

        assertNull(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).existsByUsername("newUsername");
        verify(userRepository, never()).existsByEmail("newEmail@example.com");
        verify(userRepository, never()).save(existingUser);
    }

    @Test
    void updateEmailExists() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("oldUsername");
        existingUser.setEmail("oldEmail@example.com");

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("newUsername");
        updatedUser.setEmail("newEmail@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.existsByUsername("newUsername")).thenReturn(false);
        when(userRepository.existsByEmail("newEmail@example.com")).thenReturn(true);

        User result = userServiceImpl.update(updatedUser);

        assertNull(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).existsByUsername("newUsername");
        verify(userRepository, times(1)).existsByEmail("newEmail@example.com");
        verify(userRepository, never()).save(existingUser);
    }


    @Test
    void addFriendUsersExist() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFriends(new ArrayList<>());

        User user2 = new User();
        user2.setId(2L);
        user2.setFriends(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.save(any(User.class))).thenReturn(user1).thenReturn(user2);

        boolean result = userServiceImpl.addFriend(1L, 2L);

        assertTrue(result);
        assertTrue(user1.getFriends().contains(user2));
        assertTrue(user2.getFriends().contains(user1));
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(userRepository, times(1)).save(user1);
        verify(userRepository, times(1)).save(user2);
    }

    @Test
    void addFriendUser1NotFound() {
        User user2 = new User();
        user2.setId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        boolean result = userServiceImpl.addFriend(1L, 2L);

        assertFalse(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void addFriendUser2NotFound() {
        User user1 = new User();
        user1.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        boolean result = userServiceImpl.addFriend(1L, 2L);

        assertFalse(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void removeFriendUsersExist() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        user1.setFriends(new ArrayList<>(Collections.singletonList(user2)));
        user2.setFriends(new ArrayList<>(Collections.singletonList(user1)));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));
        when(userRepository.save(any(User.class))).thenReturn(user1).thenReturn(user2);

        boolean result = userServiceImpl.removeFriend(1L, 2L);

        assertTrue(result);
        assertFalse(user1.getFriends().contains(user2));
        assertFalse(user2.getFriends().contains(user1));
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(userRepository, times(1)).save(user1);
        verify(userRepository, times(1)).save(user2);
    }

    @Test
    void removeFriendUser1NotFound() {
        User user2 = new User();
        user2.setId(2L);
        user2.setFriends(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        boolean result = userServiceImpl.removeFriend(1L, 2L);

        assertFalse(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void removeFriendUser2NotFound() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFriends(new ArrayList<>());

        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        boolean result = userServiceImpl.removeFriend(1L, 2L);

        assertFalse(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).findById(2L);
        verify(userRepository, never()).save(any(User.class));
    }
}
