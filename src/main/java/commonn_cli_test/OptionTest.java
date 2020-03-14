package commonn_cli_test;

import org.apache.commons.cli.*;

import java.util.Date;

public class OptionTest {

    public static void main(String[] args) {

        args = new String[]{"-t", "rensanning", "-f", "c:/aa.txt", "-b", "-s10", "-Dkey1=value1", "-Dkey2=value2" };
        try {
            // create Options object
            Options options = new Options();
            options.addOption(new Option("t", "text", true, "use given information(String)"));
            options.addOption(new Option("b", false, "display current time(boolean)"));
            options.addOption(new Option("s", "size", true, "use given size(Integer)"));
            options.addOption(new Option("f", "file", true, "use given file(File)"));

            @SuppressWarnings("static-access")
            Option property = OptionBuilder.withArgName("property=value")
                    .hasArgs(2)
                    .withValueSeparator()
                    .withDescription("use value for given property(property=value)")
                    .create("D");
            property.setRequired(true);
            options.addOption(property);

            // print usage
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "AntOptsCommonsCLI", options );
            System.out.println();

            // create the command line parser
            CommandLineParser parser = new PosixParser();
            CommandLine cmd = parser.parse(options, args);

            // check the options have been set correctly
            System.out.println(cmd.getOptionValue("t"));
            System.out.println(cmd.getOptionValue("f"));
            if (cmd.hasOption("b")) {
                System.out.println(new Date());
            }
          //  System.out.println(cmd.getOptionValue("b")); null
            System.out.println(cmd.getOptionValue( "s" ));
            System.out.println(cmd.getOptionProperties("D").getProperty("key1") );
            System.out.println(cmd.getOptionProperties("D").getProperty("key2") );

        } catch (Exception ex) {
            System.out.println( "Unexpected exception:" + ex.getMessage() );
        }

    }
}
