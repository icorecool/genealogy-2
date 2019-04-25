package com.zhang.genealogy.service.impl;

import cn.hutool.core.date.DateUtil;
import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.dao.PersonnelDAO;
import com.zhang.genealogy.dto.EchartsTree;
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
    public List<Personnel> queryList(PersonnelFormDTO personnel) {
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
    public List<PersonnelFormDTO> queryFamily(Long id) {
        //一家三代
        List<PersonnelFormDTO> personnelList = new ArrayList<>();

        //判断自己是不是配偶,
        //配偶没有parentId，所以需要转换.
        Personnel self = queryById(id);
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
            self = queryById(coupleId);
        } else {
            PersonnelFormDTO searchPer = new PersonnelFormDTO();
            searchPer.setCoupleId(id);
            selfCoupleList = queryList(searchPer);
        }

        //查询长辈信息，长辈配偶信息
        Personnel parent = new Personnel();
        List<Personnel> parentCoupleList = new ArrayList<>();
        Long parentId = self.getParentId();
        if (null != parentId && parentId != 0) {
            parent = queryById(parentId);
            if (null != parent) {
                PersonnelFormDTO searchPer = new PersonnelFormDTO();
                searchPer.setCoupleId(parent.getId());
                parentCoupleList = queryList(searchPer);
            }
        }

        //添加进List
        if (parent.getId() != null) {
            PersonnelFormDTO parentPersonnel = new PersonnelFormDTO();
            BeanUtils.copyProperties(parent, parentPersonnel);
            parentPersonnel.setRelative("父亲");
            personnelList.add(parentPersonnel);
        }
        if (parentCoupleList.size() > 0) {
            for (Personnel personnel : parentCoupleList) {
                PersonnelFormDTO personnelDTO = new PersonnelFormDTO();
                BeanUtils.copyProperties(personnel, personnelDTO);
                personnelDTO.setRelative("母亲");
                personnelList.add(personnelDTO);
            }
        }

        PersonnelFormDTO selfDTO = new PersonnelFormDTO();
        BeanUtils.copyProperties(self, selfDTO);
        selfDTO.setRelative("");
        personnelList.add(selfDTO);

        if (personnelList.size() > 0) {
            for (Personnel personnel : selfCoupleList) {
                PersonnelFormDTO personnelDTO = new PersonnelFormDTO();
                BeanUtils.copyProperties(personnel, personnelDTO);
                personnelDTO.setRelative("妻子");
                personnelList.add(personnelDTO);
            }
        }

        //查询子辈信息，子辈配偶信息
        List<Personnel> childList = new ArrayList<>();
        PersonnelFormDTO searchChild = new PersonnelFormDTO();
        searchChild.setParentId(self.getId());
        childList = queryList(searchChild);
        for (Personnel child : childList) {
            PersonnelFormDTO childDTO = new PersonnelFormDTO();
            BeanUtils.copyProperties(child, childDTO);
            if (child.getGender() == 0) {
                childDTO.setRelative("女儿");
            } else {
                childDTO.setRelative("儿子");
            }
            personnelList.add(childDTO);
            //查询子辈配偶信息
            PersonnelFormDTO searchPer = new PersonnelFormDTO();
            searchPer.setCoupleId(child.getId());
            List<Personnel> childCoupleList = queryList(searchPer);
            for (Personnel personnel : childCoupleList) {
                PersonnelFormDTO personnelDTO = new PersonnelFormDTO();
                BeanUtils.copyProperties(personnel, personnelDTO);
                if (personnel.getGender() == 0) {
                    personnelDTO.setRelative("儿媳妇");
                } else {
                    personnelDTO.setRelative("女婿");
                }
                personnelList.add(personnelDTO);
            }
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
        if (null != parentId && parentId != 0) {
            Personnel parentPersonnel = queryById(parentId);
            if (null != parentPersonnel) {
                personnel.setGeneration(parentPersonnel.getGeneration() + 1);
            } else {
                personnel.setGeneration(1);
            }
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
        if (null != parentId && parentId != 0) {
            Personnel parentPersonnel = personnelDAO.queryById(parentId);
            if (null != parentPersonnel) {
                personnelFormDTO.setParentName(parentPersonnel.getName());
            }
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

    /**
     * 查看家庭树 - 递归
     *
     * @param id
     * @return
     */
    @Override
    public EchartsTree queryFamilyTree(Long id) {
        EchartsTree echartsTree = new EchartsTree();

        PersonnelFormDTO searchPer = new PersonnelFormDTO();
        searchPer.setId(id);
        List<Personnel> personnelList = personnelDAO.queryList(searchPer);
        if (personnelList.size() != 1) {
            return null;
        } else {
            Personnel personnel = personnelList.get(0);
            echartsTree.setName(personnel.getName());
            echartsTree.setValue(personnel.getId());
            List<EchartsTree> tree = queryFamilyChildren(personnel.getId());
            if (null != tree) {
                echartsTree.setChildren(tree);
            }
            return echartsTree;
        }

    }

    private List<EchartsTree> queryFamilyChildren(Long id) {
        List<EchartsTree> list = new ArrayList<>();

        PersonnelFormDTO searchPer = new PersonnelFormDTO();
        searchPer.setParentId(id);
        List<Personnel> personnelList = personnelDAO.queryList(searchPer);

        if (personnelList.size() == 0) {
            return null;
        }

        for (Personnel personnel : personnelList) {
            EchartsTree echartsTree = new EchartsTree();
            echartsTree.setName(personnel.getName());
            echartsTree.setValue(personnel.getId());
            list.add(echartsTree);

            List<EchartsTree> tree = this.queryFamilyChildren(personnel.getId());
            if (null != tree) {
                echartsTree.setChildren(tree);
            } else {
                echartsTree.setChildren(new ArrayList<>());

            }
        }

        return list;
    }

}
