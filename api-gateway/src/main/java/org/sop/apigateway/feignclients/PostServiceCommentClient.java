package org.sop.apigateway.feignclients;

import org.sop.apigateway.models.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "POST-SERVICE", path = "/api/comment", contextId = "comment")
public interface PostServiceCommentClient {
    @GetMapping("/{postId}")
    List<Comment> findByPostId(@PathVariable Long postId);

    @DeleteMapping("/id/{id}")
    void deleteById(@PathVariable Long id);

    @PostMapping("/")
    Comment save(@RequestBody Comment comment);

    @PutMapping("/")
    Comment update(@RequestBody Comment comment);
}
