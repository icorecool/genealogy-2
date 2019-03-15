package com.zhang.genealogy.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.genealogy.dto.Result;
import com.zhang.genealogy.dto.FamilyFormDTO;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import com.zhang.genealogy.model.Family;
import com.zhang.genealogy.qb.FamilyQB;
import com.zhang.genealogy.service.FamilyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 家人控制层
 */
@Controller
@RequestMapping("/family")
public class FamilyController {

    /**
     * 家人服务类
     */
    @Resource
    private FamilyService familyService;

    /**
     * 查看家人分页列表
     *
     * @param familyQB
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Result queryByPage(FamilyQB familyQB) {
        PageInfo<Family> familyPageInfo = familyService.queryPage(familyQB);
        Result result = new Result();
        result.addObject("page", familyPageInfo);
        return result;
    }

    /**
     * 新增家人
     *
     * @param familyFormDTO
     * @return
     */
    @RequestMapping("/add")
    public Result addFamily(FamilyFormDTO familyFormDTO) {
        if (null == familyFormDTO) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY);
        }
        if (null == familyFormDTO.getHusband()) {
            throw new CommonException(ErrorCode.PARAM_IS_EMPTY);
        }
        familyService.addFamily(familyFormDTO);
        Result result = new Result();
        return result;
    }

}
