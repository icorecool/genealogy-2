package com.zhang.genealogy.service;

import com.zhang.genealogy.model.Personnel;

import java.util.List;

/**
 * 家人服务层
 */
public interface PersonnelService {

    /**
     * 家人列表
     *
     * @param personnel
     * @return
     */
    List<Personnel> queryPage(Personnel personnel);

    /**
     * 增加家人
     *
     * @param personnel
     * @return
     */
    int add(Personnel personnel);

    /**
     * 获取家人信息
     *
     * @param id
     * @return
     */
    Personnel queryById(Long id);
}
