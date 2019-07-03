package com.beingapple.webservice.config;

import com.beingapple.webservice.auth.BaseSecurityHandler;
import com.beingapple.webservice.auth.ajax.filter.AjaxAuthenticationFilter;
import com.beingapple.webservice.auth.jwt.JwtAuthenticationProvider;
import com.beingapple.webservice.auth.jwt.filter.JwtAuthenticationFilter;
import com.beingapple.webservice.auth.ajax.AjaxAuthenticationProvider;
import com.beingapple.webservice.auth.jwt.matcher.AuthenticationPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationProvider jwtProvider;

    @Autowired
    private AjaxAuthenticationProvider ajaxProvider;

    @Autowired
    private BaseSecurityHandler securityHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String LOGIN_ENTRY_POINT = "/api/login";
    private static final String SEARCH_ENTRY_POINT = "/api/search/**";
    private static final String HISTORY_ENTRY_POINT = "/api/history";
    private static final String[] SWAGGER_ENTRY_POINT = {"/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"};


    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/resources/**")
                .antMatchers(SWAGGER_ENTRY_POINT);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        ajaxProvider.setForcePrincipalAsString(true);

        auth.authenticationProvider(ajaxProvider)
                .authenticationProvider(jwtProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), FilterSecurityInterceptor.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(SEARCH_ENTRY_POINT).authenticated()
                .antMatchers(HISTORY_ENTRY_POINT).authenticated()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().permitAll()
                .and().cors();
    }

    @Bean
    public AntPathRequestMatcher antPathRequestMatcher() {
        return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
    }

    @Bean
    public AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
        AjaxAuthenticationFilter filter = new AjaxAuthenticationFilter(antPathRequestMatcher(), objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(securityHandler);
        filter.setAuthenticationFailureHandler(securityHandler);
        return filter;
    }

    @Bean
    public AuthenticationPathRequestMatcher authenticationPathRequestMatcher() {
        return new AuthenticationPathRequestMatcher(Arrays.asList(SEARCH_ENTRY_POINT, HISTORY_ENTRY_POINT));
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationPathRequestMatcher());
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(securityHandler);
        return filter;
    }

}
