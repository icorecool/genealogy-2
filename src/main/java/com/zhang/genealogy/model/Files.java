package com.zhang.genealogy.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 上传文件类
 *
 * @author zhangchao
 * @date 2019-01-29
 */
@Getter
@Setter
public class Files {

    /**
     * 主键
     */
    private Long id;

    /**
     * 文件路径
     */
    private String url;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建人展示姓名
     */
    private String createShowName;

    /**
     * 文件状态：0-正常；1-删除
     */
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    private Date createTime;


}