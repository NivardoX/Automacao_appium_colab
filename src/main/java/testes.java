import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.FileReader;

public class testes{
    public static void main(String[] args) throws Exception {

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader("excluirFolha.json");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String payload1 = "";
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                payload1 += line;
            }
            byte ptext[] = payload1.getBytes();
            String payload = new String(ptext,"UTF-8");



            System.out.println(payload);
            StringEntity entity = new StringEntity(payload, "UTF-8");
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost("https://qe00nlgco8.execute-api.sa-east-1.amazonaws.com/homologifce/agente/folha/excluir");
            request.setEntity(entity);

            HttpResponse response = httpClient.execute(request);
            System.out.println(response.getStatusLine().getStatusCode());
        }catch(Exception e){
            e.printStackTrace();
        }
     }
}