package com.beingapple.webservice.domain;

import com.beingapple.webservice.util.SHA256Util;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDTO {

    private String userId;
    private String userPassword;
    private String salt;

    private String encryptPassword(String salt) throws NoSuchAlgorithmException {
        return SHA256Util.getEncrypted(userPassword, salt);
    }

    public Member toSaveEntity() throws NoSuchAlgorithmException{
        salt = SHA256Util.generateSalt();

        return Member.builder()
                .userId(userId)
                .userPassword(encryptPassword(salt))
                .salt(salt)
                .build();
    }
}
