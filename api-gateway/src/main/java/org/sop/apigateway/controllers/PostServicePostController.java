package org.sop.apigateway.controllers;

import org.sop.apigateway.feignclients.PostServicePostClient;
import org.sop.apigateway.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-service/api/post")
public class PostServicePostController {
    @Autowired
    private PostServicePostClient postServicePostClient;

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Post> findByUserId(@PathVariable Long userId) {
        return postServicePostClient.findByUserId(userId);
    }

    @GetMapping("/{userId}/{page}/{size}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Post> findUnviewedPostsByUserId(@PathVariable Long userId, @PathVariable int page, @PathVariable int size) {
        return postServicePostClient.findUnviewedPostsByUserId(userId, page, size);
    }

    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        postServicePostClient.deleteById(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Post save(@RequestBody Post post) {
        return postServicePostClient.save(post);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Post update(@RequestBody Post post) {
        return postServicePostClient.update(post);
    }
}
