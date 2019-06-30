package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.MemberRequestDTO;
import com.beingapple.webservice.domain.Response;
import com.beingapple.webservice.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/member/join")
    public ResponseEntity<Response> saveMember(@RequestBody MemberRequestDTO dto){
        dto.setUserPassword(passwordEncoder.encode(dto.getUserPassword()));
        memberService.saveMember(dto);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
