package org.infobip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Autowired
    CustomAuthenticationManager authenticationManager;

    @Autowired
    CustomAuthenticationProvider authenticationProvider;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(new BasicAuthenticationFilter(authenticationManager))
                .authenticationProvider(authenticationProvider)
                .csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/{shortUrlKey}").permitAll()
                .antMatchers(HttpMethod.POST, "/account").permitAll()
                .and().httpBasic().realmName("shortener_realm").authenticationEntryPoint(basicAuthenticationEntryPoint);
    }
}
