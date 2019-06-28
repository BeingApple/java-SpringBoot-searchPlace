package com.beingapple.webservice.domain;

public class MemberSaveRequestDTO {
    private String userId;
    private String userPassword;

    private String encryptPassword(){
        return this.userPassword;
    }

    public Member toEntity(){
        return Member.builder()
                .userId(userId)
                .userPassword(encryptPassword());
    }
}
