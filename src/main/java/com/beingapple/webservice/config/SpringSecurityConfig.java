package com.beingapple.webservice.config;

import com.beingapple.webservice.auth.BaseSecurityHandler;
import com.beingapple.webservice.auth.ajax.filter.AjaxAuthenticationFilter;
import com.beingapple.webservice.auth.jwt.JwtAuthenticationProvider;
import com.beingapple.webservice.auth.jwt.filter.JwtAuthenticationFilter;
import com.beingapple.webservice.auth.ajax.AjaxAuthenticationProvider;
import com.beingapple.webservice.auth.jwt.matcher.SkipPathRequestMatcher;
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

import java.util.ArrayList;
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

    private static final String LOGIN_ENTRY_POINT = "/login";
    private static final String JOIN_ENTRY_POINT = "/join";
    private static final String ERROR_ENTRY_POINT = "/error";
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
                .antMatchers(JOIN_ENTRY_POINT).permitAll()
                .antMatchers(LOGIN_ENTRY_POINT).permitAll()
                .antMatchers(ERROR_ENTRY_POINT).permitAll()
                .antMatchers(SWAGGER_ENTRY_POINT).permitAll()
                .anyRequest()
                .authenticated();
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
    public SkipPathRequestMatcher skipPathRequestMatcher() {
        List<String> pathList = new ArrayList<>();
        for(String entryPoint : SWAGGER_ENTRY_POINT){
            pathList.add(entryPoint);
        }
        pathList.add(LOGIN_ENTRY_POINT);
        pathList.add(JOIN_ENTRY_POINT);
        pathList.add(ERROR_ENTRY_POINT);

        return new SkipPathRequestMatcher(pathList);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(securityHandler);
        return filter;
    }

}
