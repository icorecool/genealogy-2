package com.zhang.genealogy.dao;

import com.zhang.genealogy.model.Files;

import java.util.List;

/**
 * 上传文件持久化接口
 *
 * @Auther: zhangchao
 * @Date: 2019/3/4 10:21
 */
public interface FilesDAO {

    /**
     * 查询上传文件列表
     *
     * @param files
     * @return
     */
    List<Files> queryList(Files files);

    /**
     * 插入上传文件信息
     *
     * @param files
     * @return
     */
    int insert(Files files);
}
