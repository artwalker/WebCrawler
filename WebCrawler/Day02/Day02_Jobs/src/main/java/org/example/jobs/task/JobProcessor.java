package org.example.jobs.task;

import org.example.jobs.pojo.JobInfo;
import org.example.jobs.utils.MathSalary;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-24 10:41
 */
@Component
public class JobProcessor implements PageProcessor {
    private String url = "https://search.51job.com/list/000000,000000,0000,01%252C32,9,99,Java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";

    @Autowired
    private SpringDataPipeline springDataPipeline;

    //initialDelay:当任务启动后，等待多久执行方法
    //fixedDelay:每隔多久执行方法
    //@Scheduled(initialDelay = 1000, fixedDelay = 10 * 1000)
    @Scheduled(initialDelay = 1000, fixedDelay = 10 * 1000)
    public void process() {
        Spider.create(new JobProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .addPipeline(springDataPipeline)
                .thread(5)
                .run();
    }

    @Override
    public void process(Page page) {
        //解析页面，获取招聘信息详情的url地址
        List<Selectable> list = page.getHtml().css("div#resultList div.el").nodes();
        //判断获取到的集合是否为空
        if (list.size() == 0) {
            //如果为空，表示这是招聘详情页,解析页面，获取招聘详情信息，保存数据
            saveJobInfo(page);
        } else {
            //如果不为空，表示这是列表页,解析出详情页的url地址，放到任务队列中
            for (Selectable selectable : list) {
                //获取url地址
                String jobInfoUrl = selectable.links().toString();
                //把获取到的url地址放到任务队列中
                page.addTargetRequest(jobInfoUrl);
            }
            //获取下一页的url(总共有两个bk一个上一页一个下一页，0表示第一个，1表示第二个)
            String bkUrl = page.getHtml().css("div.p_in li.bk").nodes().get(1).links().toString();
            //把url放到任务队列中
            page.addTargetRequest(bkUrl);
        }
    }

    //解析页面，获取招聘详情信息，保存数据
    private void saveJobInfo(Page page) {
        //创建招聘详情对象
        JobInfo jobInfo = new JobInfo();
        //解析页面
        Html html = page.getHtml();
        //获取数据，封装到对象中
        //jobInfo.setCompanyName(Jsoup.parse(html.css("div.cn p.cname a","text").toString()).text());
        jobInfo.setCompanyName(html.css("div.cn p.cname a","text").toString());
        /*
        System.out.println(Jsoup.parse(html.css("div.cn p.cname a").toString()).text());-------1
        System.out.println(html.css("di.cn p.cname a", "text"));-------2
        System.out.println(html.css("div.cn p.cname a", "text").toString());--------3
        这三个方式打印效果相同,其中第二个第三个因为打印，如果不调用toString方法打印，默认使用toString方法打印，如果是设置属性，不会自动转换为String类型
        所以解析使用第一和第三中方式是一样的。
         */
        String companyAddr = Jsoup.parse(html.css("div.bmsg").nodes().get(1).toString()).text();
        if (companyAddr.contains("地图")) {
            jobInfo.setCompanyAddr(companyAddr.substring(0, companyAddr.length()-2));
        } else {
            jobInfo.setCompanyAddr(companyAddr);
        }
        jobInfo.setCompanyInfo(html.css("div.tmsg", "text").toString());
        jobInfo.setJobName(html.css("div.cn h1", "text").toString());
        String jobAddrAndTime = Jsoup.parse(html.css("p.msg").toString()).text();
        jobInfo.setJobAddr(jobAddrAndTime.substring(0, 2));
        jobInfo.setJobInfo(Jsoup.parse(html.css("div.job_msg").toString()).text());
        jobInfo.setUrl(page.getUrl().toString());

        //获取薪资(年薪)
        Integer[] salary = MathSalary.getSalary(html.css("div.cn strong", "text").toString());
        jobInfo.setSalaryMin(salary[0]);
        jobInfo.setSalaryMax(salary[1]);

        //职位发布时间
        String time = jobAddrAndTime.substring(jobAddrAndTime.length()-7, jobAddrAndTime.length()-2);
        jobInfo.setTime(time);

        //保存数据
        page.putField("jobInfo", jobInfo);

//        System.out.println();//设置断点，调试用
    }


    private Site site = Site.me()
            .setCharset("gbk")//设置编码
            .setTimeOut(10*1000)//设置超时时间
            .setRetrySleepTime(3000)//设置重试的间隔时间
            .setRetryTimes(3);//设置重试的次数
    @Override
    public Site getSite() {
        return site;
    }
}
