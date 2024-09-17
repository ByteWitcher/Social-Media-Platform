package org.sop.postservice.repositories;

import org.sop.postservice.models.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Reaction findByUserIdAndPostId(Long userId, Long postId);

    List<Reaction> findByPostId(Long postId);

    int deleteByUserId(Long userId);

    int deleteByPostId(Long postId);
}
