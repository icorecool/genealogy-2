package com.zhang.genealogy.controller;

import com.zhang.genealogy.dto.EchartsTree;
import com.zhang.genealogy.dto.PersonnelFormDTO;
import com.zhang.genealogy.dto.Result;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import com.zhang.genealogy.model.Personnel;
import com.zhang.genealogy.service.PersonnelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 家人控制层
 */
@Controller
@RequestMapping("/personnel")
public class PersonnelController {

    /**
     * 家人服务类
     */
    @Resource
    private PersonnelService personnelService;

    /**
     * 查看家人列表
     *
     * @param personnel
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result queryList(Personnel personnel) {
        List<Personnel> personnelList = personnelService.queryList(personnel);
        Result result = new Result();
        result.addObject("list", personnelList);
        return result;
    }

    /**
     * 查看家庭列表
     *
     * @param id
     * @return
     */
    @RequestMapping("/family")
    @ResponseBody
    public Result queryFamily(Long id) {
        List<Personnel> personnelList = personnelService.queryFamily(id);
        Result result = new Result();
        result.addObject("list", personnelList);
        return result;
    }

    /**
     * 查看家庭树
     *
     * @return
     */
    @RequestMapping("/tree")
    @ResponseBody
    public Result queryFamilyTree() {
        //TODO 将数据库中始祖parentId改为0
        Long id = 0L;
        EchartsTree echartsTree = personnelService.queryFamilyTree(id);
        Result result = new Result();
        result.addObject("tree", echartsTree);
        return result;
    }


    /**
     * 增加/修改家人信息
     *
     * @param personnel
     * @return
     */
    @RequestMapping("/submit")
    @ResponseBody
    public Result submit(Personnel personnel) {
        personnelService.submit(personnel);
        Result result = new Result();
        return result;
    }


    /**
     * 获取家人信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Result queryById(Long id) {
        if (id == null) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY, "人员ID");
        }
        PersonnelFormDTO personnelFormDTO = personnelService.queryById(id);
        Result result = new Result();
        result.addObject("personnel", personnelFormDTO);
        return result;
    }


    /**
     * 删除家人信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Result delById(Long id) {
        if (id == null) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY, "人员ID");
        }
        personnelService.delById(id);
        Result result = new Result();
        return result;
    }
}
