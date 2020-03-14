package regex;

import org.junit.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

    public static void main(String args[]) {
        String str = "$1 > 20 || $2 =='a'  && $$ !=20 || $15 =='aa'";
        String pattern = "\\$[0-9]+";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        //System.out.println(m.matches());
        System.out.println("=============");
        while (m.find()) {
            System.out.println(m.group(0));
            //System.out.println(m.group(1));    IndexOutOfBoundsException
        }

        ArrayList<Integer> objects = new ArrayList<Integer>();
        objects.add(1);
        objects.add(2);

        for (int object : objects) {
            System.out.println(object + 1);

        }

//        if (m.find()){
//            System.out.println(m.group(0));
//            System.out.println(m.group(1));
//            System.out.println(m.group(2));
//        }
    }

    @Test
    public void test01() {
        Pattern pattern = Pattern.compile("^#!\\s*(\\S.*?)\\s*$");
        Matcher matcher = pattern.matcher("#! imei:tacs");

//        if(matcher.find()){
//            System.out.println(matcher.group(1));
//        }

        while (matcher.find()) {
            System.out.println("Group 0:" + matcher.group(0));//得到第0组——整个匹配
            System.out.println("Group 1:" + matcher.group(1));//得到第一组匹配——与(or)匹配的
            System.out.println("Start 0:" + matcher.start(0) + " End 0:" + matcher.end(0));//总匹配的索引
            System.out.println("Start 1:" + matcher.start(1) + " End 1:" + matcher.end(1));//第一组匹配的索引
            System.out.println("#! imei:tacs".substring(matcher.start(0), matcher.end(1)));//从总匹配开始索引到第1组匹配的结束索引之间子串——Wor
        }
    }

    @Test
    public void test02() {

        Pattern compile = Pattern.compile("\\(([\\w*,{0,1}]*)\\)");
        long start = System.currentTimeMillis();
        String input = "98783|25288492|610|244|110032110|2020-01-07T12:42:20.032|{(33,13,20,6,39148,359,38544,257,1,32,NIL,20,13,0,0,0,0,0,0,31,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL),(33,12,20,5,39148,359,38544,176,1,32,NIL,20,13,0,0,0,0,0,0,31,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL),(33,9,20,0,39148,359,38544,231,1,32,NIL,20,13,0,0,0,0,0,0,31,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL)}|{(NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL,NIL)}|{(NIL)}|3|1|0";
        //   for (int count = 0; count < 1000000; count++) {
        String[] split3 = input.split("\\|");
        Matcher matcher = compile.matcher(split3[6]);

        while (matcher.find()) {
            String group = matcher.group(1);
            System.out.println(group);
            String replace = group.replace(",", "|");
//            String group1 = matcher.group(1);
//            String group2 = matcher.group(2);
//            System.out.println("1 : "+ group+" 2: "+group1+" 3 :"+ group2);
            // System.out.println(matcher.group(0));
            // System.out.println( matcher.group(1));
            //     }
        }

        System.out.println("正则匹配耗时： " + (System.currentTimeMillis() - start));


        // 通过字符串拆分来获取值
        long start1 = System.currentTimeMillis();

        // for (int count = 0; count < 1000000; count++) {

        String[] split2 = input.split("\\|");

        if (Integer.parseInt(split2[10])>0){
            int i = split2[7].indexOf("{");
            int i1 = split2[7].indexOf("}");
            // System.out.println(i);
            //System.out.println(i1);
            String substring = split2[7].substring(i + 1, i1);
            //System.out.println(substring);
            String[] split = substring.split("\\)", -1);
            for (String s : split) {
                // System.out.println(s);
                String replace = s.replaceAll(",?\\(", "");
                // System.out.println(replace);
                String[] split1 = replace.split(",");
                System.out.println(String.join("|", split1));
            }
            // }
        }


        System.out.println("直接拆分耗时： " + (System.currentTimeMillis() - start1));


    }


}