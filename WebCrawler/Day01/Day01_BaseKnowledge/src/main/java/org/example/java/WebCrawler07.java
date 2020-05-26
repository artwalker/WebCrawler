package org.example.java;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author HackerStar
 * @create 2020-05-21 10:31
 */
public class WebCrawler07 {
    public static void main(String[] args) {
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        cm.setMaxTotal(100);

        //设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(10);

        //使用连接池管理器发起请求
        doGet(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {
        //不是每次创建新的HttpClient，而是从连接池中获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

        //创建HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet("https://www.baidu.com/");
        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)//创建连接的最长时间，单位是毫秒
                .setConnectionRequestTimeout(500)//设置获取连接的最长时间，单位是毫秒
                .setSocketTimeout(10 * 1000)//设置数据传输的最长时间，单位是毫秒
                .build();
        //给请求设置请求信息
        httpGet.setConfig(config);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");

                System.out.println(content.length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //不能关闭HttpClient，由连接池管理HttpClient
                //httpClient.close();
            }
        }
    }
}
