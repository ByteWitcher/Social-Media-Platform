package org.sop.postservice.services.impl;

import org.sop.postservice.models.Post;
import org.sop.postservice.repositories.PostRepository;
import org.sop.postservice.services.facade.CommentService;
import org.sop.postservice.services.facade.PostService;
import org.sop.postservice.services.facade.ReactionService;
import org.sop.postservice.services.facade.ViewedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReactionService reactionService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ViewedPostService viewedPostService;

    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public List<Post> findUnviewedPostsByUserId(Long userId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Post> postPage = postRepository.findUnviewedPostsByUserId(userId, pageable);
        return postPage.getContent();
    }

    @Transactional
    public void deleteById(Long id) {
        commentService.deleteByPostId(id);
        reactionService.deleteByPostId(id);
        viewedPostService.deleteByPostId(id);
        if (postRepository.existsById(id)) postRepository.deleteById(id);
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        List<Post> posts = findByUserId(userId);
        for (Post post : posts) {
            deleteById(post.getId());
        }
    }

    public Post save(Post post) {
        Post foundPost = postRepository.findByUserIdAndCreatedAt(post.getUserId(), post.getCreatedAt());
        if (foundPost != null) return null;
        post.setCreatedAt(LocalDate.now());
        return postRepository.save(post);
    }

    public Post update(Post post) {
        return postRepository.save(post);
    }

}
