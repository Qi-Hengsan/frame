package com.wwj.common.module.file.controller;

import com.wwj.common.module.file.service.GlobalFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author by ztsong
 * @Classname FileController
 * @Description TODO
 * @Date 2021/8/24 14:22
 */
@Slf4j
@RestController
@RequestMapping("/configAdditionalProduct")
@Api(value = "文件管理API", tags = {"文件管理"})
public class FileController {

    @Resource
    private GlobalFileService fileService;

    @ApiOperation(value = "单个文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", dataType = "MultipartFile", required = true),
            @ApiImplicitParam(name = "orderNum", value = "订单编号", dataType = "String", required = true)
    })
    @PostMapping("/upload/single")
    public int singleUpload(@RequestParam("file") MultipartFile file, @RequestParam("orderNum") String orderNum) throws Exception {
        int id = fileService.singleUpload(file, orderNum);
        return id;
    }

    @ApiOperation(value = "多个文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件", dataType = "MultipartFile[]", required = true),
            @ApiImplicitParam(name = "orderNum", value = "订单编号", dataType = "String", required = true)
    })
    @PostMapping("/upload/multiple")
    public int[] multipleUpload(@RequestParam("files") MultipartFile[] files, @RequestParam("orderNum") String orderNum) throws Exception {
        int[] result = fileService.multipleUpload(files, orderNum);
        return result;
    }

}
