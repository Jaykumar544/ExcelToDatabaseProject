package com.c4t.controller;

import com.c4t.entity.Customer;
import com.c4t.entity.Department;
import com.c4t.entity.Designation;
import com.c4t.entity.Employee;
import com.c4t.repository.CustomerRepository;
import com.c4t.repository.DepartmentRepository;
import com.c4t.repository.DesignationRepository;
import com.c4t.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class EmployeeController
{

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DesignationRepository designationRepository;
    @Autowired
    DepartmentRepository departmentRepository;


    @GetMapping("/employeeDetails/{id}")
    public Map<String,Object> gettingEmpDetails(@PathVariable String id)
    {
        Employee employee = null;
        Map<String,Object> map = new LinkedHashMap<>();
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent())
        {
            employee = optionalEmployee.get();
            map.put("employeeId",employee.getId());
            map.put("employeeName",employee.getName());
            map.put("employeeSalary",employee.getSalary());
            map.put("employeeDateOfJoining",employee.getDateOfJoining());
        }

        Optional<Department> optionalDepartment = departmentRepository.findById(employee.getDepartmentId());
        if(optionalDepartment.isPresent())
        {
            Department department = optionalDepartment.get();
            map.put("departmentId",department.getId());
            map.put("departmentName",department.getDepartmentName());
            map.put("headOfDepartment",department.getHeadOfDepartment());
        }

        Optional<Designation> optionalDesignation = designationRepository.findById(employee.getDesignationId());
        if(optionalDesignation.isPresent())
        {
            Designation designation = optionalDesignation.get();
            map.put("designationId",designation.getId());
            map.put("designationName",designation.getDesignationName());
        }

        return map;
    }


}
