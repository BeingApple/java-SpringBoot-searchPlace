package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.MemberRepository;
import com.beingapple.webservice.domain.MemberSaveRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
public class WebRestController {

    private MemberRepository memberRepository;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/join")
    public void saveMember(@RequestBody MemberSaveRequestDTO dto){
        try {
            memberRepository.save(dto.toEntity());
        }catch (NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
    }
}
