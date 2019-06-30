package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Place;

public interface PlaceService {
    Place findByKeyword(String keyword, Integer page, Integer size);
}
