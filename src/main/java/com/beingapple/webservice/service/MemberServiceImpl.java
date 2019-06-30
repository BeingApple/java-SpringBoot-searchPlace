package com.beingapple.webservice.service;

import com.beingapple.webservice.auth.UserDetailsImpl;
import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.repository.MemberRepository;
import com.beingapple.webservice.domain.MemberRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;


@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    @Override
    public boolean isExistMember(MemberRequestDTO dto) {
        Member member = memberRepository.findFirstByUserId(dto.getUserId());

        if(member != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void saveMember(MemberRequestDTO dto){
        memberRepository.save(dto.toEntity());
    }

    @Override
    public Member authenticationMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            String userId = authentication.getPrincipal().toString();
            return memberRepository.findFirstByUserId(userId);
        }else{
            return null;
        }
    }
}
