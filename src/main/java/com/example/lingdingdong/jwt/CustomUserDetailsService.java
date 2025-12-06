package com.example.lingdingdong.jwt;

import com.example.lingdingdong.entity.auth.Auth;
import com.example.lingdingdong.exception.CustomException;
import com.example.lingdingdong.repo.AuthRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String phoneOrMail) throws UsernameNotFoundException {
        Auth user;
        log.info(phoneOrMail);
        if (authRepo.existsByEmail(phoneOrMail))
            user = authRepo.findByEmail(phoneOrMail).orElseThrow();
        else throw new CustomException(HttpStatus.NOT_FOUND, "Not found this email or this phone number!");

        return new CustomUserDetails(user);
    }

    private Collection<? extends GrantedAuthority> getAuthorities (Auth user){
        return user.getRoles().stream()
                .map(role-> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
}
