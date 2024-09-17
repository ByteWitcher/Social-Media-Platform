package org.sop.postservice.controllers;

import org.modelmapper.ModelMapper;
import org.sop.postservice.dtos.CommentDto;
import org.sop.postservice.models.Comment;
import org.sop.postservice.services.facade.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{postId}")
    public List<CommentDto> findByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.findByPostId(postId);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
    }

    @DeleteMapping("/user-id/{userId}")
    public void deleteByUserId(@PathVariable Long userId) {
        commentService.deleteById(userId);
    }

    @PostMapping("/")
    public CommentDto save(@RequestBody CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment = commentService.save(comment);
        if (comment == null) return null;
        return modelMapper.map(comment, CommentDto.class);
    }

    @PutMapping("/")
    public CommentDto update(@RequestBody CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return modelMapper.map(commentService.update(comment), CommentDto.class);
    }
}
