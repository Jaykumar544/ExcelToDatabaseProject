package com.c4t.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("department")
public class Department
{
    @Id
    String id;
    String departmentName;
    String headOfDepartment;

}
