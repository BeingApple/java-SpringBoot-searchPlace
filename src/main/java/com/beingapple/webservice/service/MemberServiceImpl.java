package com.beingapple.webservice.service;

import com.beingapple.webservice.domain.Member;
import com.beingapple.webservice.repository.MemberRepository;
import com.beingapple.webservice.domain.MemberRequestDTO;
import com.beingapple.webservice.util.SHA256Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;

    @Override
    public void saveMember(MemberRequestDTO dto) throws NoSuchAlgorithmException {
        memberRepository.save(dto.toSaveEntity());
    }

    @Override
    public Member login(MemberRequestDTO dto) throws NoSuchAlgorithmException{
        String userId = dto.getUserId();
        String userPassword = dto.getUserPassword();

        //userId를 통해 salt 값 가져오기
        Member saltMember = memberRepository.findFirstByUserId(userId);

        if(saltMember != null) {
            String salt = saltMember.getSalt();

            //가져온 salt 값과 입력받은 userPassword 값을 이용해 암호화하기
            userPassword = SHA256Util.getEncrypted(userPassword, salt);

            //userId, userPassword 바탕으로 Member 정보 조회하기
            Member loginMember = memberRepository.findFirstByUserIdAndUserPassword(userId, userPassword);

            return loginMember;
        }else{
            return null;
        }
    }
}
