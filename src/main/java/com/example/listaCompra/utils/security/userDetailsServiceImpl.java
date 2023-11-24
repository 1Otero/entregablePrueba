package com.example.listaCompra.utils.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.listaCompra.repository.clientRepository;
import com.example.listaCompra.entity.client;
@Service
@AllArgsConstructor
public class userDetailsServiceImpl implements UserDetailsService {
    private clientRepository clientR;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            client cli= clientR.findOneByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new userDetailsImpl(cli);
        }catch(Exception e){
            System.out.println("Error loadUserByUsername -> findOneByEmail");
            System.out.println(e);
            return null;
        }
    }
}
