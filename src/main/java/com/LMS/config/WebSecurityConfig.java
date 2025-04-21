//package com.LMS.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfiguration {
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/api/courses").permitAll() // Allow public access to /api/courses
//                .anyRequest().authenticated() // Require authentication for other requests
//            .and()
//            .csrf().disable() // Disable CSRF if it's not needed
//            .formLogin() // Configure login form
//                .loginPage("/login") // Custom login page (if needed)
//                .permitAll()
//            .and()
//            .logout() // Enable logout
//                .permitAll();
//    }
//
//    // Optional: Use this to configure the in-memory or JDBC-based user details service
//    @Bean
//    public UserDetailsService userDetailsService() {
//        // Sample in-memory users (for testing purposes)
//        return new JdbcUserDetailsManager();
//    }
//
//    // Password encoder to encode passwords (for storage in the database)
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
