package org.example.jobs.dao;

import org.example.jobs.pojo.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author HackerStar
 * @create 2020-05-24 10:29
 */
public interface JobInfoDao extends JpaRepository<JobInfo, Long> {
}
