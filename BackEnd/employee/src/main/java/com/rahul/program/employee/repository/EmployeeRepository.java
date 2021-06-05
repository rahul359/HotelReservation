package com.rahul.program.employee.repository;

import java.util.Optional;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rahul.program.employee.model.Employee;

@Repository

public interface EmployeeRepository extends MongoRepository<Employee ,Long> {
	
	@Query("{'name': ?0}")
	

	Optional<Employee> findByEmployee(Long registeredNo);
	
	Optional<Employee> findByEmail(String email);

	
	


	


}
