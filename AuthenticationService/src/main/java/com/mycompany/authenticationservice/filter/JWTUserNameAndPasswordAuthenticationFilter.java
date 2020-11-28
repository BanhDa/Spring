/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.authenticationservice.filter;

import com.mycompany.authenticationservice.config.JWTConfig;
import com.mycompany.authenticationservice.request.LoginRequestData;
import com.mycompany.utils.JsonUtil;
import com.mycompany.utils.StringUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author tuantv
 */
public class JWTUserNameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTConfig jWTConfig;

    public JWTUserNameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, JWTConfig jWTConfig) {
        this.authenticationManager = authenticationManager;
        this.jWTConfig = jWTConfig;
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jWTConfig.getUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String requestData = StringUtil.readInputStream(request.getInputStream());
            LoginRequestData userLoginRequestData = JsonUtil.toObject(requestData, LoginRequestData.class);

            String userName = userLoginRequestData.getUserName();
            String password = userLoginRequestData.getPassword();

            UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(userName, password, Collections.emptyList());

            return authenticationManager.authenticate(authenToken);
        } catch (IOException | AuthenticationException ex) {
        
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                // Convert to list of strings. 
                // This is important because it affects the way we get them back in the Gateway.
                .claim("authorities", authResult.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jWTConfig.getExpiration() * 1000)) // in milliseconds
                .signWith(SignatureAlgorithm.HS512, jWTConfig.getSecret().getBytes())
                .compact();

        // Add token to header
        response.addHeader(jWTConfig.getHeader(), jWTConfig.getPrefix() + token);
    }

}
