package com.example.listaCompra.utils.security;
import com.example.listaCompra.entity.authCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
      try{
         authCredentials auth= new ObjectMapper().readValue(request.getReader(), authCredentials.class);
         List<GrantedAuthority> listAuth= new ArrayList<>();
         listAuth.add(new SimpleGrantedAuthority("ADMIN"));
         UsernamePasswordAuthenticationToken usernamePAT= new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword(), listAuth);
         return getAuthenticationManager().authenticate(usernamePAT);
      }catch(Exception e){
          System.out.println("Error attempAuthentication");
          System.out.println(e);
          return null;
      }
    }
    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth){
        try{
            userDetailsImpl user= (userDetailsImpl) auth.getPrincipal();
            String token= tokenUtils.createToken(user.getUsername(), user.getPassword());
            response.setHeader("Authorization", "Bearer " + token);
            response.getWriter().flush();
            super.successfulAuthentication(request, response, chain, auth);
        }catch(Exception e){
            System.out.println("SuccessfulAuthentication");
            System.out.println(e);
        }
    }
}
