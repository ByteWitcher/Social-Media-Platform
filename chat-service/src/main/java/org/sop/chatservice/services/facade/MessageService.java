package org.sop.chatservice.services.facade;

import org.sop.chatservice.models.Message;

import java.util.List;

public interface MessageService {
    List<Message> findChatMessages(Long userId1, Long userId2);

    void deleteById(Long id);

    void deleteUserChats(Long id);

    Message save(Message message);
}
