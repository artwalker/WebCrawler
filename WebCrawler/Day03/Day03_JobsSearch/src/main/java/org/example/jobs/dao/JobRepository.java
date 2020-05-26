package org.example.jobs.dao;

import org.example.jobs.pojo.JobInfoField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author HackerStar
 * @create 2020-05-26 20:20
 */
public interface JobRepository extends ElasticsearchRepository<JobInfoField, Long> {
    Page<JobInfoField> findBySalaryMinBetweenAndSalaryMaxBetweenAndJobAddrAndJobNameAndJobInfo(int salaryMin, int salaryMax, int salaryMin1, int salaryMax1, String jobAddr, String keyWord, String keyWord1, Pageable pageable);
}
