package com.qufu.service;

import com.qufu.utils.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
    @Value("${web.upload-path}")
    private String path;

    @Override
    public String[] uploadPic(MultipartFile[] uploadFile, HttpServletRequest req) throws IOException {
        String format = sdf.format(new Date());
        File folder = CreatFileDir(format);
        String[] filePath = new String[uploadFile.length];
        int i = 0;
        //给上传的文件重命名避免冲突
        for (MultipartFile multipartFile : uploadFile) {
            String oldName = multipartFile.getOriginalFilename();
            System.out.println(oldName);
            //获取文件的后缀名 生成新的文件名
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
            System.out.println(newName);
            multipartFile.transferTo(new File(folder, newName));
            filePath[i] = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/uploadFile/" + format + newName;
            i++;
        }
        return filePath;
    }

    protected File CreatFileDir(String filepath) {
        File dir = new File(path + "/uploadFile/" + filepath);
        System.out.println(dir);
        if (!dir.isDirectory()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 删除不通过的文件或者删除的文件
     */
    public void uploaddelet(String name, HttpServletRequest req) {
        String str = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
        name = path + name.substring(str.length(), name.length());
        System.out.println(name);
        File fiel = new File(name);
        if (fiel.exists()) {
            fiel.delete();
        }
    }
}
