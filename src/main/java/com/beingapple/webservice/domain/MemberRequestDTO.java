package com.beingapple.webservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDTO {
    private String userId;
    private String userPassword;
    private String role;

    public Member toSaveEntity(){
        return Member.builder()
                .userId(userId)
                .userPassword(userPassword)
                .role("USER")
                .build();
    }
}
