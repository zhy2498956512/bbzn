<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/3/14
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="css/fontastic.css">
    <!-- Google fonts - Poppins -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">
    <link rel="stylesheet"  href="css/global.css"/>
    <link rel="stylesheet" href="css/pagination.css" />
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.pagination.js"></script>
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->

    <style>
        .xmxq{
            width: 95%;
            margin: 0 auto;
            border-top: 2px solid skyblue;
            height: 350px;
            background-color: #fff;
            border-radius:5px ;
        }
        .xmxq_1{
            float: left;
            width: 100%;

            font-size: 18px;

            height: 48px;
        }
        .xmxq_1 span{
            float: left;
            width: 148px;
            line-height: 28px;
            text-align: center;
            height: 28px;
            border-radius:3px ;
            background-color: #00c0ef;
            margin: 10px 0 0 15px;
            color: #fff;
        }
        .xmxq_1 span:hover{
            float: left;
            width: 148px;
            line-height: 28px;
            text-align: center;
            height: 28px;
            border-radius:3px ;
            background-color: cadetblue;
            cursor:pointer;
            margin: 10px 0 0 15px;
            color: #fff;
        }
    </style>
</head>
<body>
<div style=" width: 100%;height:80px;float: left;">
    <div class="xmxq_1">
        <span id="regression">返回项目列表</span>
        <span>设备列表</span>
    </div>
    <div style="border-top: 1px solid #ccc;">
        <br>
        &nbsp;&nbsp;<input type="button" value="新增设备" style=""
                           class="btn btn-primary" data-toggle="modal" data-target="#myModal2">
        <select id="equipmentType">
            <option value="1">已激活设备</option>
            <option value="2">入库但未激活</option>
            <option value="3">未入库被拦截</option>
        </select>
        &nbsp;&nbsp;<span>序列号：</span><input type="text" id="searchEquipment"><input type="hidden" class="projectId1" value="${projectId}">
        <input type="hidden" value="1"><input type="button" value="搜索" class="record">
    </div>
</div>
<%--<div style=" width: 100%;height:40px;float: left;">
    <div>
        <br><br>
        &nbsp;&nbsp;<input type="button" value="新增设备" style=""
               class="btn btn-primary" data-toggle="modal" data-target="#myModal2">
        <select id="equipmentType">
            <option value="1">已激活设备</option>
            <option value="2">入库但未激活</option>
            <option value="3">未入库被拦截</option>
        </select>
    </div>
</div>--%>
<div style="width: 100%;height: 600px;float: left;border-top: #00F7DE 2px solid;background-color: #fff;">
    <div style="float: left;width: 96%;height: 40px;border:1px solid #c8cbcf;margin-left: 2%;background-color: #fff;margin-top: 30px;">
        <div style="width: 14%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">设备ID</div>
        <div style="width: 13%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">系统版本</div>
        <div style="width: 13%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">APK版本</div>
        <div style="width: 20%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">创建时间</div>
        <div style="width: 20%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">最近登陆时间</div>
        <div style="width: 20%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">操作</div>
    </div>
    <div class="pre-scrollable" style="float: left;width: 97.5%;height: 400px;margin-left: 2%;overflow-y:scroll;" id="viewRecord">
        <c:forEach items="${page.list}" var="equipment" >
            <div style="width: 100%;height: 50px;">
                <input type="hidden" value="${equipment.equipmentId}">
                <div style="border-bottom: 1px solid #B9BBBE;color: skyblue;cursor: pointer;width: 14%;height: 50px;
                        border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;" class="grantCode">
                        ${equipment.grantCode}
                </div>
                <div style="width: 13%;height: 50px;float: left;border-bottom: 1px solid #B9BBBE;text-align: center;line-height: 50px;">
                    <c:if test="${equipment.systemEdition==null||equipment.systemEdition==''}">
                        暂无信息
                    </c:if>
                    <c:if test="${equipment.systemEdition!=null&&equipment.systemEdition!=''}">
                        ${equipment.systemEdition}
                    </c:if>
                </div>
                <div style="width: 13%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">
                    <c:if test="${equipment.apkEdition==null||equipment.apkEdition==''}">
                        暂无信息
                    </c:if>
                    <c:if test="${equipment.apkEdition!=null&&equipment.apkEdition!=''}">
                        ${equipment.apkEdition}
                    </c:if>
                </div>
                <div style="width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">
                    <fmt:formatDate value="${equipment.equipmentFoundtime}" type="date" pattern="yyyy-MM-dd　HH:mm:ss"/>
                </div>
                <div style="width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">
                    <c:if test="${equipment.equipmentLogintime==null||equipment.equipmentLogintime==''}">
                        暂无信息
                    </c:if>
                    <c:if test="${equipment.equipmentLogintime!=null&&equipment.equipmentLogintime!=''}">
                        <fmt:formatDate value="${equipment.equipmentLogintime}" type="date" pattern="yyyy-MM-dd　HH:mm:ss"/>
                    </c:if>
                </div>
                <div style="width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">
                    <input type="button" value="配置语言" style="" class="configure btn btn-primary" data-toggle="modal" data-target="#myModal">
                    <input type="hidden" value="${equipment.projectId}" class="projectId1">
                    <input type="hidden" value="${equipment.equipmentId}">
                    <input type="button" value="推送APK" class="btn btn-primary uploadAPK"  data-toggle="modal" data-target="#myModal1">
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="m-style M-box3" style="float: right;margin-top:20px ;">
        <input type="hidden" value="0"><input class="record" type="button" value="上页"><span id="totalPage">${page.pageNum}/${page.totalPage}</span>
        <input type="hidden" value="2"><input class="record" type="button" value="下页">
        <input type="text" width="20px" onkeyup="value=value.replace(/[^0-9]/g,'')" value="1"><input type="button" class="record" value="跳转">
    </div>
    <div style="float: right;margin-top:20px ;size: 5px;"><span id="totalRecord">共${page.totalRecord}台设备</span></div>
