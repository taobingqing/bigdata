package MD5;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Test {
    @Test
    public void test01(){
        try {
            String a = md5Digest("aaaa");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void Md5UtilTest(){

        String s = MD5Util.MD5_32("12345", "gbk");
        System.out.println(s);


        byte[] bytes = "1234af".getBytes();
        String s1 = byte2hex(bytes);
        System.out.println(s1);
    }

    @Test
    public void appacheMd5Util(){
        String a = DigestUtils.md5Hex("1");
        System.out.println(a);
        try {
            String a1 = md5Digest("1");
            System.out.println(a1);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String md5Digest(String str) throws NoSuchAlgorithmException {

        String temp;
        //大小写没有关系
        MessageDigest alg = MessageDigest.getInstance("MD5");

        alg.update(str.getBytes());
        //转换成16个字节
        byte[] digest = alg.digest();
        System.out.println(digest);
        temp = byte2hex(digest);
        return temp;

    }
    public String byte2hex(byte[] bytes) {

        String hs = "";
        String stmp = "";

        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
            //每个字节和 16进制做为运算 自动转化成int 32位 变成两个字符
            stmp = Integer.toHexString(bytes[i] & 0XFF);
            System.out.println("stmp = "+stmp);

            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();

    }

}
