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
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length=  500, nullable = false)
    private String userId;

    @Column(length=  500, nullable = false)
    private String userPassword;

    @Builder
    public Member(String userId, String userPassword){
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
