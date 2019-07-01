package com.beingapple.webservice.repository;

import com.beingapple.webservice.domain.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<History, Long> {
    Page<History> findByMemberIdOrderByCreatedDateDesc(Long memberId, Pageable pageable);
}
