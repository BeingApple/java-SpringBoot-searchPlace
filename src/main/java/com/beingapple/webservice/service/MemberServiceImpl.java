package com.beingapple.webservice.service;

import com.beingapple.webservice.repository.MemberRepository;
import com.beingapple.webservice.domain.MemberRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    @Override
    public void saveMember(MemberRequestDTO dto){
        memberRepository.save(dto.toSaveEntity());
    }
}
