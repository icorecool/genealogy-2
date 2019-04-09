package com.zhang.genealogy.dto;

import com.zhang.genealogy.model.Personnel;
import lombok.Getter;
import lombok.Setter;

/**
 * 家人查询类
 */
@Getter
@Setter
public class PersonnelFormDTO {

    /**
     * 丈夫
     */
    Personnel husband;

    /**
     * 妻子
     */
    Personnel wife;
}
