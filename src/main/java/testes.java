import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class testes{
    public static void main(String[] args) throws Exception {
        String PATH = System.getProperty("user.dir")+"/";
        String file= "enviarMensagem.json";
        String endpoint = "agente/mensagem/incluir";


        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(PATH + "src/main/java/" + file);

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
            HttpPost request = new HttpPost("https://qe00nlgco8.execute-api.sa-east-1.amazonaws.com/homologifce/" + endpoint);
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}