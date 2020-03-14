package generate_userid;

import org.junit.Test;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class test01 {
    public static String getNewUserId() {
        String ipAddress = "";
        try {
            //获取服务器IP地址
            ipAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println("ipAddress =="+ ipAddress);
        } catch (Exception e) {
            System.out.println("getNewUserId=" + e.getMessage());
        }
        //获取UUID
        String uuid = ipAddress + "$" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        System.out.println("ipaddress + uuid =="+uuid);
        //生成后缀
        long suffix = Math.abs(uuid.hashCode() % 100000000);
        System.out.println("uuid.hashCode() =="+ uuid.hashCode());

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        System.out.println("time == "+ time);
        //生成前缀
        long prefix = Long.parseLong(time) * 100000000;
        System.out.println("prefix == " + prefix);
        String userId = String.valueOf(prefix + suffix);
        return userId;
    }

    public static void main(String[] args) {
        String newUserId = getNewUserId();
        System.out.println(newUserId);
    }

    @Test
    public void test(){

        String s = UUID.randomUUID().toString().replaceAll("-","");
        //取9位数
        long a = s.hashCode() % 10000000000L;
        System.out.println(s.hashCode()+"|"+a);
        long abs = Math.abs(a);


        System.out.println(abs);

        System.out.println(56 % 100);
    }
}
