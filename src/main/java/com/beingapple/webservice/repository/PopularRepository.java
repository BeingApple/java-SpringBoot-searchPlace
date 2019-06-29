package com.beingapple.webservice.repository;

import com.beingapple.webservice.domain.Popular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopularRepository extends JpaRepository<Popular, Long> {
}
