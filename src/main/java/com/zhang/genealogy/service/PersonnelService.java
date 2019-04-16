package com.zhang.genealogy.service;

import com.zhang.genealogy.dto.PersonnelFormDTO;
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
    List<Personnel> queryList(Personnel personnel);

    /**
     * 家庭列表
     *
     * @param id
     * @return
     */
    List<Personnel> queryFamily(Long id);


    /**
     * 增加/修改家人信息
     *
     * @param personnel
     * @return
     */
    int submit(Personnel personnel);

    /**
     * 获取家人信息
     *
     * @param id
     * @return
     */
    PersonnelFormDTO queryById(Long id);

    /**
     * 删除家人信息
     *
     * @param id
     * @return
     */
    int delById(Long id);

}
