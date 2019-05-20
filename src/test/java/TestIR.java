import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TestIR extends TestBase {

    @Test
    public void test_ir_empty_state() {

        logar();

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

    @Test
    public void test_ir_vizualizar_declaracao() {
        enviar_req("enviarDeclaracao.json", "agente/declaracao/incluir");
        logar_cpf("01607344521");


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"IR\");")));

        ferias.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/viewpager")));

        List<MobileElement> matriculas = scroll_view.findElements(By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        assert (matriculas.get(0).isDisplayed() && matriculas.get(0).isEnabled());

    }

    @Test
    public void test_ir_att_dados_troca_empresa() {

        logar_cpf("01530880521");


        MobileElement empresa = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_name_company")));
        empresa.click();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"IR\");")));

        ferias.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/viewpager")));

        List<MobileElement> meses_empresa1 = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));
        ArrayList<String> meses_empresa1_str = new ArrayList<String>();

        for (MobileElement mobileElement : meses_empresa1) {
            meses_empresa1_str.add(mobileElement.getText());
        }

        trocar_empresa();


        sleep_testes(6000 * CONST_NET);

        ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"IR\");")));

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

    @Test
    public void test_ir_refresh() {

        enviar_req("excluirDeclaracao.json", "agente/declaracao/excluir");

        logar_cpf("01607344521");


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ir = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"IR\");")));

        ir.click();
        enviar_req("enviarDeclaracao.json", "agente/declaracao/incluir");



        sleep_testes(4000 * CONST_NET);
        atualizar();

        sleep_testes(2000 * CONST_NET);

        List<MobileElement> irs = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));


        assert (irs.size() > 0);

    }

    @Test
    public void test_ir_exibir_download_pdf() {

        logar_cpf("01530880521");


        MobileElement empresa = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_name_company")));
        empresa.click();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ferias = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"IR\");")));

        ferias.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/viewpager")));

        List<MobileElement> matriculas = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));
        matriculas.get(0).click();

        sleep_testes(1000 * CONST_NET);


        MobileElement download_pdf = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/action_delete")));

        assert (download_pdf.isDisplayed() && download_pdf.isEnabled());
    }

    @Test
    public void test_ir_vizualizar_matriculas() {
        logar_cpf("01530880521");

        sleep_testes((1000 * CONST_NET));
        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(0).click();

        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement ir = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
                .AndroidUIAutomator("new UiSelector().description(\"IR\");")));

        ir.click();


        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/viewpager")));

        List<MobileElement> matriculas = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));


        assert (matriculas.size() != 0);


    }

}
