package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;


public class ReusableMethods {

    static Actions actions = new Actions(Driver.getDriver());

    //-----------------ACTIONS----------------//

    public static void actionClick(WebElement element) {
        actions.click(element).perform();
    }

    public static void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public static void moveAndClick(WebElement element) {
        actions.moveToElement(element).click().perform();
    }


    //--------------JSE--------------------//

    public static void clickButtonWithJSE(WebElement element, WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public static void sendKeysWithJSE(WebElement element, WebDriver driver, String textToSend) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value = arguments[1]", element, textToSend);
    }


    //--------------Explicit Waits--------------//

    public static void waitForLoad(int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
    }

    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static List<WebElement> waitForVisibilityList(List<WebElement> element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static WebElement waitToBeClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        actions.moveToElement(element).perform();
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    //-----------------SendData----------------//
    public static void sendData(WebElement element, String key) {
        element.clear();
        element.sendKeys(key + Keys.ENTER);
    }

    //--------------------RANDOM----------------//

    public static String generateRandomNumber(int length) {
        StringBuilder number = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            number.append(random.nextInt(10)); // 0-9 arasında rastgele sayı üret
        }
        return number.toString();
    }

    public static String generateRandomEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder email = new StringBuilder();
        Random random = new Random();

        int usernameLength = 5 + random.nextInt(6);
        for (int i = 0; i < usernameLength; i++) {
            email.append(characters.charAt(random.nextInt(characters.length())));
        }

        email.append("@example.com");
        return email.toString();
    }
}
