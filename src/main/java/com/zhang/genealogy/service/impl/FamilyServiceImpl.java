package com.zhang.genealogy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.dao.FamilyDAO;
import com.zhang.genealogy.dto.FamilyFormDTO;
import com.zhang.genealogy.model.Family;
import com.zhang.genealogy.qb.FamilyQB;
import com.zhang.genealogy.service.FamilyService;
import com.zhang.genealogy.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 家人服务层实现类
 */
@Service
public class FamilyServiceImpl implements FamilyService {

    @Resource
    private FamilyDAO familyDAO;

    /**
     * 家人分页列表
     *
     * @param familyQB
     * @return
     */
    @Override
    public PageInfo<Family> queryPage(FamilyQB familyQB) {
        // 分页参数校验
        Integer pageNum = familyQB.getPageNum();
        if (null == pageNum || pageNum < 1) {
            pageNum = 1;
        }
        Integer pageSize = familyQB.getPageSize();
        if (null == pageSize || pageSize < 1) {
            pageSize = 10;
        }
        // 启用分页
        PageHelper.startPage(pageNum, pageSize);
        List<Family> familyList = familyDAO.queryList(familyQB);
        PageInfo<Family> familyPageInfo = new PageInfo<>(familyList);

        return familyPageInfo;
    }

    /**
     * 新增家人
     *
     * @param familyFormDTO
     */
    @Override
    public void addFamily(FamilyFormDTO familyFormDTO) {
        //TODO 获取上一代
        //参数校验
        Family husband = familyFormDTO.getHusband();
        addFamily(husband);
        Family wife = familyFormDTO.getWife();
        if (null != wife) {
            wife.setHusbandId(husband.getId());
            addFamily(husband);
        }

    }


    /**
     * 新增家人
     *
     * @param family
     * @return
     */
    public int addFamily(Family family) {
        family.setCreateTime(DateUtil.getCurrentDate());
        family.setUpdateTime(DateUtil.getCurrentDate());
        family.setDeleteStatus(Constants.DELETE_STATUS_OK);
        return familyDAO.insert(family);
    }
}
