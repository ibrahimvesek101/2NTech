package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigurationReader;
import utils.Driver;
import utils.ReusableMethods;
import static utils.ReusableMethods.generateRandomEmail;
import static utils.ReusableMethods.generateRandomNumber;

public class FormPage {
    public FormPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    String adi_soyadi = ConfigurationReader.getProperty("adi_soyadi");
    String dogum_tarihi = ConfigurationReader.getProperty("dogum_tarihi");
    String tc_kimlik_no = ConfigurationReader.getProperty("tc_kimlik_no");
    String telefon_no = ConfigurationReader.getProperty("telefon_no");
    String e_mail = ConfigurationReader.getProperty("e_mail");

    @FindBy(xpath = "//input[@id='name']")
    public static WebElement name;
    @FindBy(xpath = "//input[@id='birth']")
    public static WebElement dateOfBirth;
    @FindBy(xpath = "//input[@id='tcKimlik']")
    public static WebElement identificationNumber;
    @FindBy(xpath = "//input[@id='phone']")
    public static WebElement telNumber;
    @FindBy(xpath = "//input[@id='email']")
    public static WebElement e_mailAddress;
    @FindBy(xpath = "//*[@id='cv_field']")
    public static WebElement cv;
    @FindBy(xpath = "//button[text()='Lisans']")
    public static WebElement lisansButton;
    @FindBy(xpath = "//*[@class='w-8 h-8 flex justify-center items-center p-2 bg-[#DF1F29] rounded-full']")
    public static WebElement rightButton;

    @FindBy(xpath = "//*[text()='Test Engineer']")
    public static WebElement testEngineer;
    @FindBy(xpath = "//*[text()='Gönder']")
    public static WebElement gonderButton;
    @FindBy(xpath = "//*[@class='text-[16px] flex justify-start items-start leading-6']")
    public static WebElement verifyText;

    public void goTo2NtechForm() {
        String url = ConfigurationReader.getProperty("form_url");
        Driver.getDriver().get(url);
    }

    public void formFill_Send_Verify() {
        ReusableMethods.sendData(name, adi_soyadi);
        ReusableMethods.sendData(dateOfBirth, dogum_tarihi);
        ReusableMethods.sendData(identificationNumber, tc_kimlik_no);
        ReusableMethods.sendData(telNumber, telefon_no);
        ReusableMethods.sendData(e_mailAddress, e_mail);

        String filePath = System.getProperty("user.dir") + "/src/test/resources/ibrahim_vesek_resume.pdf";
        cv.sendKeys(filePath);

        lisansButton.click();
        rightButton.click();

        ReusableMethods.waitToBeClickable(testEngineer, 10).click();
        gonderButton.click();
        Assert.assertEquals("Form Başarı ile gönderildi. Katıldığınız için teşekkür ederiz.", verifyText.getText());
    }

    public void formFill_Send_Verify_RandomData(){

        /*  Formda sadece bu alanlar(tcKimlikNo,telefonNo,randomEmail)
        değiştirilirse yeni kişi olarak algılanmak için yeterli. O yüzden sadece bunlar random yapıldı.
         */

        String tcKimlikNo = generateRandomNumber(11);
        String telefonNo = "0532" + generateRandomNumber(7);
        String randomEmail = generateRandomEmail();

        ReusableMethods.sendData(name, adi_soyadi);
        ReusableMethods.sendData(dateOfBirth, dogum_tarihi);
        ReusableMethods.sendData(identificationNumber, tcKimlikNo);
        ReusableMethods.sendData(telNumber, telefonNo);
        ReusableMethods.sendData(e_mailAddress, randomEmail);

        String filePath = System.getProperty("user.dir") + "/src/test/resources/ibrahim_vesek_resume.pdf";
        cv.sendKeys(filePath);

        lisansButton.click();
        rightButton.click();

        ReusableMethods.waitToBeClickable(testEngineer, 10).click();
        gonderButton.click();

        Assert.assertEquals("Form Başarı ile gönderildi. Katıldığınız için teşekkür ederiz.", verifyText.getText());
    }

}