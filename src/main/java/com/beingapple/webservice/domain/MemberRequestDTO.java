package com.beingapple.webservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDTO {
    private String userName;
    private String userId;
    private String userPassword;
    private String userPasswordCheck;
    private String role;

    public Member toEntity(){
        return Member.builder()
                .userName(userName)
                .userId(userId)
                .userPassword(userPassword)
                .role("USER")
                .build();
    }
}
