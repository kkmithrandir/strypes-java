package configurations;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configurationProperties {

    public static Properties property;
    private static String configPath = "src\\test\\java\\configurations\\configuration.properties";

    public static void initializePropertyFile()
    {
        property = new Properties();
        try {
            InputStream instm = new FileInputStream(configPath);
            property.load(instm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

