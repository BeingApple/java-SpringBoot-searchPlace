package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.domain.MemberRequestDTO;

import java.util.Optional;


public interface MemberService {
    Member getMemberByUserId(String userId);
    boolean isExistMember(String userId);
    void saveMember(Member member);
    Member makeSaveMemberData(MemberRequestDTO dto);
    Optional<Member> getAuthenticationMember();
}
