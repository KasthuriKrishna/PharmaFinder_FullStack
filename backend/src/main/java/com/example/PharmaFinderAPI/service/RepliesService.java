package com.example.PharmaFinderAPI.service;
import java.util.List;

import com.example.PharmaFinderAPI.dto.RepliesDTO;
import com.example.PharmaFinderAPI.entity.Replies;

public interface RepliesService {
    Replies createReply(RepliesDTO repliesDTO);
    Replies getReplyById(Long id);
    List<RepliesDTO> getRepliesByUserId(Long userId);
}
