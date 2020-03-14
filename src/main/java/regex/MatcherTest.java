package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherTest {
    public static void main(String[] args) {
        /**
         *当正则完全匹配字符串，从头到尾正好匹配上字符串，matches()方法是true，find()方法为false
         *
         * 当正则只能匹配字符串中的部分内容，matches()方法是fasle ,find()方法是true
         */
        partTest();
        allTest();

    }

    private static void partTest() {
        Pattern pattern = Pattern.compile("abc");
        Matcher matcher = pattern.matcher("abcd");
        if(matcher.matches()){
            System.out.println("matcher.matches() == true");
            /**
             * wo 替换了 abc 所以结果是 wod
             */
            System.out.println(matcher.replaceFirst("wo"));
        }else{
            System.out.println("matcher.matches() == false");
            System.out.println(matcher.replaceFirst("wo"));
            System.out.println(matcher.group());
        }

        if(matcher.find()){
            System.out.println("matcher.find() == true");
        }else{
            System.out.println("matcher.find() == false");
        }
    }

    public static void allTest(){

        Pattern pattern = Pattern.compile("abcd");
        Matcher matcher = pattern.matcher("abcd");
        if(matcher.matches()){
            System.out.println("matcher.matches() == true");
        }else{
            System.out.println("matcher.matches() == false");
        }

        if(matcher.find()){
            System.out.println("matcher.find() == true");
        }else{
            System.out.println("matcher.find() == false");
        }
    }



}
