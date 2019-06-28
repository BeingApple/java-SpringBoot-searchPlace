package com.beingapple.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanup(){
        memberRepository.deleteAll();
    }

    @Test
    public void memberSaveAndGet(){
        //given
        memberRepository.save(Member.builder()
                .userId("beingapple")
                .userPassword("beingapple123")
                .build());

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);
        assertThat(member.getUserId(), is("beingapple"));
        assertThat(member.getUserPassword(), is("beingapple123"));
    }
}
