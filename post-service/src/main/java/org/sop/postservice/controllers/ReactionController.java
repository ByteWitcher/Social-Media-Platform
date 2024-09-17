package org.sop.postservice.controllers;

import org.modelmapper.ModelMapper;
import org.sop.postservice.dtos.ReactionDto;
import org.sop.postservice.models.Reaction;
import org.sop.postservice.services.facade.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/reaction")
public class ReactionController {
    @Autowired
    private ReactionService reactionService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{postId}")
    public List<ReactionDto> findByPostId(@PathVariable Long postId) {
        List<Reaction> reactions = reactionService.findByPostId(postId);
        List<ReactionDto> reactionDtos = new ArrayList<>();
        for (Reaction reaction : reactions) {
            ReactionDto reactionDto = modelMapper.map(reaction, ReactionDto.class);
            reactionDtos.add(reactionDto);
        }
        return reactionDtos;
    }

    @DeleteMapping("/{userId}")
    public void deleteByUserId(@PathVariable Long userId) {
        reactionService.deleteByUserId(userId);
    }

    @PostMapping("/")
    public ReactionDto react(@RequestBody ReactionDto reactionDto) {
        Reaction reaction = modelMapper.map(reactionDto, Reaction.class);
        reaction = reactionService.react(reaction);
        if (reaction == null) return null;
        return modelMapper.map(reaction, ReactionDto.class);
    }
}
