package org.sop.postservice.services.facade;

import org.sop.postservice.models.Reaction;

import java.util.List;

public interface ReactionService {
    List<Reaction> findByPostId(Long postId);


    void deleteByUserId(Long userId);


    void deleteByPostId(Long postId);


    Reaction react(Reaction reaction);
}
