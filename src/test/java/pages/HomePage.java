package pages;

import configurations.configurationProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static runner.TestRunner.driver;

public class HomePage {
    public static final String WIDGET_ROOT   = "//div[contains(@class,'elementor-widget-container')]";
    public static final String HOMEPAGE_ROOT = "(" + WIDGET_ROOT + "//a//img[contains(@alt,'Strypes logo')])[1]";
    public static final String POLICY_ACCEPT = "/html/body/div[3]/div/div/div/div[2]/button[3]";
    public static final String CHATBOT_CLOSE  = "/html/body/div[2]/div[1]/div/div/button";

    public static final String DISCLAIMER   = WIDGET_ROOT + "[contains(.,'{LABEL}')]";
    public static final String NAV_ITEM     = "(//section[2]//li//a[contains(@class,'elementor-item menu-link')][contains(text(),'{LABEL}')])[1]";
    public static final String NAV_SUB_ITEM = "(//section[2]//li//a[contains(@class,'elementor-sub-item menu-link')][contains(text(),'{LABEL}')])[1]";

    public static final String SLIDER_COUNT = "//div[contains(@aria-label,'{COUNT}')][1]";
    public static final String SLIDER_TEXT  = SLIDER_COUNT + "//div[contains(@class,'elementor-slide-heading')][contains(text(),'{VALUE}')]";
    public static final String SLIDER_DESC  = SLIDER_COUNT + "//div[contains(@class,'elementor-slide-description')][contains(text(),'{VALUE}')]";
    public static final String SLIDER_BTN   = "(//a[contains(@class,'elementor-button elementor-slide-button elementor-size-md')])[1][contains(text(),'{LABEL}')]";

    public static final String CAREER_DROPDOWN  = "//select[contains(@class,'facetwp-dropdown')]";
    public static final String CAREER_TITLE     = WIDGET_ROOT + "//h2[contains(.,'{LABEL}')]";
    public static final String CAREER_OPTION    = CAREER_DROPDOWN + "//option[contains(.,'{OPTION}')]";
    public static final String CAREER_POSITIONS = "//article[contains(@class,'elementor-post')]//h2//a";

    public static final String CONNECT_ITEMS = "//span[contains(@class,'elementor-grid-item')]";
    public static final String CONNECT_TITLE = CONNECT_ITEMS + "//span[contains(.,'{LABEL}')]";
    public static final String CONNECT_HREF  = CONNECT_ITEMS + "//a[span[contains(.,'{LABEL}')]]";


    @Given("User navigates to home page")
    public void user_navigates_to_home_page() {
        if (configurationProperties.property.getProperty("BrowserType").equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (configurationProperties.property.getProperty("BrowserType").equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.navigate().to(configurationProperties.property.getProperty("BaseUrl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String correctUrl = driver.getCurrentUrl();
        assertEquals("https://strypes.eu/", correctUrl);
        System.out.println("*** url is correct ***");
        driver.findElement(By.xpath(HOMEPAGE_ROOT));
        System.out.println("*** banner is there ***");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement image = driver.findElement(By.tagName("img"));
        boolean state = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete" + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", image);
        if (state) {
            System.out.println("*** image is present ***");
        } else {
            System.out.println("*** image is not present ***");
        }
        Assert.assertTrue(state);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(POLICY_ACCEPT)).click();
        System.out.println("*** cookie policy has been accepted ***");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.switchTo().frame("hubspot-conversations-iframe");
        driver.findElement(By.xpath(CHATBOT_CLOSE)).click();
        System.out.println("*** chatbot has been closed ***");
        driver.switchTo().defaultContent();
    }


    @And("^User (sees|clicks|hovers) \"([^\"]*)\" navigation menu item$")
    public void user_sees_string_navigation_menu_item(String action, String label) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        switch (action) {
            case "sees":
                driver.findElement(By.xpath(NAV_ITEM.replace("{LABEL}", label)));
                System.out.println("*** navigation item is present ***");
                break;
            case "clicks":
                driver.findElement(By.xpath(NAV_ITEM.replace("{LABEL}", label))).click();
                System.out.println("*** clicking on navigation item ***");
                break;
            case "hovers":
                Actions actions = new Actions(driver);
                WebElement selector = driver.findElement(By.xpath(NAV_ITEM.replace("{LABEL}",label)));
                actions.moveToElement(selector).moveToElement(selector).click().build().perform();
                System.out.println("*** hovering over the element ***");
                break;
            default:
                throw new IllegalArgumentException("Invalid action: " + action);}
    }
    @And("User scrolls down the home page to see {string} disclaimer")
    public void user_scrolls_down_the_home_page_to_disclaimer(String label) {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        System.out.println("*** scroll down successful ***");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(DISCLAIMER.replace("{LABEL}", label)));
        System.out.println("*** disclaimer found ***");}

