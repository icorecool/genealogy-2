package com.zhang.genealogy.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.genealogy.config.Result;
import com.zhang.genealogy.constant.Constants;
import com.zhang.genealogy.exception.CommonException;
import com.zhang.genealogy.exception.ErrorCode;
import com.zhang.genealogy.model.Files;
import com.zhang.genealogy.qb.FilesQB;
import com.zhang.genealogy.service.FilesService;
import com.zhang.genealogy.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * 文件上传对外接口
 *
 * @Auther: zhangchao
 * @Date: 2019/3/4 10:59
 */
@Controller
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    /**
     * 上传文件记录表
     */
    @Resource
    private FilesService filesService;

    /**
     * 上传文件路径
     */
    @Value("${upload.filePath}")
    String filePath;

    /**
     * 文件上传
     *
     * @param file
     * @param session
     * @return
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file, HttpSession session) {
        try {
            if (file.isEmpty()) {
                throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR);
            }
            // 拼装文件名
            String fileName = FileUtils.getFileName(file.getOriginalFilename());
            // 拼装路径
            String realPath = filePath + "/" + fileName;
            // 上传文件
            FileUtils.upload(file, realPath);
            // 获取session信息
            String id = session.getAttribute(Constants.PLATFORM_USER_ID).toString();
            String showName = session.getAttribute(Constants.PLATFORM_USER_SHOW_NAME).toString();
            // 保存表
            Files files = new Files();
            files.setName(file.getOriginalFilename());
            files.setUrl(realPath);
            files.setCreateShowName(showName);
            files.setCreateUserId(new Long(id));
            filesService.insert(files);
            //返回参数
            Result result = new Result();
            result.addObject("file", files);
            return result;
        } catch (Exception e) {
            logger.error("上传文件出错", e);
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR);
        }

    }

    /**
     * 文件下载
     *
     * @param filePath
     * @param response
     * @return
     */
    @RequestMapping("/download")
    @ResponseBody
    public Result downloadFile(String filePath, HttpServletResponse response) {
        if (null == filePath) {
            throw new CommonException(ErrorCode.RECORD_IS_NOT_EXISTS);
        }
        //设置文件路径
        File file = new File(filePath);
        if (!file.exists()) {
            throw new CommonException(ErrorCode.RECORD_IS_NOT_EXISTS);
        }
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader("Content-Disposition", "attachment;fileName=" + filePath);
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            return new Result();
        } catch (Exception e) {
            logger.error("文件下载失败", e);
            throw new CommonException(ErrorCode.FILE_UPLOAD_ERROR);
        } finally {
            // 关闭流
            try {
                if (null != bis) {
                    bis.close();
                }
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                logger.error("文件下载失败", e);
                throw new CommonException(ErrorCode.RECORD_IS_NOT_EXISTS);
            }
        }
    }

    /**
     * 查询分页列表
     *
     * @param filesQB
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Result queryPage(FilesQB filesQB) {
        PageInfo<Files> filesPageInfo = filesService.queryPage(filesQB);
        Result result = new Result();
        result.addObject("page", filesPageInfo);
        return result;
    }

}
