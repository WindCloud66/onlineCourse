package com.houyongju.educenter.controller;

import com.houyongju.educenter.utils.ConstantWxUtils;
import com.houyongju.educenter.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author HouYongJu
 * @create 2021-08-17 21:50
 */
@Slf4j
@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    //2 获取扫描人信息,添加数据
    @GetMapping("callback")
    public String callback(String code, String state) {
        //得到授权临时票据code

        try {
            String baseAccessTokenUrl =
                    "https://api.weixin.qq.com/sns/oauth2/access_token" +
                            "?appid=%s" +
                            "&secret=%s" +
                            "&code=%s" +
                            "&grant_type=authorization_code";
            // 拿着code请求 微信固定的地址, 得到两个值 accsess_token 和openid
            String qrcodeUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code);

            String result = HttpClientUtils.get(qrcodeUrl);
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:http://localhost:3000";
    }

    // 生成二维码
    @GetMapping("login")
    public String getWxCode(){
        //请求微信地址
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "imhelen");
        return "redirect:" + qrcodeUrl;
    }
}
