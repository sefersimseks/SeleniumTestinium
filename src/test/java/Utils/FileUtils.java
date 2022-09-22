package Utils;

import java.io.File;

public class FileUtils {


    public static String getAbsolutePath(String path){

        File file = new File(path);
        System.out.println(file.getAbsolutePath());
        return file.getAbsolutePath();
    }
}
