package jvmTest;

public class MemoryDemo01 {

    public static void main(String[] args) {

        long max = Runtime.getRuntime().maxMemory(); // -Xmx 最大可用内存
        long freeMemory = Runtime.getRuntime().freeMemory(); //jvm 空闲内存
        long totalMemory = Runtime.getRuntime().totalMemory(); //当前JVM占用的内存总数，其值相当于当前JVM已使用的内存及freeMemory()的总和
        System.out.println(String.format("max :{%s},freeMemory: {%s},totoalMemory:{%s}", max, freeMemory, totalMemory));

        System.gc();
        //gc 之后的内存情况
        max = Runtime.getRuntime().maxMemory();
        freeMemory = Runtime.getRuntime().freeMemory();
        totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println(String.format("max :{%s},freeMemory: {%s},totoalMemory:{%s}", max, freeMemory, totalMemory));



    }
}
