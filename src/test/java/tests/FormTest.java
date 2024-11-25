package tests;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import pages.FormPage;
import static utils.Driver.driver;

public class FormTest {
    FormPage formPage = new FormPage();

    @Before
    public void goToFormPage() {
        formPage.goTo2NtechForm();
    }

    @Test
    public void testForm_Fill_Send_Verify() {
        /*  ---> config.properties teki datalar ile yürütmek istenildiğinde;
                 formPage.formFill_Send_Verify();

            ---> random datalar ile yürütmek istenildiğinde;
                 formPage.formFill_Send_Verify_RandomData();

                 methodları kullanılabilir.
            NOT: formFill_Send_Verify_RandomData(); açık bırakıldı. */

        formPage.formFill_Send_Verify_RandomData();
      //formPage.formFill_Send_Verify();

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}


