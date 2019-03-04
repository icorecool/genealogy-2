package com.zhang.genealogy.dao;

import com.zhang.genealogy.model.Family;
import com.zhang.genealogy.model.Files;

import java.util.List;

/**
 * 家人持久化接口
 *
 * @Auther: zhangchao
 * @Date: 2019/3/4 10:21
 */
public interface FamilyDAO {

    /**
     * 查询家人列表
     *
     * @param family
     * @return
     */
    List<Files> queryList(Family family);

    /**
     * 根据主键ID查询家人信息
     *
     * @param id
     * @return
     */
    Files queryById(Long id);

    /**
     * 插入家人信息
     *
     * @param family
     * @return
     */
    int insert(Family family);
}
