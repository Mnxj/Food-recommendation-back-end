package com.qufu.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 上传文件
 */
public interface FileUploadService {

    String[] uploadPic(MultipartFile[] multipartFiles, HttpServletRequest req) throws IOException;

    void uploaddelet(String name, HttpServletRequest req);
}
