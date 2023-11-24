package com.example.listaCompra.utils.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class securityWebConfig {
    private UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager auth){
       JWTAuthenticationFilter jwtAuthenticationFilter= new JWTAuthenticationFilter();
       jwtAuthenticationFilter.setAuthenticationManager(auth);
       jwtAuthenticationFilter.setFilterProcessesUrl("/login");
       try{
           return http.csrf().disable()
                   .authorizeHttpRequests(r -> {
                       r.requestMatchers("/client/savecli").permitAll()
                               .requestMatchers("/client/**").hasRole("CLIENT")
                               .requestMatchers("/product/**").hasRole("ADMIN")
                               .requestMatchers("/ini/**").permitAll()
                               .anyRequest().authenticated();
                   })
                   .httpBasic()
                   .and()
                   .sessionManagement()
                   .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                   .and()
                   .addFilter(jwtAuthenticationFilter)
                   .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                   .build();
       }catch(Exception e){
           System.out.println("Error filterChain");
           System.out.println(e);
           return null;
       }
    }
    @Bean
    AuthenticationManager authManager(HttpSecurity http){
       try{
           return http.getSharedObject(AuthenticationManagerBuilder.class)
                   .userDetailsService(userDetailsService)
                   .passwordEncoder(passwordEncoder())
                   .and()
                   .build();
       }catch(Exception e){
           System.out.println("Error authManager");
           System.out.println(e);
           return null;
       }
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
