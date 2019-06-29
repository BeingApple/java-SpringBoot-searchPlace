package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.domain.MemberRequestDTO;

import java.security.NoSuchAlgorithmException;

public interface MemberService {
    void saveMember(MemberRequestDTO dto) throws NoSuchAlgorithmException;
    Member login(MemberRequestDTO dto) throws NoSuchAlgorithmException;
}
