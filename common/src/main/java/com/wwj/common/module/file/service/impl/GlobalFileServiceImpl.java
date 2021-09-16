package com.wwj.common.module.file.service.impl;

import com.wwj.common.constant.FileConstant;
import com.wwj.common.exception.BaseException;
import com.wwj.common.module.file.entity.GlobalFile;
import com.wwj.common.module.file.mapper.GlobalFileMapper;
import com.wwj.common.module.file.service.GlobalFileService;
import com.wwj.core.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 文件路径 服务实现类
 *
 * @author ztsong
 * @since 2021-08-24
 */
@Slf4j
@Service
public class GlobalFileServiceImpl extends BaseServiceImpl<GlobalFileMapper, GlobalFile> implements GlobalFileService {

    @Value("${file.upload.path}")
    private String path;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int singleUpload(MultipartFile file, String orderNum) throws Exception {
        if (file.isEmpty()) {
            throw new BaseException("请选择文件");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf(FileConstant.SEPARATOR));
        // 上传文件名
        String uploadFileName = orderNum + suffixName;
        // 格式化日期文件夹
        String dateFile = new SimpleDateFormat(FileConstant.FOLDER_DATEFORMAT).format(new Date());
        String uploadPath = path + dateFile;
        File filePath = new File(uploadPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        file.transferTo(new File(uploadPath + uploadFileName));
        GlobalFile globalFile = new GlobalFile();
        globalFile.setName(fileName);
        globalFile.setPath(dateFile + uploadFileName);
        super.save(globalFile);
        return globalFile.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int[] multipleUpload(MultipartFile[] files, String orderNum) throws Exception {
        if (Objects.equals(files.length, 0)) {
            throw new BaseException("请选择文件");
        }
        int[] result = new int[files.length];
        for (int i = 0; i < files.length; i++) {
            // 获取文件名
            String fileName = files[i].getOriginalFilename();
            // 获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf(FileConstant.SEPARATOR));
            // 上传文件名
            String uploadFileName = orderNum + FileConstant.FILENAME_CONNECTOR + i + suffixName;
            // 格式化日期文件夹
            String dateFile = new SimpleDateFormat(FileConstant.FOLDER_DATEFORMAT).format(new Date());
            String uploadPath = path + dateFile;
            File filePath = new File(uploadPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            files[i].transferTo(new File(uploadPath + uploadFileName));
            GlobalFile globalFile = new GlobalFile();
            globalFile.setName(fileName);
            globalFile.setPath(dateFile + uploadFileName);
            super.save(globalFile);
            result[i] = globalFile.getId();
        }
        return result;
    }
}
