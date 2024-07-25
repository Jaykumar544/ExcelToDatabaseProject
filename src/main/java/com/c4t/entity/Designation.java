package com.c4t.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("designation")
public class Designation
{
    @Id
    String id;
    String designationName;

}
