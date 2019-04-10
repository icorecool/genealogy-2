package com.zhang.genealogy.controller;

import com.zhang.genealogy.dto.Result;
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
     * 查看家人分页列表
     *
     * @param personnel
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result queryByPage(Personnel personnel) {
        List<Personnel> personnelList = personnelService.queryPage(personnel);
        Result result = new Result();
        result.addObject("list", personnelList);
        return result;
    }

    /**
     * 增加家人
     *
     * @param personnel
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Result add(Personnel personnel) {
        personnelService.add(personnel);
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
        Result result = new Result();
        if (id == null) {
            return result;
        }
        Personnel personnel = personnelService.queryById(id);
        result.addObject("personnel", personnel);
        return result;
    }


}
