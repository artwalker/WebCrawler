package org.example.jobs.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author HackerStar
 * @create 2020-05-26 20:06
 */
@Document(indexName = "jobinfo", type = "JobInfoField")
public class JobInfoField {
    @Id
    @Field(index = true, store = true, type = FieldType.Long)
    private Long id;//主键ID
    @Field(index = false, store = true, type = FieldType.Text)
    private String companyName;//公司名称
    @Field(index = false, store = true, type = FieldType.Text)
    private String companyAddr;//公司联系方式
    @Field(index = false, store = true, type = FieldType.Text)
    private String companyInfo;//公司信息
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String jobName;//职位名称
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String jobAddr;//工作地点
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String jobInfo;//职位信息
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer salaryMin;//最小工资
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer salaryMax;//最大工资
    @Field(index = true, store = true, type = FieldType.Text)
    private String url;//招聘信息详情页
    @Field(index = true, store = true, type = FieldType.Text)
    private String time;//职位最近发布时间

    @Override
    public String toString() {
        return "JobInfoField{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyAddr='" + companyAddr + '\'' +
                ", companyInfo='" + companyInfo + '\'' +
                ", jobName='" + jobName + '\'' +
                ", jobAddr='" + jobAddr + '\'' +
                ", jobInfo='" + jobInfo + '\'' +
                ", salaryMin=" + salaryMin +
                ", salaryMax=" + salaryMax +
                ", url='" + url + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobAddr() {
        return jobAddr;
    }

    public void setJobAddr(String jobAddr) {
        this.jobAddr = jobAddr;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(String jobInfo) {
        this.jobInfo = jobInfo;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
