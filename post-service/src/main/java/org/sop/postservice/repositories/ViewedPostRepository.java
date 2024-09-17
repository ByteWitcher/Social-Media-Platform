package org.sop.postservice.repositories;

import org.sop.postservice.models.ViewedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewedPostRepository extends JpaRepository<ViewedPost, Long> {
    ViewedPost findByUserIdAndPostId(Long userId, Long postId);

    List<ViewedPost> findByUserIdOrderBySeenAtDesc(Long userId);

    int deleteByUserId(Long userId);

    int deleteByPostId(Long postId);

}
