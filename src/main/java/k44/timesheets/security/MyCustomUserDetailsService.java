package k44.timesheets.security;

import k44.timesheets.model.User;
import k44.timesheets.repository.RoleRepository;
import k44.timesheets.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyCustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<SimpleGrantedAuthority> userRoles = user.getRoles()
                .stream()
                .map(it -> new SimpleGrantedAuthority(it.getName()))
                .toList();



        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                userRoles
        );
    }
}
