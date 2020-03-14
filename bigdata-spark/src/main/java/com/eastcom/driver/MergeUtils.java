package com.eastcom.driver;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class MergeUtils {



    public static boolean copyMerge(FileSystem srcFS, Path srcDir,
                                    FileSystem dstFS, Path dstFile,
                                    boolean deleteSource,
                                    Configuration conf, String addString) throws IOException {

        if (!srcFS.getFileStatus(srcDir).isDirectory())
            return false;

        OutputStream out = dstFS.append(dstFile);

        try {
            FileStatus contents[] = srcFS.listStatus(srcDir);
            Arrays.sort(contents);
            for (int i = 0; i < contents.length; i++) {
                if (contents[i].isFile()) {
                    InputStream in = srcFS.open(contents[i].getPath());
                    try {
                        IOUtils.copyBytes(in, out, conf, false);
                        if (addString!=null)
                            out.write(addString.getBytes("UTF-8"));

                    } finally {
                        in.close();
                    }
                }
            }
        } finally {
            out.close();
        }


        if (deleteSource) {
            return srcFS.delete(srcDir, true);
        } else {
            return true;
        }
    }
}
