package com.nhao.springboot.jsonpojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileOutputStream;

public class JacksonPojoToJson {
    public static void main(String[] args) throws Exception {

        // Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Employee employee = new Employee();
        employee.setId(2005l);
        employee.setName("John Doe");
        employee.setSalary(40000);
        employee.setSkills(new String[] { "Java", "SQL", "jQuery" });

        Address address = new Address();
        address.setId(100L);
        address.setCity("Delhi");
        address.setCountry("India");
        address.setPin("231245");

        employee.setAddress(address);

        // Convert object to JSON string
        String json = mapper.writeValueAsString(employee);
        System.out.println(json);

        // Save JSON string to file
        FileOutputStream fileOutputStream = new FileOutputStream("employee.json");
        mapper.writeValue(fileOutputStream, employee);
        fileOutputStream.close();
    }
}
