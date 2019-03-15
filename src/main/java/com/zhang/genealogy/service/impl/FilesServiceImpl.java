package com.zhang.genealogy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.dao.FilesDAO;
import com.zhang.genealogy.model.Files;
import com.zhang.genealogy.qb.FilesQB;
import com.zhang.genealogy.service.FilesService;
import com.zhang.genealogy.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 上传文件记录
 *
 * @Auther: zhangchao
 * @Date: 2019/3/4 11:20
 */
@Service
public class FilesServiceImpl implements FilesService {

    /**
     * 上传文件DAO
     */
    @Resource
    private FilesDAO filesDAO;

    /**
     * 插入上传文件记录表
     *
     * @param files
     * @return
     */
    @Override
    public int insert(Files files) {
        files.setCreateTime(DateUtil.getCurrentDate());
        files.setDeleteStatus(Constants.DELETE_STATUS_OK);
        return filesDAO.insert(files);
    }

    /**
     * 分页查询文件列表
     *
     * @param filesQB
     * @return
     */
    @Override
    public PageInfo<Files> queryPage(FilesQB filesQB) {
        // 分页参数校验
        Integer pageNum = filesQB.getPageNum();
        if (null == pageNum || pageNum < 1) {
            pageNum = 1;
        }
        Integer pageSize = filesQB.getPageSize();
        if (null == pageSize || pageSize < 1) {
            pageSize = 10;
        }

        // 启用分页
        PageHelper.startPage(pageNum, pageSize);
        Files files = new Files();
        BeanUtils.copyProperties(filesQB, files);
        List<Files> filesList = filesDAO.queryList(files);
        // 构造分页对象
        PageInfo<Files> filesPage = new PageInfo<>(filesList);
        return filesPage;
    }
}
