import io.appium.java_client.MobileElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

public class TestContraCheque extends TestBase {

    @Test
    public void test_contracheque_anos_ordenados() {

        logar();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();


        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/frame_layout_principal")));

        List<MobileElement> anos = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_header_name"));

        assert (isSorted(anos));
    }

    @Test
    public void test_contracheque_exibir_download_pdf() {

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
    public void test_contracheque_att_dados_troca_empresa() {

        logar_cpf("01530880521");

        MobileElement empresa = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/tv_name_company")));
        empresa.click();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();


        List<MobileElement> rc_matriculas1 = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));
        ArrayList<String> rc_matriculas1_str = new ArrayList<String>();

        for (int i = 0; i < rc_matriculas1.size(); i++) {
            rc_matriculas1_str.add(rc_matriculas1.get(i).getText());
            System.out.println(rc_matriculas1.get(i).getText());
        }

        trocar_empresa();


        List<MobileElement> rc_matriculas2 = driver.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));
        ArrayList<String> rc_matriculas2_str = new ArrayList<String>();

        for (int i = 0; i < rc_matriculas2.size(); i++) {
            rc_matriculas2_str.add(rc_matriculas2.get(i).getText());
            System.out.println(rc_matriculas2.get(i).getText());
        }


        boolean flagTodasIguas = true;

        if (rc_matriculas1.size() != rc_matriculas2.size()) {
            flagTodasIguas = false;
        } else if (rc_matriculas1.size() == 0) {
            flagTodasIguas = false;
        } else {
            for (int i = 0; i < rc_matriculas2.size(); i++) {
                flagTodasIguas = flagTodasIguas && rc_matriculas1_str.get(i).equals(rc_matriculas2_str.get(i));

            }
        }
        assert (!flagTodasIguas);


    }

    @Test
    public void test_contracheque_exibir_matriculas() {

        logar_cpf("01530880521");

        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(0).click();


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
                ("br.com.fortes.appcolaborador:id/text_item_header_name"));

        for (int i = 0; i < matriculas.size(); i++) {
            if (!matriculas.get(i).getText().contains("Matrícula")) {
                fail("Não contém Matrŕicula na string.");
            }
        }

        assert true;


    }

    @Test
    public void test_contracheque_exibir_folhas() {

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
                ("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getText().contains("Indefinido")) {
                fail("Exibindo Indefinido");
            }
        }

        assert true;


    }

    @Test
    public void test_contracheque_atualizar() {

        logar_cpf("01607344521");


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("incluirFolha.json");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String payload = "";
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                payload += line;
            }

            System.out.println(payload);
            StringEntity entity = new StringEntity(payload,
                    "UTF-8");

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("https://qe00nlgco8.execute-api.sa-east-1.amazonaws.com/homologifce/agente/folha/incluir");
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }


        atualizar();
        sleep_testes(2000 * CONST_NET);
        atualizar();


        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/listShowPayChecks")));
        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("excluirFolha.json");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String payload = "";
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                payload += line;
            }

            System.out.println(payload);
            StringEntity entity = new StringEntity(payload,
                    "UTF-8");

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("https://qe00nlgco8.execute-api.sa-east-1.amazonaws.com/homologifce/agente/folha/excluir");
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());

        } catch (Exception e) {
            e.printStackTrace();
        }


        boolean flag = false;
        for (MobileElement me : meses
        ) {
            if (me.getText().equals("Fevereiro")) {
                flag = true;
            }

        }

        assert flag;
    }

    @Test
    public void test_contracheque_empty_state() {

        logar_cpf("01530880521");


        List<MobileElement> empresas = driver.findElements(By.id("br.com.fortes.appcolaborador:id/tv_name_company"));
        empresas.get(4).click();


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        try {
            MobileElement nothing_point = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("br.com.fortes.appcolaborador:id/nothing_paycheck")));
        } catch (Exception e) {
            assert (false);
        }

        assert true;


    }

    @Test
    public void test_contracheque_enviar_folha() {
        //Envia a folha
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("incluirFolha.json");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String payload = "";
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                payload += line;
            }

            System.out.println(payload);
            StringEntity entity = new StringEntity(payload,
                    "UTF-8");

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("https://qe00nlgco8.execute-api.sa-east-1.amazonaws.com/homologifce/agente/folha/incluir");
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logar_cpf("01607344521");


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/listShowPayChecks")));
        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        boolean flag = false;
        for (MobileElement me : meses
        ) {
            if (me.getText().equals("Fevereiro")) {
                flag = true;
            }

        }

        int response2 = 0;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("excluirFolha.json");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String payload = "";
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                payload += line;
            }

            System.out.println(payload);
            StringEntity entity = new StringEntity(payload,
                    "UTF-8");

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("https://qe00nlgco8.execute-api.sa-east-1.amazonaws.com/homologifce/agente/folha/excluir");
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            response2 = response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert flag && response2 == 200;


    }

    @Test
    public void test_contracheque_transicao_folha() {
        //Envia a folha
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("incluirFolha.json");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String payload = "";
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                payload += line;
            }

            System.out.println(payload);
            StringEntity entity = new StringEntity(payload,
                    "UTF-8");

            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("https://qe00nlgco8.execute-api.sa-east-1.amazonaws.com/homologifce/agente/folha/incluir");
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logar_cpf("01607344521");


        MobileElement folha = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("br.com.fortes.appcolaborador:id/financial")));
        folha.click();

        MobileElement scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/listShowPayChecks")));
        List<MobileElement> meses = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));
        ArrayList<String> meses_str = new ArrayList<String>();


        for (MobileElement mes : meses) {
            meses_str.add(mes.getText());
        }
        meses.get(0).click();


        sleep_testes(6000 * CONST_NET);


        driver.navigate().back();


        scroll_view = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.id("br.com.fortes.appcolaborador:id/listShowPayChecks")));
        List<MobileElement> meses2 = scroll_view.findElements
                (By.id("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        boolean flag = true;


        if (meses.size() != meses2.size()) {
            flag = false;
        } else if (meses.size() != 0) {
            for (int i = 0; i < meses.size(); i++) {
                System.out.println(meses_str.get(i));
                flag = meses_str.get(i).equals(meses2.get(i).getText()) && flag;
            }
        }


        assert flag;
    }

    @Test
    public void test_contracheque_folhas_diferentes() {
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
                ("br.com.fortes.appcolaborador:id/text_item_paycheck_name"));

        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getText().contains("Indefinido")) {
                fail("Exibindo Indefinido");
            }
        }

        assert true;


    }


}

