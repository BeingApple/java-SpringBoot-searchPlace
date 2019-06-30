package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.History;
import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.domain.Response;
import com.beingapple.webservice.service.HistoryService;
import com.beingapple.webservice.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
public class HistoryController {
    private MemberService memberService;
    HistoryService historyService;

    @GetMapping("/history")
    public ResponseEntity<?> getHistory(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", required = false, defaultValue = "15") Integer size){
        Member member = memberService.authenticationMember();

        if(member != null) {
            Page<History> historyList = historyService.getHistory(member.getId(), page, size);

            return new ResponseEntity<>(historyList, HttpStatus.OK);
        }else{
            Response response = new Response(
                    HttpStatus.UNAUTHORIZED.toString(),
                    "",
                    "MEMBER", "토큰값에 해당하는 멤버가 존재하지 않습니다."
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
