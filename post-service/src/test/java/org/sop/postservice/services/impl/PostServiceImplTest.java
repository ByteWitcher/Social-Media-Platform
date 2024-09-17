package org.sop.postservice.services.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sop.postservice.models.Comment;
import org.sop.postservice.models.Post;
import org.sop.postservice.models.Reaction;
import org.sop.postservice.models.ViewedPost;
import org.sop.postservice.repositories.PostRepository;
import org.sop.postservice.services.facade.CommentService;
import org.sop.postservice.services.facade.ReactionService;
import org.sop.postservice.services.facade.ViewedPostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;
    @Mock
    private ReactionService reactionService;
    @Mock
    private CommentService commentService;
    @Mock
    private ViewedPostService viewedPostService;

    @InjectMocks
    private PostServiceImpl postServiceImpl;

    @Test
    void findByUserId() {
        Long userId = 1L;

        List<Post> posts = new ArrayList<>();
        posts.add(new Post());
        posts.add(new Post());

        when(postRepository.findByUserIdOrderByCreatedAtDesc(userId)).thenReturn(posts);

        List<Post> result = postServiceImpl.findByUserId(userId);

        assertEquals(posts, result);
        verify(postRepository, times(1)).findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Test
    void findUnviewedPostsByUserId() {
        Long userId = 1L;
        int page = 0;
        int size = 10;

        List<Post> unviewedPosts = new ArrayList<>();
        unviewedPosts.add(new Post());
        unviewedPosts.add(new Post());

        Page<Post> unviewedPostPage = mock(Page.class);
        when(unviewedPostPage.getContent()).thenReturn(unviewedPosts);

        PageRequest pageable = PageRequest.of(page, size);

        when(postRepository.findUnviewedPostsByUserId(userId, pageable)).thenReturn(unviewedPostPage);

        List<Post> result = postServiceImpl.findUnviewedPostsByUserId(userId, page, size);

        assertEquals(unviewedPosts, result);
        verify(postRepository, times(1)).findUnviewedPostsByUserId(userId, pageable);
    }

    @Test
    void deleteByIdPostExists() {
        Long postId = 1L;

        List<Reaction> reactions = new ArrayList<>();
        reactions.add(new Reaction());
        reactions.add(new Reaction());

        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());

        List<ViewedPost> viewedPosts = new ArrayList<>();
        viewedPosts.add(new ViewedPost());
        viewedPosts.add(new ViewedPost());

        when(postRepository.existsById(postId)).thenReturn(true);

        doNothing().when(reactionService).deleteByPostId(postId);
        doNothing().when(commentService).deleteByPostId(postId);
        doNothing().when(viewedPostService).deleteByPostId(postId);

        postServiceImpl.deleteById(postId);

        verify(postRepository, times(1)).existsById(postId);
        verify(reactionService, times(1)).deleteByPostId(postId);
        verify(commentService, times(1)).deleteByPostId(postId);
        verify(viewedPostService, times(1)).deleteByPostId(postId);
        verify(postRepository, times(1)).deleteById(postId);
    }

    @Test
    void deleteByIdPostDoesNotExist() {
        Long postId = 1L;

        List<Reaction> reactions = new ArrayList<>();
        reactions.add(new Reaction());
        reactions.add(new Reaction());

        List<Comment> comments = new ArrayList<>();
        comments.add(new Comment());
        comments.add(new Comment());

        List<ViewedPost> viewedPosts = new ArrayList<>();
        viewedPosts.add(new ViewedPost());
        viewedPosts.add(new ViewedPost());

        when(postRepository.existsById(postId)).thenReturn(false);

        doNothing().when(reactionService).deleteByPostId(postId);
        doNothing().when(commentService).deleteByPostId(postId);
        doNothing().when(viewedPostService).deleteByPostId(postId);

        postServiceImpl.deleteById(postId);

        verify(postRepository, times(1)).existsById(postId);
        verify(reactionService, times(1)).deleteByPostId(postId);
        verify(commentService, times(1)).deleteByPostId(postId);
        verify(viewedPostService, times(1)).deleteByPostId(postId);
        verify(postRepository, never()).deleteById(postId);
    }

    @Test
    void deleteByUserId() {
        Long userId = 1L;

        List<Post> posts = new ArrayList<>();
        Post post1 = new Post();
        post1.setId(1L);
        Post post2 = new Post();
        post2.setId(2L);
        posts.add(post1);
        posts.add(post2);

        when(postRepository.findByUserIdOrderByCreatedAtDesc(userId)).thenReturn(posts);

        doNothing().when(reactionService).deleteByPostId(anyLong());
        doNothing().when(commentService).deleteByPostId(anyLong());
        doNothing().when(viewedPostService).deleteByPostId(anyLong());
        when(postRepository.existsById(anyLong())).thenReturn(true);

        postServiceImpl.deleteByUserId(userId);

        for (Post post : posts) {
            verify(reactionService, times(1)).deleteByPostId(post.getId());
            verify(commentService, times(1)).deleteByPostId(post.getId());
            verify(viewedPostService, times(1)).deleteByPostId(post.getId());
            verify(postRepository, times(1)).deleteById(post.getId());
        }
    }

    @Test
    void savePostExists() {
        Post post = new Post();
        post.setText("Test post");
        post.setImage("test.jpg");
        post.setCreatedAt(LocalDate.now());
        post.setUserId(1L);

        Post foundPost = new Post();
        foundPost.setId(1L);

        when(postRepository.findByUserIdAndCreatedAt(post.getUserId(), post.getCreatedAt())).thenReturn(foundPost);

        Post result = postServiceImpl.save(post);

        assertNull(result);
        verify(postRepository, times(1)).findByUserIdAndCreatedAt(post.getUserId(), post.getCreatedAt());
        verify(postRepository, never()).save(post);
    }


    @Test
    void savePostDoesNotExist() {
        Post post = new Post();
        post.setText("Test post");
        post.setImage("test.jpg");
        post.setCreatedAt(LocalDate.now());
        post.setUserId(1L);

        when(postRepository.findByUserIdAndCreatedAt(post.getUserId(), post.getCreatedAt())).thenReturn(null);

        Post savedPost = new Post();
        savedPost.setId(1L);
        savedPost.setText(post.getText());
        savedPost.setImage(post.getImage());
        savedPost.setCreatedAt(post.getCreatedAt());
        savedPost.setUserId(post.getUserId());

        when(postRepository.save(post)).thenReturn(savedPost);

        Post result = postServiceImpl.save(post);

        assertEquals(savedPost, result);
        verify(postRepository, times(1)).findByUserIdAndCreatedAt(post.getUserId(), post.getCreatedAt());
        verify(postRepository, times(1)).save(post);
    }


    @Test
    void update() {
        Post post = new Post();
        post.setId(1L);
        post.setText("Updated post");
        post.setImage("updated.jpg");
        post.setCreatedAt(LocalDate.now());
        post.setUserId(1L);

        when(postRepository.save(post)).thenReturn(post);

        Post result = postServiceImpl.update(post);

        assertEquals(post, result);
        verify(postRepository, times(1)).save(post);
    }
}
