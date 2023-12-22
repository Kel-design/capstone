package com.cupitmadland.capstone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                                    .requestMatchers("/product/**").permitAll()
                                    .requestMatchers("/our_story").permitAll()
                                    .requestMatchers("/contact_us").permitAll()
                                    .requestMatchers("/shopping_cart").permitAll()
                                    .requestMatchers("/order_details").permitAll()
                                    .requestMatchers("/register/**").permitAll()
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers("/newsletter").hasRole("USER")
                                    .requestMatchers("/static/**").permitAll().anyRequest().authenticated()


                    //continue building security chain AND form login in AND logout

                    )
                    .formLogin(
                            form -> form
                                    .loginPage("/login")
                                    .loginProcessingUrl("/login")
                                    .defaultSuccessUrl("/newsletter")
                                    .permitAll()
                    ).logout(
                            logout -> logout
                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                    .permitAll());

return http.build();
    }
}
