package com.example.listaCompra.utils.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

public class tokenUtils {
  private static final String ACCESS_TOKEN_SECRECT= "4qhkldfjsojfdsghsfljSalkjddsE3hWr";
  private static final Long ACCESS_TOKEN_VALIDITY_SECOND= 2_592_000L;
  public static String createToken(String email, String password){
    Long expirationTime= ACCESS_TOKEN_VALIDITY_SECOND * 1_000L;
    Date expirationDate= new Date(System.currentTimeMillis() + expirationTime);
    List<GrantedAuthority> listAuth= new ArrayList<>();
    listAuth.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    listAuth.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
    Map<String, Object> claims= new HashMap<>();
    claims.put("email", email);
    claims.put("roles", Arrays.asList("ROLE_ADMIN","ROLE_CLIENT"));
    return Jwts.builder()
            .setSubject(email)
            .setExpiration(expirationDate)
            .addClaims(claims)
            .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRECT.getBytes()))
            .compact();
  }
  public static UsernamePasswordAuthenticationToken getAuthentication(String token){
     try{
       Claims claims= Jwts.parser()
               .setSigningKey(ACCESS_TOKEN_SECRECT.getBytes())
               .build()
               .parseClaimsJws(token)
               .getBody();
       String email= claims.getSubject();
       List<String> listA= (List<String>) claims.get("roles");
       List<GrantedAuthority> listMeRoles= listA.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
       return new UsernamePasswordAuthenticationToken(email, null, listMeRoles);
     }catch(Exception e){
       System.out.println("Error getAuthentication Token");
       System.out.println(e);
       return null;
     }
  }
}
