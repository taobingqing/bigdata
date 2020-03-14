package filter_test;

import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.FelContext;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by dccsc on 2019/6/15.
 */
public class testExpression {

    @Test
    public void testFel() {
        int count = 10000000;//100w
        boolean flag = false;
        String string = "10000001|csc|1|26|hena";
        String[] str = string.split("\\|");

        long start = System.currentTimeMillis();
        FelEngine fel = FelEngine.instance;
        FelContext ctx = fel.getContext();
        ctx.set("sex", "1");
        ctx.set("age", 20);
        //sex==1 && (age > 20 && age < 30)
        Expression exp = fel.compile("sex=='1' && (age > 20 && age < 30)", ctx);
        System.out.println("编译表达式用时：" + (System.currentTimeMillis() - start));
        System.out.println("=============");
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            ctx.set("sex", str[2]);
            ctx.set("age", Integer.parseInt(str[3]));
            boolean eval = (Boolean) exp.eval(ctx);
            flag = eval;

        }

        System.out.println("执行表达式用时：" + (System.currentTimeMillis() - start1));
        System.out.println("总用时：" + (System.currentTimeMillis() - start));
        System.out.println(flag);
    }

    @Test
    public void testAviator() {
        boolean flag = false;
        int count = 10000000;//100w

        String string = "10000001|csc|1|26|hena";
        String[] str = string.split("\\|");
        String expr = "sex=='1' && (age > 20 && age < 30) ";
        long start = System.currentTimeMillis();
        com.googlecode.aviator.Expression expression = AviatorEvaluator.compile(expr, true);
        System.out.println("编译表达式用时：" + (System.currentTimeMillis() - start));

        long start1 = System.currentTimeMillis();
        Map<String, Object> env = new HashMap<String, Object>();
        for (int i = 0; i < count; i++) {
            env.put("sex", str[2]);
            env.put("age", Integer.parseInt(str[3]));
            flag = (Boolean) expression.execute(env);
        }
        System.out.println("执行用时：" + (System.currentTimeMillis() - start1));
        System.out.println("总用时：" + (System.currentTimeMillis() - start));
        System.out.println(flag);


    }


    //===================================================================================================
    @Test
    public void test1() {

        Object execute = null;
        long startTime = System.currentTimeMillis();

        String expression = "a == 'aaa'&& b <10 ";
        // 编译表达式
        com.googlecode.aviator.Expression compiledExp = AviatorEvaluator.compile(expression, true);

        Map<String, Object> map = new HashMap<String, Object>();
        String s = "aaa|bb|123";
        String[] split = s.split("\\|");
        //System.out.println(split[1]);
        //long startTime = System.currentTimeMillis();
        for (int count = 0; count < 100000; count++) {
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

        long startTime = System.currentTimeMillis();
        String s = "aaa|bb|123";
        String[] split = s.split("\\|");
        FelEngine e = FelEngine.instance;
        //获取引擎
        FelContext ctx = e.getContext();
        String expression = "a == 'aaa' && b <10";
        ctx.set("a", "a");
        ctx.set("b", 2);
        //编译表达式
        Expression compile = e.compile(expression, ctx);
        //long startTime = System.currentTimeMillis();
        for (int count = 0; count < 100000; count++) {
            ctx.set("a", split[0]);
            ctx.set("b", Integer.parseInt(split[2]));
            eval = compile.eval(ctx);
            //eval =  compile.eval(ctx);
            //System.out.println(eval);
        }
        long useTime = System.currentTimeMillis() - startTime;
        System.out.println(useTime);
        System.out.println(eval);


    }

}
