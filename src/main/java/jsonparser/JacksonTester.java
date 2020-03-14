package jsonparser;

/**
 * Jackson ObjectMapper类
 */
//ObjectMapper类是Jackson库的主要类。它提供一些功能将转换成Java对象匹配JSON结构，反之亦然。它使用JsonParser和JsonGenerator的实例实现JSON实际的读/写。
//以下是org.codehaus.jackson.map.ObjectMapper类的声明：


import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class JacksonTester {
    public static void main(String args[]) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        //map json to student
        try {
            Student student = mapper.readValue(jsonString, Student.class);
//           Student student = mapper.readValue(jsonString.getBytes(), Student.class);

            System.out.println(student);


            mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
            //把对象转成json string类型的
            jsonString = mapper.writeValueAsString(student);
            System.out.println(jsonString);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Student {
    private String name;
    private int age;

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Student [ name: " + name + ", age: " + age + " ]";
    }
}

//输出结果
//Student [ name: Mahesh, age: 21 ]
//        {
//        "name" : "Mahesh",
//        "age" : 21
//        }