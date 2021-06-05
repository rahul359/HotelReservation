package com.hms.zuulapigateway.repository;

import java.util.Optional;




import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.hms.zuulapigateway.model.Employee;



@Repository

public interface EmployeeRepository extends MongoRepository<Employee ,Long> {
	
	@Query("{'name': ?0}")
	

	Optional<Employee> findByEmployee(Long registeredNo);

	Employee findByEmail(String username);
	
}
