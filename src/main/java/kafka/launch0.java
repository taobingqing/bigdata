package kafka;

/**
 * @ClassName launch0
 * Description TODO
 * @Author wangjm
 * Date 2019/6/10 14:51
 * @Version 1.0
 */
public class launch0 {
    public static void main(String[] args){
        String info = "voice_infost";
        new Thread(new kbu_KafkaProducer(ProducerLauncher.infost,info,2)).start();
    }
}


