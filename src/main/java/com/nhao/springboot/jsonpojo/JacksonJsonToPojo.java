package com.nhao.springboot.jsonpojo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;

public class JacksonJsonToPojo {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON file and convert to java object
        FileInputStream fileInputStream = new FileInputStream("employee.json");
        Employee employee = mapper.readValue(fileInputStream, Employee.class);
        fileInputStream.close();

        // Print
        System.out.println("Emplyoee ID : " + employee.getId());
        System.out.println("Emplyoee Name : " + employee.getName());
        System.out.println("Emplyoee Salary : " + employee.getSalary());
        System.out.println("Emplyoee Skills : ");
        for (String skill : employee.getSkills()) {
            System.out.println("\t" + skill);
        }

        Address address=employee.getAddress();
        System.out.println("Address ID : "+address.getId());
        System.out.println("City : "+address.getCity());
        System.out.println("Country : "+address.getCountry());
        System.out.println("Pin : "+address.getPin());

    }
}
