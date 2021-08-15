package com.houyongju.eduservice.client;

import com.houyongju.commonutils.ResultMessage;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HouYongJu
 * @create 2021-08-14 23:36
 */

@Component
public class VodFileDegradeFeignClient implements VocClient{
    @Override
    public ResultMessage removeAliCloudVideo(String id) {
        return ResultMessage.error().message("删除视频出错");
    }

    @Override
    public ResultMessage deleteBatch(List<String> videoIdList) {
        return ResultMessage.error().message("删除多个视频出错");
    }
}
