import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TestIR extends TestBase {
    @Test
    public void ir_empty_state() {

        logar_cpf("01530880521");


        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(4).click();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"IR\");")));

        ferias.click();
        try {
            MobileElement nothing_point = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("br.com.fortes.appcolaborador:id/nothing_ir")));
        } catch (Exception e) {
            assert (false);
        }

        assert true;

    }
    /*@Test
    public void test_vizualizar_ir() {
        logar_cpf("01607344521");


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"IR\");")));

        ferias.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/viewpager")));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/item"));

        assert (meses.get(0).isDisplayed() && meses.get(0).isEnabled());

    }
*/
}
