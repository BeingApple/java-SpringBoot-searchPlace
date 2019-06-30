package com.beingapple.webservice.auth;

import com.beingapple.webservice.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserDetailsImpl extends User {
    public UserDetailsImpl(String userId, List<GrantedAuthority> authorities) {
        super(userId, "", authorities);
    }

    public UserDetailsImpl(Member member, List<GrantedAuthority> authorities) {
        super(member.getUserId(), member.getUserPassword(), authorities);
    }
}
