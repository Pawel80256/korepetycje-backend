package config.authentication;

import com.example.korepetycjebackend.constants.ERole;
import com.example.korepetycjebackend.repositories.AdminRepository;
//import com.example.korepetycjebackend.repositories.BarberRepository;
import com.example.korepetycjebackend.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;
//    private final BarberRepository barberRepository;
    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {

        return new User("test","test", List.of(new SimpleGrantedAuthority("test")));
       /* if(barberRepository.existsByEmailAddress(emailAddress)){
            var doctor = barberRepository.findByEmailAddress(emailAddress);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(){{add(new SimpleGrantedAuthority(ERole.BARBER.toString()));}};
            return new User(doctor.getUserData().getEmailAddress(), doctor.getUserData().getPassword(), authorities);
        }
        if(clientRepository.existsByEmailAddress(emailAddress)){
            var client = clientRepository.findByEmailAddress(emailAddress);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(){{add(new SimpleGrantedAuthority(ERole.CLIENT.toString()));}};
            return new User(client.getUserData().getEmailAddress(), client.getUserData().getPassword(), authorities);
        }
        if(adminRepository.existsByEmailAddress(emailAddress)){
            var admin = adminRepository.findByEmailAddress(emailAddress);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>(){{add(new SimpleGrantedAuthority(ERole.CLIENT.toString()));}};
            return new User(admin.getUserData().getEmailAddress(), admin.getUserData().getPassword(), authorities);
        }else {
            throw new RuntimeException("Nie znaleziono uzytkownika");
        }*/
    }
}