package com.example.web.vo;

import com.example.web.entity.Laboratory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Avery
 * @create 2024-04-25 11:40
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryVo extends Laboratory {
    private int current;
    private String img;
}
