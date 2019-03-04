package com.zhang.genealogy.qb;

import lombok.Getter;
import lombok.Setter;

/**
 * 页码参数
 *
 * @Auther: zhangchao
 * @Date: 2019/3/4 11:53
 */
@Getter
@Setter
public class PageInfoQB {
    /**
     * 页码
     */
    Integer pageNum;

    /**
     * 条数
     */
    Integer pageSize;
}
