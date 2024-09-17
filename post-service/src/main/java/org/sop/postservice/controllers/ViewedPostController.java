package org.sop.postservice.controllers;

import org.modelmapper.ModelMapper;
import org.sop.postservice.dtos.ViewedPostDto;
import org.sop.postservice.models.ViewedPost;
import org.sop.postservice.services.facade.ViewedPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/viewed-post")
public class ViewedPostController {
    @Autowired
    private ViewedPostService viewedPostService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{userId}")
    public List<ViewedPostDto> findByUserId(@PathVariable Long userId) {
        List<ViewedPost> viewedPosts = viewedPostService.findByUserId(userId);
        List<ViewedPostDto> viewedPostDtos = new ArrayList<>();
        for (ViewedPost viewedPost : viewedPosts) {
            ViewedPostDto viewedPostDto = modelMapper.map(viewedPost, ViewedPostDto.class);
            viewedPostDtos.add(viewedPostDto);
        }
        return viewedPostDtos;
    }

    @DeleteMapping("/{userId}")
    public void deleteByUserId(@PathVariable Long userId) {
        viewedPostService.deleteByUserId(userId);
    }

    @PostMapping("/")
    public ViewedPostDto save(@RequestBody ViewedPostDto viewedPostDto) {
        ViewedPost viewedPost = modelMapper.map(viewedPostDto, ViewedPost.class);
        viewedPost = viewedPostService.save(viewedPost);
        if (viewedPostDto == null) return null;
        return modelMapper.map(viewedPost, ViewedPostDto.class);
    }
}
