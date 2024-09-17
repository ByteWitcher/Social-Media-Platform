package org.sop.userservice.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CHAT-SERVICE", path = "/api/message")
public interface ChatServiceMessageClient {
    @DeleteMapping("/chats/{id}")
    void deleteUserChats(@PathVariable Long id);
}
