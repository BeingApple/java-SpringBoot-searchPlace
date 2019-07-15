package com.beingapple.webservice.util;

import com.beingapple.webservice.domain.MemberRequestDTO;

public class MemberValidation {
    private String userName;
    private String userId;
    private String userPassword;
    private String check;

    public MemberValidation(MemberRequestDTO dto){
        this.userName = dto.getUserName();
        this.userId = dto.getUserId();
        this.userPassword = dto.getUserPassword();
        this.check = dto.getUserPasswordCheck();
    }

    public boolean hasEmptySpace(){
        return (userName == null || "".equals(userName)) || (userId == null || "".equals(userId)) || (userPassword == null || "".equals(userPassword));
    }

    public boolean isPasswordSameWithCheck(){
        return !userPassword.equals(check);
    }
}
