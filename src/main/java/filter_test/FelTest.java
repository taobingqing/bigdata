package filter_test;

import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.FelContext;
import org.apache.hadoop.fs.shell.Count;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FelTest {

    @Test
    public void test1() {
//        Long result = (Long) AviatorEvaluator.execute("1+2+3");
//        System.out.println(result);
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("username", "Bob");
//        System.out.println(AviatorEvaluator.execute("'hello: ' + username  + '!'", map));

        Object execute = null;

        long startTime = System.currentTimeMillis();

        String expression = "a == 'aaa'&& b <10 ";
        // 编译表达式
        com.googlecode.aviator.Expression compiledExp = AviatorEvaluator.compile(expression,true);


        Map<String, Object> map = new HashMap<String, Object>();
        String s = "aaa|bb|123";
        String[] split = s.split("\\|");
        //System.out.println(split[1]);
        for (int count = 0; count < 100000000; count++) {
            map.put("a", split[0]);
            map.put("b", Integer.parseInt(split[2]));
            //execute = AviatorEvaluator.execute("a == 'aaa'&& b <10 ", map);
             execute = compiledExp.execute(map);
        }
        long useTime = System.currentTimeMillis() - startTime;
        System.out.println(useTime);
        System.out.println(execute);
    }

    @Test
    public void test2() {
        Object eval = null;

        String s = "aaa|bb|123";
        String[] split = s.split("\\|");

        long startTime = System.currentTimeMillis();

        FelEngine e = FelEngine.instance;
        //获取引擎
        FelContext ctx = e.getContext();
        String expression = "a == a && b <10";
//        ctx.set("a","a");
//        ctx.set("b",2);
        //编译表达式
        Expression compile = e.compile(expression, ctx);

//        for (int count = 0; count < 100000000; count++) {
            ctx.set("a", split[0]);
            ctx.set("b", Integer.parseInt(split[2]));
            eval = compile.eval(ctx);
            //eval =  compile.eval(ctx);
            //System.out.println(eval);
//        }
        long useTime = System.currentTimeMillis() - startTime;
        System.out.println(useTime);
        System.out.println(eval);
        //解释执行不支持
        //boolean eval = (Boolean) e.eval(expression, ctx);

    }

    @Test
    public void felTest() {

        FelEngine e = FelEngine.instance;
        final FelContext ctx = e.getContext();
        ctx.set("a", 3600);
        ctx.set("b", 14);
        ctx.set("c", 5);
        com.greenpineyu.fel.Expression exp = e.compile("a*b*c", ctx);
        long start = System.currentTimeMillis();
        Object eval = null;
        for (int i = 0; i < 100000; i++) {
            eval = exp.eval(ctx);
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(eval);
    }



}
