package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;

@Slf4j
public class HttpClientOkHttp {
    public static void main(String[] args) throws Exception {

//        getSync("http://www.baidu.com");
//        getDataAsync();
        postDataWithParame();
    }

    public static void getSync(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        // 2.创建一个Request对象
        Request request = new Request.Builder()
                .url(url)
                .build();
        // 3.创建一个Call对象并调用execute()方法
        Response response = null;
        response = client.newCall(request).execute();//得到Response 对象
        if (response.isSuccessful()) {
            log.info("response.code()==" + response.code());
            log.info("response.message()==" + response.message());
            assert response.body() != null;
            log.info("res==" + response.body().string());
        }
    }

    public static void getDataAsync() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("获取数据失败了", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    log.info("获取数据成功了");
                    log.info("response.code()==" + response.code());
                    log.info("response.body().string()==" + response.body().string());
                }
            }
        });
    }

    public static void postDataWithParame() {
        OkHttpClient client = new OkHttpClient();//创建OkHttpClient对象。
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("username", "zhangsan");//传递键值对参数
        Request request = new Request.Builder()//创建Request 对象。
                .url("http://www.baidu.com")
                .post(formBody.build())//传递请求体
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("获取数据失败了", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    log.info("获取数据成功了");
                    log.info("response.code()==" + response.code());
                    log.info("response.body().string()==" + response.body().string());
                }
            }
        });//回调方法的使用与get异步请求相同
    }
}
