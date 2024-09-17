package org.sop.postservice.services.impl;

import org.sop.postservice.models.ViewedPost;
import org.sop.postservice.repositories.ViewedPostRepository;
import org.sop.postservice.services.facade.ViewedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ViewedPostServiceImpl implements ViewedPostService {
    @Autowired
    private ViewedPostRepository viewedPostRepository;

    public List<ViewedPost> findByUserId(Long userId) {
        return viewedPostRepository.findByUserIdOrderBySeenAtDesc(userId);
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        viewedPostRepository.deleteByUserId(userId);
    }

    @Transactional
    public void deleteByPostId(Long postId) {
        viewedPostRepository.deleteByPostId(postId);
    }

    public ViewedPost save(ViewedPost viewedPost) {
        ViewedPost foundViewedPost = viewedPostRepository.findByUserIdAndPostId(viewedPost.getUserId(), viewedPost.getPostId());
        if (foundViewedPost != null) return null;
        viewedPost.setSeenAt(LocalDate.now());
        return viewedPostRepository.save(viewedPost);
    }
}
