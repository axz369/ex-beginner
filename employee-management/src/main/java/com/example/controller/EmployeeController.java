package com.example.controller;


import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;

    @GetMapping("")
    public String index() {
        return "employee";
    }

    @GetMapping("/execute")
    public String execute(){
//        //全権検索
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList = repository.findAll();
//        for(Employee employee : employeeList){
//            System.out.println(employee.getId());
//            System.out.println(" " + employee.getName());
//            System.out.println(" " + employee.getAge());
//            System.out.println(" " + employee.getDepartmentId());
//            System.out.println();
//        }

//        //一見検索
//        Employee employee = repository.findById(3);
//        System.out.println(employee.getId());
//        System.out.println(employee.getName());
//        System.out.println(employee.getAge() + "歳");
//        System.out.println("所属 : " + employee.getDepartmentId());


        //挿入&更新
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("村田晶");
        employee.setAge(22);
        employee.setGender("X");
        employee.setDepartmentId(1);
        Employee resultEmployss = repository.save(employee);
        System.out.println(resultEmployss);
        return "finished";
    }
}