package org.example.java;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author HackerStar
 * @create 2020-05-20 21:29
 */
public class WebCrawler02 {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            //创建HttpClient对象
            httpClient = HttpClients.createDefault();
            //创建HttpGet对象，设置url访问地址
            HttpGet httpGet = new HttpGet("https://www.apple.com/cn");
            //使用HttpClient发起请求，获取response
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
