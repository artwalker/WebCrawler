package org.example.java;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author HackerStar
 * @create 2020-05-21 09:05
 */
public class WebCrawler03 {
    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        URIBuilder uriBuilder = null;
        try {
            //创建HttpClient对象
            httpClient = HttpClients.createDefault();
            //创建URIBuilder,设置url访问地址
            uriBuilder = new URIBuilder("https://www.acfun.cn/search?");
            //设置参数
            uriBuilder.setParameter("keyword", "apple");
            //创建HttpGet对象，设置url访问地址
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            System.out.println("发起请求的信息：" + httpGet);
            //使用HttpClient发起请求，获取response
            response = httpClient.execute(httpGet);
            //解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (response != null) {
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
