package com.zhang.genealogy.util;


import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 文件工具类
 *
 * @author hhc
 * @date 2019年2月13日
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 文件上传
     *
     * @param file     文件
     * @param realPath 文件存放路径
     * @return
     */
    public static String upload(MultipartFile file, String realPath) {
        File uploadFile = new File(realPath);

        //判断文件父目录是否存在
        if (!uploadFile.getParentFile().exists()) {
            uploadFile.getParentFile().mkdir();
        }
        //保存文件
        try {
            file.transferTo(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR);
        }
        return realPath;
    }

    /**
     * 生成新的文件名
     *
     * @param fileName 源文件名
     * @return
     */
    public static String getFileName(String fileName) {
        //fileName_12564684856
        return fileName + Constants.COMMON_CONNECTOR + System.currentTimeMillis();
    }

}
