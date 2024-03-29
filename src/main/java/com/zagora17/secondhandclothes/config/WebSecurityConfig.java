package com.zagora17.secondhandclothes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/orders/**").hasAuthority(USER)
                .requestMatchers(HttpMethod.GET, "/api/cart-details/**").hasAuthority(USER)
                .requestMatchers(HttpMethod.DELETE, "/api/images/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.POST, "/api/images/**").hasAuthority((ADMIN))
                .requestMatchers(HttpMethod.POST, "/api/products").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAuthority(ADMIN)
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/checkout/**" ).permitAll()
                .anyRequest().authenticated();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        System.out.println();
        return http.build();
    }
}
