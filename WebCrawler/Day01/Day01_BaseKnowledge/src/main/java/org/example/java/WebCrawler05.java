package org.example.java;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-21 09:37
 */
public class WebCrawler05 {
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
        //创建表单的Entity对象,第一个参数就是封装好的表单数据，第二个参数就是编码
        List<NameValuePair> params = null;
        UrlEncodedFormEntity formEntity = null;
        try {
            //不是每次创建新的HttpClient，而是从连接池中获取HttpClient对象
            buildClient = HttpClients.custom().setConnectionManager(cm).build();
            params = new ArrayList<>();
            params.add(new BasicNameValuePair("keyword","java"));
            //创建表单的Entity对象,第一个参数就是封装好的表单数据，第二个参数就是编码
            formEntity = new UrlEncodedFormEntity(params, "UTF-8");
            //创建HttpPost对象，设置url访问地址
            httpPost = new HttpPost("https://www.bilibili.com/search");
            httpPost.setEntity(formEntity);
            System.out.println("请求:" + httpPost);
            //使用HttpClient发起请求，获取response
            response = buildClient.execute(httpPost);
            //解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
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
