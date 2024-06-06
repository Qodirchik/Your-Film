package com.example.yourfilm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    private final String[] whileList = new String[]{"/auth/register",
            "/auth/login",
            "/css/**",
            "/", "/img/**", "/js/**", "/role", "/templates/videos", "/permission", "/video"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.
                authorizeHttpRequests(
                        registry -> registry
                                    .requestMatchers(whileList)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .oauth2Client(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .formLogin(
                        loginConfig -> loginConfig
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/login/post")
                                .usernameParameter("phoneNumber")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/")
                )
                .logout(
                        logoutConfig -> logoutConfig
                                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                                .logoutSuccessUrl("/")
                )
                .rememberMe(
                        rememberMeConfig -> rememberMeConfig
                                .rememberMeParameter("rem")
                                .tokenValiditySeconds(3600 * 24)
                )
                .build();
    }
}
