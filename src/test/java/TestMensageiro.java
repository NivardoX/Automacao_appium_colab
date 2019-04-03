import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class TestMensageiro extends testBase {

    @Test
    public void mensagem_empty_state(){
        logar_cpf("01607344521");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MobileElement mensagens_button = (MobileElement) driver.findElementById("br.com.fortes.appcolaborador:id/message");
        mensagens_button.click();

        MobileElement empty_state = (MobileElement) driver.findElementById("br.com.fortes.appcolaborador:id/nothing_message");

        assert empty_state.isDisplayed();
    }
    @Test
    public void att_mensagem_trocar_empresa() {

        logar_cpf("01530880521");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement mensagens_button = (MobileElement) driver.findElementById("br.com.fortes.appcolaborador:id/message");
        mensagens_button.click();

        MobileElement scroll_view = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/list_message"));

        List<MobileElement> mensagens1 = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        ArrayList<String> mensagens1_cnt = new ArrayList<String>();

        for (int i = 0; i < mensagens1.size(); i++) {
            String titulo =mensagens1.get(i).findElementById("br.com.fortes.appcolaborador:id/title_message").getText();
            String texto = mensagens1.get(i).findElementById("br.com.fortes.appcolaborador:id/text_message").getText();
            mensagens1_cnt.add(titulo+","+texto);
        }

        trocar_empresa();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scroll_view = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/list_message"));

        List<MobileElement> mensagens2 = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        ArrayList<String> mensagens2_cnt = new ArrayList<String>();

        for (int i = 0; i < mensagens2.size(); i++) {
            String titulo =mensagens2.get(i).findElementById("br.com.fortes.appcolaborador:id/title_message").getText();
            String texto = mensagens2.get(i).findElementById("br.com.fortes.appcolaborador:id/text_message").getText();
            mensagens2_cnt.add(titulo+","+texto);
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
    public void vizualizar_mensagem(){
        logar_cpf("01530880521");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MobileElement empresa = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresa.click();

        try {
            sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MobileElement mensagens_button = (MobileElement) driver.findElementById("br.com.fortes.appcolaborador:id/message");
        mensagens_button.click();

        MobileElement scroll_view = (MobileElement) driver.findElement(By.id("br.com.fortes.appcolaborador:id/list_message"));

        MobileElement mensagem = (MobileElement) scroll_view.findElement
                (By.id("br.com.fortes.appcolaborador:id/constraint_layout_profile"));

        mensagem.click();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Boolean texto_acessivel = false;
        Boolean titulo_acessivel = false;

        try {
            MobileElement titulo = (MobileElement) driver.findElementById("br.com.fortes.appcolaborador:id/title_message");
            titulo_acessivel = titulo.isDisplayed();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MobileElement text = (MobileElement) driver.findElementById("br.com.fortes.appcolaborador:id/text_message");
            texto_acessivel = text.isDisplayed();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assert texto_acessivel && titulo_acessivel;
    }

}

