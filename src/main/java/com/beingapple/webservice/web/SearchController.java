package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.Place;
import com.beingapple.webservice.service.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SearchController {
    PlaceService placeService;

    @GetMapping("/place")
    public ResponseEntity<Place> searchPlace(@RequestParam("keyword") String keyword,
                                             @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size){
        Place place = placeService.findByKeyword(keyword, page, size);

        return new ResponseEntity<>(place, HttpStatus.OK);
    }
}
