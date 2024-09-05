package cn.lb.subject.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页结果类，封装分页查询的结果
 *
 * @author RainSoul
 * @create 2024-09-05
 */
@Data
public class PageResult<T> implements Serializable {
    // 当前页码，默认为1
    private Integer pageNo = 1;

    // 每页显示的大小，默认为10
    private Integer pageSize = 10;

    // 总记录数，默认为0
    private Integer total = 0;

    // 总页数，默认为0
    private Integer totalPages = 0;

    // 存储分页后的结果列表，默认为空列表
    private List<T> result = Collections.emptyList();

    // 分页数据的起始编号，默认为1
    private Integer start = 1;

    // 分页数据的结束编号，默认为0
    private Integer end = 0;

    /**
     * 设置分页后的结果列表
     * @param result 分页后的数据列表
     */
    public void setRecords(List<T> result) {
        this.result = result;
        // 如果结果列表非空且有数据，设置总记录数
        if (result != null && result.size() > 0) {
            setTotal(result.size());
        }
    }

    /**
     * 设置总记录数，并更新总页数和分页的起始、结束编号
     * @param total 总记录数
     */
    public void setTotal(Integer total) {
        this.total = total;
        // 根据总记录数和每页显示大小计算总页数
        if (this.pageSize > 0) {
            this.totalPages = (total / this.pageSize) + (total % this.pageSize == 0 ? 0 : 1);
        } else {
            this.totalPages = 0;
        }
        // 计算分页数据的起始编号
        this.start = (this.pageSize > 0 ? (this.pageNo - 1) * this.pageSize : 0) + 1;
        // 计算分页数据的结束编号
        this.end = (this.start - 1 + this.pageSize * (this.pageNo > 0 ? 1 : 0));
    }

    /**
     * 设置每页显示的大小
     * @param pageSize 每页显示的大小
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置当前页码
     * @param pageNo 当前页码
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
