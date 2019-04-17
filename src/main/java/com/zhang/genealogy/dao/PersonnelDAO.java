package com.zhang.genealogy.dao;

import com.zhang.genealogy.dto.PersonnelFormDTO;
import com.zhang.genealogy.model.Personnel;

import java.util.List;

/**
 * 家人持久化接口
 *
 * @Auther: zhangchao
 * @Date: 2019/3/4 10:21
 */
public interface PersonnelDAO {

    /**
     * 查询家人列表
     *
     * @param personnel
     * @return
     */
    List<Personnel> queryList(PersonnelFormDTO personnel);

    /**
     * 根据主键ID查询家人信息
     *
     * @param id
     * @return
     */
    Personnel queryById(Long id);

    /**
     * 插入家人信息
     *
     * @param personnel
     * @return
     */
    int insert(Personnel personnel);

    /**
     * 删除家人信息
     *
     * @param id
     * @return
     */
    int delById(Long id);

    /**
     * 更新家人信息
     *
     * @param personnel
     * @return
     */
    int updateById(Personnel personnel);
}
