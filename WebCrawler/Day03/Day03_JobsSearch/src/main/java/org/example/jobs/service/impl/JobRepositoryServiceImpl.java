package org.example.jobs.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.example.jobs.dao.JobRepository;
import org.example.jobs.pojo.JobInfo;
import org.example.jobs.pojo.JobInfoField;
import org.example.jobs.pojo.JobResult;
import org.example.jobs.service.JobRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.JobName;
import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-26 20:22
 */
@Service
public class JobRepositoryServiceImpl implements JobRepositoryService {
    @Autowired
    private JobRepository jobRepository;

    @Override
    public void save(JobInfoField jobInfoField) {
        jobRepository.save(jobInfoField);
    }

    @Override
    public void saveAll(List<JobInfoField> jobInfoFields) {
        jobRepository.saveAll(jobInfoFields);
    }

    @Override
    public JobResult search(String salary, String jobAddr, String keyWord, Integer page) {
        //薪资处理
        int salaryMin = 0, salaryMax = 0;
        String[] salarys = salary.split("-");
        //获取最小值
        if ("*".equals(salarys[0])) {
            salaryMin = 0;
        } else {
            salaryMin = Integer.parseInt(salarys[0]) * 10000;
        }
        //获取最大值
        if ("*".equals(salarys[1])) {
            salaryMax = 9000000;
        } else {
            salaryMax = Integer.parseInt(salarys[1]) * 10000;
        }
        //工作地址如果为空，只设置为*
        if (StringUtils.isBlank(jobAddr)) {
            jobAddr = "*";
        }
        //查询关键词为空，就设置为*
        if (StringUtils.isBlank(keyWord)) {
            keyWord = "*";
        }
        //获取分页,设置每页显示30条数据
        Pageable pageable = PageRequest.of(page - 1, 30);
        //执行查询
        Page<JobInfoField> jobInfoFields = jobRepository.findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(salaryMin, salaryMax, salaryMin, salaryMax, jobAddr, keyWord, keyWord, pageable);

        JobResult jobResult = new JobResult();
        //设置结果集
        jobResult.setRows(jobInfoFields.getContent());
        //设置总页数
        jobResult.setPageTotal(jobInfoFields.getContent().size());

        return jobResult;
    }
}
