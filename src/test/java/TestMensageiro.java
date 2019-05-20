import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TestMensageiro extends TestBase {

    @Test
    public void test_mensagem_empty_state() {
        logar_cpf("01530880521");

        sleep_testes(3000 * CONST_NET);

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(2).click();


        MobileElement mensagens_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/message")));
        mensagens_button.click();

        MobileElement empty_state = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/nothing_message")));

        assert empty_state.isDisplayed();
    }

    @Test
    public void test_mensagem_att_mensagem_trocar_empresa() {

        logar_cpf("01530880521");
        MobileElement empresa = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_name_company")));
        empresa.click();


        MobileElement mensagens_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/message")));
        mensagens_button.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/list_message")));

        List<MobileElement> mensagens1 = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        ArrayList<String> mensagens1_cnt = new ArrayList<String>();

        for (int i = 0; i < mensagens1.size(); i++) {

            String titulo = mensagens1.get(i).findElementById("br.com.fortes.appcolaborador:id/title_message").getText();
            String texto = mensagens1.get(i).findElementById("br.com.fortes.appcolaborador:id/text_message").getText();
            mensagens1_cnt.add(titulo + "," + texto);
        }

        trocar_empresa();


        scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/list_message")));

        List<MobileElement> mensagens2 = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        ArrayList<String> mensagens2_cnt = new ArrayList<String>();

        for (int i = 0; i < mensagens2.size(); i++) {
            String titulo = mensagens2.get(i).findElementById("br.com.fortes.appcolaborador:id/title_message").getText();
            String texto = mensagens2.get(i).findElementById("br.com.fortes.appcolaborador:id/text_message").getText();
            mensagens2_cnt.add(titulo + "," + texto);
        }

        boolean flagTodasIguas = true;

        if (mensagens2_cnt.size() != mensagens1_cnt.size()) {
            assert (true);
        } else if (mensagens2_cnt.size() == 0 && mensagens1_cnt.size() == 0) {
            assert (true);
        } else {
            for (int i = 0; i < mensagens2_cnt.size(); i++) {
                String aux1 = mensagens2_cnt.get(i);
                for (int j = 0; j < mensagens1_cnt.size(); j++) {
                    String aux2 = (mensagens1_cnt.get(j));
                    if (!aux1.equals(aux2)) {
                        flagTodasIguas = false;
                    }
                }
            }

            assert (flagTodasIguas);

        }
    }

    @Test
    public void test_mensagem_vizualizar_mensagem() {
        logar_cpf("01530880521");

        MobileElement empresa = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_name_company")));
        empresa.click();


        MobileElement mensagens_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/message")));
        mensagens_button.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/list_message")));

        MobileElement mensagem = scroll_view.findElement
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        mensagem.click();
        sleep_testes(2000 * CONST_NET);


        Boolean texto_acessivel = false;
        Boolean titulo_acessivel = false;

        try {
            MobileElement titulo = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/title_message")));
            titulo_acessivel = titulo.isDisplayed();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MobileElement text = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/text_message")));
            texto_acessivel = text.isDisplayed();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assert texto_acessivel && titulo_acessivel;
    }

    @Test
    public void test_mensagem_enviar_mensagem() {

        enviar_req("enviarMensagem.json", "agente/mensagem/incluir");

        logar_cpf("01607344521");


        MobileElement mensagens_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/message")));
        mensagens_button.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/list_message")));

        List<MobileElement> mensagens = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));


        String titulo = mensagens.get(0).findElementById("br.com.fortes.appcolaborador:id/title_message").getText();
        String texto = mensagens.get(0).findElementById("br.com.fortes.appcolaborador:id/text_message").getText();

        mensagens.get(0).click();

        MobileElement delete_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/action_delete")));
        delete_button.click();


        MobileElement delete_button_confirm = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
        delete_button_confirm.click();

        System.out.println(titulo + ", Teste");
        System.out.println(texto + ",Este é um teste automatizado, não bula!");
        assert (titulo.equals("Teste") && texto.equals("Este é um teste automatizado, não bula!"));


    }

    @Test
    public void test_mensagem_marcar_mensagem_lida() {
        Boolean assertionFlag = true;
        enviar_req("enviarMensagem.json", "agente/mensagem/incluir");

        logar_cpf("01607344521");


        MobileElement mensagens_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/message")));
        mensagens_button.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/list_message")));

        List<MobileElement> mensagens = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));


        String titulo = mensagens.get(0).findElementById("br.com.fortes.appcolaborador:id/title_message").getText();
        String texto = mensagens.get(0).findElementById("br.com.fortes.appcolaborador:id/text_message").getText();

        MobileElement is_read = null;
        try {

            is_read = mensagens.get(0).findElementById("br.com.fortes.appcolaborador:id/iv_is_read");
        } catch (Exception e) {
            System.out.println("'br.com.fortes.appcolaborador:id/iv_is_read' não foi encontrado.");
            assertionFlag = false;
        }

        mensagens.get(0).click();
        sleep_testes(1000 * CONST_NET);


        driver.navigate().back();
        scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/list_message")));

        sleep_testes(500 * CONST_NET);

        try {
            is_read = mensagens.get(0).findElementById("br.com.fortes.appcolaborador:id/iv_is_read");
            assertionFlag = false;
        } catch (Exception e) {
            assertionFlag = true;
            System.out.println("Nao foi encontrada leitura");
        }


        mensagens = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));


        mensagens.get(0).click();

        MobileElement delete_button = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/action_delete")));
        delete_button.click();


        MobileElement delete_button_confirm = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/button1")));
        delete_button_confirm.click();

        assert assertionFlag;

    }
}

