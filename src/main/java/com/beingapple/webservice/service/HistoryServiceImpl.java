package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.History;
import com.beingapple.webservice.domain.HistoryRequestDTO;
import com.beingapple.webservice.repository.HistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService{
    private HistoryRepository historyRepository;
    private PageRequest pageRequest;

    public HistoryServiceImpl(HistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }

    @Async
    @Override
    public void saveSearchHistory(History history) {
        historyRepository.save(history);
    }

    public History makeSearchHistoryEntity(Long memberId, String keyword) {
        HistoryRequestDTO historyRequestDTO = new HistoryRequestDTO();
        historyRequestDTO.setMemberId(memberId);
        historyRequestDTO.setKeyword(keyword);

        return historyRequestDTO.toEntity();
    }

    @Override
    public Page<History> getSearchHistory(Long memberId) {
        return historyRepository.findByMemberIdOrderByCreatedDateDesc(memberId, this.pageRequest);
    }

    @Override
    public void setPageRequest(Integer page, Integer size) {
        if(page <= 0) page = 1;
        if(size <= 0) size = 15;

        this.pageRequest = PageRequest.of(page - 1, size);
    }
}
