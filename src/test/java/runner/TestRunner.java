package runner;
import configurations.configurationProperties;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src\\test\\java\\features",
        glue = {"utility", "pages"},
        plugin ={"pretty","html:target/cucumber-html-report","json:cucumber.json"},
        tags = "@testing",
        publish = true
)
public class TestRunner {
    public static WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        configurationProperties.initializePropertyFile();
    }

    @AfterClass
    public static void afterClass() {driver.quit();}

}
