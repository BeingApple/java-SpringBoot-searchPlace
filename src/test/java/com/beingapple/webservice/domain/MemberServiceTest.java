package com.beingapple.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.beingapple.webservice.repository.MemberRepository;
import com.beingapple.webservice.service.MemberService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @After
    public void cleanup(){
        memberRepository.deleteAll();
    }

    @Test
    public void memberJoinAndLogin() throws NoSuchAlgorithmException{
        //given
        MemberRequestDTO memberDTO = new MemberRequestDTO();
        memberDTO.setUserId("beingapple");
        memberDTO.setUserPassword("beingapple123");
        memberService.saveMember(memberDTO);

        //when
        Member member = memberService.login(memberDTO);

        //then
        assertThat(member.getUserId(), is("beingapple"));
    }
}
