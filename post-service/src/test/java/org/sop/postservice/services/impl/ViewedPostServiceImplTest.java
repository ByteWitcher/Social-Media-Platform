package org.sop.postservice.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sop.postservice.models.ViewedPost;
import org.sop.postservice.repositories.ViewedPostRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViewedPostServiceImplTest {
    @Mock
    private ViewedPostRepository viewedPostRepository;

    @InjectMocks
    private ViewedPostServiceImpl viewedPostServiceImpl;

    @Test
    void findByUserId() {
        Long userId = 1L;

        List<ViewedPost> viewedPosts = new ArrayList<>();
        viewedPosts.add(new ViewedPost());
        viewedPosts.add(new ViewedPost());

        when(viewedPostRepository.findByUserIdOrderBySeenAtDesc(userId)).thenReturn(viewedPosts);

        List<ViewedPost> result = viewedPostServiceImpl.findByUserId(userId);

        assertEquals(viewedPosts, result);
        verify(viewedPostRepository, times(1)).findByUserIdOrderBySeenAtDesc(userId);
    }

    @Test
    void deleteByUserId() {
        Long userId = 1L;

        viewedPostServiceImpl.deleteByUserId(userId);

        verify(viewedPostRepository, times(1)).deleteByUserId(userId);
    }

    @Test
    void deleteByPostId() {
        Long postId = 1L;

        viewedPostServiceImpl.deleteByPostId(postId);

        verify(viewedPostRepository, times(1)).deleteByPostId(postId);
    }

    @Test
    void saveViewedPostExists() {
        ViewedPost viewedPost = new ViewedPost();
        viewedPost.setUserId(1L);
        viewedPost.setPostId(1L);

        ViewedPost foundViewedPost = new ViewedPost();
        foundViewedPost.setId(1L);

        when(viewedPostRepository.findByUserIdAndPostId(viewedPost.getUserId(), viewedPost.getPostId())).thenReturn(foundViewedPost);

        ViewedPost result = viewedPostServiceImpl.save(viewedPost);

        assertNull(result);
        verify(viewedPostRepository, times(1)).findByUserIdAndPostId(viewedPost.getUserId(), viewedPost.getPostId());
        verify(viewedPostRepository, never()).save(viewedPost);
    }

    @Test
    void saveViewedPostDoesNotExist() {
        ViewedPost viewedPost = new ViewedPost();
        viewedPost.setUserId(1L);
        viewedPost.setPostId(1L);

        when(viewedPostRepository.findByUserIdAndPostId(viewedPost.getUserId(), viewedPost.getPostId())).thenReturn(null);

        ViewedPost savedViewedPost = new ViewedPost();
        savedViewedPost.setId(1L);
        savedViewedPost.setUserId(viewedPost.getUserId());
        savedViewedPost.setPostId(viewedPost.getPostId());

        when(viewedPostRepository.save(viewedPost)).thenReturn(savedViewedPost);

        ViewedPost result = viewedPostServiceImpl.save(viewedPost);

        assertEquals(savedViewedPost, result);
        verify(viewedPostRepository, times(1)).findByUserIdAndPostId(viewedPost.getUserId(), viewedPost.getPostId());
        verify(viewedPostRepository, times(1)).save(viewedPost);
    }


}
