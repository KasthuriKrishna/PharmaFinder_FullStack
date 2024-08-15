package com.example.PharmaFinderAPI.service.impl;
import com.example.PharmaFinderAPI.dto.PharmacyDTO;
import com.example.PharmaFinderAPI.dto.RepliesDTO;
import com.example.PharmaFinderAPI.dto.UserDTO;
import com.example.PharmaFinderAPI.entity.Replies;
import com.example.PharmaFinderAPI.entity.User;
import com.example.PharmaFinderAPI.entity.Pharmacy;
import com.example.PharmaFinderAPI.repository.RepliesRepo;
import com.example.PharmaFinderAPI.repository.UserRepo;
import com.example.PharmaFinderAPI.service.RepliesService;
import com.example.PharmaFinderAPI.repository.PharmacyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RepliesServiceImpl implements RepliesService {

    @Autowired
    private RepliesRepo repliesRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PharmacyRepo pharmacyRepository;

    @Override
    public Replies createReply(RepliesDTO repliesDTO) {
        Optional<User> user = userRepository.findById(repliesDTO.getUser().getId());
        Optional<Pharmacy> pharmacy = pharmacyRepository.findById(repliesDTO.getPharmacy().getId());

        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }

        if (!pharmacy.isPresent()) {
            throw new RuntimeException("Pharmacy not found");
        }

        Replies reply = new Replies();
        reply.setUser(user.get());
        reply.setPharmacy(pharmacy.get());
        reply.setReplyMessage(repliesDTO.getReplyMessage());
        reply.setReplyDate(repliesDTO.getReplyDate());

        return repliesRepository.save(reply);
    }

    @Override
    public Replies getReplyById(Long id) {
        return repliesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reply not found"));
    }
    @Override
    public List<RepliesDTO> getRepliesByUserId(Long userId) {
        List<Replies> replies = repliesRepository.findByUserId(userId);
        return replies.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RepliesDTO convertToDTO(Replies reply) {
        UserDTO userDTO = new UserDTO(reply.getUser().getId(), reply.getUser().getUsername(), reply.getUser().getEmail(),
                reply.getUser().getDob(), reply.getUser().getMobileNumber(), reply.getUser().getAddress(), reply.getUser().getPincode());
        
        PharmacyDTO pharmacyDTO = new PharmacyDTO(reply.getPharmacy().getId(), reply.getPharmacy().getPharmacyName(),
                reply.getPharmacy().getAddress(), reply.getPharmacy().getContact(), reply.getPharmacy().getOperatingHoursFrom(),
                reply.getPharmacy().getOperatingHoursTo(), reply.getPharmacy().getEmail(), reply.getPharmacy().getPincode());

        return new RepliesDTO(reply.getId(), userDTO, pharmacyDTO, reply.getReplyMessage(), reply.getReplyDate());
    }
}
