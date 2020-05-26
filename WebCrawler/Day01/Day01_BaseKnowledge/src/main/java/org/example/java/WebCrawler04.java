package org.example.java;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author HackerStar
 * @create 2020-05-21 09:37
 */
public class WebCrawler04 {
    public static void main(String[] args) throws Exception {
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数(也就是最多分配100浏览器)
        cm.setMaxTotal(100);
        //设置每个主机的最大连接数(也就是给一个网站最多分配10个浏览器)
        cm.setDefaultMaxPerRoute(10);
        //使用连接池管理器发起请求
        doPost(cm);
    }

    private static void doPost(PoolingHttpClientConnectionManager cm) throws Exception {
        CloseableHttpClient buildClient = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        try {
            //不是每次创建新的HttpClient，而是从连接池中获取HttpClient对象
            buildClient = HttpClients.custom().setConnectionManager(cm).build();
            httpPost = new HttpPost("https://www.acfun.cn");
            System.out.println("请求:" + httpPost);
            response = buildClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //不能关闭HttpClient，由连接池管理HttpClient
        }
    }
}
