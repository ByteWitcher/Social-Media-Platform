package org.sop.apigateway.controllers;

import org.modelmapper.ModelMapper;
import org.sop.apigateway.dtos.FriendDto;
import org.sop.apigateway.dtos.UserDto;
import org.sop.apigateway.security.models.User;
import org.sop.apigateway.services.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/id/{id}")
    public UserDto findById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) return null;
        return modelMapper.map(user, UserDto.class);
    }

    @GetMapping("/friends/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<FriendDto> findFriends(@PathVariable Long id) {
        List<User> friends = userService.findFriends(id);
        List<FriendDto> friendDtos = new ArrayList<>();
        for (User friend : friends) {
            FriendDto friendDto = modelMapper.map(friend, FriendDto.class);
            friendDtos.add(friendDto);
        }
        return friendDtos;
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user = userService.update(user);
        if (user == null) return null;
        return modelMapper.map(user, UserDto.class);
    }

    @PutMapping("/update-profile")
    @PreAuthorize("hasRole('USER')")
    public UserDto updateProfile(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user = userService.update(user);
        if (user == null) return null;
        return modelMapper.map(user, UserDto.class);
    }

    @GetMapping("/add-friend/{id1}/{id2}")
    public boolean addFriend(@PathVariable Long id1, @PathVariable Long id2) {
        return userService.addFriend(id1, id2);
    }

    @GetMapping("/remove-friend/{id1}/{id2}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public boolean removeFriend(@PathVariable Long id1, @PathVariable Long id2) {
        return userService.removeFriend(id1, id2);
    }


}
