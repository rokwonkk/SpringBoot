package com.springboot.security.service.impl;

import com.springboot.security.data.repository.UserRepository;
import com.springboot.security.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){

        log.info("[loadUserByUsername] loadUserByUsername 수행. username : {}", username);

        return userRepository.getByUid(username);
    }
}
