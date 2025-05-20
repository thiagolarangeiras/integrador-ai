package com.satc.integrador.ai.auth;

import com.satc.integrador.ai.enums.Plano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired private UserAuthenticationFilter userAuthenticationFilter;

    public static final String[] AUTH_REQUIRED = {
            "/preferencia",
            "/preferencia/*",
            "/usuario",
            "/usuario/*",
    };

    public static final String[] AUTH_PLANO_NORMAL = {
            "/test/normal"
    };

    public static final String[] AUTH_PLANO_PAGO = {
            "/test/pago"
    };

    public static final String[] AUTH_PLANO_GRATIS = {
            "/test/gratis"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(AUTH_REQUIRED).authenticated()
                        .requestMatchers(AUTH_PLANO_NORMAL).hasAuthority(Plano.NORMAL.name())
                        .requestMatchers(AUTH_PLANO_PAGO).hasAuthority(Plano.PAGO.name())
                        .requestMatchers(AUTH_PLANO_GRATIS).hasAuthority(Plano.GRATIS.name())
                        .anyRequest().permitAll()
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}