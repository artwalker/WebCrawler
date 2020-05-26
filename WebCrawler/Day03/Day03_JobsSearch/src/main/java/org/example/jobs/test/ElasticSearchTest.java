package org.example.jobs.test;

import org.example.jobs.Application;
import org.example.jobs.pojo.JobInfo;
import org.example.jobs.pojo.JobInfoField;
import org.example.jobs.service.JobInfoService;
import org.example.jobs.service.JobRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

/**
 * @author HackerStar
 * @create 2020-05-26 20:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ElasticSearchTest {
    @Autowired
    private JobInfoService jobInfoService;
    @Autowired
    private JobRepositoryService jobRepositoryService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 创建索引和映射
     */
    @Test
    public void createIndex() {
        elasticsearchTemplate.createIndex(JobInfoField.class);
        elasticsearchTemplate.putMapping(JobInfoField.class);
    }

    /**
     * 导出数据库的数据到索引库
     */
    @Test
    public void jobData() {
        //声明当前页码数
        int count = 1;
        //声明查询数据条数
        int pageSize = 0;

        //循环查询
        do{
            //从MySQL数据库中分页查询数据
            Page<JobInfo> page = jobInfoService.findJobInfoByPage(count, 500);
            //声明存放索引库数据的容器
            ArrayList<JobInfoField> list = new ArrayList<>();
            //遍历查询结果
            for (JobInfo jobInfo :
                    page.getContent()) {
                //创建存放索引库数据的对象
                JobInfoField jobInfoField = new JobInfoField();
                //复制数据
                BeanUtils.copyProperties(jobInfo, jobInfoField);
                //把复制好的数据放到容器中
                list.add(jobInfoField);
            }
            //批量保存数据到索引库中
            jobRepositoryService.saveAll(list);
            //页面加一
            count++;
            //获取查询数据条数
            pageSize = page.getContent().size();
        }while (pageSize == 500);
    }
}
