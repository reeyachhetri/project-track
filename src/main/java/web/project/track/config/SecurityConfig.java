package web.project.track.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import web.project.track.repo.StudentUserRepository;

@Configuration
public class SecurityConfig {

    @Autowired
    StudentUserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(getUserDetailsService());
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(){
       return  authentication -> authenticationProvider().authenticate(authentication);
    }
    @Bean
    public UserDetailsService getUserDetailsService(){
        return username -> userRepository.findByUsername(username);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().requestMatchers("/register").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .permitAll()
                        .defaultSuccessUrl("/teacher/home", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}
