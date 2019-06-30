package com.beingapple.webservice.auth;

import com.beingapple.webservice.domain.Response;
import com.beingapple.webservice.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Component
@Slf4j
public class BaseSecurityHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDetails userDetails = new UserDetailsImpl(authentication.getPrincipal().toString(), new ArrayList<>(authentication.getAuthorities()));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String accessToken = JwtUtil.createToken(userDetails);
        Response responseData = new Response(
                HttpStatus.OK.toString(),
                accessToken,
                "", ""
        );

        PrintWriter out = response.getWriter();
        out.print(new ObjectMapper().writeValueAsString(responseData));
        out.flush();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
