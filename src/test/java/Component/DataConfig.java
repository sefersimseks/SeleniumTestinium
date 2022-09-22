package Component;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DataConfig {

    public static String PLATFORM;
    public static String LOGIN_ADDRESS;

    @SneakyThrows
    public void properties() {

        Properties p = new Properties();

        InputStream inputStream = new FileInputStream("dataConfig.properties");
        p.load(inputStream);
        LOGIN_ADDRESS = p.getProperty("LOGIN_ADDRESS");
        PLATFORM = p.getProperty("PLATFORM");
    }
}