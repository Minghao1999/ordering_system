package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * COMMON CONTROLLER
 */
@RestController
@RequestMapping("/admin/common")
@Api("Common Controller")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;
    /**
     * File Upload
     * @param file
     * @return
     */
    //TODO Use Azure Upload File
    @PostMapping("/upload")
    @ApiOperation("File Upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {  // 必须是 "file"
        if (file == null || file.isEmpty()) {
            log.error("File is empty! Please check the request.");
            return Result.error("File is empty! Please check the request.");
        }
        log.info("Uploading file: {}, size: {} bytes", file.getOriginalFilename(), file.getSize());
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.contains(".") ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
            String objectName = UUID.randomUUID().toString() + extension;
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            log.info("Upload successful: {}", filePath);
            return Result.success(filePath);
        } catch (Exception e) {
            log.error("File upload failed: {}", e.getMessage(), e);
            return Result.error("File upload failed: " + e.getMessage());
        }
    }

}