    @And("^User verifies the slider \"([^\"]*)\" has \"([^\"]*)\" headings value$")
    public void user_verifies_the_slider_headings_are_of_value(String count, String value) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath(SLIDER_TEXT.replace("{COUNT}", count).replace("{VALUE}", value)));
        System.out.println("*** heading has expected value ***");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement image = driver.findElement(By.tagName("img"));
        boolean state = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete" + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", image);
        if (state) {
            System.out.println("*** image is present ***");
        } else {
            System.out.println("*** image is not present ***");
        }
        Assert.assertTrue(state);}

    @And("^User verifies the slider \"([^\"]*)\" has \"([^\"]*)\" description$")
    public void user_verifies_the_slider_description(String count, String value) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath(SLIDER_DESC.replace("{COUNT}", count).replace("{VALUE}", value)));
        System.out.println("*** description has expected value ***");}

    @And("User verifies that {string} slider button is present")
    public void user_verifies_that_slider_button_is_present(String label) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath(SLIDER_BTN.replace("{LABEL}", label)));
        System.out.println("*** button is present ***");}

    @And("^User (sees|clicks) the \"([^\"]*)\" sub-menu option in the navigation menu$")
    public void user_sees_string_navigation_sub_menu_item(String action, String label) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        switch (action) {
            case "sees":
                driver.findElement(By.xpath(NAV_SUB_ITEM.replace("{LABEL}", label)));
                System.out.println("*** sub item is present ***");
                break;
            case "clicks":
                driver.findElement(By.xpath(NAV_SUB_ITEM.replace("{LABEL}", label))).click();
                System.out.println("*** clicking on sub item ***");
                break;
            default:
                throw new IllegalArgumentException("Invalid action: " + action);}
    }
    @And("^User verifies landing on \"([^\"]*)\" page$")
    public void user_verifies_landing_on_string_page(String page)throws InterruptedException{
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        String correctUrl = driver.getCurrentUrl();
        Thread.sleep(5000);
        Assert.assertTrue(correctUrl.contains(page));
        System.out.println("*** url is correct ***");}

    @And("User sees {string} text")
    public void user_sees_string_message(String label){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(CAREER_TITLE.replace("{LABEL}", label)));
        System.out.println("*** text is found ***");}

    @And("^User applies \"([^\"]*)\" filter in open positions and prints them$")
    public void user_applies_string_filter_and_prints(String filter)throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(CAREER_DROPDOWN)).click();
        driver.findElement(By.xpath(CAREER_OPTION.replace("{OPTION}", filter))).click();
        driver.findElement(By.xpath(CAREER_DROPDOWN)).sendKeys(Keys.ESCAPE);
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath(WIDGET_ROOT));
        ((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(730,830);", element);
        Thread.sleep(2000);
        List<WebElement> filteredElements = driver.findElements(By.xpath(CAREER_POSITIONS));
        int count = filteredElements.size();
        System.out.println("*** number positions by current filter:" + count + " ***");
        for (WebElement positions : filteredElements) {
            String elementText = positions.getText();
            System.out.println("*** position details:" + elementText + " ***");}
        }
    @And ("User finds {string} social media link and verifies the image is loaded")
    public void user_finds_social_media_link_and_verifies_image_is_loaded (String label){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(CONNECT_TITLE.replace("{LABEL}",label)));
        WebElement image = driver.findElement(By.tagName("img"));
        boolean state = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete" + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", image);
        if (state) {
            System.out.println("*** social media image is present ***");
        } else {
            System.out.println("*** social media image is not present ***");}
    }
   @And ("User clicks on {string} social media link")
   public void user_clicks_on_social_media_link(String label){
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       driver.findElement(By.xpath(CONNECT_HREF.replace("{LABEL}",label))).click();
       System.out.println("*** clicking on social media link ***");}

    @And ("User switches to {int} browser tab")
    public void user_switches_to_tab(int tab)throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tab > 0 && tab <= tabs.size()) {
            driver.switchTo().window(tabs.get(tab - 1));
            Thread.sleep(5000);
        } else {
            throw new IllegalArgumentException("Invalid tab index: " + tab);}
        }
}




