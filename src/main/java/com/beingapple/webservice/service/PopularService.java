package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Popular;
import com.beingapple.webservice.domain.Search;

import java.util.List;

public interface PopularService {
    void savePopularKeyword(String keyword);
    List<Popular> getPopular();
}
