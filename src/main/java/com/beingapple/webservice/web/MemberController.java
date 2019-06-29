package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.domain.MemberRequestDTO;
import com.beingapple.webservice.domain.Response;
import com.beingapple.webservice.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    @PostMapping("/member/join")
    public ResponseEntity<Response> saveMember(@RequestBody MemberRequestDTO dto){
        try {
            memberService.saveMember(dto);

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }catch (NoSuchAlgorithmException exception){
            return new ResponseEntity<>(
                    new Response("Fail", "", "No Such Algorithm", ""),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/member/login")
    public ResponseEntity<Response> loginMember(@RequestBody MemberRequestDTO dto){
        Member member;

        try {
            member = memberService.login(dto);
        }catch (NoSuchAlgorithmException exception){
            return new ResponseEntity<>(
                    new Response("Fail", "", "Internal Server Error", ""),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(member != null) {
            //토큰 생성하고 응답 보내기
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(
                    new Response("Fail", "로그인에 실패했습니다.", "정확한 아이디, 패스워드를 입력하세요", ""),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
