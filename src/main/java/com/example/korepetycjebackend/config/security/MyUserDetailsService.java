package com.example.korepetycjebackend.config.security;

//import com.example.korepetycjebackend.repositories.BarberRepository;
import com.example.korepetycjebackend.repositories.AdminRepository;
import com.example.korepetycjebackend.repositories.ClientRepository;
import com.example.korepetycjebackend.repositories.TeacherRepository;
import com.example.korepetycjebackend.repositories.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserDataRepository userDataRepository;
    private final ClientRepository clientRepository;
    private final TeacherRepository teacherRepository;
    private final AdminRepository adminRepository;

    @Override
    public MyUserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        var userData = userDataRepository.findByEmailAddress(emailAddress)
                .orElseThrow(()->new RuntimeException("user not found"));
        var optionalTeacher = teacherRepository.findByUserDataId(userData.getId());
        var optionalClient = clientRepository.findByUserDataId(userData.getId());
        var optionalAdmin = adminRepository.findByUserDataId(userData.getId());
        if(optionalClient.isEmpty() && optionalTeacher.isEmpty() && optionalAdmin.isEmpty()){
            throw new RuntimeException("neither client nor teacher found");
        }

        UUID userId = null;
        if(optionalClient.isPresent()) userId= optionalClient.get().getId();
        if(optionalTeacher.isPresent()) userId= optionalTeacher.get().getId();
        if(optionalAdmin.isPresent()) userId= optionalAdmin.get().getId();

        return new MyUserDetails(userData,userId);

    }
}