package com.example.customer_role_service.service;

import com.example.customer_role_service.entity.Customer;
import com.example.customer_role_service.entity.MyCustomerDetail;
import com.example.customer_role_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyCustomerDetailService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByCustomerName(customerName);

        if(!customer.isPresent()){
            throw new UsernameNotFoundException("Customer not found " + customerName);
        }
        return new MyCustomerDetail(customer.get());
    }
}
