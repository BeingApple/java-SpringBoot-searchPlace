package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Search;

public interface SearchService {
    Search findByKeyword(String keyword, Integer page, Integer size);
}
