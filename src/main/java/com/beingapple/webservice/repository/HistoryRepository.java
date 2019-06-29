package com.beingapple.webservice.repository;

import com.beingapple.webservice.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
