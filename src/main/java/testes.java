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

        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);     }
}