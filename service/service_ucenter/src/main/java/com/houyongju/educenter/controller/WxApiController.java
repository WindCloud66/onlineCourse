package com.houyongju.educenter.controller;

import com.google.gson.Gson;
import com.houyongju.commonutils.JwtUtils;
import com.houyongju.educenter.entity.UcenterMember;
import com.houyongju.educenter.service.UcenterMemberService;
import com.houyongju.educenter.utils.ConstantWxUtils;
import com.houyongju.educenter.utils.HttpClientUtils;
import com.houyongju.servicebase.exceptionhandler.WindException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author HouYongJu
 * @create 2021-08-17 21:50
 */
@Slf4j
@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    @Autowired
    private UcenterMemberService memberService;
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
            //使用json转换工具Gson
            String result = HttpClientUtils.get(qrcodeUrl);
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(result, HashMap.class);

            String access_token = (String)mapAccessToken.get("access_token");
            String openid = (String)mapAccessToken.get("openid");



            UcenterMember member = memberService.getOpenIdMember(openid);
            if(member == null){
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl,
                        access_token,
                        openid);
                //发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)userInfoMap.get("nickname");
                String headimgurl = (String)userInfoMap.get("headimgurl");

                member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }
            // 把扫码人信息添加数据库里面
            //判断数据表里面是否存在  根据openid
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            return "redirect:http://localhost:3000?token=" + jwtToken;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WindException(20001, "微信登录失败");

        }

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
