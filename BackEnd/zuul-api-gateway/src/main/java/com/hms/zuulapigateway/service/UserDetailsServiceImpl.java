package com.hms.zuulapigateway.service;


import com.hms.zuulapigateway.model.Employee;

import com.hms.zuulapigateway.repository.EmployeeRepository;
import com.hms.zuulapigateway.util.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        email = email.toLowerCase();
        Employee employee=employeeRepository.findByEmail(email);

        if (employee==null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MyUserDetails(employee);
    }
}
