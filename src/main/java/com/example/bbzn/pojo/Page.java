package com.example.bbzn.pojo;

import java.util.List;

public class Page {

    private int totalRecord;        //总条数
    private int totalPage = 1;          //总页数
    private int pageSize;           //每页显示的记录数
    private int pageNum;            //当前页
    private List<?> list;           //记录

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        this.totalPage = this.totalRecord%this.pageSize == 0 ? (this.totalRecord/this.pageSize) : (this.totalRecord/this.pageSize+1);
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", list=" + list +
                '}';
    }

}
