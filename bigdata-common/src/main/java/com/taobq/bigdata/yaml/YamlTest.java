package com.taobq.bigdata.yaml;

import org.apache.commons.math3.util.RandomPivotingStrategy;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YamlTest {

    private static Map config;
    private static Map config2;

    @Before
    public void before() throws IOException {
        config = YamlUtil.load2("D:\\idea_files\\Bigdata-Test\\bigdata-common\\src\\main\\resources\\ue-flink-dev.yaml");
        config2 = (Map) config.get("config");
    }

    @Test
    public void test02() {

        Object o = config2.get("kafka.group.id");
        System.out.println(o);
        //includes=[flink/serializer.yaml, flink/redisClusterPool.yaml
        System.out.println(config);

        System.out.println("==========");
        List topic = (List) config2.get("file.topic.names");
        for (Object t : topic) {
            System.out.println(t);

        }
        System.out.println(topic);

        ArrayList<Map<String, String>> list = (ArrayList) config2.get("topology.kryo.register");
//        System.out.println(list.size());

        System.out.println(config.get("delimiter"));

//        Set<Map.Entry<String,String>> set = config.entrySet();
//        for (Map.Entry<String, String> entry : set) {
//            System.out.println(entry.getKey()+entry.getValue());
//        }
    }

    @Test
    public void test01() {
        List list = (List) config2.get("kafka.topic.names");
        System.out.println(list.get(1));
    }
}
