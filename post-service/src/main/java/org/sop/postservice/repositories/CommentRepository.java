package org.sop.postservice.repositories;

import org.sop.postservice.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findByUserIdAndPostIdAndCreatedAt(Long userId, Long postId, LocalDate createdAt);

    List<Comment> findByPostIdOrderByCreatedAtDesc(Long postId);

    int deleteByUserId(Long userId);

    int deleteByPostId(Long postId);

}
