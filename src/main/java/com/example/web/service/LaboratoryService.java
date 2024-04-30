package com.example.web.service;

import com.example.web.entity.Laboratory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.web.util.Result;
import com.example.web.vo.CommonPageVo;
import org.springframework.web.multipart.MultipartFile;

/**
* @author avery
* @description 针对表【laboratory】的数据库操作Service
* @createDate 2024-04-25 10:04:17
*/
public interface LaboratoryService extends IService<Laboratory> {

    boolean add(String name, Integer max,MultipartFile file);

    CommonPageVo laboratory(Integer pageSize, Integer pageNum,String name);

    boolean edit(Integer id, String name, Integer max,MultipartFile file);

    CommonPageVo laboratoryUser(Integer pageSize,Integer pageNum);

    CommonPageVo listByUser(Integer pageSize, Integer pageNum, Integer userId);

    boolean cancel(Integer id, Integer userId);

    Object get(Integer id);

    Result apply(Integer id, Integer userId,String applyTime,String no);

    void delete(Integer id);
}
