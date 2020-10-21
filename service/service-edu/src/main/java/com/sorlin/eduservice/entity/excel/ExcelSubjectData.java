package com.sorlin.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @program: guli-parent
 * @description: Excel对应的实体类
 * @author: sorlin
 * @create: 2020-08-01 08:55
 */
@Data
public class ExcelSubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}