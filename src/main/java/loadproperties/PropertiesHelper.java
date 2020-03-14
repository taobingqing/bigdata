package loadproperties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Properties;

/**
 * kafka Helper
 *
 * @Auther: zhangys
 * @Date: 2018/8/21 09:09
 * @Description:
 */
public class PropertiesHelper {

    private static Logger logger = Logger.getLogger(PropertiesHelper.class);

    /**
     * 属性前缀过滤器
     *
     * @param properties
     * @param prefix     前缀
     * @return
     */
    public static Properties filterConfig(Properties properties, String prefix) {
        Properties prop = new Properties();
        Iterator<Object> iterator = properties.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            if (key.startsWith(prefix)) {
                prop.setProperty(key.replace(prefix, ""), properties.getProperty(key));
            }
        }
        return prop;
    }

    /**
     * 加载文件
     *
     * @param clazz
     * @param path
     * @return
     */
    // 加 / 从根类路径下寻找
    public static Properties loadFile(Class clazz, String path) {
        InputStream is = null;
        try {
            is = clazz.getResourceAsStream(path);
            Properties prop = new Properties();
            prop.load(is);
            return prop;
        } catch (IOException e) {
            logger.error("Error", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("Error", e);
                }
            }
        }
        return null;
    }

    /**
     * 加本地载Yaml文件
     *
     * @param path
     * @return
     */
    public static Properties loadLocalFile(String path) {
        InputStream is = null;
        try {
            is = new FileInputStream(path);
            Properties prop = new Properties();
            prop.load(is);
            return prop;
        } catch (IOException e) {
            logger.error("Error", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("Error", e);
                }
            }
        }
        return null;
    }

    public static void loadClasspathFile(Class clazz, String path)  {
        URL resource = clazz.getClassLoader().getResource(path);
        //获取 文件的 路径  /D:/idea_files/bigdata/target/classes/conf/conf-test01
        File file = new File(resource.getFile());
        //D:\idea_files\bigdata\target\classes\conf\conf-test01
        //System.out.println(file.getAbsoluteFile());
        //D:\idea_files\bigdata\target\classes\conf
        //System.out.println(file.getParentFile());


        //判断是否为目录，并列出目录下所有文件
//        if (file.isDirectory()) {
////            File[] files = file.listFiles();
////            for (File f : files) {
////                System.out.println(f.getName());
////            }
////        }
        if (file.isFile()) {
            try (FileInputStream fileInputStream = new FileInputStream(file);

            ) {

                byte[] buf = new byte[1024];
                int i ;
                while ((i = fileInputStream.read(buf))!=-1){
                }
                System.out.println(StringUtils.toString(buf,"utf-8"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //path 为相对路径 或者 包路径
    public static Properties getProperties(Class clazz, String path){
        InputStream is = clazz.getClassLoader().getResourceAsStream(path);
        Properties prop = new Properties();
        try {
            prop.load(is);
            return prop;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) throws ClassNotFoundException {
        Properties properties = loadFile(PropertiesHelper.class, "/conf/conf-test01");
        String name = properties.getProperty("aaa");
        System.out.println(name);

        System.out.println("=======================");
        loadClasspathFile(PropertiesHelper.class, "conf/conf-test01");

        System.out.println("=======================");
        //Class.forName("loadproperties.PropertiesHelper" 要加 包名
        Properties properties1 = getProperties(Class.forName("loadproperties.PropertiesHelper"), "conf/conf-test01");
        String aaa = properties1.getProperty("aaa");
        System.out.println(aaa);
    }
}

