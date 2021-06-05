package com.rahul.program.employee.service;

import static org.assertj.core.api.Assertions.assertThat;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rahul.program.employee.exception.EmployeeException;
import com.rahul.program.employee.model.Employee;
import com.rahul.program.employee.repository.EmployeeRepository;

class EmployeeServiceImplTest {
	
	@InjectMocks
	EmployeeServiceImpl service;
	
	@Mock
	EmployeeRepository repo;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	void testGetAllEmployee() {
		Employee emp1=new Employee();
		emp1.setName("Test1");
		emp1.setAge(28);
		emp1.setDesignation("Receptionist");
		emp1.setRegisteredNo((long) 1049);
		emp1.setEmail("test@gmail.com");
		emp1.setPassword("testing");
		emp1.setSalary(18000);
		emp1.setStatus(true);
		Employee emp2=new Employee();
		emp2.setName("Test2");
		emp2.setAge(30);
		emp2.setDesignation("Manager");
		emp2.setRegisteredNo((long) 1049);
		emp2.setEmail("test@gmail.com");
		emp2.setPassword("testing");
		emp2.setSalary(28000);
		emp2.setStatus(true);
		List<Employee> list=new ArrayList<>();
		list.add(emp1);
		list.add(emp2);
		
		when( repo.findAll()).thenReturn(list);	       
		assertThat(service.getAllEmployee()).isEqualTo(list);
	}
	
	@Test
	void testGetSingleEmployee() throws EmployeeException {
		Employee emp=new Employee();
		emp.setName("Test");
		emp.setAge(30);
		emp.setDesignation("Manager");
		emp.setRegisteredNo((long) 1049);
		emp.setEmail("test@gmail.com");
		emp.setPassword("testing");
		emp.setSalary(28000);
		emp.setStatus(true);
		
		when(repo.findById( (long) 1049)).thenReturn(Optional.of(emp));
		Employee employee=service.getSingleEmployee((long) 1049);
		assertNotNull(employee);
		assertEquals("Test",emp.getName());
	}
	
	@Test
	void testUpdateEmployee() throws EmployeeException{
		Employee emp=new Employee();
		emp.setName("Test");
		emp.setAge(30);
		emp.setDesignation("Manager");
		emp.setRegisteredNo((long) 1049);
		emp.setEmail("test@gmail.com");
		emp.setPassword("testing");
		emp.setSalary(28000);
		emp.setStatus(true);
		repo.save(emp);
		emp.setAge(27);
		when(repo.findById( (long) 1049)).thenReturn(Optional.of(emp));
		service.updateEmployee((long) 1049, emp);
		assertEquals(27,emp.getAge());
	}
	
	@Test
	void testDeleteEmployee() throws EmployeeException {
		Employee emp=new Employee();
		emp.setName("Test");
		emp.setAge(30);
		emp.setDesignation("Receptionist");
		emp.setRegisteredNo((long) 1049);
		emp.setEmail("test@gmail.com");
		emp.setPassword("testing");
		emp.setSalary(28000);
		emp.setStatus(true);
		repo.save(emp);
		when(repo.findById((long) 1049)).thenReturn(Optional.of(emp));
		assertThat(service.deleteEmployeeById((long) 1049)).isEqualTo("Success");
	}
}
