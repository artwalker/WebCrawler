package org.example.jobs.service;

import org.example.jobs.pojo.JobInfoField;
import org.example.jobs.pojo.JobResult;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-26 20:22
 */
public interface JobRepositoryService {
    /**
     * 保存
     */
    void save(JobInfoField jobInfoField);

    /**
     * 批量保存数据
     */
    void saveAll(List<JobInfoField> jobInfoFields);

    JobResult search(String salary, String jobAddr, String keyword, Integer page);
}
