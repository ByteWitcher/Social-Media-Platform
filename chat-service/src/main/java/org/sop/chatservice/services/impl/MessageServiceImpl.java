package org.sop.chatservice.services.impl;

import org.sop.chatservice.models.Message;
import org.sop.chatservice.repositories.MessageRepository;
import org.sop.chatservice.services.facade.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findChatMessages(Long userId1, Long userId2) {
        List<Message> messagesUserId1 = messageRepository.findBySenderIdAndReceiverId(userId1, userId2);
        List<Message> messagesUserId2 = messageRepository.findBySenderIdAndReceiverId(userId2, userId1);
        List<Message> messages = new ArrayList<>();
        messages.addAll(messagesUserId1);
        messages.addAll(messagesUserId2);
        messages.sort(Comparator.comparing(Message::getSentAt));
        return messages;
    }

    @Transactional
    public void deleteById(Long id) {
        if (messageRepository.existsById(id)) messageRepository.deleteById(id);
    }

    @Transactional
    public void deleteUserChats(Long id) {
        messageRepository.deleteBySenderId(id);
        messageRepository.deleteByReceiverId(id);
    }

    public Message save(Message message) {
        Message foundMessage = messageRepository.findBySenderIdAndReceiverIdAndSentAt(message.getSenderId(), message.getReceiverId(), message.getSentAt());
        if (foundMessage != null) return null;
        return messageRepository.save(message);
    }

}
