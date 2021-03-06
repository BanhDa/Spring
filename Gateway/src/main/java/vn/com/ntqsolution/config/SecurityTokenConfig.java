/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.ntqsolution.config;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.com.ntqsolution.filters.JwtTokenAuthenticationFilter;

/**
 *
 * @author tuantv
 */
@EnableWebSecurity
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] uriPermits = new String[2];
        uriPermits[0] = jwtConfig.getUri();
        uriPermits[1] = jwtConfig.getRegisterUri();
        http
                .csrf().disable()
                // make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // handle an authorized attempts 
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                // Add a filter to validate the tokens with every request
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                // authorization requests config
                .authorizeRequests()
                // allow all who are accessing "auth" service
                .antMatchers(HttpMethod.POST, uriPermits).permitAll()
                // must be an admin if trying to access admin area (authentication is also required here)
                .antMatchers("/gallery" + "/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                // Any other request must be authenticated
                .anyRequest().authenticated();
    }

    @Bean
    public JWTConfig jwtConfig() {
        return new JWTConfig();
    }
}
