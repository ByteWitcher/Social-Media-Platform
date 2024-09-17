package org.sop.apigateway.controllers;

import org.sop.apigateway.feignclients.PostServiceCommentClient;
import org.sop.apigateway.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-service/api/comment")
public class PostServiceCommentController {
    @Autowired
    private PostServiceCommentClient postServiceCommentClient;

    @GetMapping("/{postId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Comment> findByPostId(@PathVariable Long postId) {
        return postServiceCommentClient.findByPostId(postId);
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        postServiceCommentClient.deleteById(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Comment save(@RequestBody Comment comment) {
        return postServiceCommentClient.save(comment);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Comment update(@RequestBody Comment comment) {
        return postServiceCommentClient.update(comment);
    }
}
