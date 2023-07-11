package com.loncark.guitarapp.security;

import com.loncark.guitarapp.service.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfigurer{

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        //UserDetails admin = User.withUsername("John").password(encoder.encode("johnpassword")).roles("ADMIN").build();
        //UserDetails user1 = User.withUsername("Mark").password(encoder.encode("markpassword")).roles("USER").build();

        return new UserInfoUserDetailsService();
    }

    // for the purposes of encrypting the user details above
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/1231").authenticated()
                .and().formLogin().and().build();
    }
}
