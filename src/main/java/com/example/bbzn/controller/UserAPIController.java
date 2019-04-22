package com.example.bbzn.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bbzn.dao.GrantDao;
import com.example.bbzn.pojo.*;
import com.example.bbzn.service.*;
import com.example.bbzn.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/api/user")
@Transactional
public class UserAPIController {

    @Autowired
    private CommonResponse commonResponse;

    @Autowired
    private UserService userService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentLanguageService equipmentLanguageService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private AboutUsService aboutUsService;

    @Autowired
    private UserFeedbackService userFeedbackService;

    @Autowired
    private SimultaneousService simultaneousService;

    @Autowired
    private RecordingService recordingService;

    @Autowired
    private TranslateService translateService;

    @Autowired
    private PhotographService photographService;

    @Autowired
    private APKService apkService;

    @Value("${user_photograph_img}")
    private String user_photograph_img;


    /*
     * 用户修改信息
     * */
    @RequestMapping(value = "/modifyInformation", method = RequestMethod.POST)
    @ResponseBody
    public String modifyInformation(String json,HttpServletRequest request)  {
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0){
                User userJson= JSONObject.parseObject(json,User.class);
                int count = userService.updateUserInformation(userJson);
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString(userService.getUserId(userJson.getUserId())));
                }else{
                    return commonResponse.commonErrorReturn(500,"修改失败");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    /*
     * 选择语言
     * */
    @RequestMapping(value = "/showLanguageList", method = RequestMethod.GET)
    @ResponseBody
    public String showLanguageList(String grantId,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                int equipment_id = equipmentService.getGrantId(Integer.valueOf(grantId));
                List<EquipmentLanguage> list = equipmentLanguageService.getEquipmentLanguageList(equipment_id);
                List<Integer> idlist = new ArrayList<Integer>();
                for(EquipmentLanguage equipmentLanguage:list){
                    idlist.add(equipmentLanguage.getLanguageId());
                }
                List<Language> languageList = null;
                if(idlist.size()>0){
                    languageList = languageService.getEquipmentLanguageList(idlist);
                }else{
                    return commonResponse.commonErrorReturn(500,"未检测到语言");
                }
                if(languageList.size()>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString(languageList));
                }else{
                    return commonResponse.commonErrorReturn(500,"系统异常");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return commonResponse.commonErrorReturn(500,"系统异常");
    }

    /*
     * 获取关于我们信息
     * */
    @RequestMapping(value = "/getAboutUs", method = RequestMethod.GET)
    @ResponseBody
    public String getAboutUs(HttpServletRequest request){
        String token = request.getHeader("authorization");
        int lenth = userService.getUserToken(token);
        if(lenth>0) {
            AboutUs aboutUs = aboutUsService.getAboutUs();
            if(aboutUs!=null){
                return commonResponse.commonReturn(JSONObject.toJSONString(aboutUs));
            }else {
                return commonResponse.commonErrorReturn(500,"系统异常");
            }
        }else {
            return commonResponse.commonErrorReturn(500,"token失效");
        }
    }

    /*
     * 用户反馈信息
     * */
    @RequestMapping(value = "/saveUserFeedback", method = RequestMethod.POST)
    @ResponseBody
    public String saveUserFeedback(String json,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                UserFeedback userFeedback = JSONObject.parseObject(json,UserFeedback.class);
                userFeedback.setFeedbackDate(new Date());
                int count = userFeedbackService.saveUserFeedback(userFeedback);
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString("反馈成功"));
                }else {
                    return commonResponse.commonErrorReturn(500,"反馈失败");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    //同声传译start

    /*
     * 用户获取同声传译列表
     * */
    @RequestMapping(value = "/getSimultaneousList", method = RequestMethod.GET)
    @ResponseBody
    public String getSimultaneousList(String userId,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                System.out.println("userId:"+userId);
                List<Simultaneous> simultaneousList = simultaneousService.getSimultaneousList(Integer.valueOf(userId));
                if(simultaneousList.size()>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString(simultaneousList));
                }else {
                    return commonResponse.commonReturn(JSONObject.toJSONString("未获取到数据"));
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    /*
     * 用户增加同声传译记录
     * */
    @RequestMapping(value = "/saveSimultaneous", method = RequestMethod.POST)
    @ResponseBody
    public String saveSimultaneous(String json,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                Simultaneous simultaneous = JSONObject.parseObject(json,Simultaneous.class);
                int count = simultaneousService.saveSimultaneous(simultaneous);
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString("新增成功"));
                }else {
                    return commonResponse.commonErrorReturn(500,"新增失败");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    /*
     * 用户删除同声传译记录
     * */
    @RequestMapping(value = "/deleteSimultaneous", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteSimultaneous(String simultaneousId,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                System.out.println("simultaneousId:"+simultaneousId);
                int count = simultaneousService.deleteSimultaneous(Integer.valueOf(simultaneousId));
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString("删除成功"));
                }else {
                    return commonResponse.commonErrorReturn(500,"删除失败");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }
    //同声传译end


    //录音记录start

    /*
     * 用户获取录音记录列表
     * */
    @RequestMapping(value = "/getRecordingList", method = RequestMethod.GET)
    @ResponseBody
    public String getRecordingList(String userId,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                System.out.println("userId:"+userId);
                userService.updateUserLoginTime(Integer.valueOf(userId));                   //更新user的登录时间
                equipmentService.updateEquipmentLogintime(Integer.valueOf(userId));         //更新设备的登录时间
                List<Recording> recordingList = recordingService.getRecordingList(Integer.valueOf(userId));
                if(recordingList.size()>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString(recordingList));
                }else {
                    return commonResponse.commonReturn(JSONObject.toJSONString("未获取到数据"));
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    /*
     * 用户新增录音记录
     * */
    @RequestMapping(value = "/saveRecording", method = RequestMethod.POST)
    @ResponseBody
    public String saveRecording(String json,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                Recording recording = JSONObject.parseObject(json,Recording.class);
                int count = recordingService.saveRecording(recording);
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString("新增成功"));
                }else {
                    return commonResponse.commonErrorReturn(500,"新增失败");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    /*
     * 用户删除录音记录
     * */
    @RequestMapping(value = "/deleteRecording", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteRecording(String recordingId,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                System.out.println("recordingId:"+recordingId);
                int count = recordingService.deleteRecording(Integer.valueOf(recordingId));
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString("删除成功"));
                }else {
                    return commonResponse.commonErrorReturn(500,"删除失败");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    /*
     * 用户修改录音名
     * */
    @RequestMapping(value = "/updateRecording", method = RequestMethod.PUT)
    @ResponseBody
    public String updateRecording(String recordingId,String recordingName,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                System.out.println("userId:"+recordingId);
                System.out.println("recordingName:"+recordingName);
                int count = recordingService.updateRecording(recordingName,Integer.valueOf(recordingId));
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString("修改成功"));
                }else {
                    return commonResponse.commonErrorReturn(500,"修改失败");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }
    //录音记录end

    //翻译记录start

    /*
     * 用户获取翻译记录列表
     * */
    @RequestMapping(value = "/getTranslateList", method = RequestMethod.GET)
    @ResponseBody
    public String getTranslateList(String userId,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                System.out.println("userId:"+userId);
                List<Translate> translateList = translateService.getTranslateList(Integer.valueOf(userId));
                if(translateList.size()>0){
                    for (Translate t:translateList){
                        System.out.println("Translate:"+t);
                    }

                    return commonResponse.commonReturn(JSONObject.toJSONString(translateList));
                }else {
                    return commonResponse.commonReturn(JSONObject.toJSONString("未获取到数据"));
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    /*
     * 用户新增翻译记录
     * */
    @RequestMapping(value = "/saveTranslate", method = RequestMethod.POST)
    @ResponseBody
    public String saveTranslate(String json,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                Translate translate = JSONObject.parseObject(json,Translate.class);
                translate.setTranslateDate(new Date());
                int count = translateService.saveTranslate(translate);
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString("新增成功"));
                }else {
                    return commonResponse.commonErrorReturn(500,"新增失败");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }

    /*
     * 用户删除翻译记录
     * */
    @RequestMapping(value = "/deleteTranslate", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteTranslate(String translateId,HttpServletRequest request){
        try {
            String token = request.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                System.out.println("translateId:"+translateId);
                int count = translateService.deleteTranslate(Integer.valueOf(translateId));
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString("删除成功"));
                }else {
                    return commonResponse.commonErrorReturn(500,"删除失败");
                }
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }
    //翻译记录end

    //拍照翻译start

    @RequestMapping(value = "/savePhotograph", method = RequestMethod.POST)
    @ResponseBody
    public String savePhotograph(String json,HttpServletRequest req){
        try {
            String token = req.getHeader("authorization");
            int lenth = userService.getUserToken(token);
            if(lenth>0) {
                /*BASE64Decoder decoder=new BASE64Decoder();*/
                Photograph photograph = JSONObject.parseObject(json,Photograph.class);
                System.out.println(photograph);
                int count = photographService.savePhotograph(photograph);
                if(count>0){
                    return commonResponse.commonReturn(JSONObject.toJSONString("新增成功"));
                }else {
                    return commonResponse.commonErrorReturn(500,"新增失败");
                }
                /*String updateName= DataGeneratorUtil.generateRandomStr(32)+".jpg";
                File file=new File("D:/xxxx.jpg");*/
                /*File file=new File(req.getSession().getServletContext().getRealPath("/") + "../UserPhotographImg/", updateName);*/
               /* FileOutputStream fos=new FileOutputStream(file);
                fos.write(imgBinary);
                fos.flush();
                fos.close();*/
            }else {
                return commonResponse.commonErrorReturn(500,"token失效");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }
    /*@RequestMapping(value = "/savePhotograph", method = RequestMethod.POST)
    @ResponseBody
    public String savePhotograph(HttpServletRequest request){
        try {
            Photograph photograph = new Photograph();
                    *//*JSONObject.parseObject(json,Photograph.class);*//*
            photograph.setPhotographDate(new Date());
            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
            MultipartFile file = null;
            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    String hz=fileName.substring(fileName.indexOf("."));
                    String updateName= DataGeneratorUtil.generateRandomStr(32)+hz;
                    File filePath = new File(request.getSession().getServletContext().getRealPath("/") + "../UserPhotographImg/", updateName);
                    photograph.setPhotographImg(user_photograph_img + updateName);
                    file.transferTo(filePath);       //上传文件
                }
            }
            int count = photographService.savePhotograph(photograph);
            if(count>0){
                return commonResponse.commonReturn(JSONObject.toJSONString("新增成功"));
            }else {
                return commonResponse.commonErrorReturn(500,"新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }*/

    //拍照翻译end


    /*
     *推送升级
    */
    @RequestMapping(value = "/apkUpgrade", method = RequestMethod.GET)
    @ResponseBody
    public String apkUpgrade(String userId){
        try {
            System.out.println("userId:"+userId);
            return commonResponse.commonReturn(JSONObject.toJSONString(apkService.getAPK(Integer.valueOf(userId))));
        }catch (Exception e){
            e.printStackTrace();
            return commonResponse.commonErrorReturn(500,"系统异常");
        }
    }



}
