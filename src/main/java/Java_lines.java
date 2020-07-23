import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Java_lines {
    public static int i;
    public static void main(String[] args) throws Exception{
        File file = new File("D:\\idea_files\\PMSpark");
        lineCount(file);
        System.out.println(i);
    }
    public static void lineCount(File file)throws Exception{
        File[] files = file.listFiles();
        for (File f : files) {
            if(f.isFile()&&f.getName().endsWith(".java")){
                BufferedReader br = new BufferedReader(new FileReader(f));
                String str;
                while ((str=br.readLine())!=null){
                    i++;
                }

            }else if(f.isDirectory()) {
                lineCount(f);
            }
        }
    }
}
