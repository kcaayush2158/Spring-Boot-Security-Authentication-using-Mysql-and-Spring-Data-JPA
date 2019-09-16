package com.application.demo.service;

import com.application.demo.model.User;
import com.application.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=  userRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + user));

        return user.map(CustomUserDetails::new).get();
    }
}
