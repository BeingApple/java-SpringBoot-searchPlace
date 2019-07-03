package com.beingapple.webservice.web;

import com.beingapple.webservice.domain.MemberRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class SearchAndHistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private String token = "";

    private void join(String userName, String userId, String userPassword) throws Exception{
        MemberRequestDTO dto = new MemberRequestDTO();
        dto.setUserName(userName);
        dto.setUserId(userId);
        dto.setUserPassword(userPassword);
        dto.setUserPasswordCheck(userPassword);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(dto);

        mockMvc.perform(post("/api/join")
                    .content(requestJson)
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    private String login(String userId, String userPassword) throws Exception {
        MemberRequestDTO dto = new MemberRequestDTO();
        dto.setUserId(userId);
        dto.setUserPassword(userPassword);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        String requestJson = ow.writeValueAsString(dto);

        ResultActions result = mockMvc.perform(post("/api/login")
                    .content(requestJson)
                    .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("message").toString();
    }

    @Before
    public void setUp() throws Exception{
        String userId = "beingapple";
        String userPassword = "beingapple123";

        join("조용비", userId, userPassword);
        token = login(userId, userPassword);
    }

    @After
    public void tearDown() throws Exception{
        SecurityContextHolder.clearContext();
    }

    @Test
    public void 검색하기() throws Exception{
        mockMvc.perform(get("/api/search/place")
                    .header("Authorization", token)
                    .param("keyword", "사가정역 맛집"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.documents").isArray());
    }

    @Test
    public void 히스토리_가져오기() throws Exception{
        mockMvc.perform(get("/api/history")
                    .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 인기목록_가져오기() throws Exception{
        mockMvc.perform(get("/api/search/popular")
                    .header("Authorization", token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$").isArray());
    }
}
