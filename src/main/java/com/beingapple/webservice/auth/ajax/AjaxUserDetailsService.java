package com.beingapple.webservice.auth.ajax;

import com.beingapple.webservice.auth.UserDetailsImpl;
import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AjaxUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findFirstByUserId(username);

        if (member == null) {
            throw new UsernameNotFoundException(username + "라는 사용자가 없습니다.");
        }
        return new UserDetailsImpl(member, AuthorityUtils.createAuthorityList(member.getRole()));
    }
}
