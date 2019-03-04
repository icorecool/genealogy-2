package com.zhang.genealogy.qb;

import com.zhang.genealogy.model.Family;
import lombok.Getter;
import lombok.Setter;

/**
 * 家人查询类
 */
@Getter
@Setter
public class FamilyQB extends Family {

    /**
     * 页码
     */
    Integer pageNum;

    /**
     * 条数
     */
    Integer pageSize;
}
