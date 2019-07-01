package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.History;
import com.beingapple.webservice.domain.HistoryRequestDTO;
import com.beingapple.webservice.repository.HistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService{
    private HistoryRepository historyRepository;

    @Async
    @Override
    public void saveHistory(Long memberId, String keyword) {
        HistoryRequestDTO historyRequestDTO = new HistoryRequestDTO();
        historyRequestDTO.setMemberId(memberId);
        historyRequestDTO.setKeyword(keyword);

        historyRepository.save(historyRequestDTO.toEntity());
    }

    @Override
    public Page<History> getHistory(Long memberId, Integer page, Integer size) {
        if(page <= 0) page = 1;
        if(size <= 0) size = 15;
        return historyRepository.findByMemberIdOrderByCreatedDateDesc(memberId, PageRequest.of(page - 1, size));
    }
}
