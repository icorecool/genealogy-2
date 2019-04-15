package com.zhang.genealogy.dto;

import com.zhang.genealogy.model.Personnel;
import lombok.Getter;
import lombok.Setter;

/**
 * 家人查询类
 */
@Getter
@Setter
public class PersonnelFormDTO extends Personnel {

    /**
     * 父母姓名
     */
    String parentName;

    /**
     * 配偶姓名
     */
    String coupleName;
}
