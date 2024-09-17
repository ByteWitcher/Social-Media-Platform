package org.sop.postservice.services.impl;

import org.sop.postservice.models.Comment;
import org.sop.postservice.repositories.CommentRepository;
import org.sop.postservice.services.facade.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtDesc(postId);
    }

    public void deleteById(Long id) {
        if (commentRepository.existsById(id)) commentRepository.deleteById(id);
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        commentRepository.deleteByUserId(userId);
    }

    @Transactional
    public void deleteByPostId(Long postId) {
        commentRepository.deleteByPostId(postId);
    }

    public Comment save(Comment comment) {
        Comment foundComment = commentRepository.findByUserIdAndPostIdAndCreatedAt(comment.getUserId(), comment.getPostId(), comment.getCreatedAt());
        if (foundComment != null) return null;
        comment.setCreatedAt(LocalDate.now());
        return commentRepository.save(comment);
    }

    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }
}

