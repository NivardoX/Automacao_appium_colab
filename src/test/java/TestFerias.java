import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class TestFerias extends TestBase {


    @Test
    public void test_ferias_vizualizar_ferias() {
        logar();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");")));

        ferias.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/viewpager")));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/item"));

        assert (meses.get(0).isDisplayed() && meses.get(0).isEnabled());

    }

    @Test
    public void test_ferias_exibir_download_pdf() {

        logar_cpf("01530880521");


        MobileElement empresa = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_name_company")));
        empresa.click();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();


        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/listShowPayChecks")));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        meses.get(0).click();


        MobileElement view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/paycheck_registry_recyclerview")));

        List<MobileElement> matriculas = driver.findElements(By.id
                ("br.com.fortes.appcolaborador:id/item"));

        matriculas.get(0).click();


        MobileElement download_pdf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/action_delete")));

        assert (download_pdf.isDisplayed() && download_pdf.isEnabled());
    }

    @Test
    public void test_ferias_vizualizar_matriculas() {
        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(2).click();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");")));

        ferias.click();


        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/viewpager")));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/item"));

        meses.get(0).click();

        sleep_testes(1000 * CONST_NET);

        List<MobileElement> matricula = driver.findElements(By.id
                ("br.com.fortes.appcolaborador:id/text_item_header_name"));

        System.out.println(matricula.size());
        assert (matricula.size() != 0);


    }

    @Test
    public void test_ferias_vizualizar_ferias_meses() {
        logar();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");")));

        ferias.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/viewpager")));

        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/item"));

        assert (meses.size() >= 1);

    }

    @Test
    public void test_ferias_refresh() {

        logar();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");")));

        ferias.click();


        Dimension size = driver.manage().window().getSize();
        System.out.println(size);
        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;


        startY = (int) (size.height * 0.70);
        endY = (int) (size.height * 0.30);
        startX = (size.width / 2);
        //Swipe from Bottom to Top.

        new TouchAction(driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(200)))
                .moveTo(PointOption.point(endX, endY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .release()
                .perform();


        ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");")));

        assert (ferias.isDisplayed());

    }

    @Test
    public void test_ferias_folha_empty_state() {

        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(4).click();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();
        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");")));

        ferias.click();


        try {

            MobileElement nothing_point = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("br.com.fortes.appcolaborador:id/nothing_paycheck")));
        } catch (Exception e) {
            assert (false);
        }

        assert true;


    }

    @Test
    public void test_ferias_att_dados_troca_empresa() {

        logar_cpf("01530880521");


        MobileElement empresa = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_name_company")));
        empresa.click();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");")));

        ferias.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/viewpager")));

        List<MobileElement> meses_empresa1 = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));
        ArrayList<String> meses_empresa1_str = new ArrayList<String>();

        for (MobileElement mobileElement : meses_empresa1) {
            meses_empresa1_str.add(mobileElement.getText());
        }

        trocar_empresa();


        ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"FÉRIAS\");")));

        ferias.click();

        List<MobileElement> meses_empresa2 = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));
        ArrayList<String> meses_empresa2_str = new ArrayList<String>();

        for (int i = 0; i < meses_empresa2.size(); i++) {
            meses_empresa1_str.add(meses_empresa2.get(i).getText());
        }


        boolean flagTodasIguas = true;

        if (meses_empresa1_str.size() != meses_empresa2_str.size()) {
            assert (true);
        } else if (meses_empresa1_str.size() == 0 && meses_empresa2_str.size() == 0) {
            assert (true);
        } else {
            for (int i = 0; i < meses_empresa2_str.size(); i++) {
                String aux1 = meses_empresa2_str.get(i);
                for (int j = 0; j < meses_empresa1.size(); j++) {
                    String aux2 = (meses_empresa1_str.get(j));
                    if (!aux1.equals(aux2)) {
                        flagTodasIguas = false;
                    }
                }
            }

            assert (flagTodasIguas);

        }
    }


}
