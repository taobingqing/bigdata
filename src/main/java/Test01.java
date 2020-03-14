import clojure.lang.IFn;
import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.jcraft.jsch.HASH;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.kafka.common.utils.ByteUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import redis.clients.util.SafeEncoder;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;

public class Test01 {

//    public static void main(String[] args) {
//        String jsonstr = "[{\"name\": \"eco\", \"age\": \"21\"}]";
//
//        System.out.println(jsonstr);
//        //<editor-fold desc="Description">
//        String[] split = ",".split(",");
//        for (String s : split) {
//            System.out.println(s);
//        }
//        //</editor-fold>
//        System.out.println(split.length);
//
//        System.out.println("===============");
//        System.out.println(0xff);
//        System.out.println(0x80);
//        System.out.println(0x40);
//        System.out.println(0x10);
//        System.out.println(0x00);
//        System.out.println(0x00 & 0x40);
//
//        System.out.println("================");
//        System.out.println(test06());
//
//
//    }

    //保留几位小数
    public String scaleFloat(double f, int num) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(num, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    @Test
    public void test1() {
        // 转化为 毫秒
        long l = TimeUnit.MINUTES.toMillis(1);
        System.out.println(TimeUnit.SECONDS.toMillis(1));
        System.out.println(l);

        Double d = 2234d;
        String s = scaleFloat(d, 3);
        System.out.println("d:" + d + " s:" + s);

    }

    @Test
    public void Test02() {
        String s = "2019-08-28 15:39:45@#2019-08-28 15:15:00@#@#10.212.97.12@#Huawei@#PM-ENB-NSA-EUTRANCELLTDD-07-V1.0.2-20190828151500-15.csv.gz@#HW-CMZJ-HZ,SubNetwork=7,ManagedElement=28727,EnbFunction=1,EutranCellTdd=194@#EutranCellTdd@#@#V1.0.2@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@&&#";
        int length = s.split("@#", -1).length;
        System.out.println(length);
    }

    /**
     * indexOf 的使用
     */
    @Test
    public void test03() {
        String s = "aabbccdd";
        int bb = s.indexOf("bcc");
        System.out.println(bb);
    }

    /**
     * for(;;count++) 的用法
     */
    @Test
    public void test04() throws InterruptedException {
        int count = 0;
        for (; ; count++) {
            System.out.println(count);
            if (count > 20) {
                return;
            }
            TimeUnit.SECONDS.sleep(1);
        }

    }

    /**
     * 3__6_000 和 3600 一样
     */
    @Test
    public void test05() {
        System.out.println(2 * 3__6_000);
    }


    /**
     * return 直接跳出嵌套循环 不执行下面的语句
     *
     * @return
     */
    public static int test06() {
        while (true) {

            for (int i = 0; i < 10; i++) {
                try {
                    int j = 2 / i;
                    return i;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * Math.ceil 向上取整
     */

    @Test
    public void test07() {
        double ceil = Math.ceil(1 / 5f);
        System.out.println(ceil);

    }

    /**
     * 局部变量 存活范围在方法内
     */
    @Test
    public void test08() {
        final String a = "a";
        System.out.println(a);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String a = "b";
                System.out.println(a);
            }
        }).start();
    }

    @Test
    public void test09() {
        System.out.println(new Date());
        for (int i = 0; i < 1; i++) {

        }
        System.out.println("jie  shu ");
    }

    /**
     * Pattern的split的用法 正则切分
     */
    @Test
    public void test10() {
//        Pattern linePattern = Pattern.compile("(\n)|(\r\n)");
//        linePattern.split()

        String input = "This!!unusual use!!of exclamation!!points";
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input, 3)));


    }

    /**
     * Random 的用法
     * 种子的使用  指定随机数 每次生成的随机数相同  不指定的话根据系统时间指定 所以每次都是不一样的
     */
    @Test
    public void test11() {
        Random random1 = new Random(10240);
        System.out.println(random1.nextInt());
        System.out.println(random1.nextFloat());
        System.out.println(random1.nextBoolean());
        Random random2 = new Random(100);
        System.out.println(random2.nextInt());
        System.out.println(random2.nextFloat());
        System.out.println(random2.nextBoolean());

        Random random = new Random();
        System.out.println(random.nextInt(10240));


    }

    /**
     * 这种测试行不通,
     */
    @Test
    public void test12() {
        String data = "proxy-ef1443687ed69133d967aa996770244b";
        byte[] bytes = data.getBytes();
        String json = SafeEncoder.encode(bytes);
        CodisProxyInfo proxyInfo = new Gson().fromJson(json, CodisProxyInfo.class);
        String state = proxyInfo.getState();
        System.out.println(state);
    }

    @Test
    public void test13() {
        String[] strings = new String[3];
        String result = strings[2];
        System.out.println(result);
    }

    @Test
    public void test14() {
        String[] a = {"a", "b", "c", "", ""};
        String join = String.join("|", a);
        System.out.println(join);

        long l = TimeUnit.HOURS.toSeconds(1);
        System.out.println(l);

        System.out.println(new Date().getTime());

        System.out.println(1575006726000L / 1000 / 3600);

        String[] b = new String[3];
        b[1] = "";
        StringBuffer sb = new StringBuffer();
        StringBuffer append = sb.append(b[0]).append("|").append(b[1]).append("|").append(b[2]);
        System.out.println(append.toString());
    }

    /**
     * 测试 math.ceil();
     */
    @Test
    public void test15() {
        Date date = new Date();
        System.out.println(date);
        long a = 1575253010000L;
        long b = 1575187046000L;
        int c = 10800;
        System.out.println((a - b) / 1000 >= c);
        System.out.println(1575250413000L - 1575202634000L);

        String s1 = "a";
        String s2 = "b";
        System.out.println(!s1.equals(s2));

        double ceil = Math.ceil(4 * 1f / 10);
        System.out.println(40 * 1f);
        System.out.println(ceil);
        System.out.println(Math.ceil(8));

        int x = new Random(System.currentTimeMillis()).nextInt(64 + 1);
        System.out.println("x: " + x);
        int abs = Math.abs((x - 1) % 65);
        System.out.println("abs : " + abs);
    }

    /**
     * AtomicInteger 通过线程安全的方式操作加减   addAndGet会叠加
     */
    @Test
    public void test16() {
        AtomicInteger jedisIndex = new AtomicInteger(-1);
        System.out.println(jedisIndex.intValue());
        int i3 = jedisIndex.addAndGet(2);//加上给定的值 返回更新后的值
////        jedisIndex.addAndGet(3);
////        System.out.println(i1); //   -1
        int i1 = jedisIndex.incrementAndGet();//自增1  返回更新的值
        int i2 = jedisIndex.getAndIncrement();//自增1  返回以前的值
        System.out.println("i1: " + i1 + "  " + "i2  " + i2);

    }

    /**
     * 取 pid 和 tid
     */
    @Test
    public void test17() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());//  PID和hostname
        String pid = runtimeMXBean.getName().split("@")[0];
        String tid = String.valueOf(Thread.currentThread().getId());//线程 ID
        System.out.println("pid " + pid + "  tid " + tid);

        System.out.println(Matcher.quoteReplacement("\n"));
        String a = "a";
        assert false;
        System.out.println("走到这一步");
    }

    /**
     * 测试 hash
     */
    @Test
    public void test18() {
        Map<Integer, List<Integer>> result = new HashMap<>();
        int totalPartition = 1000;
        int parallelism = 10;
        for (int i = 0; i < totalPartition; i++) {
            List<Integer> list = result.get(i % parallelism);
            if (list == null) {
                list = new ArrayList<>();
                result.put(i % parallelism, list);
            }
            list.add(i);
        }

        System.out.println(result.size());
        System.out.println(result);

        HashFunction hashFunction = Hashing.murmur3_128();
        int hash = hashFunction.newHasher().putString("b", Charsets.UTF_8).hash().asInt();
        System.out.println("hash : " + hash);
        int partition = Math.abs(hash % totalPartition);
        System.out.println(partition);

        //hashcode值
        System.out.println(hashFunction.newHasher().putString("b", Charsets.UTF_8).hash());
    }

    /**
     * 比较 double 的大小
     */
    @Test
    public void test19() {
        String value = String.valueOf("222");
        Double d1 = Double.valueOf(value);
        System.out.println(d1);
        Double d2 = 222.0;
        System.out.println(d1.compareTo(d2));

        String format = new DecimalFormat("0").format(d2);
        System.out.println(format);

    }

    /**
     * 比较 两个string 的大小 两个double的大小
     */
    @Test
    public void test20() {
        String v1 = "abc";
        String v2 = "abc";
        System.out.println(v1.equals(v2));


        Double d1 = 2.0;// 2.0
        Double d2 = 2.1;
        System.out.println(d1);
        System.out.println(d1.compareTo(d2) != 0);

        if (!v1.equals(v2) || d1.compareTo(d2) != 0) {
            System.out.println("send   true");
        }


        String[] arr = new String[3];
        arr[1] = "0";
        int i = NumberUtils.toInt("");
        System.out.println(i);
        System.out.println(String.valueOf(i));
        //  System.out.println( Double.valueOf(arr[0]));
        // System.out.println(Double.parseDouble(""));

        System.out.println(arr[1] != null);
        // 第一个如果为null  则报 java.lang.NullPointerException 异常
        // System.out.println(arr[0].equals(arr[1]));

        StringBuffer sb = new StringBuffer();

        sb.append("a").append(arr[0]);

        System.out.println(sb.toString());
    }

    @Test
    public void test22() {

        int numPerReceiver = (int) Math.ceil(100 * 1f / 4);
        Map<Integer, List<Integer>> receiverPartitions = getReceiverPartitions(100, 10);
        for (Integer i : receiverPartitions.keySet()) {
            System.out.println(receiverPartitions.get(i));
        }
    }

    @Test
    public void test21() {
        String s = "kafkaconsumer.topic.name";
        String pre = "kafkaconsumer.";
        if (s.startsWith(pre)) {
            System.out.println(s.replace("kafkaconsumer.", ""));
            System.out.println(s.substring(pre.length()));
        }
    }

    /**
     * System.getProperty("user.dir")
     * <p>
     * 字符串转 json
     */
    @Test
    public void test23() {
        String s = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        System.out.println(s);

        String JAAS_POSTFIX = ".jass.conf";
        String jaasPath =
                new File(System.getProperty("java.io.tmpdir")) + File.separator + System.getProperty("user.name")
                        + JAAS_POSTFIX;
        System.out.println(jaasPath);

        String a = "b";
        String b = "a=" + "\"" + a + "\"";
        System.out.println(b);

        Map<String, String> getenv = System.getenv();
        String s1 = getenv.get("PWD");
        System.out.println("pwd " + s1);
        System.out.println(getenv.get("word.dir"));
        System.out.println("kafkaClient{" + "com.sun.security.auth.module.Krb5LoginModule required "
                + "useTicketCache=false "
                + "renewTicket=true "
                + "serviceName=\"kafka\" "
                + "useKeyTab=true "
                + "keyTab=" + "\"./kafka_kerberos\""
                + "principal=\"ZJ_ODC@HADOOP.COM\";};\n"
                + "Client{" + "com.sun.security.auth.module.Krb5LoginModule required "
                + "useTicketCache=false "
                + "renewTicket=true "
                + "serviceName=\"kafka\" "
                + "useKeyTab=true "
                + "keyTab=" + "\"./kafka_kerberos\""
                + "principal=\"ZJ_ODC@HADOOP.COM\";};");


    }

    /**
     * 遍历目录下所有文件内容
     */
    @Test
    public void test24() {
        List<File> files = traverseFolder1("E:\\a");
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }

    /**
     * 写文件到本地
     *
     * @throws Exception
     */
    @Test
    public void test25() throws Exception {
        String usrDir = System.getProperty("user.dir");
        System.out.println(usrDir);
        writeKeytabToLocal("/ue-flink-dev.yaml", usrDir + "\\keytab");
    }


    /**
     * list addall 用法 ：类型 要一致
     */
    @Test
    public void test26() {
        ArrayList<Object> list1 = new ArrayList<>();
        ArrayList<Object> list2 = new ArrayList<>();
        list1.add("2");
        list1.add("3");
        list2.add("a");
        list2.add("b");
        list1.addAll(list2);
        for (Object o : list1) {
            System.out.println(o);
        }

        System.out.println(0 + ((-1) * 0.1));
        System.out.println(1578632369396L - 1578632368280L);
        System.out.println(-1 * 0.1);
    }

    /**
     * 测试  AtomicInteger 的  incrementAndGet 和 set，
     */

    @Test
    public void test27() {
        Map map = new HashMap();
        map.put("1", 1);
        map.put("2", 2);

        AtomicInteger i = new AtomicInteger(0);
        for (int a = 0; a < 4; a++) {
            for (int s = 0; s < 6; s++) {
                int i1 = i.incrementAndGet();
                System.out.println(i1);
            }
            i.set(0);
        }


    }

    /**
     * throw 抛异常 不会继续往下走
     */
    @Test
    public void test28() {
        String s = null;
        checkArgument(s != null);
        System.out.println("走到这一步");
    }

    public class CodisProxyInfo {

        private String addr;

        private String state;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

    private Map<Integer, List<Integer>> getReceiverPartitions(int totalPartition, int parallelism) {
        Map<Integer, List<Integer>> result = new HashMap<>();
        for (int i = 0; i < totalPartition; i++) {
            List<Integer> list = result.get(i % parallelism);
            if (list == null) {
                list = new ArrayList<>();
                result.put(i % parallelism, list);
            }
            list.add(i);
        }
        return result;
    }

    public List<File> traverseFolder2(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        // System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        fileList.add(file2);
                        // System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return fileList;
    }

    public static List<File> traverseFolder1(String path) {
        List<File> fileList = new ArrayList<>();
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    // System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {
                    fileList.add(file2);
                    // System.out.println("文件:" + file2.getAbsolutePath());
                    fileNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        // System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;
                    } else {
                        fileList.add(file2);
                        // System.out.println("文件:" + file2.getAbsolutePath());
                        fileNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
        return fileList;
    }

    public String writeKeytabToLocal(String inPutPath, String outputPath) throws Exception {

        FileInputStream in = null;
        FileOutputStream fileOutputStream = null;
        try {

            FileInputStream resourceAsStream = (FileInputStream) this.getClass().getResourceAsStream(inPutPath);
            in = resourceAsStream;
            //   in = new FileInputStream(inPutPath);
            fileOutputStream = new FileOutputStream(outputPath);

            byte[] bytes = new byte[2048];
            int len = 0;
            while ((len = in.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            fileOutputStream.close();
        }

        return outputPath;


    }
}
