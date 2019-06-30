package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.History;
import org.springframework.data.domain.Page;

public interface HistoryService {
    void saveHistory(Long memberId, String keyword);
    Page<History> getHistory(Long memberId, Integer page, Integer size);
}
