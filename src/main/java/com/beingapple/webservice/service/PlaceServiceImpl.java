package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Place;
import com.beingapple.webservice.repository.HistoryRepository;
import com.beingapple.webservice.repository.PopularRepository;
import lombok.extern.slf4j.Slf4j;
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

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class PlaceServiceImpl implements PlaceService{
    private final RestTemplate restTemplate;

    HistoryRepository historyRepository;
    PopularRepository popularRepository;

    @Value("${kakao.api.url}")
    private String kakaoApiUrl;

    @Value("${kakao.api.key}")
    private String kakaoApiKey;

    public PlaceServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Place findByKeyword(String keyword) {
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(kakaoApiUrl+"/v2/local/search/keyword.json")
                .queryParam("query", keyword)
                .build(false);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", kakaoApiKey);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, Place.class).getBody();
    }
}
