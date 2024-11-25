package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationReader;
import utils.Driver;
import utils.ReusableMethods;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "nav a")
    public static List<WebElement> navElements;

    @FindBy(className = "elementor-widget-cmsmasters-search__popup-trigger-inner-icon")
    public static WebElement searchButton;

    @FindBy(className = "elementor-widget-cmsmasters-search__field")
    public static WebElement searchInput;

    @FindBy(xpath = "//h1")
    public static WebElement detailTitle;

    @FindBy(xpath = "//h3[@class='entry-title cmsmasters-widget-title__heading']/a")
    public static List<WebElement> newsList;


    public void goTo2Nhaber() {
        String url = ConfigurationReader.getProperty("url");
        Driver.getDriver().get(url);
    }

    public void navbarElementsClickAndVerify() {

        for (int i = 0; i < navElements.size(); i++) {
            WebElement element = navElements.get(i);
            String elementText = element.getText().trim();
            ReusableMethods.actionClick(element);

            String pageTitle = Driver.getDriver().getTitle();
            Assert.assertFalse("Sayfa başlığı boş: " + pageTitle, pageTitle.isEmpty());
            Assert.assertTrue(
                    "Sayfa başlığı beklendiği gibi değil: " + pageTitle,
                    pageTitle.toLowerCase().contains(elementText.toLowerCase())
            );

            String enSonElement = ConfigurationReader.getProperty("en_son_tiklanacak_element");
            if (elementText.equalsIgnoreCase(enSonElement)) {
                break;
            }

            Driver.getDriver().navigate().back();
            navElements = Driver.getDriver().findElements(By.cssSelector("nav a"));
        }
    }

    public void searchWord(String searchWord) {
        searchButton.click();
        searchInput.sendKeys(searchWord + Keys.ENTER);
    }

    public void NewsNumberAndDetailVerify(int haberNo, String expectedTitle) {

        List<WebElement> filteredNewsList = newsList.stream()
                .filter(news -> {
                    String text = news.getText().trim();
                    return !text.equalsIgnoreCase("Aydınlatma Metni") && !text.equalsIgnoreCase("İletişim");
                })
                .collect(Collectors.toList());

        WebElement news = filteredNewsList.get(haberNo - 1);
        ReusableMethods.actionClick(news);

        String actualTitle = detailTitle.getText().trim();
        Assert.assertEquals("Başlık eşleşmiyor!", expectedTitle, actualTitle);
    }
}
