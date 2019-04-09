package com.zhang.genealogy.service.impl;

import com.zhang.genealogy.dao.PersonnelDAO;
import com.zhang.genealogy.model.Personnel;
import com.zhang.genealogy.service.PersonnelService;
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

}
