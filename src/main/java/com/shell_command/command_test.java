package com.shell_command;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class command_test {
    private final static Logger logger = Logger.getLogger(command_test.class);
    public static void main(String[] args) throws Exception {
        String command = "";

        String[] cmdArray = new String[]{"/bin/sh", "-c", command};
        Process process = Runtime.getRuntime().exec(cmdArray);
        logger.warn("[i]  executing command : " + command);
        if (process.waitFor() != 0) {
            InputStreamReader inputStreamReader = new InputStreamReader(process.getErrorStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String result;
            StringBuffer tmp = new StringBuffer();
            while ((result = bufferedReader.readLine()) != null) {
                tmp.append(result);
            }
            throw new Exception(tmp.toString());
        } else {
            logger.warn("[i]  publish MQ!");
        }
    }
}
