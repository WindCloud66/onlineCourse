package com.houyongju.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author HouYongJu
 * @create 2021-08-13 19:51
 */
public interface VodService {
    String uploadCloudeVideo(MultipartFile file);
}
