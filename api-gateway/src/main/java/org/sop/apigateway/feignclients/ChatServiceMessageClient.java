package org.sop.apigateway.feignclients;

import org.sop.apigateway.models.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CHAT-SERVICE", path = "/api/message")
public interface ChatServiceMessageClient {
    @GetMapping("/{userId1}/{userId2}")
    List<Message> findChatMessages(@PathVariable Long userId1, @PathVariable Long userId2);

    @DeleteMapping("/message/{id}")
    void deleteById(@PathVariable Long id);
}
