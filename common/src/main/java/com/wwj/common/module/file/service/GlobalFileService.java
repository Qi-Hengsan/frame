package com.wwj.common.module.file.service;

import com.wwj.common.module.file.entity.GlobalFile;
import com.wwj.core.service.BaseService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件路径 服务类
 *
 * @author ztsong
 * @since 2021-08-24
 */
public interface GlobalFileService extends BaseService<GlobalFile> {

    /**
     * 单个文件上传
     * @param file
     * @return
     */
    int singleUpload(MultipartFile file, String orderNum) throws Exception;

    /**
     * 多个文件上传
     * @param files
     * @return
     */
    int[] multipleUpload(MultipartFile[] files, String orderNum) throws Exception;
}
