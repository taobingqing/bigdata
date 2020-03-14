package com.yaml;


import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Yaml工具类
 */
public class YamlUtil {

    /**
     * 加载文件
     *
     * @param path: flux/voice-topology.yaml
     * @return
     * @throws IOException
     */
    public static Map load(String path) throws IOException {
        Yaml yaml = new Yaml();
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
            Map map = (Map) yaml.load(is);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
