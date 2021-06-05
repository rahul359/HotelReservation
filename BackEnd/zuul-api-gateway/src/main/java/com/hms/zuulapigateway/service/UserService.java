package com.hms.zuulapigateway.service;




import com.hms.zuulapigateway.model.AuthRequest;

import com.hms.zuulapigateway.model.AuthResponse;
import com.hms.zuulapigateway.model.Employee;
import com.hms.zuulapigateway.model.UserResponse;
import com.hms.zuulapigateway.repository.EmployeeRepository;
import com.hms.zuulapigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeRepository employeeRepository ;

    

    //Generates token
    public ResponseEntity<?> generateToken(AuthRequest authRequest) throws Exception {
        try {
            //Verifying email and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid email or password");
        }

        //Generate token
        String token = jwtUtil.generateToken(authRequest.getEmail());
        Employee employee = employeeRepository.findByEmail(authRequest.getEmail());

        UserResponse userResponse=new UserResponse(employee.getDesignation(),employee.getName(),employee.getEmail());
        
        //Response
        return ResponseEntity.status(HttpStatus.OK)
                .body(new AuthResponse(token, userResponse));
    }
}
