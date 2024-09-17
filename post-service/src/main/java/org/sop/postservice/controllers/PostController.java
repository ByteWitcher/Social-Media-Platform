package org.sop.postservice.controllers;

import org.modelmapper.ModelMapper;
import org.sop.postservice.dtos.PostDto;
import org.sop.postservice.models.Post;
import org.sop.postservice.services.facade.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{userId}")
    public List<PostDto> findByUserId(@PathVariable Long userId) {
        List<Post> posts = postService.findByUserId(userId);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            PostDto postDto = modelMapper.map(post, PostDto.class);
            postDtos.add(postDto);
        }
        return postDtos;
    }

    @GetMapping("/{userId}/{page}/{size}")
    public List<PostDto> findUnviewedPostsByUserId(@PathVariable Long userId, @PathVariable int page, @PathVariable int size) {
        List<Post> posts = postService.findUnviewedPostsByUserId(userId, page, size);
        List<PostDto> postDtos = new ArrayList<>();
        for (Post post : posts) {
            PostDto postDto = modelMapper.map(post, PostDto.class);
            postDtos.add(postDto);
        }
        return postDtos;
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deleteById(id);
    }

    @DeleteMapping("/user-id/{userId}")
    public void deleteByUserId(@PathVariable Long userId) {
        postService.deleteByUserId(userId);
    }

    @PostMapping("/")
    public PostDto save(@RequestBody PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        post = postService.save(post);
        if (post == null) return null;
        return modelMapper.map(post, PostDto.class);
    }

    @PutMapping("/")
    public PostDto update(@RequestBody PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        return modelMapper.map(postService.update(post), PostDto.class);
    }
}
