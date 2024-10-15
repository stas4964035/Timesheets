package k44.timesheets.security;

import k44.timesheets.model.User;
import k44.timesheets.model.UserRole;
import k44.timesheets.repository.UserRoleRepository;
import k44.timesheets.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyCustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<SimpleGrantedAuthority> userRoles = userRoleRepository.findByUserId(user.getId())
                .stream()
                .map(it -> new SimpleGrantedAuthority(it.getRoleName()))
                .toList();



        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                userRoles
        );
    }
}
