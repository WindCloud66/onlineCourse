package com.houyongju.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.houyongju.oss.service.OssService;
import com.houyongju.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author HouYongJu
 * @create 2021-08-10 14:54
 */
@Service
public class OssServiceImpl implements OssService {


    @Override
    public String uploadFileAvator(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String fileName = file.getOriginalFilename();
        String datePath = new DateTime().toString("yyyy/MM/dd");
        UUID uuid = UUID.randomUUID();
        fileName = datePath + uuid + fileName;


        InputStream inputStream = null;
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            inputStream = file.getInputStream();

            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if( ossClient != null){
                // 关闭OSSClient。
                ossClient.shutdown();
                StringBuffer sb = new StringBuffer();
                sb.append("https://").append(bucketName).append(".")
                    .append(endpoint).append("/").append(fileName);



                return sb.toString();
            }
        }

        return null;
    }
}
