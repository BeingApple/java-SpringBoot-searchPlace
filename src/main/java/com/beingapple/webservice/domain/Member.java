package com.beingapple.webservice.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(length=  100, nullable = false)
    private String userId;

    @Column(length=  256, nullable = false)
    private String userPassword;

    @Column(length = 16, nullable = false)
    private String salt;

    @Builder
    public Member(String userId, String userPassword, String salt){
        this.userId = userId;
        this.userPassword = userPassword;
        this.salt = salt;
    }
}
