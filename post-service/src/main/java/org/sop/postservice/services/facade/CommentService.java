package org.sop.postservice.services.facade;

import org.sop.postservice.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findByPostId(Long postId);

    void deleteById(Long id);

    void deleteByUserId(Long userId);

    void deleteByPostId(Long postId);

    Comment save(Comment comment);

    Comment update(Comment comment);
}
