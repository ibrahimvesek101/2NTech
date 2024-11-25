package tests;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import pages.HomePage;
import utils.ConfigurationReader;
import static utils.Driver.driver;

public class Navbar_News_Tests {
    HomePage navbarPage = new HomePage();

    @Before
    public void goTo2Nhaber() {
        navbarPage.goTo2Nhaber();
    }

    @Test
    public void testNavbarElementsClickAndVerify() {
        navbarPage.navbarElementsClickAndVerify();
    }

    @Test
    public void testNewsNumberAndDetail() {
        navbarPage.searchWord(ConfigurationReader.getProperty("aranacak_kelime"));
        navbarPage.NewsNumberAndDetailVerify(Integer.parseInt(ConfigurationReader.getProperty("haber_no")), ConfigurationReader.getProperty("haber_icerigi"));
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}


