package com.houyongju.vod.service.imlpl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.houyongju.vod.utils.ConstantVodUtils;
import com.houyongju.vod.service.VodService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author HouYongJu
 * @create 2021-08-13 19:51
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadCloudeVideo(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String title = fileName.substring(0, fileName.lastIndexOf("."));


        //文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadStreamRequest request = new UploadStreamRequest(
                ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.END_POINT,
                title, fileName, inputStream);
        request.setApiRegionId("cn-shenzhen");

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = null;
        if (response.isSuccess()) {
            videoId =  response.getVideoId();
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            videoId =  response.getVideoId();
        }
        return videoId;
    }
}
