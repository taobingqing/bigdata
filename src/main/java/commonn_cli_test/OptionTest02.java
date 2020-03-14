package commonn_cli_test;

import org.apache.commons.cli.*;

/**
 * 程序执行顺序如下：
 *
 * 1、根据命令行参数定义Option对象；
 * 2、将Option对象添加到Options中；
 * 3、CommandLineParser解析传递给main方法的args得到CommandLine对象；
 * 4、根据CommandLine对象包含的命令行参数设定处理逻辑。
 *
 */
public class OptionTest02 {
    public static void main(String[] args) {
        args = new String[]{"-a", "6", "-s", "6", "-m", "10", "-d", "8"};

        // 根据命令行参数定义Option对象，第1/2/3/4个参数分别是指命令行参数名缩写、参数名全称、是否有参数值、参数描述
        Option opt1 = new Option("a", "add", true, "add operation");
        opt1.setRequired(false);
        Option opt2 = new Option("s", "subtract", true, "subbstract operation");
        opt2.setRequired(false);    // 设置该参数是否是必须的
        Option opt3 = new Option("m", "multiply", true, "multiply operation");
        opt3.setRequired(false);
        Option opt4 = new Option("d", "divide", true, "divide operation");
        opt4.setRequired(false);

        Options options = new Options();
        options.addOption(opt1);
        options.addOption(opt2);
        options.addOption(opt3);
        options.addOption(opt4);


        CommandLine cli = null;
       // CommandLineParser cliParser = new DefaultParser();
        //解析main 方法参数
        CommandLineParser cliParser = new PosixParser();

        HelpFormatter helpFormatter = new HelpFormatter();

        try {
            cli = cliParser.parse(options, args);
        } catch (ParseException e) {
            // 解析失败是用 HelpFormatter 打印 帮助信息
            helpFormatter.printHelp(">>>>>> test cli options", options);
            e.printStackTrace();
        }

        //根据不同参数执行不同逻辑
        int num = 6;
        if (cli.hasOption("a")) {
            int a = Integer.parseInt(cli.getOptionValue("a", "1"));  // 获取参数“a”对应的参数值，如果为空则返回1（默认值）
            System.out.println(String.format(">>>>>> add operation, %s+%s=%s", num, a, num + a));
        }
        if (cli.hasOption("s")) {
            int a = Integer.parseInt(cli.getOptionValue("s", "1"));
            System.out.println(String.format(">>>>>> subtract operation, %s-%s=%s", num, a, num - a));
        }
        if (cli.hasOption("m")) {
            int a = Integer.parseInt(cli.getOptionValue("m", "1"));
            System.out.println(String.format(">>>>>> multiply operation, %s*%s=%s", num, a, num * a));
        }
        if (cli.hasOption("d")) {
            int a = Integer.parseInt(cli.getOptionValue("d", "1"));
            System.out.println(String.format(">>>>>> divide operation, %s/%s=%s", num, a, num / a));
        }
    }
}
