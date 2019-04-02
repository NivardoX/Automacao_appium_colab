import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;

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
    public void att_trocar_empresa() {

    }

}

