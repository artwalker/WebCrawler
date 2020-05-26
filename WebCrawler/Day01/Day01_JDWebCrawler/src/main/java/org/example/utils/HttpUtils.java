package org.example.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

/**
 * @author HackerStar
 * @create 2020-05-21 16:13
 */
@Component
public class HttpUtils {
    private PoolingHttpClientConnectionManager cm;
    //HttpClient对象
    private CloseableHttpClient httpClient = null;
    //httpGet请求对象
    private HttpGet httpGet = null;

    public HttpUtils() {
        cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        cm.setMaxTotal(100);
        //设置每个主机的最大连接数
        cm.setDefaultMaxPerRoute(10);
    }

    /**
     * 根据请求地址下载页面数据
     *
     * @param url
     * @return 页面数据
     */
    public String doGetHtml(String url) {
        init(url);
        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求，获取响应
            response = httpClient.execute(httpGet);
            System.out.println(EntityUtils.toString(response.getEntity(), "utf8"));//查看是否获取到了数据
            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空，如果不为空就可以使用EntityUtils
                if (response.getEntity() != null) {
                    String content = EntityUtils.toString(response.getEntity(), "utf8");
                    return content;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 下载图片
     *
     * @param url
     * @return 图片名称
     */
    public String doGetImage(String url) {
        init(url);
        CloseableHttpResponse response = null;
        //使用HttpClient发起请求，获取响应
        try {
            response = httpClient.execute(httpGet);
            //解析响应，下载图片
            if (response.getStatusLine().getStatusCode() == 200) {
                //判断响应体Entity是否不为空
                if (response.getEntity() != null) {
                    //下载图片
                    //获取图片的后缀
                    String extName = url.substring(url.lastIndexOf("."));
                    //创建图片名，重命名图片
                    String picName = UUID.randomUUID().toString() + extName;
                    //下载图片
                    //声明OutPutStream
                    OutputStream outputStream = new FileOutputStream(new File("/Users/XinxingWang/Development/Java/IDEA_Project/WebCrawler/pic"));
                    response.getEntity().writeTo(outputStream);
                    //返回图片名称
                    return picName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //如果下载失败，返回空串
        return "";
    }

    //设置请求信息
    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(1000)//创建连接的最长时间
                .setConnectionRequestTimeout(500)//获取连接的最长时间
                .setSocketTimeout(10000)//数据传输的最长时间
                .build();
        return config;
    }

    //初始化
    private void init(String url) {
        //获取HttpClient对象
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
        //创建httpGet请求对象，设置url地址
        httpGet = new HttpGet(url);
        //设置请求信息
        httpGet.setConfig(getConfig());
    }
}
