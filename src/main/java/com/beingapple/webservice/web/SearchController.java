package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.Search;
import com.beingapple.webservice.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class SearchController {
    private SearchService searchService;

    @GetMapping("/place")
    public ResponseEntity<Search> searchPlace(@RequestParam("keyword") String keyword,
                                              @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size){
        Search search = searchService.findByKeyword(keyword, page, size);

        return new ResponseEntity<>(search, HttpStatus.OK);
    }
}
