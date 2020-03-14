package filter_test.main;

import com.googlecode.aviator.AviatorEvaluator;
import com.greenpineyu.fel.Expression;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.context.FelContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainTest {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        String s = "aaa|bb|123";
        String[] split = s.split("\\|");

        boolean b = false;
        for (int i = 0; i < 1000; i++) {
            b = doFilter2(split);
        }

        System.out.println(b);
        long l = System.currentTimeMillis() - startTime;
        System.out.println(l);

//        String a ="男";
//        System.out.println(a == ("男"));

//        String string1="aaa" ;
//        //String string2=new String( "aaa" );
//        String string2=new String[]{"aaa"}[0];
//        System.out.println(string2);
//        System.out.println(string1==string2);

        System.out.println(new Random().nextInt(10)+1);
    }

    private static boolean doFilter(String[] content) {
        boolean eval = false;

        FelEngine e = FelEngine.instance;
        //获取引擎
        FelContext ctx = e.getContext();

        ctx.set("a", "a");
        ctx.set("b", 2);
        //编译表达式
        String expression = "a == 'aaa' && b <10";
        Expression compile = e.compile(expression, ctx);

        ctx.set("a", content[0]);
        ctx.set("b", Integer.parseInt(content[2]));

        eval = (Boolean) compile.eval(ctx);

        return eval;

    }

    public static boolean doFilter2(String[] content) {
        String expression = "a == 'aaa'&& b <10 ";
        // 编译表达式
        com.googlecode.aviator.Expression compiledExp = AviatorEvaluator.compile(expression,true);
        boolean execute = false;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", content[0]);
        map.put("b", Integer.parseInt(content[2]));
        //execute = AviatorEvaluator.execute("a == 'aaa'&& b <10 ", map);
        execute = (Boolean) compiledExp.execute(map);

        return execute;
    }

}
