package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
@Slf4j
public class HttpClient {
    public static void main(String[] args) throws Exception {
        java.net.http.HttpClient httpClient = java.net.http.HttpClient.newBuilder()
                .version(java.net.http.HttpClient.Version.HTTP_2)
                .followRedirects(java.net.http.HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest httpRequestGet = HttpRequest.newBuilder()
                .uri(new URI("http://www.baidu.com"))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();
//        get(httpClient, httpRequestGet);
//        asyncGet(httpClient,httpRequestGet);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("name", "bob");
        hashMap.put("age", 23);
        String requestBody = mapper.writeValueAsString(hashMap);
        HttpRequest httpRequestPost = HttpRequest.newBuilder()
                .uri(new URI("http://www.baidu.com"))
                .timeout(Duration.ofSeconds(10))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
//        post(httpClient, httpRequestPost);
        asyncPost(httpClient, httpRequestPost);
    }

    public static void get(java.net.http.HttpClient httpClient, HttpRequest httpRequest) throws IOException, InterruptedException {
       log.info(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body());
    }

    public static void asyncGet(java.net.http.HttpClient httpClient, HttpRequest httpRequest){
        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(log::info)
                .join();
    }

    public static void post(java.net.http.HttpClient httpClient, HttpRequest httpRequest) throws IOException, InterruptedException {
        log.info(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body());
    }

    public static void asyncPost(java.net.http.HttpClient httpClient, HttpRequest httpRequest){
        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(log::info)
                .join();
    }
}
