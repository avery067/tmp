package com.example.web.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Avery
 * @create 2024-04-25 14:24
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code;
    private String msg;
    private Object data;

    public static Result build(int code,String msg,Object data){
        return new Result(code,msg,data);
    }
}
