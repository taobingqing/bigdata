package filter_test;

import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.ArrayCtxImpl;
import com.greenpineyu.fel.context.FelContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 */
public class PerformanceTest {

    public static void main(String[] args) {
        speed();
    }

    /**
     * 测试性能
     */
    private static void speed() {
        final Map<String, Object> vars = new HashMap<String, Object>();
        Random r = new Random();
        vars.put("i", 100);
        vars.put("pi", 3.14d);
//        vars.put("i", r.nextInt(100));
//        vars.put("pi", r.nextDouble());

        vars.put("d", -3.9);
        vars.put("b", (byte) 4);
        vars.put("bool", false);
        final Map<String, Integer> m = new HashMap<String, Integer>();
        m.put("d", 6);
        vars.put("m", m);
        vars.put("s", "hello world");



        String[] exps = new String[10];

        int index = 0;
        exps[index++] = "1000+100.0*99-(600-3*15)%(((68-9)-3)*2-100)+10000%7*71";
        exps[index++] = "i * pi + (d * b - 199) / (1 - d * pi) - (2 + 100 - i / pi) % 99 ==i * pi + (d * b - 199) / (1 - d * pi) - (2 + 100 - i / pi) % 99 ";
        exps[index++] = "pi*d+b-(1000-d*b/pi)/(pi+99-i*d)-i*pi*d/b";
        exps[index++] = "i > 0 && pi > 3";
        //切割字符串表达式写法不同，分开测试
        //exps[index++] = "s.substring(m.d)"; //fel-----240ms
        //exps[index++] = "string.substring(s,m.d)"; //aviator---1767ms

        //包含字符串测试
       // exps[index++]="s.contains('orl')";//fel----77ms
        //exps[index++]="string.contains(s,'orl')";//aviator---470ms


        exps[index++]="s=='hello world'";

        int times = 1000 * 10000;
        for (String exp : exps) {
            if (exp == null) {
                break;
            }
            fel(exp, vars, times);
            aviator(exp,vars, times);
        }
    }




    ////////////////aviator
    public static void aviator(String exp,Map<String, Object> vars, int times) {
        com.googlecode.aviator.Expression expression = AviatorEvaluator.compile(exp,true);
        //开始时间
        long start = System.currentTimeMillis();
        Object result = null;
        int i = 0;
        while(i++ < times) {
            result = expression.execute(vars);
        }
        //结束时间
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("aviator-----costTime[" + cost + " ]---value[" + result
                + "]------exp[" + exp + "]");
    }



///////////////////////////////fel/////////////////////////////////////////////////////
    private static void fel(String exp, Map<String, Object> vars, int times) {
        FelContext ctx = new ArrayCtxImpl(vars);
        fel(exp, ctx, times);
    }

    private static long fel(String exp, FelContext ctx, int times) {
        FelEngine engine = new FelEngineImpl();
        Expression expObj = engine.compile(exp, ctx);
        //Object evalResult = null;
        //开始时间
        long start = System.currentTimeMillis();
        Object result = null;
        int i = 0;
        while (i++ < times) {
            result = expObj.eval(ctx);
        }
        //结束时间
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println("fel --------costTime[" + cost + " ]---value[" + result
                + "] ------exp[" + exp + "]");
        return cost;
    }
}
