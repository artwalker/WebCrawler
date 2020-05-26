package org.example.jobs.controller;

import org.example.jobs.dao.JobRepository;
import org.example.jobs.pojo.JobResult;
import org.example.jobs.service.JobRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HackerStar
 * @create 2020-05-26 21:06
 */
@RestController
public class SearchController {
    @Autowired
    private JobRepositoryService jobRepositoryService;

    /**
     * 根据条件分页查询数据
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public JobResult indexSearch(String salary,String jobaddr,String keyword,Integer page) {
        JobResult jobResult = jobRepositoryService.search(salary, jobaddr, keyword, page);
        return jobResult;
    }
}
