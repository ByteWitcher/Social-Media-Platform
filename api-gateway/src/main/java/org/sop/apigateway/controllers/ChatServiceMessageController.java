package org.sop.apigateway.controllers;

import org.sop.apigateway.feignclients.ChatServiceMessageClient;
import org.sop.apigateway.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat-service/api/message")
public class ChatServiceMessageController {
    @Autowired
    private ChatServiceMessageClient chatServiceMessageClient;

    @GetMapping("/{userId1}/{userId2}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Message> findChatMessages(@PathVariable Long userId1, @PathVariable Long userId2) {
        return chatServiceMessageClient.findChatMessages(userId1, userId2);
    }

    @DeleteMapping("/message/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        chatServiceMessageClient.deleteById(id);
    }
}
