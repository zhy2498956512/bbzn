package com.example.bbzn.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bbzn.pojo.Grant;
import com.example.bbzn.pojo.User;
import com.example.bbzn.service.EquipmentService;
import com.example.bbzn.service.GrantService;
import com.example.bbzn.service.UserService;
import com.example.bbzn.util.*;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

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
     * 用户邮箱密码登陆
     * */
    @RequestMapping(value = "/getUser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getProjectType(String json) throws Exception {
        /*AESUtil aesUtil = new AESUtil();*/
        User userJson = JSONObject.parseObject(json, User.class);
        String Mail = Aes.getInstance().decrypt(userJson.getUserMail());
        String Pass = Aes.getInstance().decrypt(md5Util.convertMD5(md5Util.convertMD5(userJson.getUserPass())));
        User user = userService.getUser(Mail,Pass);
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
     * *//*
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
    }*/


    /*
     * 用户注册
     * */
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public String registerUser(String json) {
        try {
            User userJson = JSONObject.parseObject(json, User.class);
            String UserMail = Aes.getInstance().decrypt(userJson.getUserMail());
            String UserPass = Aes.getInstance().decrypt(userJson.getUserPass());
            userJson.setUserMail(UserMail);
            userJson.setUserPass(md5Util.string2MD5(UserPass));
//            String mailCode = (String) request.getSession().getAttribute("mailCode");
            if(userJson.getVerificationCode().equals(mailCode)){
                int i = userService.saveUser(userJson);
                if (i > 0) {
                    return commonResponse.commonReturn("注册成功");
                } else {
                    return commonResponse.commonErrorReturn(500, "系统异常");
                }
            }else{
                return commonResponse.commonErrorReturn(201, "验证码错误");
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
    public String verificationPhone(String userMail,String verificationCode){
        try {
            if(verificationCode.equals(mailCode)){
                return commonResponse.commonErrorReturn(200,"验证成功");
            }else{
                return commonResponse.commonErrorReturn(201,"验证码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return commonResponse.commonErrorReturn(500,"系统异常");
    }


    /*
     * 用户验证mail
     * */
    /*private String mailCode = "";
    @RequestMapping(value = "/verificationMail", method = RequestMethod.POST)
    @ResponseBody
    public String verificationMail(String mail,String judge){
            try {
                String from = "2498956512@qq.com";// 发件人电子邮箱
                if("1".equals(judge)){
                    //注册时发送验证码
                    if(userService.getMail(mail) > 0){
                        return commonResponse.commonErrorReturn(202,"邮箱已被注册");
                    }
                }else if("2".equals(judge)){
                    //忘记密码时发送验证码
                    if(userService.getMail(mail) < 1){
                        return commonResponse.commonErrorReturn(203,"系统未检测到该邮箱用户");
                    }
                }
                String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
                Properties properties = System.getProperties();// 获取系统属性
                properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
                properties.setProperty("mail.smtp.auth", "true");// 打开认证
                //QQ邮箱需要下面这段代码，163邮箱不需要
                MailSSLSocketFactory sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
                properties.put("mail.smtp.ssl.enable", "true");
                properties.put("mail.smtp.ssl.socketFactory", sf);

                StringBuffer result = new StringBuffer();
                for (int i = 0; i < 6; i++) {
                    result.append(Integer.toHexString(new Random().nextInt(16)));
                }

                // 1.获取默认session对象
                Session session = Session.getDefaultInstance(properties, new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("2498956512@qq.com", "vlheojkdvjyiecdg"); // 发件人邮箱账号、授权码
                    }
                });

                // 2.创建邮件对象
                Message message = new MimeMessage(session);
                // 2.1设置发件人
                message.setFrom(new InternetAddress(from));
                // 2.2设置接收人
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
                // 2.3设置邮件主题
                message.setSubject("账号激活");
                // 2.4设置邮件内容
                String content = "<html><head></head><body><h1>感谢您使用VTrans，本次的验证码为：</h1>" +
                        "<h3 >"+result.toString()+"</h3>" +
                        "</body></html>";
                message.setContent(content, "text/html;charset=UTF-8");
                // 3.发送邮件
                Transport.send(message);
                mailCode = result.toString();
                return commonResponse.commonReturn(JSONObject.toJSONString("邮件成功发送"));
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }*/

    /*
     * 用户验证mail
     * */
    private String mailCode = "";

    @RequestMapping(value = "/verificationMail", method = RequestMethod.POST)
    @ResponseBody
    public String verificationMail(String mail,String judge){
        try {
            if("1".equals(judge)){
                //注册时发送验证码
                if(userService.getMail(mail) > 0){
                    return commonResponse.commonErrorReturn(202,"邮箱已被注册");
                }
            }else if("2".equals(judge)){
                //忘记密码时发送验证码
                if(userService.getMail(mail) < 1){
                    return commonResponse.commonErrorReturn(203,"系统未检测到该邮箱用户");
                }
            }
            Double random = Math.random();//随机数
            String nuberm = random.toString();
            nuberm =  nuberm.substring(nuberm.length()-6,nuberm.length());
            MailboxDemo.setComMailbox(mail,nuberm);
            mailCode = nuberm;
            return commonResponse.commonReturn(JSONObject.toJSONString("邮件成功发送"));
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,e.toString());
        }
    }


    /*
     * 用户修改密码
     * */
    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    @ResponseBody
    public String changePass(String userMail,String userPass){
        try {
            System.out.println("userPhone:"+userMail);
            System.out.println("userPass:"+userPass);
            String Pass = Aes.getInstance().decrypt(userPass);
            String Mail = Aes.getInstance().decrypt(userMail);
            System.out.println("Mail:"+Mail);
            System.out.println("Pass:"+Pass);
            int count = userService.updateUserPass(Mail,md5Util.string2MD5(Pass));
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
