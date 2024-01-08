package com.cupitmadland.capstone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Created for custom Spring Security Filter Chain

/**
 * Configuration class for customizing the Spring Security Filter Chain.
 * Provides bean for password encoder and configures security setting for various endpoints.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurity {

    /**
     * Bean definition for a BCryptPasswordEncode to be used for password encoding.
     *
     * @return PasswordEncoder instance using BCrypt hashing algorithm.
     */
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();}

    /**
     * Configures the Spring Security Filter Chain for various endpoints and security settings.
     *
     * @param http HttpSecurity object for configuring security settings.
     * @return SecurityFilterChain instance representing the configured security filter chain.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
            http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((authorize) -> authorize
                                    .requestMatchers("/home").permitAll()
                                    .requestMatchers("/candles").permitAll()
                                    .requestMatchers("/scents").permitAll()
                                    .requestMatchers("/single_product").permitAll()
                                    .requestMatchers("/product/**").permitAll()
                                    .requestMatchers("/api/**").permitAll()
                                    .requestMatchers("/our_story").permitAll()
                                    .requestMatchers("/contact_us/**").permitAll()
                                    .requestMatchers("/thank_you").permitAll()
                                    .requestMatchers("/shopping_cart/").permitAll()
                                    .requestMatchers("/shoppingcart/**").permitAll()
                                    .requestMatchers("/processCustomerName").permitAll()
                                    .requestMatchers("/checkout").permitAll()
                                    .requestMatchers("/addToCart").permitAll()
                                    .requestMatchers("/view").permitAll()
                                    .requestMatchers("/order_details").permitAll()
                                    .requestMatchers("/register/**").permitAll()
                                    .requestMatchers("/static/javascript/**").permitAll()
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers("/newsletter/**").hasRole("USER")
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
