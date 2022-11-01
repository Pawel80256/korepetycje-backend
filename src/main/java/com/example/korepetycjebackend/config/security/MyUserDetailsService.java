package com.example.korepetycjebackend.config.security;

//import com.example.korepetycjebackend.repositories.BarberRepository;
import com.example.korepetycjebackend.repositories.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserDataRepository userDataRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        var userData = userDataRepository.findByEmailAddress(emailAddress);
        if(userData.isEmpty()){
            throw new RuntimeException("user not found");
        }

        return new MyUserDetails(userData.get());

    }
}