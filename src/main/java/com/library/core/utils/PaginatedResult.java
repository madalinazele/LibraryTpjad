package com.library.core.utils;

import java.util.List;

public class PaginatedResult<RESULT> {

    private List<RESULT> resultList;

    private Integer totalPages;

    private Integer recordsPerPage;

    public List<RESULT> getResultList() {
        return resultList;
    }

    public void setResultList(List<RESULT> resultList) {
        this.resultList = resultList;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(Integer recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }
}
