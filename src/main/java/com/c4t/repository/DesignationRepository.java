package com.c4t.repository;

import com.c4t.entity.Designation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DesignationRepository extends MongoRepository<Designation,String>
{
    Optional<Designation> findById(String id);
}
