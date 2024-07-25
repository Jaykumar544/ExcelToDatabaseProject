package com.c4t.repository;

import com.c4t.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee,String>
{
    Optional<Employee> findById(String  id);

}
