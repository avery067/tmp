package com.example.web.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Avery
 * @create 2024-04-26 18:34
 * @description
 */
public class FileUtil {
    public static String save(MultipartFile file) {
        // 获取项目根目录
        String projectRoot = System.getProperty("user.dir");
        String prePath = projectRoot + "/upload/";
        File preFile = new File(prePath);
        if (!preFile.exists()) {
            preFile.mkdirs();
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-", "");
        String[] pre = file.getOriginalFilename().split("\\.");
        fileName = fileName + pre[pre.length - 1];
        // 构建文件路径
        String filePath = prePath + fileName;

        try {
            // 将文件保存到指定路径
            file.transferTo(new File(filePath));
            return filePath;
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 读取文件为字节数组
     *
     * @param file
     * @return
     */
    public static byte[] readFileToByteArray(File file) throws Exception {
        //原生方法读取文件为字节数组
        FileInputStream fis = null;
        fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        return bytes;
    }
}
