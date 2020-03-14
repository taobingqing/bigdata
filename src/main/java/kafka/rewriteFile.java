package kafka;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;

/**
 * @ClassName rewriteFile
 * Description TODO
 * @Author wangjm
 * Date 2019/6/11 9:20
 * @Version 1.0
 */
public class rewriteFile {

    public static void rewrite(String p) throws Exception {
        File file = new File(p);
        if (!file.exists()) {
            System.out.println("file:" + p + " does not exit");
            return;
        }
        InputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line;
//        Producer<String, String> producer = genarateProducer();
        int ind = 0;
        while ((line = br.readLine()) != null) {
            //要将原数据中的逗号分隔符换成竖线
            String[] splits = line.split(",");
            String str = "";
            for (int i = 0; i < splits.length; i++) {
                if (i == splits.length - 1) {
                    str += splits[i];
                } else {
                    str += splits[i] + "|";
                }
            }

            System.out.println(ind + " :  " + str);
            ind++;
        }
        br.close();
        fis.close();
    }

    public static void main(String[] args) {
        try {
            rewrite(ProducerLauncher.sipcall);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
