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
import java.util.ArrayList;
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
    public List<Personnel> queryList(Personnel personnel) {
        List<Personnel> personnelList = personnelDAO.queryList(personnel);
        return personnelList;
    }

    /**
     * 查看家庭列表
     *
     * @param id
     * @return
     */
    @Override
    public List<Personnel> queryFamily(Long id) {
        //TODO 添加关系
        //一家三代
        List<Personnel> personnelList = new ArrayList<>();

        //判断自己是不是配偶,
        //配偶没有parentId，所以需要转换.
        Personnel self = personnelDAO.queryById(id);
        if (null == self) {
            return new ArrayList<>();
        }

        List<Personnel> selfCoupleList = new ArrayList<>();
        //查询自己信息，自己配偶信息
        Long coupleId = self.getCoupleId();
        if (null != coupleId) {
            Personnel couple = new Personnel();
            couple = self;
            selfCoupleList.add(couple);
            self = personnelDAO.queryById(coupleId);
        } else {
            Personnel searchPer = new Personnel();
            searchPer.setCoupleId(id);
            selfCoupleList = queryList(searchPer);
        }

        //查询长辈信息，长辈配偶信息
        Personnel parent = new Personnel();
        List<Personnel> parentCoupleList = new ArrayList<>();
        Long parentId = self.getParentId();
        if (null != parentId) {
            parent = personnelDAO.queryById(parentId);
            Personnel searchPer = new Personnel();
            searchPer.setCoupleId(parent.getId());
            parentCoupleList = queryList(searchPer);
        }

        //添加进List
        if (parent.getId() != null) {
            personnelList.add(parent);
        }
        if (parentCoupleList.size() > 0) {
            personnelList.addAll(parentCoupleList);
        }
        personnelList.add(self);
        if (personnelList.size() > 0) {
            personnelList.addAll(selfCoupleList);
        }

        //查询子辈信息，子辈配偶信息
        List<Personnel> childList = new ArrayList<>();
        Personnel searchChild = new Personnel();
        searchChild.setParentId(self.getId());
        childList = queryList(searchChild);
        for (Personnel child : childList) {
            personnelList.add(child);
            //查询子辈配偶信息
            Personnel searchPer = new Personnel();
            searchPer.setCoupleId(child.getId());
            List<Personnel> childCoupleList = queryList(searchPer);
            personnelList.addAll(childCoupleList);
        }

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
