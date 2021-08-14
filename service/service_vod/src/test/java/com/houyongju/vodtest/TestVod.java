package com.houyongju.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-08-13 15:29
 */
public class TestVod {
    public static void main(String[] args) throws ClientException {
        String accessKeyId = "LTAI5tMKV3Vq24VeZNyQkfQ1";
        String accessKeySecret ="aZ5ONIj67PSMWZJEe3vhxoicEG9ILd";
        String title = "How Should I do";
        String fileName = "D:/BaiduNetdiskDownload/Faster.mp4";
        UploadVideoRequest request = new UploadVideoRequest(
                accessKeyId,
                accessKeySecret,
                title,
                fileName);
        /* 点播服务接入点 */
        request.setApiRegionId("cn-shenzhen");

        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
    public static void getPlayAuth() throws ClientException {
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tMKV3Vq24VeZNyQkfQ1", "aZ5ONIj67PSMWZJEe3vhxoicEG9ILd");
        //创建request 和response 对象
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();



        //向request设置视频id
        request.setVideoId("df44e902af8841b5993179589e5db61f");
        response = client.getAcsResponse(request);
        System.out.println("trueUrl:" + response.getPlayAuth());
    }

    public static void getPlayUrl() throws ClientException {
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tMKV3Vq24VeZNyQkfQ1", "aZ5ONIj67PSMWZJEe3vhxoicEG9ILd");

        //创建request 和response 对象
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        //向request设置视频id
        request.setVideoId("df44e902af8841b5993179589e5db61f");
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();

        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
    }
}
