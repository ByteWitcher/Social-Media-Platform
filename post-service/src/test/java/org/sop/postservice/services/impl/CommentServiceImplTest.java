package org.sop.postservice.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sop.postservice.models.Comment;
import org.sop.postservice.repositories.CommentRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @Test
    void findByPostId() {
        Long postId = 1L;

        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setPostId(postId);
        comment1.setUserId(1L);
        comment1.setCreatedAt(LocalDate.of(2021, 1, 1));
        comment1.setText("First comment");

        Comment comment2 = new Comment();
        comment2.setId(2L);
        comment2.setPostId(postId);
        comment2.setUserId(2L);
        comment2.setCreatedAt(LocalDate.of(2021, 1, 2));
        comment2.setText("Second comment");

        List<Comment> comments = Arrays.asList(comment2, comment1);

        when(commentRepository.findByPostIdOrderByCreatedAtDesc(postId)).thenReturn(comments);

        List<Comment> result = commentServiceImpl.findByPostId(postId);

        assertEquals(comments, result);
        verify(commentRepository, times(1)).findByPostIdOrderByCreatedAtDesc(postId);
    }

    @Test
    void deleteByIdCommentExists() {
        Long commentId = 1L;

        when(commentRepository.existsById(commentId)).thenReturn(true);

        commentServiceImpl.deleteById(commentId);

        verify(commentRepository, times(1)).existsById(commentId);
        verify(commentRepository, times(1)).deleteById(commentId);
    }

    @Test
    void deleteByIdCommentDoesNotExist() {
        Long commentId = 1L;

        when(commentRepository.existsById(commentId)).thenReturn(false);

        commentServiceImpl.deleteById(commentId);

        verify(commentRepository, times(1)).existsById(commentId);
        verify(commentRepository, never()).deleteById(commentId);
    }

    @Test
    void deleteByUserId() {
        Long userId = 1L;

        commentServiceImpl.deleteByUserId(userId);

        verify(commentRepository, times(1)).deleteByUserId(userId);
    }

    @Test
    void deleteByPostId() {
        Long postId = 1L;

        commentServiceImpl.deleteByPostId(postId);

        verify(commentRepository, times(1)).deleteByPostId(postId);
    }

    @Test
    void saveExistingComment() {
        Comment comment = new Comment();
        comment.setUserId(1L);
        comment.setPostId(1L);
        comment.setCreatedAt(LocalDate.now());
        comment.setText("Existing comment");

        Comment foundComment = new Comment();
        foundComment.setId(1L);

        when(commentRepository.findByUserIdAndPostIdAndCreatedAt(comment.getUserId(), comment.getPostId(), comment.getCreatedAt())).thenReturn(foundComment);

        Comment result = commentServiceImpl.save(comment);

        assertNull(result);
        verify(commentRepository, times(1)).findByUserIdAndPostIdAndCreatedAt(comment.getUserId(), comment.getPostId(), comment.getCreatedAt());
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    void saveNewComment() {
        Comment comment = new Comment();
        comment.setUserId(1L);
        comment.setPostId(1L);
        comment.setCreatedAt(LocalDate.now());
        comment.setText("New comment");

        Comment savedComment = new Comment();
        savedComment.setId(1L);
        savedComment.setUserId(comment.getUserId());
        savedComment.setPostId(comment.getPostId());
        savedComment.setCreatedAt(comment.getCreatedAt());
        savedComment.setText(comment.getText());

        when(commentRepository.findByUserIdAndPostIdAndCreatedAt(comment.getUserId(), comment.getPostId(), comment.getCreatedAt())).thenReturn(null);
        when(commentRepository.save(comment)).thenReturn(savedComment);

        Comment result = commentServiceImpl.save(comment);

        assertEquals(savedComment, result);
        verify(commentRepository, times(1)).findByUserIdAndPostIdAndCreatedAt(comment.getUserId(), comment.getPostId(), comment.getCreatedAt());
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    void update() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setUserId(1L);
        comment.setPostId(1L);
        comment.setCreatedAt(LocalDate.now());
        comment.setText("Updated comment");

        when(commentRepository.save(comment)).thenReturn(comment);

        Comment updatedComment = commentServiceImpl.update(comment);

        assertEquals(comment, updatedComment);

        verify(commentRepository, times(1)).save(comment);
    }
}
