package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * COMMON CONTROLLER
 */
@RestController
@RequestMapping("/admin/common")
@Api("Common Controller")
@Slf4j
public class CommonController {
    /**
     * File Upload
     * @param file
     * @return
     */
    //TODO Use Azure Upload File
    @PostMapping("/upload")
    @ApiOperation("File Upload")
    public Result<String> upload(MultipartFile file) {
        log.info("File Upload: {}", file);
        return null;
    }
}
