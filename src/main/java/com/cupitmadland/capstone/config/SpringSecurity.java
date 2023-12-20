package com.cupitmadland.capstone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((authorize) -> authorize
                                    .requestMatchers("/home").permitAll()
                                    .requestMatchers("/candles").permitAll()
                                    .requestMatchers("/scents").permitAll()
                                    .requestMatchers("/single_product").permitAll()
                                    .requestMatchers("/our_story").permitAll()
                                    .requestMatchers("/contact_us").permitAll()
                                    .requestMatchers("/shopping_cart").permitAll()


                    //continue building security chain AND form login in AND logout

                    );

return http.build();
    }
}
