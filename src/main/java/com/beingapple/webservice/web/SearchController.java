package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.domain.Response;
import com.beingapple.webservice.domain.Search;
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
public class SearchController {
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
        Member member = memberService.authenticationMember();

        if(member != null) {
            Search search = searchService.findByKeyword(keyword, page, size);

            historyService.saveHistory(member.getId(), keyword);
            popularService.savePopularKeyword(keyword);

            return new ResponseEntity<>(search, HttpStatus.OK);
        }else{
            Response response = new Response(
                    HttpStatus.UNAUTHORIZED.toString(),
                    "",
                    "MEMBER", "토큰값에 해당하는 멤버가 존재하지 않습니다."
            );
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization header", required = true,
                    dataType = "string", paramType = "header", defaultValue = "key")
    })
    @GetMapping("/api/search/popular")
    public ResponseEntity<List> getPopular(){
        return new ResponseEntity<>(popularService.getPopular(), HttpStatus.OK);
    }
}
