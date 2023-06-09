package com.deng.controller;

import com.deng.pojo.Result;
import com.deng.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import javax.annotation.Resource;

@Slf4j
@RestController
public class UploadController {
    @Resource
    AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("上传文件：{}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }
}
