package org.example.jobs.service;

import org.example.jobs.pojo.JobInfo;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-24 10:32
 */
public interface JobInfoService {
    /**
     * 保存工作信息
     *
     * @param jobInfo
     */
    public void save(JobInfo jobInfo);


    /**
     * 根据条件查询工作信息
     *
     * @param jobInfo
     * @return
     */
    public List<JobInfo> findJobInfo(JobInfo jobInfo);
}
