package org.example.jobs.task;

import org.example.jobs.pojo.JobInfo;
import org.example.jobs.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author HackerStar
 * @create 2020-05-24 17:01
 */
@Component
public class SpringDataPipeline implements Pipeline {
    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的招聘详情对象
        JobInfo jobInfo = resultItems.get("jobInfo");

        //判断数据是否不为空
        if (jobInfo != null) {
            //如果不为空把数据保存到数据库中
            jobInfoService.save(jobInfo);
        }
    }
}
