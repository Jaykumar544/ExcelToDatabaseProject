package com.c4t.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("customer")
public class Customer
{
    @Id
    private String id;

    private String name;
    private String email;
    private String phone;



}
