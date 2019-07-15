package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.*;
import com.beingapple.webservice.service.HistoryService;
import com.beingapple.webservice.service.MemberService;
import com.beingapple.webservice.service.PopularService;
import com.beingapple.webservice.service.SearchService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SearchController extends CommonController{
    private SearchService searchService;
    private MemberService memberService;
    private HistoryService historyService;
    private PopularService popularService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization header", required = true,
                    dataType = "string", paramType = "header", defaultValue = "key")
    })
    @GetMapping("/api/search/place")
    public ResponseEntity<?> searchPlace(@RequestParam("keyword") String keyword,
                                              @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size){
        Long memberId = memberService.getAuthenticationMember()
                .map(Member::getId)
                .orElse(0L);

        if(memberId > 0) {
            Search search = searchService.findByKeyword(keyword, page, size);
            History saveData = historyService.makeSearchHistoryEntity(memberId, keyword);

            //비동기 처리
            historyService.saveSearchHistory(saveData);
            popularService.savePopularKeyword(keyword);

            return new ResponseEntity<>(search, HttpStatus.OK);
        }else{
            return returnResponseObjectWithHttpStatus(HttpStatus.UNAUTHORIZED, "", "MEMBER", "토큰값에 해당하는 멤버가 존재하지 않습니다.");
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization header", required = true,
                    dataType = "string", paramType = "header", defaultValue = "key")
    })
    @GetMapping("/api/search/popular")
    public ResponseEntity<?> getPopular(){
        List<Popular> popularList = popularService.getPopularList();
        return new ResponseEntity<>(popularList, HttpStatus.OK);
    }
}
