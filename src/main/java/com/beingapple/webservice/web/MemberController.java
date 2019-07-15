package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.domain.MemberRequestDTO;
import com.beingapple.webservice.domain.Response;
import com.beingapple.webservice.service.MemberService;
import com.beingapple.webservice.util.MemberValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class MemberController extends CommonController{
    private MemberService memberService;

    @PostMapping("/api/join")
    public ResponseEntity<?> saveMember(@RequestBody MemberRequestDTO dto){
        MemberValidation validation = new MemberValidation(dto);

        if( validation.hasEmptySpace() ){
            return returnResponseObjectWithHttpStatus(HttpStatus.BAD_REQUEST, "", "모두 입력이 필요합니다.", "FORM");
        }

        if( validation.isPasswordSameWithCheck() ){
            return returnResponseObjectWithHttpStatus(HttpStatus.BAD_REQUEST, "", "비밀번호 확인이 불일치 합니다 ", "PASSWORD");
        }

        String userId = Optional.ofNullable(dto).map(MemberRequestDTO::getUserId).orElse("");
        if(!memberService.isExistMember(userId)) {
            Member saveData = memberService.makeSaveMemberData(dto);
            memberService.saveMember(saveData);

            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }else{
            return returnResponseObjectWithHttpStatus(HttpStatus.CONFLICT, "", "중복된 유저 아이디입니다.", "DUP");
        }
    }
}
