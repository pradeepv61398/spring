package com.insurnace.customer.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insurnace.customer.EntityModel.User;
import com.insurnace.customer.Repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),   // hashed password from DB
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
           

    }

}
