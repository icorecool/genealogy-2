package com.zhang.genealogy.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * eacharts树形
 *
 * @Auther: zhangchao
 * @Date: 2019/4/16 14:20
 */
@Getter
@Setter
public class EchartsChild {

    String name;

    Long value;

    @Override
    public String toString() {
        return "EchartsChild{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
