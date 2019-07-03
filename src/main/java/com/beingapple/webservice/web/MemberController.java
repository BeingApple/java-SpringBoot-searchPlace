package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.MemberRequestDTO;
import com.beingapple.webservice.domain.Response;
import com.beingapple.webservice.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MemberController {
    private MemberService memberService;

    @PostMapping("/api/join")
    public ResponseEntity<Response> saveMember(@RequestBody MemberRequestDTO dto){
        String userName = dto.getUserName();
        String userId = dto.getUserId();
        String userPassword = dto.getUserPassword();
        String check = dto.getUserPasswordCheck();

        //공백 검사
        if( (userName == null || "".equals(userName)) || (userId == null || "".equals(userId)) || (userPassword == null || "".equals(userPassword)) ){
            Response response = new Response(
                    HttpStatus.BAD_REQUEST.toString(),
                    "",
                    "모두 입력이 필요합니다.",
                    "FORM");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //비밀번호 확인 검사
        if(!userPassword.equals(check)){
            Response response = new Response(
                    HttpStatus.BAD_REQUEST.toString(),
                    "",
                    "비밀번호 확인이 불일치 합니다 ",
                    "PASSWORD");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if(!memberService.isExistMember(dto)) {
            memberService.saveMember(dto);

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }else{
            Response response = new Response(
                    HttpStatus.CONFLICT.toString(),
                    "",
                    "중복된 유저 아이디입니다.",
                    "DUP");

            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
