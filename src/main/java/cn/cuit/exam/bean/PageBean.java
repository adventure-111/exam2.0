package cn.cuit.exam.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PageBean<Map> {
    @ApiModelProperty(value = "总记录条数")
    private int totalCount; // 总记录条数
    @ApiModelProperty(value = "每页数据条数")
    private int pageSize;       // 每页数据条数
    @ApiModelProperty(value = "总页码")
    private int totalPage;  // 总页码
    @ApiModelProperty(value = "当前页码")
    private int pageNum;// 当前页码
    @ApiModelProperty(value = "当前页面数据")
    private List<Map> list;   // 当前页面数据

    public PageBean() {
    }

    public PageBean(int totalCount, List<Map> list, int pageSize, int pageNum) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = getTotalPage();
        this.pageNum = pageNum;
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage() {
        totalPage = totalCount % pageSize == 0 ?
                totalCount / pageSize : totalCount / pageSize + 1;
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<Map> getList() {
        return list;
    }

    public void setList(List<Map> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", pageNum=" + pageNum +
                ", list=" + list +
                '}';
    }
}
