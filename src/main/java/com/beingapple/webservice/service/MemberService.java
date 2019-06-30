package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.domain.MemberRequestDTO;


public interface MemberService {
    boolean isExistMember(MemberRequestDTO dto);
    void saveMember(MemberRequestDTO dto);
    Member authenticationMember();
}
