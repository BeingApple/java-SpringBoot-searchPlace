package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.MemberRequestDTO;
import com.beingapple.webservice.domain.Response;
import com.beingapple.webservice.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    @PostMapping("/api/join")
    public ResponseEntity<Response> saveMember(@RequestBody MemberRequestDTO dto){
        if(!memberService.isExistMember(dto)) {
            memberService.saveMember(dto);

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }else{
            Response response = new Response(
                    HttpStatus.CONFLICT.toString(),
                    "",
                    "EMAIL",
                    "중복된 유저 아이디입니다.");

            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
