package web.project.track.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/student/register", "/student/login").permitAll()  // Include the /student prefix
                        .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/student/login")
                .permitAll()
                .defaultSuccessUrl("/student/dashboard", true)  // Ensure the URL matches your paths
                .and()
                .logout()
                .permitAll();

        return http.build();
    }
}
