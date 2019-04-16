package com.zhang.genealogy.service.impl;

import cn.hutool.core.date.DateUtil;
import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.dao.PersonnelDAO;
import com.zhang.genealogy.dto.PersonnelFormDTO;
import com.zhang.genealogy.model.Personnel;
import com.zhang.genealogy.service.PersonnelService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 家人服务层实现类
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

    /**
     * 家人持久化接口
     */
    @Resource
    private PersonnelDAO personnelDAO;

    /**
     * 家人分页列表
     *
     * @param personnel
     * @return
     */
    @Override
    public List<Personnel> queryPage(Personnel personnel) {
        List<Personnel> personnelList = personnelDAO.queryList(personnel);
        return personnelList;
    }

    /**
     * 增加/修改家人信息
     *
     * @param personnel
     * @return
     */
    @Override
    public int submit(Personnel personnel) {
        //计算世代. 父辈世代+1，或跟配偶保持一致
        Long parentId = personnel.getParentId();
        if (null != parentId) {
            Personnel parentPersonnel = queryById(parentId);
            personnel.setGeneration(parentPersonnel.getGeneration() + 1);
        } else {
            personnel.setGeneration(1);
        }
        Long coupleId = personnel.getCoupleId();
        if (null != coupleId) {
            Personnel couplePersonnel = queryById(coupleId);
            personnel.setGeneration(couplePersonnel.getGeneration());
        }

        //赋值
        personnel.setDeleteStatus(Constants.DELETE_STATUS_OK);
        personnel.setUpdateTime(DateUtil.date());
        if (null == personnel.getId()) {
            personnel.setCreateTime(DateUtil.date());
            return personnelDAO.insert(personnel);
        } else {
            return personnelDAO.updateById(personnel);
        }
    }

    /**
     * 获取家人信息
     *
     * @param id
     * @return
     */
    @Override
    public PersonnelFormDTO queryById(Long id) {
        PersonnelFormDTO personnelFormDTO = new PersonnelFormDTO();
        Personnel personnel = personnelDAO.queryById(id);
        BeanUtils.copyProperties(personnel, personnelFormDTO);
        //查询长辈姓名
        Long parentId = personnel.getParentId();
        if (null != parentId) {
            Personnel parentPersonnel = personnelDAO.queryById(parentId);
            personnelFormDTO.setParentName(parentPersonnel.getName());
        }
        //查询配偶姓名
        Long coupleId = personnel.getCoupleId();
        if (null != coupleId) {
            Personnel couplePersonnel = personnelDAO.queryById(coupleId);
            personnelFormDTO.setCoupleName(couplePersonnel.getName());
        }
        return personnelFormDTO;
    }

    /**
     * 删除家人信息
     *
     * @param id
     * @return
     */
    @Override
    public int delById(Long id) {
        return personnelDAO.delById(id);
    }

}
