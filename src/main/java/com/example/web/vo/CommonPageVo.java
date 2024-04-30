package com.example.web.vo;

import lombok.Data;

/**
 * @author Avery
 * @create 2024-04-25 12:18
 * @description
 */
@Data
public class CommonPageVo {
    private Long total;
    private Integer pageSize;
    private Integer pageNum;
    private Object data;
}
