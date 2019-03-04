package com.zhang.genealogy.dto;

import com.zhang.genealogy.model.Family;
import lombok.Getter;
import lombok.Setter;

/**
 * 家人查询类
 */
@Getter
@Setter
public class FamilyFormDTO {

    /**
     * 丈夫
     */
    Family husband;

    /**
     * 妻子
     */
    Family wife;
}
