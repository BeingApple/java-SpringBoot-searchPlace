package com.beingapple.webservice.repository;

import com.beingapple.webservice.domain.Popular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PopularRepository extends JpaRepository<Popular, Long> {
    Popular findFirstByKeyword(String keyword);
    List<Popular> findFirst10ByOrderByCountDesc();
}
