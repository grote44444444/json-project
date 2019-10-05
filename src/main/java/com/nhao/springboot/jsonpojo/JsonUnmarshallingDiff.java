package com.nhao.springboot.jsonpojo;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonPatch;
import javax.json.JsonStructure;
import javax.json.spi.JsonProvider;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Set;

public class JsonUnmarshallingDiff {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Read JSON file and convert to java object
        FileInputStream fileInputStream = new FileInputStream("employee.json");
//        JsonNode employee = mapper.readValue(fileInputStream, JsonNode.class);
        JsonNode treeNode = mapper.readTree(fileInputStream);
        fileInputStream.close();

        FileInputStream fileInputStream1 = new FileInputStream("employee2.json");
        JsonNode treeNode2 = mapper.readTree(fileInputStream1);
        fileInputStream1.close();



//        System.out.print(employee.toString());
//        System.out.print(treeNode.toString());
        JsonNode next = null;
        String fieldName;
//        while(treeNode.fieldNames().hasNext()){
//            fieldName = treeNode.fieldNames().next();
//
//            compareJson(treeNode.get(fieldName), treeNode2.get(fieldName), fieldName);
//        }

        final JsonProvider provider = JsonProvider.provider();
        JsonStructure jsonStructure = provider.createReader(new StringReader(treeNode.toString())).read();
        JsonStructure jsonStructure2 = provider.createReader(new StringReader(treeNode2.toString())).read();

        JsonPatch diff = Json.createDiff(jsonStructure, jsonStructure2);
        System.out.print(diff.toString());
        
//        Employee employee = new Gson().fromJson(treeNode.toString(), Employee.class);
//        Employee employee2 = new Gson().fromJson(treeNode2.toString(), Employee.class);


//        JSONParser parser = new JSONParser();
//        try {
//            Object obj = parser.parse(new FileReader("employee.json"));
//            Object obj1 = parser.parse(new FileReader("employee3.json"));
//            JSONObject jsonObject = (JSONObject) obj;
//            JSONObject jsonObject1 = (JSONObject) obj1;
//            Set<String> s = jsonObject.keySet();
//            for (String str : s) {
////                System.out.println("key:" + str + " : value1:" + jsonObject.get(str) + ":value2:" + jsonObject1.get(str));
//                //compare value of json1 with json2
//                if(!jsonObject.get(str).equals(jsonObject1.get(str))){
//                    System.out.println("diff!!!!  [" + jsonObject.get(str) + "] with ["  + jsonObject1.get(str) + "]");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    public static void compareJson(JsonNode json1, JsonNode json2, String key) {
        if (json1 instanceof ObjectNode) {
            System.out.println("this ObjectNode----" + key);
            compareJson((JsonNode) json1, (JsonNode) json2, key);
        } else if (json1 instanceof ArrayNode) {
           System.out.println("this ArrayNode----" + key);
            compareJson((ArrayNode) json1, (ArrayNode) json2, key);
        } else {
            System.out.println("this value----" + key);
            compareJson(json1.toString(), json2.toString(), key);
        }
    }

    public static void compareJson(String str1, String str2, String key) {
        if (!str1.equals(str2)) {
           // sb.append("key:" + key + ",json1:" + str1 + ",json2:" + str2);
            System.err.println("不一致key:" + key + ",json1:" + str1 + ",json2:" + str2);
        } else {
            System.out.println("一致：key:" + key + ",json1:" + str1 + ",json2:" + str2);
        }
    }

}
