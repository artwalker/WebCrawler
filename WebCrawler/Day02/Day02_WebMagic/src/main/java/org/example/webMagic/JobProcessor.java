package org.example.webMagic;

import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * @author HackerStar
 * @create 2020-05-23 17:21
 */
public class JobProcessor implements PageProcessor {
    //解析页面
    public void process(Page page) {
        //解析返回的数据page，并且把解析的结果放到ResultItems中
        //css选择器
        page.putField("css选择器", page.getHtml().css("div.news-list-b p").all());
        //XPath
        page.putField("XPath", page.getHtml().xpath("//div[@class=news-list-b]/ul/li/p/a").all());
        //正则表达式
        page.putField("正则表达式", page.getHtml().css("div.news-list-b a").regex(".*篮网.*").all());
        //处理结果API
        page.putField("处理结果API", page.getHtml().css("div.news-list-b a").regex(".*篮网.*").get());
        page.putField("处理结果API2", page.getHtml().css("div.news-list-b a").regex(".*篮网.*").toString());
        //获取链接
        //page.addTargetRequests(page.getHtml().css("div.news-list-b").links().all());
        //page.putField("url", page.getHtml().css("div.news-list-b p").all());

        //测试去重(只访问一次指定的链接)
        page.addTargetRequest("https://finance.sina.com.cn");
        page.addTargetRequest("https://finance.sina.com.cn");
        page.addTargetRequest("https://finance.sina.com.cn");
    }

    private Site site = Site.me()
            .setCharset("utf8")//设置编码
            .setTimeOut(10000)//设置超时时间，单位是ms毫秒
            .setRetrySleepTime(3000)//设置重试的间隔时间
            .setSleepTime(3);//设置重试次数
    public Site getSite() {
        return site;
    }

    //主函数，执行爬虫
    public static void main(String[] args) {
        Spider spider = Spider.create(new JobProcessor())
                .addUrl("http://sports.sina.com.cn/nba/")//设置爬取数据的页面(初始访问url地址)
//                .addPipeline(new FilePipeline("/development/files/"))//爬取的数据存入到指定的位置
                //设置布隆去重过滤器，指定最多对1000万数据进行去重操作(需要导包com.google.guava)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))//参数设置需要对多少条数据去重
                .thread(5);//设置线程数

        Scheduler scheduler = spider.getScheduler();//设置断点查看使用的是哪个去重器

        //执行爬虫
        spider.run();
    }
}
