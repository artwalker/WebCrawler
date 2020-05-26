package org.example.start;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author HackerStar
 * @create 2020-05-23 21:47
 */
public class JobProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        page.putField("author", page.getHtml().css("div.author").all());
    }

    private Site site = Site.me();
    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JobProcessor())
                //初始访问url地址
                .addUrl("http://book.sina.com.cn")
                .run();

    }
}
