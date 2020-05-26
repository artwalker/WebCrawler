package org.example.cron.test;

import org.jsoup.Jsoup;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @author HackerStar
 * @create 2020-05-26 11:24
 */
@Component
public class ProxyTest implements PageProcessor {

    @Scheduled(fixedDelay = 10000)
    public void testProxy() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("59.124.224.180", 3128)));

        Spider.create(new ProxyTest())
                .addUrl("https://api.ipify.org")
                .setDownloader(httpClientDownloader)
                .run();
    }


    @Override
    public void process(Page page) {
        //打印获取到的结果以测试代理服务器是否生效
        System.out.println(page.getHtml().css("body", "text"));
    }

    private Site site = new Site();

    @Override
    public Site getSite() {
        return site;
    }
}
