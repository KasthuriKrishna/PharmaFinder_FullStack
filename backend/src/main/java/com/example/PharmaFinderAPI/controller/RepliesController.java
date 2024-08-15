package com.example.PharmaFinderAPI.controller;

import com.example.PharmaFinderAPI.dto.RepliesDTO;
import com.example.PharmaFinderAPI.entity.Replies;
import com.example.PharmaFinderAPI.service.RepliesService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/replies")
public class RepliesController {

    @Autowired
    private RepliesService repliesService;
    
    //add new replies by pharmacies
    @PostMapping
    public ResponseEntity<Replies> createReply(@RequestBody RepliesDTO repliesDTO) {
        Replies createdReply = repliesService.createReply(repliesDTO);
        return new ResponseEntity<>(createdReply, HttpStatus.CREATED);
    }
 
    //to view specified reply
    @GetMapping("/{id}")
    public ResponseEntity<Replies> getReplyById(@PathVariable Long id) {
        Replies reply = repliesService.getReplyById(id);
        return ResponseEntity.ok(reply);
    }
    //To fetch the replies to a particular user
    @GetMapping("/user/{userId}")
    public List<RepliesDTO> getRepliesByUserId(@PathVariable Long userId) {
        return repliesService.getRepliesByUserId(userId);
    }
}
