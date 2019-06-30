package com.beingapple.webservice.repository;

import com.beingapple.webservice.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findFirstByUserId(String userId);
}
