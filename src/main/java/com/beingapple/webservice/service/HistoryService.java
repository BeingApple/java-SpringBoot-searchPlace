package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.History;
import com.beingapple.webservice.domain.HistoryRequestDTO;
import org.springframework.data.domain.Page;

public interface HistoryService extends PageService{
    void saveSearchHistory(History history);
    History makeSearchHistoryEntity(Long memberId, String keyword);
    Page<History> getSearchHistory(Long memberId);
}
