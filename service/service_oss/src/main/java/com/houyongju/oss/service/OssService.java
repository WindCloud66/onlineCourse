package com.houyongju.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author HouYongJu
 * @create 2021-08-10 14:30
 */
public interface OssService {

     String uploadFileAvator(MultipartFile file);
}
