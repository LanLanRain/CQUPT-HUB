package cn.lb.subject.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页信息类，用于封装分页查询的参数
 *
 * @author [Your Name]
 * @version 1.0
 */
@Data
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     */
    private Integer pageNo = 1;

    /**
     * 每页显示条数
     */
    private Integer pageSize = 10;

    /**
     * 获取经过验证的页码
     * 如果页码为null或小于1，则默认返回第1页
     *
     * @return 经过验证的页码
     */
    public Integer getPageNo() {
        if (pageNo == null || pageNo < 1) {
            return 1;
        }
        return pageNo;
    }

    /**
     * 获取经过验证的每页显示条数
     * 如果每页显示条数为null、小于1或超过最大值，则默认返回20
     *
     * @return 经过验证的每页显示条数
     */
    public Integer getPageSize() {
        if (pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE) {
            return 10;
        }
        return pageSize;
    }
}

