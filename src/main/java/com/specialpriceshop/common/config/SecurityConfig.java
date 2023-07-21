package com.specialpriceshop.common.config;

import com.specialpriceshop.auth.domain.JwtTokenParser;
import com.specialpriceshop.auth.filter.JwtFilter;
import com.specialpriceshop.common.error.JwtAuthenticationEntryPointHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String secretKey;

    public SecurityConfig(@Value("${custom.jwt.secretKey}") final String secretKey) {
        this.secretKey = secretKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()

            .headers()
            .frameOptions()
            .sameOrigin()

            .and()
            .cors()

            .and()
            .logout()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            .antMatchers(HttpMethod.POST, "/accounts").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .antMatchers(HttpMethod.POST, "/auth/reissue").permitAll()
            .anyRequest()
            .authenticated()

            .and()
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPointHandler());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtTokenParser jwtTokenParser() {
        return new JwtTokenParser(secretKey);
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtTokenParser());
    }

    @Bean
    public JwtAuthenticationEntryPointHandler jwtAuthenticationEntryPointHandler() {
        return new JwtAuthenticationEntryPointHandler();
    }
}
