package com.example.customer_role_service.securityConfig;


import com.example.customer_role_service.entity.MyCustomerDetail;
import com.example.customer_role_service.service.MyCustomerDetailService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

    @Autowired
    MyCustomerDetailService myCustomerDetailService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myCustomerDetailService);
        provider.setPasswordEncoder(getPasswordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (ensure this is safe for your use case)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/addCustomer", "/customers", "/swagger-ui/index.html").permitAll() // No authentication required
                        .requestMatchers("/customer").hasAnyAuthority("ROLE_CUSTOMER", "ROLE_ADMIN") // Restricted access
                        .requestMatchers("/admin").hasAuthority("ROLE_ADMIN") // Restricted access
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(formLogin -> formLogin
                        .permitAll() // Allow all to access the login page
                )
                .logout(logout -> logout
                        .permitAll() // Allow all to log out
                );
        return httpSecurity.build();
    }


}

