package kafka;

/**
 * @ClassName ProducerLauncher
 * Description TODO
 * @Author wangjm
 * Date 2019/6/10 14:21
 * @Version 1.0
 */
public class ProducerLauncher {
    public static String infost = "E:\\eastcom\\0611infost&sipcall\\0619\\voice_infost0619.txt";
    public static String sipcall = "E:\\eastcom\\0611infost&sipcall\\0619\\sipcall0619.txt";
    public static String groupid = "data9";
    public static void main(String[] args) throws Exception {
        new Thread(new kbu_KafkaProducer(sipcall, "sipcall", 2)).start();
//        new Thread(new kbu_KafkaProducer(infost, "voice_infost",2000)).start();
//        new kbu_KafkaProducer(sipcall, "sipcall", 1000).run();
//        new kbu_KafkaProducer(infost, "voice_infost",2000).run();
    }

}
