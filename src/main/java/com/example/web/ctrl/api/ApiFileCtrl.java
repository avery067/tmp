package com.example.web.ctrl.api;

import com.example.web.service.DeviceService;
import com.example.web.service.LaboratoryService;
import com.example.web.service.NoticeService;
import com.example.web.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;

/**
 * @author Avery
 * @create 2024-04-26 19:06
 * @description
 */
@Controller
@RequestMapping("/api/file")
public class ApiFileCtrl {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private LaboratoryService laboratoryService;
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/view/{id}")
    public void view(@PathVariable("id") String id, HttpServletResponse response) {
        try {
            String path = noticeService.getById(id).getImg();
            //读取path的图片文件并返回
            File file = new File(path);
            //设置响应头
            response.setContentType("image/jpeg");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            //将文件写入响应体
            byte[] bytes = FileUtil.readFileToByteArray(file);
            // 写入响应流
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/view1/{id}")
    public void view1(@PathVariable("id") String id, HttpServletResponse response){
        try {
            String path = laboratoryService.getById(id).getImg();
            //读取path的图片文件并返回
            File file = new File(path);
            //设置响应头
            response.setContentType("image/jpeg");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            //将文件写入响应体
            byte[] bytes = FileUtil.readFileToByteArray(file);
            // 写入响应流
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/view2/{id}")
    public void view2(@PathVariable("id") String id, HttpServletResponse response){
        try {
            String path = deviceService.getById(id).getImage();
            //读取path的图片文件并返回
            File file = new File(path);
            //设置响应头
            response.setContentType("image/jpeg");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            //将文件写入响应体
            byte[] bytes = FileUtil.readFileToByteArray(file);
            // 写入响应流
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
