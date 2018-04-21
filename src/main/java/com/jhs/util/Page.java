package com.jhs.util;

import java.util.List;

/**
 * @author: TangNengFa
 * @descption: 分页类
 * @create: 2018-04-13-10-08
 **/
public class Page<T> {
    public Integer pageSize = 10; //分页大小
    private Integer count; //总记录数
    private List<T> pageList; //当前的记录集合
    private Integer pageIndex; //当前的页号
    private Integer totalPages; //总页数

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getTotalPages() {
        this.totalPages = this.count / this.pageSize;
        if (this.count % this.pageSize != 0) this.totalPages++;
        return this.totalPages;
    }

}
