package com.insurnace.customer.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Value("${cors.allowed.origins:https://witty-stone-00c784300.1.azurestaticapps.net,http://localhost:4200}")
    private String allowedOrigins;

    // 1️⃣ Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2️⃣ Authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 3️⃣ Main security filter chain
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http
    // .csrf(csrf -> csrf.disable())
    // .cors(cors -> cors.configurationSource(corsConfigurationSource())) // enable
    // CORS
    // .authorizeHttpRequests(auth -> auth
    // .requestMatchers("/api/register", "/api/login").permitAll() // public
    // endpoints
    // .anyRequest().authenticated())
    // .sessionManagement(session -> session
    // .sessionFixation(sessionFixation -> sessionFixation.migrateSession()))
    // .formLogin(form -> form.disable())
    // .logout(logout -> logout
    // .logoutUrl("/api/logout")
    // .deleteCookies("JSESSIONID"));

    // return http.build();
    // }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/register", "/api/login").permitAll() // session endpoints
                        .requestMatchers("/jwt/**").permitAll() // JWT endpoints
                        .anyRequest().authenticated());

        return http.build();
    }
 
    
  
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allowed origins
        configuration.setAllowedOrigins(List.of(
                "http://localhost:4200",
                "https://witty-stone-00c784300.1.azurestaticapps.net"
        ));

        // Allowed HTTP methods
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Allow all headers
       // configuration.setAllowedHeaders(List.of("*"));
        setAllowedHeaders(List.of("*", "Authorization"))

        // No credentials since JWT is sent in headers
        configuration.setAllowCredentials(false);

        // Cache preflight response for 1 hour
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
