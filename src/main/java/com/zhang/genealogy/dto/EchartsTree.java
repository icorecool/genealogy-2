package com.zhang.genealogy.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * eacharts树形
 *
 * @Auther: zhangchao
 * @Date: 2019/4/16 14:20
 */
@Getter
@Setter
public class EchartsTree {

    String name;

    Long value;

    List<EchartsTree> children;

}
