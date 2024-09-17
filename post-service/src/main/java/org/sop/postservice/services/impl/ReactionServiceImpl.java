package org.sop.postservice.services.impl;

import org.sop.postservice.models.Reaction;
import org.sop.postservice.repositories.ReactionRepository;
import org.sop.postservice.services.facade.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {
    @Autowired
    private ReactionRepository reactionRepository;

    public List<Reaction> findByPostId(Long postId) {
        return reactionRepository.findByPostId(postId);
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        reactionRepository.deleteByUserId(userId);
    }

    @Transactional
    public void deleteByPostId(Long postId) {
        reactionRepository.deleteByPostId(postId);
    }

    @Transactional
    public Reaction react(Reaction reaction) {
        Reaction foundReaction = reactionRepository.findByUserIdAndPostId(reaction.getUserId(), reaction.getPostId());
        if (foundReaction != null) {
            reactionRepository.deleteById(foundReaction.getId());
            return null;
        }
        return reactionRepository.save(reaction);
    }
}
