package com.example.bbzn.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bbzn.pojo.Grant;
import com.example.bbzn.pojo.SMSReturn;
import com.example.bbzn.pojo.User;
import com.example.bbzn.service.EquipmentService;
import com.example.bbzn.service.GrantService;
import com.example.bbzn.service.UserService;
import com.example.bbzn.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/api/user")
@Transactional
public class UserLoginController {

    @Autowired
    private CommonResponse commonResponse;

    @Autowired
    private UserService userService;

    @Autowired
    private GrantService grantService;

    @Autowired
    private EquipmentService equipmentService;

    MD5Util md5Util;

    /*
     * 用户账号密码登陆
     * */
    @RequestMapping(value = "/getUser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProjectType(String json) throws Exception {
        AESUtil aesUtil = new AESUtil();
        User userJson = JSONObject.parseObject(json, User.class);
        String Phone = Aes.getInstance().decrypt(userJson.getUserPhone());
        String Pass = Aes.getInstance().decrypt(md5Util.convertMD5(md5Util.convertMD5(userJson.getUserPass())));
        User user = userService.getUser(Phone,Pass);
        if (user != null) {
            if(!"".equals(userJson.getUserLocation())){
                userService.updateUserlocation(user.getUserId(),userJson.getUserLocation());
            }
            String token = DataGeneratorUtil.generateRandomStr(32);
            user.setToken(token);
            userService.updateUserToken(user.getUserId(),"Baran "+token);
            return commonResponse.commonReturn(JSONObject.toJSONString(user));
        } else {
            return commonResponse.commonErrorReturn(500, "用户验证失败");
        }
    }

    /*
     * 用户注册
     * */
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(String json) {
        try {
            User userJson = JSONObject.parseObject(json, User.class);
            SmsWebApiKit smsWebApiKit = new SmsWebApiKit("2a35d9414a2fd");
            String UserPhone = Aes.getInstance().decrypt(userJson.getUserPhone());
            String UserPass = Aes.getInstance().decrypt(userJson.getUserPass());
            String s = smsWebApiKit.checkcode(UserPhone, userJson.getCountryCode(), userJson.getVerificationCode());
            SMSReturn smsReturn = JSONObject.parseObject(s, SMSReturn.class);
            userJson.setUserPhone(UserPhone);
            userJson.setUserPass(md5Util.string2MD5(UserPass));
            int phone = userService.getPhone(userJson.getUserPhone());
            if (phone < 1) {
                if (smsReturn.getStatus() == 200) {
                    int i = userService.saveUser(userJson);
                    if (i > 0) {
                        return commonResponse.commonReturn("注册成功");
                    } else {
                        return commonResponse.commonErrorReturn(500, "用户注册失败");
                    }
                } else if(smsReturn.getStatus() == 457){
                    return commonResponse.commonErrorReturn(202, "用户手机错误");
                }else {
                    return commonResponse.commonErrorReturn(203, "验证码错误");
                }
            } else {
                return commonResponse.commonErrorReturn(201, "该手机已被注册");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commonResponse.commonErrorReturn(500, "系统异常");
    }



    /*
     * 用户验证激活码
     * */
    @RequestMapping(value = "/verificationCode", method = RequestMethod.POST)
    @ResponseBody
    public String verificationCode(String json)  {
        User userJson= JSONObject.parseObject(json,User.class);
        Grant grant = grantService.getGrant(userJson.getGrantCode());
        if(grant==null){
            return commonResponse.commonErrorReturn(205,"授权码不存在");
        }else{
            if(userService.getGrant(grant.getGrantId())>0){
                return commonResponse.commonErrorReturn(206,"授权码已使用");
            }
            if(equipmentService.isItActivated(userJson.getGrantCode())<1){
                return commonResponse.commonErrorReturn(207,"授权码暂未激活");
            }
            if(userService.updateUserGrantId(userJson.getUserId(),grant.getGrantId())>0){
                equipmentService.updateEquipmentType(grant.getGrantId());
                return commonResponse.commonReturn(grant.getGrantId()+"");
            }else {
                return commonResponse.commonErrorReturn(500,"系统异常");
            }
        }
    }


    /*
     * 用户验证码验证
     * */
    @RequestMapping(value = "/verificationPhone", method = RequestMethod.POST)
    @ResponseBody
    public String verificationPhone(String userPhone,String countryCode,String verificationCode){
        try {
            System.out.println("countryCode:"+countryCode);
            String Phone = Aes.getInstance().decrypt(userPhone);
            System.out.println("userPhone:"+Phone);
            System.out.println("verificationCode:"+verificationCode);
            SmsWebApiKit smsWebApiKit = new SmsWebApiKit("2a35d9414a2fd");
            String s = smsWebApiKit.checkcode(Phone,countryCode,verificationCode);
            SMSReturn smsReturn = JSONObject.parseObject(s, SMSReturn.class);
            int phone = userService.getPhone(Phone);
            if (phone > 0) {
                if (smsReturn.getStatus() == 200) {
                    return commonResponse.commonErrorReturn(200, "手机认证通过");
                } else if(smsReturn.getStatus() == 457){
                    return commonResponse.commonErrorReturn(202, "用户手机错误");
                }else {
                    return commonResponse.commonErrorReturn(203, "验证码错误");
                }
            }else {
                return commonResponse.commonErrorReturn(204,"未检测到该用户");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return commonResponse.commonErrorReturn(500,"系统异常");
    }


    /*
     * 用户修改密码
     * */
    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    @ResponseBody
    public String changePass(String userPhone,String userPass){
        try {
            System.out.println("userPhone:"+userPhone);
            System.out.println("userPass:"+userPass);
            String Pass = Aes.getInstance().decrypt(userPass);
            String Phone = Aes.getInstance().decrypt(userPhone);
            System.out.println("Phone:"+Phone);
            System.out.println("Pass:"+Pass);
            int count = userService.updateUserPass(Phone,md5Util.string2MD5(Pass));
            if(count>0){
                return commonResponse.commonErrorReturn(200, "修改成功");
            }else{
                return commonResponse.commonErrorReturn(500,"修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return commonResponse.commonErrorReturn(500,"系统异常");
    }

}
