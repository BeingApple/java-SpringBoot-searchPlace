package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Search;
import com.beingapple.webservice.repository.HistoryRepository;
import com.beingapple.webservice.repository.PopularRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SearchServiceImpl implements SearchService {
    private final RestTemplate restTemplate;

    private HistoryRepository historyRepository;
    private PopularRepository popularRepository;

    @Value("${kakao.api.url}")
    private String kakaoApiUrl;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public SearchServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Search findByKeyword(String keyword, Integer page, Integer size) {
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(kakaoApiUrl+"/v2/local/search/keyword.json")
                .queryParam("query", keyword)
                .queryParam("page", page)
                .queryParam("size", size)
                .build(false);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", kakaoApiKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, Search.class).getBody();
    }
}
