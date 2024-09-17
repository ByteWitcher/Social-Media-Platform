package org.sop.postservice.services.facade;

import org.sop.postservice.models.ViewedPost;

import java.util.List;

public interface ViewedPostService {
    List<ViewedPost> findByUserId(Long userId);


    void deleteByUserId(Long userId);


    void deleteByPostId(Long postId);

    ViewedPost save(ViewedPost viewedPost);
}
