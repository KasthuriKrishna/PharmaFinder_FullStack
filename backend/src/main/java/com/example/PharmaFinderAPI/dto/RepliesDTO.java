package com.example.PharmaFinderAPI.dto;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepliesDTO {
    private Long id;
    private UserDTO user;
    private PharmacyDTO pharmacy;
    private String replyMessage;
    private LocalDateTime replyDate;
}
