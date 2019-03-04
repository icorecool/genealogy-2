package com.zhang.genealogy.service;

import com.github.pagehelper.PageInfo;
import com.zhang.genealogy.dto.FamilyFormDTO;
import com.zhang.genealogy.model.Family;
import com.zhang.genealogy.qb.FamilyQB;

/**
 * 家人服务层
 */
public interface FamilyService {

    /**
     * 家人分页列表
     *
     * @param familyQB
     * @return
     */
    PageInfo<Family> queryPage(FamilyQB familyQB);

    /**
     * 新增家人
     *
     * @param familyFormDTO
     */
    void addFamily(FamilyFormDTO familyFormDTO);
}
