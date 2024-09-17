package org.sop.postservice.repositories;

import org.sop.postservice.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByUserIdAndCreatedAt(Long userId, LocalDate createdAt);

    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);

    @Query("SELECT p FROM Post p WHERE p.id NOT IN (SELECT vp.postId FROM ViewedPost vp WHERE vp.userId = ?1) ORDER BY p.createdAt DESC")
    Page<Post> findUnviewedPostsByUserId(Long userId, Pageable pageable);

    int deleteByUserId(Long userId);
}
