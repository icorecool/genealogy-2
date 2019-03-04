package com.zhang.genealogy.service;

import com.github.pagehelper.PageInfo;
import com.zhang.genealogy.model.Files;
import com.zhang.genealogy.qb.FilesQB;

/**
 * 上传文件记录表
 */
public interface FilesService {

    /**
     * 插入上传文件记录表
     *
     * @param files
     * @return
     */
    int insert(Files files);

    /**
     * 分页查询文件列表
     *
     * @param filesQB
     * @return
     */
    PageInfo<Files> queryPage(FilesQB filesQB);
}
