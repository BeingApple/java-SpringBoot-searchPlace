package com.beingapple.webservice.domain;

import com.beingapple.webservice.util.SHA256Util;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@NoArgsConstructor
public class MemberSaveRequestDTO{
    private String userId;
    private String userPassword;
    private String salt;

    private String encryptPassword() throws NoSuchAlgorithmException {
        this.salt = SHA256Util.generateSalt();

        return SHA256Util.getEncrypted(userPassword, salt);
    }

    public Member toEntity() throws NoSuchAlgorithmException{
        return Member.builder()
                .userId(userId)
                .userPassword(encryptPassword())
                .salt(salt)
                .build();
    }
}
