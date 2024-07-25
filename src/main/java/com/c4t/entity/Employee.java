package com.c4t.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("employee")
public class Employee
{
    @Id
    String id;
    String name;
    String salary;
    String dateOfJoining;
    String designationId;
    String departmentId;

}
