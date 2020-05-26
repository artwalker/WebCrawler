package org.example.jobs.pojo;

import java.util.List;

/**
 * @author HackerStar
 * @create 2020-05-26 21:02
 */
public class JobResult {
    private List<JobInfoField> rows;
    private Integer pageTotal;

    @Override
    public String toString() {
        return "JobResult{" +
                "rows=" + rows +
                ", pageTotal=" + pageTotal +
                '}';
    }

    public List<JobInfoField> getRows() {
        return rows;
    }

    public void setRows(List<JobInfoField> rows) {
        this.rows = rows;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
