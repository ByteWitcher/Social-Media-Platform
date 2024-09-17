package org.sop.apigateway.controllers;

import org.sop.apigateway.feignclients.PostServiceViewedPostClient;
import org.sop.apigateway.models.ViewedPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-service/api/viewed-post")
public class PostServiceViewedPostController {
    @Autowired
    private PostServiceViewedPostClient postServiceViewedPostClient;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<ViewedPost> findByUserId(@PathVariable Long userId) {
        return postServiceViewedPostClient.findByUserId(userId);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ViewedPost save(@RequestBody ViewedPost viewedPost) {
        return postServiceViewedPostClient.save(viewedPost);
    }
}
