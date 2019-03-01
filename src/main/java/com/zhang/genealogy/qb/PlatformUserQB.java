package com.zhang.genealogy.qb;

import com.zhang.genealogy.model.PlatformUser;

/**
 * 平台用户查询条件
 *
 * @Auther: zhangchao
 * @Date: 2019/2/27 15:38
 */
public class PlatformUserQB extends PlatformUser {

    /**
     * 查询参数
     */
    private String queryParam;

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }
}
