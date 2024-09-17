package org.sop.chatservice.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sop.chatservice.models.Message;
import org.sop.chatservice.repositories.MessageRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {
    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private MessageServiceImpl messageServiceImpl;

    @Test
    void findChatMessages() {
        Long userId1 = 1L;
        Long userId2 = 2L;

        Message message1 = new Message();
        message1.setId(1L);
        message1.setSenderId(userId1);
        message1.setReceiverId(userId2);
        message1.setSentAt(LocalDate.of(2021, 1, 1));
        message1.setMessage("Hello");

        Message message2 = new Message();
        message2.setId(2L);
        message2.setSenderId(userId2);
        message2.setReceiverId(userId1);
        message2.setSentAt(LocalDate.of(2021, 1, 2));
        message2.setMessage("Hi");

        Message message3 = new Message();
        message3.setId(3L);
        message3.setSenderId(userId1);
        message3.setReceiverId(userId2);
        message3.setSentAt(LocalDate.of(2021, 1, 3));
        message3.setMessage("How are you?");

        when(messageRepository.findBySenderIdAndReceiverId(userId1, userId2)).thenReturn(Arrays.asList(message1, message3));
        when(messageRepository.findBySenderIdAndReceiverId(userId2, userId1)).thenReturn(Arrays.asList(message2));

        List<Message> expectedMessages = Arrays.asList(message1, message2, message3);

        List<Message> actualMessages = messageServiceImpl.findChatMessages(userId1, userId2);

        assertEquals(expectedMessages, actualMessages);
    }

    @Test
    void deleteByIdMessageExists() {
        Long messageId = 1L;

        when(messageRepository.existsById(messageId)).thenReturn(true);

        messageServiceImpl.deleteById(messageId);

        verify(messageRepository, times(1)).existsById(messageId);
        verify(messageRepository, times(1)).deleteById(messageId);
    }

    @Test
    void deleteByIdMessageDoesNotExist() {
        Long messageId = 1L;

        when(messageRepository.existsById(messageId)).thenReturn(false);

        messageServiceImpl.deleteById(messageId);

        verify(messageRepository, times(1)).existsById(messageId);
        verify(messageRepository, never()).deleteById(messageId);
    }

    @Test
    void deleteUserChats() {
        Long userId = 1L;

        messageServiceImpl.deleteUserChats(userId);

        verify(messageRepository, times(1)).deleteBySenderId(userId);
        verify(messageRepository, times(1)).deleteByReceiverId(userId);
    }

    @Test
    void saveExistingMessage() {
        Long senderId = 1L;
        Long receiverId = 2L;
        LocalDate sentAt = LocalDate.of(2021, 1, 1);
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setSentAt(sentAt);
        message.setMessage("Hello");

        Message foundMessage = new Message();
        foundMessage.setId(1L);

        when(messageRepository.findBySenderIdAndReceiverIdAndSentAt(senderId, receiverId, sentAt)).thenReturn(foundMessage);

        Message result = messageServiceImpl.save(message);

        assertNull(result);
        verify(messageRepository, times(1)).findBySenderIdAndReceiverIdAndSentAt(senderId, receiverId, sentAt);
        verify(messageRepository, never()).save(any(Message.class));
    }

    @Test
    void saveNewMessage() {
        Long senderId = 1L;
        Long receiverId = 2L;
        LocalDate sentAt = LocalDate.of(2021, 1, 1);
        Message message = new Message();
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        message.setSentAt(sentAt);
        message.setMessage("Hello");

        Message savedMessage = new Message();
        savedMessage.setId(1L);
        savedMessage.setSenderId(senderId);
        savedMessage.setReceiverId(receiverId);
        savedMessage.setSentAt(sentAt);
        savedMessage.setMessage("Hello");

        when(messageRepository.findBySenderIdAndReceiverIdAndSentAt(senderId, receiverId, sentAt)).thenReturn(null);
        when(messageRepository.save(message)).thenReturn(savedMessage);

        Message result = messageServiceImpl.save(message);

        assertNotNull(result);
        assertEquals(savedMessage, result);
        verify(messageRepository, times(1)).findBySenderIdAndReceiverIdAndSentAt(senderId, receiverId, sentAt);
        verify(messageRepository, times(1)).save(message);
    }
}
