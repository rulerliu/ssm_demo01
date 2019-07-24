package com.liuwq.po;

import io.swagger.annotations.ApiModelProperty;

public class PaginationData<T>{
    private T rows;
    @ApiModelProperty("总页数")
    private long total;

    public T getRows() {
        return this.rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public PaginationData(T rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public PaginationData() {
    }

    public String toString() {
        return "PaginationData{rows=" + this.rows + ", total=" + this.total + '}';
    }
}
