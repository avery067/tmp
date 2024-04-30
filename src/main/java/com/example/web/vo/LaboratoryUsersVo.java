package com.example.web.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Avery
 * @create 2024-04-25 12:02
 * @description
 */
@Data
public class LaboratoryUsersVo {
    private int id;
    private String name;
    private String userName;
    private String createTime;
    private int state;
    private int sign;
    private String signTime;
    private String no;
}
