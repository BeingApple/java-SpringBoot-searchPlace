package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.repository.MemberRepository;
import com.beingapple.webservice.domain.MemberRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean isExistMember(String userId) {
        Member member = getMemberByUserId(userId);
        return Optional.ofNullable(member).isPresent();
    }

    @Override
    public Member getMemberByUserId(String userId) {
        return memberRepository.findFirstByUserId(userId);
    }

    @Override
    public void saveMember(Member member){
        memberRepository.save(member);
    }

    @Override
    public Member makeSaveMemberData(MemberRequestDTO dto) {
        String encoded = passwordEncode(dto.getUserPassword());
        dto.setUserPassword(encoded);

        return dto.toEntity();
    }

    public String passwordEncode(String password) {
        String encoded = passwordEncoder.encode(password);
        return encoded;
    }

    @Override
    public Optional<Member> getAuthenticationMember() {
        Optional<Member> memberOptional = Optional.empty();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null) {
            String userId = authentication.getPrincipal().toString();
            Member authenticationMemberData = memberRepository.findFirstByUserId(userId);
            memberOptional = memberOptional.ofNullable(authenticationMemberData);
        }

        return memberOptional;
    }
}
