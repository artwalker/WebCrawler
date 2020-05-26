package org.example.jobs.service.impl;

import org.example.jobs.dao.JobInfoDao;
import org.example.jobs.pojo.JobInfo;
import org.example.jobs.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-24 10:34
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {
    @Autowired
    private JobInfoDao jobInfoDao;

    @Override
    @Transactional
    public void save(JobInfo jobInfo) {
        //先从数据库查询数据,根据发布日期查询和url查询
        JobInfo param = new JobInfo();
        param.setUrl(jobInfo.getUrl());
        param.setTime(jobInfo.getTime());
        //执行查询
        List<JobInfo> list = findJobInfo(param);

        if (list.size() == 0) {
            //没有查询到数据则新增或者修改数据
            jobInfoDao.saveAndFlush(jobInfo);
        }
    }

    @Override
    public List<JobInfo> findJobInfo(JobInfo jobInfo) {
        //设置查询条件
        Example example = Example.of(jobInfo);
        //执行查询
        List<JobInfo> list = jobInfoDao.findAll(example);
        return list;
    }
}
