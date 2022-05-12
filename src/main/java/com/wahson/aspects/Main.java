package com.wahson.aspects;

import com.kemai.components.multienv.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by WahsonLeung on 2022/4/26
 */
public class Main {
    public static void main(String[] args) throws IOException {
//        Account account = new Account();
//        account.withdraw(10);
//        account.withdraw(100);
        InvocationContext.getContext().setAttribute("X-Target-Env", "env-001");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.baidu.com").build();
//
        Response execute = okHttpClient.newCall(request).execute();
        System.out.println(execute.body().string());

        String envHeaderKey = Constants.ENV_HEADER_KEY;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(new HttpGet("http://www.baidu.com"));
        System.out.println(response.getStatusLine().getStatusCode());
    }
}
