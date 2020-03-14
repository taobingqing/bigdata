package com.yaml;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class YamlTest {
    public static void main(String[] args) throws IOException {

        Map load = YamlUtil.load("ue-flink-dev.yaml");
        Map rootProperty = (Map) load.get("config");
        Object o = rootProperty.get("kafka.group.id");
        System.out.println(o);
        //includes=[flink/serializer.yaml, flink/redisClusterPool.yaml
        System.out.println(load);

        System.out.println("==========");
        List topic = (List)rootProperty.get("file.topic.names");
        for (Object t : topic) {
            System.out.println(t);

        }
        System.out.println(topic);

//        Set<Map.Entry<String,String>> set = load.entrySet();
//        for (Map.Entry<String, String> entry : set) {
//            System.out.println(entry.getKey()+entry.getValue());
//        }
    }
}