</div>

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 675px;">
                <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                    <h5>配置代理语言种类</h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>

                </div>


                <div class="modal-body" style="border-top:3px solid skyblue ; margin: 30px 30px 0 30px;">
                    <!-- 设置代理信息 -->
                </div>
                <div style="margin: -5px 0 0 30px;">
                    <%--<h3 style="float: left;">设置当前代理的语言</h3>--%>
                    <div style="float: left;margin: 0 0 0 30px;">当前左侧可选的数量:<span style="padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;" id="leftNumber"></span></div>
                    <div style="float: left;margin: 0 0 0 30px;">当前右侧可选的数量:<span style="padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;" id="rightNumber"></span></div>
                </div>
                <div style="width: 700px;height: 210px;margin: 10px 0 0 30px;">
                    <select class="form-control" id="multiselect_to_3" multiple="multiple"size="200"style="height: 170px;width: 200px;float: left;">

                    </select>
                    <div style="width: 100px;height: 200px;float: left;margin: 0 0 0 10px;">
                        <button type="button" id="allLeft" class="btn btn-default" style="margin: 5px auto;color: #fff;">
                            全部左移
                        </button>
                        <button type="button" id="right" class="btn btn-default" style="margin: 0 auto;color: #fff;padding: 5px 28px ;">
                            右移
                        </button>
                        <button type="button" id="left" class="btn btn-default" style="margin: 5px auto;color: #fff;padding: 5px 28px ;">
                            左移
                        </button>
                        <button type="button" id="allRight" class="btn btn-default" style="margin: 0 auto;color: #fff;">
                            全部右移
                        </button>
                    </div>
                    <select class="form-control" id="multiselect_to_2"multiple="multiple"size="200"style="height: 170px;width: 200px;float: left;">

                    </select>
                    <div style="width: 100px;height: 200px;float: left;margin: 0 0 0 10px;">
                        <button type="button" id="top" class="btn btn-default" style="margin: 5px auto;color: #fff;padding: 5px 28px ;">
                            置顶
                        </button>
                        <button type="button" id="lower" class="btn btn-default" style="margin: 0 auto;color: #fff;padding: 5px 28px ;">
                            下移
                        </button>
                        <button type="button" id="upper" class="btn btn-default" style="margin: 5px auto;color: #fff;padding: 5px 28px ;">
                            上移
                        </button>
                        <button type="button" id="bottom" class="btn btn-default" style="margin: 0 auto;color: #fff;padding: 5px 28px ;">
                            置底
                        </button>
                    </div>

                </div>
                <form action="api/equipmentLanguage/saveEquipmentLanguage" method="post" id="submission">
                    <input type="hidden" id="languageIdList" name="languageIdList">
                    <input type="hidden" id="cId" name="cId">
                    <input type="hidden" id="projectId" name="projectId">
                    <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal" style="background-color: crimson;color: #fff;">取消当前修改
                        </button>
                        <button id="submit" type="submit" class="btn btn-primary">
                            确认提交修改
                        </button>

                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div>
    </div>

    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 800px; height:370px;">
                <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                    <h5>推送APK版本</h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                </div>
                <div class="" style="border:1px solid skyblue ;background-color:skyblue ; margin: 25px auto;width: 95%;height: 100px;">
                    <p style="color: #fff;font-weight: bold;">上传APK提示</p>
                    <p style="color: #fff;text-indent: 20px;">。选择要上传的APK文件和配置好版本信息后点击上传即可</p>
                </div>
                <div class="blank"></div>
                <div style="width: 90%;margin: 0 0 0 30px;">
                </div>
                <div class="blank"></div>
                <form action="api/equipment/saveAPK" method="post" enctype="multipart/form-data" id="submitAPK">
                    <strong style="float: left;line-height: 25px;text-indent: 70px;">APK文件：</strong><input style="float: left;line-height: 25px; " type="file" name="fileName">
                    <strong style="float: left;line-height: 25px;">APK版本号：</strong><input style="float: left;line-height: 25px;" type="text" name="edition" onkeyup="value=value.replace(/[^0-9]/g,'')" placeholder="只能输入数字">
                    <input type="hidden" name="projectId">
                    <input type="hidden" name="equipmentId">
                    <button type="submit" class="btn btn-success" style="padding: 6px 25% ;float: left; margin: 25px 0 0 20%;">提交上传</button>
                </form>
            </div>

            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </div>

    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 470px;">
                <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                    <h5>新增设备</h5>
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                </div>
                <div class="modal-body" style="border-top:3px solid skyblue ; margin: 30px 30px 0 30px;">
                    <!-- 设置代理信息 -->
                </div>
                <div style="margin: 10px 0 0 60px;" >
                    <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 请输入您要增加的设备序列号/IMEI</span><br>
                    <input type="text" name="grantCode" id="grantCode" style="width: 300px;margin: 0px 30px 0 30px;"/>
                    <input type="hidden" name="projectId1">
                </div>
                <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary" id="saveEquipment">
                        提交
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    </div>

    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/jquery-validation/jquery.validate.min.js"></script>
    <!-- Main File-->
    <script src="js/front.js"></script>
    <script type="text/javascript">
        $(function () {
            /*$(".saveE").click(function () {
                $("[name='projectId1']").val($(".projectId1").val());
            });*/

            $("#regression").click(function () {
                location.href = "/api/grantNumberRecord/jump";
            });

            $(".grantCode").click(function () {
                location.href = "/api/equipment/equipmentConfigure?equipmentId="+$(this).prev().val()+"&projectId="+$(".projectId1").val();
            });

            $(".record").focus(function(){
                $(this).css("background-color","#D6D6FF");
            });
            $(".record").blur(function(){
                $(this).css("background-color","#ffffff");
            });

            $(".record").click(function () {
                var projectId = $(".projectId1").val();
                var equipmentType = $("#equipmentType").val();
                var searchEquipment = $("#searchEquipment").val();
                var record = $(this);
                $.ajax({
                    url: "/api/equipment/jumpEquipmentJson",
                    data: {"pageNum": record.prev().val(),"projectId":projectId,"equipmentType":equipmentType,"searchEquipment":searchEquipment},
                    type: "POST",
                    dataType: "json",
                    success: function (msg) {
                        var pageNum = msg.pageNum;              //当前页
                        var totalRecord = msg.totalRecord;      //总条数
                        var totalPage = msg.totalPage;          //总页数
                        var pageSize = msg.pageSize;            //每页显示的记录数
                        $(".record").each(function(){
                            if ($(this).val()=="上页"){
                                $(this).prev().val((pageNum-1));
                            }else if ($(this).val()=="下页") {
                                $(this).prev().val((pageNum+1));
                            }else if ($(this).val()=="跳转") {
                                $(this).prev().val((pageNum));
                            }else{
                                $(this).prev().val(1);
                            }
                        });
                        $("#totalPage").html(pageNum+"/"+totalPage);
                        $("#totalRecord").html("共"+totalRecord+"条记录  ");
                        var data = msg.list;
                        var num = 1;
                        var html = "";
                        $.each(data, function (num) {
                            var projectId = data[num].projectId;
                            var equipmentId = data[num].equipmentId;
                            var grantCode = data[num].grantCode;
                            var systemEdition = data[num].systemEdition;
                            var apkEdition = data[num].apkEdition;
                            var foundtime = data[num].foundtime;
                            var logintime = data[num].logintime;
                            html = html + "<div style='border-bottom: 1px solid #B9BBBE;color: skyblue;width: 14%;height: 50px;border-bottom: 1px solid #B9BBBE;\n" +
                                "float: left;text-align: center;line-height: 50px;'>"+grantCode+"</div>\n" +
                                "<div style='width: 13%;height: 50px;float: left;cursor: pointer;border-bottom: 1px solid #B9BBBE;text-align: center;line-height: 50px;'>\n";
                                if(systemEdition==null||systemEdition==""){
                                    html = html + "暂无信息";
                                }else{
                                    html = html + systemEdition;
                                }
                                html = html + "</div><div style='width: 13%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>\n";
                                if(apkEdition==null||apkEdition==""){
                                    html = html + "暂无信息";
                                }else{
                                    html = html + apkEdition;
                                }
                                html = html + "</div><div style='width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>\n"+
                                    foundtime+"</div>\n" +
                                "<div style='width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>\n";
                                if(logintime==null||logintime==""){
                                    html = html + "暂无信息";
                                }else{
                                    html = html + logintime;
                                }
                                html = html + "</div>\n" +
                                "<div style='width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>\n" +
                                "<input type='button' value='配置语言' " +
                                "class='btn btn-primary configure' data-toggle='modal' data-target='#myModal'>\n" +
                                "<input type='hidden' value='"+projectId+"' class='projectId1'>\n" +
                                "<input type='hidden' value='"+equipmentId+"'>\n" +
                                "<input type='button' value='推送APK' class='btn btn-primary uploadAPK'  data-toggle='modal' data-target='#myModal1'>\n" +
                                "</div>"
                        })
                        $("#viewRecord").html("");
                        $("#viewRecord").append(html);
                        xx();
                    }
                });
            });

            $("#equipmentType").change(function () {
                var projectId = $(".projectId1").val();
                var equipmentType = $("#equipmentType").val();
                var searchEquipment = $("#searchEquipment").val();
                $.ajax({
                    url: "/api/equipment/jumpEquipmentJson",
                    data: {"pageNum": 1+"","projectId":projectId,"equipmentType":equipmentType,"searchEquipment":searchEquipment},
                    type: "POST",
                    dataType: "json",
                    success: function (msg) {
                        var pageNum = msg.pageNum;              //当前页
                        var totalRecord = msg.totalRecord;      //总条数
                        var totalPage = msg.totalPage;          //总页数
                        var pageSize = msg.pageSize;            //每页显示的记录数
                        $("#totalPage").html(pageNum+"/"+totalPage);
                        $("#totalRecord").html("共"+totalRecord+"条记录  ");
                        var data = msg.list;
                        var num = 1;
                        var html = "";
                        $.each(data, function (num) {
                            var projectId = data[num].projectId;
                            var equipmentId = data[num].equipmentId;
                            var grantCode = data[num].grantCode;
                            var systemEdition = data[num].systemEdition;
                            var apkEdition = data[num].apkEdition;
                            var foundtime = data[num].foundtime;
                            var logintime = data[num].logintime;
                            html = html + "<div style='border-bottom: 1px solid #B9BBBE;color: skyblue;width: 14%;height: 50px;border-bottom: 1px solid #B9BBBE;\n" +
                                "float: left;text-align: center;line-height: 50px;'>"+grantCode+"</div>\n" +
                                "<div style='width: 13%;height: 50px;float: left;cursor: pointer;border-bottom: 1px solid #B9BBBE;text-align: center;line-height: 50px;'>\n";
                            if(systemEdition==null||systemEdition==""){
                                html = html + "暂无信息";
                            }else{
                                html = html + systemEdition;
                            }
                            html = html + "</div><div style='width: 13%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>\n";
                            if(apkEdition==null||apkEdition==""){
                                html = html + "暂无信息";
                            }else{
                                html = html + apkEdition;
                            }
                            html = html + "</div><div style='width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>\n"+
                                foundtime+"</div>\n" +
                                "<div style='width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>\n";
                            if(logintime==null||logintime==""){
                                html = html + "暂无信息";
                            }else{
                                html = html + logintime;
                            }
                            html = html + "</div>\n" +
                                "<div style='width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>\n" +
                                "<input type='button' value='配置语言' " +
                                "class='btn btn-primary configure' data-toggle='modal' data-target='#myModal'>\n" +
                                "<input type='hidden' value='"+projectId+"' class='projectId1'>\n" +
                                "<input type='hidden' value='"+equipmentId+"'>\n" +
                                "<input type='button' value='推送APK' class='btn btn-primary uploadAPK'  data-toggle='modal' data-target='#myModal1'>\n" +
                                "</div>"
                        })
                        $("#viewRecord").html("");
                        $("#viewRecord").append(html);
                        xx();
                    }
                });
            });

            function xx(){
                $(".configure").click(function () {
                    var projectId = $(this).next().val();
                    var equipmentId = $(this).next().next().val();
                    $("#cId").val(equipmentId);
                    $("#projectId").val(projectId);
                    $.ajax({
                        url: "/api/equipmentLanguage/getEquipmentLanguage",
                        data: {"projectId":""+projectId,"equipmentId":""+equipmentId},
                        type: "POST",
                        dataType: "json",
                        success: function (msg) {
                            $("#multiselect_to_3").html("");
                            $("#multiselect_to_2").html("");
                            var languageList = msg.languageList;
                            var companyLanguageList = msg.companyLanguageList;
                            var num1 = 1;
                            var num2 = 1;
                            var html1 = "";
                            var html2 = "";
                            $.each(languageList, function (num1) {
                                var languageId = languageList[num1].languageId;
                                var languageName = languageList[num1].languageName;
                                html1 = html1 + "<option value='"+languageId+"' id='"+languageId+"'>"+languageName+"</option>";
                            })
                            $("#multiselect_to_3").append(html1);
                            $.each(companyLanguageList, function (num2) {
                                var languageId = companyLanguageList[num2].languageId;
                                var languageName = companyLanguageList[num2].languageName;
                                html2 = html2 + "<option value='"+languageId+"' id='"+languageId+"'>"+languageName+"</option>";
                            })
                            $("#multiselect_to_2").append(html2);
                        }
                    });
                });

                $(".uploadAPK").click(function () {
                    $("[name='equipmentId']").val($(this).prev().val());
                    $("[name='projectId']").val($(this).prev().prev().val());
                });
            }

            $("#saveEquipment").click(function () {
                var x = /^[0-9a-zA-Z]*$/;
                if(!x.test($("#grantCode").val())){
                    alert("您输入序列号/IMEI含非法字符");
                    return;
                }else{
                    var projectId = $(".projectId1").val();
                    var grantCode = $("#grantCode").val();
                    $.ajax({
                        url: "/api/equipment/saveEquipment",
                        data: {"projectId":""+projectId,"grantCode":""+grantCode},
                        type: "POST",
                        dataType: "json",
                        success: function (msg) {
                            if (msg==-1){
                                alert("系统异常！！！");
                            }else if (msg==0) {
                                alert("授权码不存在！！！");
                            }else if (msg==2) {
                                alert("授权码不属于该代理商！！！");
                            }else if (msg==3) {
                                alert("授权码已使用！！！");
                            }else if (msg==1) {
                                alert("新增成功");
                                location.href="/api/equipment/jumpEquipment?projectId="+projectId+"&equipmentType=1&searchEquipment=";
                            }
                        }
                    });
                }
            });

            function LeftRight(){
                $("#leftNumber").html($("#multiselect_to_3").find("option").length);
                $("#rightNumber").html($("#multiselect_to_2").find("option").length);
            };

            $(".configure").click(function () {
                var projectId = $(this).next().val();
                var equipmentId = $(this).next().next().val();
                $("#cId").val(equipmentId);
                $("#projectId").val(projectId);
                $.ajax({
                    url: "/api/equipmentLanguage/getEquipmentLanguage",
                    data: {"projectId":""+projectId,"equipmentId":""+equipmentId},
                    type: "POST",
                    dataType: "json",
                    success: function (msg) {
                        $("#multiselect_to_3").html("");
                        $("#multiselect_to_2").html("");
                        var languageList = msg.languageList;
                        var companyLanguageList = msg.companyLanguageList;
                        var num1 = 1;
                        var num2 = 1;
                        var html1 = "";
                        var html2 = "";
                        $.each(languageList, function (num1) {
                            var languageId = languageList[num1].languageId;
                            var languageName = languageList[num1].languageName;
                            html1 = html1 + "<option value='"+languageId+"' id='"+languageId+"'>"+languageName+"</option>";
                        })
                        $("#multiselect_to_3").append(html1);
                        $.each(companyLanguageList, function (num2) {
                            var languageId = companyLanguageList[num2].languageId;
                            var languageName = companyLanguageList[num2].languageName;
                            html2 = html2 + "<option value='"+languageId+"' id='"+languageId+"'>"+languageName+"</option>";
                        })
                        $("#multiselect_to_2").append(html2);
                        LeftRight();
                    }
                });
            });

            $("#allRight").click(function () {
                $("#multiselect_to_2").append($("#multiselect_to_3").html());
                $("#multiselect_to_3").html("");
                LeftRight();
            });

            $("#allLeft").click(function () {
                $("#multiselect_to_3").append($("#multiselect_to_2").html());
                $("#multiselect_to_2").html("");
                LeftRight();
            });

            $("#left").click(function () {
                var html = "";
                $("#multiselect_to_2 option:selected").each(function () {
                    var v = $(this).val();
                    var t = $(this).text();
                    html = html + "<option value='"+v+"' id='"+v+"'>"+t+"</option>"
                    $("#"+v).remove();
                })
                $("#multiselect_to_3").append(html);
                LeftRight();
            });

            $("#right").click(function () {
                var html = "";
                $("#multiselect_to_3 option:selected").each(function () {
                    var v = $(this).val();
                    var t = $(this).text();
                    html = html + "<option value='"+v+"' id='"+v+"'>"+t+"</option>"
                    $("#"+v).remove();
                })
                $("#multiselect_to_2").append(html);
                LeftRight();
            });


            var patt1 = /^[0-9]\d*$/;
            $("#top").click(function () {
                if(patt1.test($("#multiselect_to_2 option:selected").val())){
                    $("#multiselect_to_2 option:selected").each(function () {
                        var html = "";
                        var v = $(this).val();
                        var t = $(this).text();
                        $("#" + v).remove();
                        $("#multiselect_to_2").prepend("<option value='" + v + "' id='" + v + "'>" + t + "</option>");
                    })
                }
            });

            $("#bottom").click(function () {
                if(patt1.test($("#multiselect_to_2 option:selected").val())) {
                    $("#multiselect_to_2 option:selected").each(function () {
                        var html = "";
                        var v = $(this).val();
                        var t = $(this).text();
                        $("#" + v).remove();
                        $("#multiselect_to_2").append("<option value='" + v + "' id='" + v + "'>" + t + "</option>");
                    })
                }
            });


            $("#lower").click(function () {
                if ($("#multiselect_to_2 option:selected").next().val() != null && $("#multiselect_to_2 option:selected").next().val() != ""){
                    $("#multiselect_to_2 option:selected").each(function () {
                        var v1 = $(this).val();
                        var t1 = $(this).text();
                        var v2 = $(this).next().val();
                        var t2 = $(this).next().text();
                        $(this).val(v2);
                        $(this).attr("id", v2);
                        $(this).text(t2);
                        $(this).next().val(v1);
                        $(this).next().attr("id", v1);
                        $(this).next().text(t1);
                    })
                }
            });

            $("#upper").click(function () {
                if ($("#multiselect_to_2 option:selected").prev().val() != null && $("#multiselect_to_2 option:selected").prev().val() != ""){
                    $("#multiselect_to_2 option:selected").each(function () {
                        var v1 = $(this).val();
                        var t1 = $(this).text();
                        var v2 = $(this).prev().val();
                        var t2 = $(this).prev().text();
                        $(this).val(v2);
                        $(this).attr("id", v2);
                        $(this).text(t2);
                        $(this).prev().val(v1);
                        $(this).prev().attr("id", v1);
                        $(this).prev().text(t1);
                    })
                }
            });

            $("#submission").submit(function () {
                var idlist = "";
                $("#multiselect_to_2 option").each(function () {
                    idlist = idlist + "," +$(this).val();
                });
                $("#languageIdList").val(idlist);
            });
            
            $(".uploadAPK").click(function () {
                $("[name='equipmentId']").val($(this).prev().val());
                $("[name='projectId']").val($(this).prev().prev().val());
            });
            
            $("#submitAPK").submit(function () {
                var len = $("#submitAPK [name='fileName']").val();
                if (len.substring(len.length,len.length-3)!="apk"){
                    alert("只能上传APK文件！！！");
                    return false;
                }
                return true;
            });
        });
    </script>
</body>
</html>
