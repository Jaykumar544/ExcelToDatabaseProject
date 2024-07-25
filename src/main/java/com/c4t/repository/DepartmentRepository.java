package com.c4t.repository;

import com.c4t.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DepartmentRepository extends MongoRepository<Department,String>
{
    Optional<Department> findById(String id);
}
