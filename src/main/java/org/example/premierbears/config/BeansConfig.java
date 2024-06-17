package org.example.premierbears.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {
    //l'interface UserDetailsService est utilisée pour récupérer les détails de l'utilisateur
    //l'implémentation de cette interface est fournie par l'application
    private final UserDetailsService userDetailsService;

    //l'interface AuthenticationProvider est utilisée pour l'authentification des utilisateurs
    //permet de créer un bean de type AuthenticationProvider qui est utilisé pour l'authentification des utilisateurs
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authprovider = new DaoAuthenticationProvider();
        authprovider.setUserDetailsService(userDetailsService);
        authprovider.setPasswordEncoder(passwordEncoder());
        return authprovider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration Configuration) throws Exception {
       return Configuration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
