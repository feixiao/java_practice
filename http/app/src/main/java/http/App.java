/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static String url = "http://172.20.99.13:8080";

    public void post() throws IOException {
        // 声明Post请求
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头，在Post请求中限制了浏览器后才能访问
        httpPost.addHeader("User-Agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        // httpPost.addHeader("Connection", "keep-alive");

        JSONObject json = new JSONObject();
        json.put("filePath", "js");
        json.put("projectId", "61020ccdfd33d86b6abe8745");
        json.put("type", "fileFolder");

        // 发送 json 类型数据，通过new StringEntity()，可将Content-Type设置为text/plain类型
        httpPost.setEntity(new StringEntity(json.toString(), "UTF-8"));

        // 获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();

            try {
                // 获取返回的信息
                String string = EntityUtils.toString(entity, "UTF-8");
                System.out.println(string);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        } else {
            System.out.printf("http code : %d", response.getStatusLine().getStatusCode());
        }

        // 关闭response、HttpClient资源
        response.close();
        httpClient.close();
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.getGreeting());
        try {
            app.post();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
