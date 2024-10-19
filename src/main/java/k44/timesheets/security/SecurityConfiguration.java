package k44.timesheets.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.RequestContextFilter;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfiguration {

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http, RequestContextFilter requestContextFilter) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers("/home/timesheets/**").hasAnyAuthority("user", "admin")
//                        .requestMatchers("/home/projects/**").hasAnyAuthority("admin")
//                        .requestMatchers("/**").hasAuthority("rest")
//
//                        .anyRequest().denyAll()
//                )
//
//                .formLogin(Customizer.withDefaults())
//                .build();
//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, RequestContextFilter requestContextFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.anyRequest().permitAll())
                .build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
