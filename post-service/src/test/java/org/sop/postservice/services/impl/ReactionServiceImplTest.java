package org.sop.postservice.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sop.postservice.models.Reaction;
import org.sop.postservice.repositories.ReactionRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReactionServiceImplTest {
    @Mock
    private ReactionRepository reactionRepository;

    @InjectMocks
    private ReactionServiceImpl reactionServiceImpl;

    @Test
    void findByPostId() {
        Long postId = 1L;

        List<Reaction> reactions = new ArrayList<>();
        reactions.add(new Reaction());
        reactions.add(new Reaction());

        when(reactionRepository.findByPostId(postId)).thenReturn(reactions);

        List<Reaction> result = reactionServiceImpl.findByPostId(postId);

        assertEquals(reactions, result);
        verify(reactionRepository, times(1)).findByPostId(postId);
    }

    @Test
    void deleteByUserId() {
        Long userId = 1L;

        reactionServiceImpl.deleteByUserId(userId);

        verify(reactionRepository, times(1)).deleteByUserId(userId);
    }

    @Test
    void deleteByPostId() {
        Long postId = 1L;

        reactionServiceImpl.deleteByPostId(postId);

        verify(reactionRepository, times(1)).deleteByPostId(postId);
    }

    @Test
    void reactReactionExists() {
        Reaction reaction = new Reaction();
        reaction.setUserId(1L);
        reaction.setPostId(1L);

        Reaction foundReaction = new Reaction();
        foundReaction.setId(1L);

        when(reactionRepository.findByUserIdAndPostId(reaction.getUserId(), reaction.getPostId())).thenReturn(foundReaction);

        Reaction result = reactionServiceImpl.react(reaction);

        assertNull(result);
        verify(reactionRepository, times(1)).findByUserIdAndPostId(reaction.getUserId(), reaction.getPostId());
        verify(reactionRepository, times(1)).deleteById(foundReaction.getId());
        verify(reactionRepository, never()).save(reaction);
    }

    @Test
    void reactReactionDoesNotExist() {
        Reaction reaction = new Reaction();
        reaction.setUserId(1L);
        reaction.setPostId(1L);

        when(reactionRepository.findByUserIdAndPostId(reaction.getUserId(), reaction.getPostId())).thenReturn(null);

        Reaction savedReaction = new Reaction();
        savedReaction.setId(1L);
        savedReaction.setUserId(reaction.getUserId());
        savedReaction.setPostId(reaction.getPostId());

        when(reactionRepository.save(reaction)).thenReturn(savedReaction);

        Reaction result = reactionServiceImpl.react(reaction);

        assertEquals(savedReaction, result);
        verify(reactionRepository, times(1)).findByUserIdAndPostId(reaction.getUserId(), reaction.getPostId());
        verify(reactionRepository, times(1)).save(reaction);
        verify(reactionRepository, never()).deleteById(anyLong());
    }
}
