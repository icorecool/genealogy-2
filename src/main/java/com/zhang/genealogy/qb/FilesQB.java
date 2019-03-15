package com.zhang.genealogy.qb;

import com.zhang.genealogy.model.Files;
import lombok.Getter;
import lombok.Setter;

/**
 * 文件qb类
 *
 * @Auther: zhangchao
 * @Date: 2019/3/4 11:58
 */
@Getter
@Setter
public class FilesQB extends Files {

    /**
     * 页码
     */
    Integer pageNum;

    /**
     * 条数
     */
    Integer pageSize;
}
