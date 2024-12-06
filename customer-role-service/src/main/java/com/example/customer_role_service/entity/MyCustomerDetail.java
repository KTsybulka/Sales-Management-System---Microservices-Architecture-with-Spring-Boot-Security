package com.example.customer_role_service.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyCustomerDetail implements UserDetails {
    private String customerName;
    private String customerPassword;
//    private boolean isActive;
    private List<GrantedAuthority> authorityList;
//    private LocalDateTime createdAt;

    public MyCustomerDetail(Customer customer) {
        this.customerName = customer.getCustomerName();
        this.customerPassword = customer.getCustomerPassword();
//        this.isActive = customer.isActive();

        this.authorityList = Arrays.stream(customer.getRole().split(","))
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
//        this.createdAt = customer.getCreatedAt();
    }

    public MyCustomerDetail() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return customerPassword;
    }

    @Override
    public String getUsername() {
        return customerName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
