<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/3/6
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" href="css/global.css">
<link rel="stylesheet" href="css/Equipment.css">
<link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="vendor/jquery/jquery.min.js"></script>
<link rel="stylesheet"  href="css/global.css"/>
<link rel="stylesheet" href="css/pagination.css" />
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
    <div style=" width: 100%;height:120px;float: left;">
        <div>
            <div>
                <select  class="form-control1" id="multiselect_to_1"multiple="multiple"size="200"style="height: 100px;width: 200px;float: left;">
                    <c:forEach items="${CompanyList}" var="company">
                        <c:if test="${company.companyUserType==0}">
                            <option value="${company.companyId}">${company.companyName}</option>
                        </c:if>
                    </c:forEach>
                </select></div>
            <div>
                <select  class="form-control" id="multiselect_to_2"multiple="multiple"size="200"style="height: 100px;width: 200px;float: left;">
                </select></div>
            <div><input type="date" style="height: 32px;" class="form-control" id="multiselect_to_3" /><br>
                 <input type="date" style="height: 32px;" class="form-control" id="multiselect_to_4" /></div>
        </div>


    </div>
    <div style="width: 100%;height: 600px;float: left;border-top: #00F7DE 2px solid;background-color: #fff;">
        <div style="float: left;width: 96%;height: 40px;border:1px solid #c8cbcf;margin-left: 2%;background-color: #fff;margin-top: 30px;">
            <div style="width: 15%;height: 40px;float: left;line-height: 40px;text-align: center ;font-size: 13px;font-weight: bold; ">设备ID<input type="hidden" id="cId" VALUE="${sessionScope.Company.companyUserType}"></div>
            <div style="width: 9%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">代理</div>
            <div style="width: 9%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">项目</div>
            <div style="width: 9%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; " >状态</div>
            <div style="width: 9%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">系统版本</div>
            <div style="width: 9%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">APK版本</div>
            <div style="width: 15%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">创建时间</div>
            <div style="width: 15%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">最近登陆时间</div>
            <div style="width: 9%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">操作</div>
        </div>
        <div class="row pre-scrollable" style="float: left;width: 97.5%;height: 400px;margin-left: 2%;overflow-y:scroll;" id="list">
        </div>
        <div class="m-style M-box3" style="float: right;margin-top:20px ;">
            <span id="record0"></span>
            <input type="hidden"><input class="record" type="button" value="上页"><span id="totalPage"></span>
            <input type="hidden"><input class="record" type="button" value="下页">
            <input type="text" style="height:30px;width:40px" onkeyup="value=value.replace(/[^0-9]/g,'')">&nbsp;<input type="button" class="record" value="跳转">
        </div>
    </div>

    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#multiselect_to_1").change(function () {
                var multiselect_to_1 = $(this).val();
                $("#multiselect_to_2").html("");
                $.ajax({
                    url: "/api/equipment/getProjectList",
                    data: {"multiselect_to_1":""+multiselect_to_1},
                    type: "POST",
                    dataType: "json",
                    success: function (msg) {
                        var data = eval(msg);//将msg化为数
                        var num = 1;
                        var html = "";
                        $.each(data, function (num) {
                            var projectId = data[num].projectId;
                            var projectName = data[num].projectName;
                            html = html + "<option value='"+projectId+"'>"+projectName+"</option>";
                        })
                        $("#multiselect_to_2").append(html);
                    }
                });
            });
            
            $(".form-control").change(function () {
                var multiselect_to_2 = $("#multiselect_to_2").val();
                var multiselect_to_3 = $("#multiselect_to_3").val();
                var multiselect_to_4 = $("#multiselect_to_4").val();
                $.ajax({
                    url: "/api/equipment/getEquipment",
                    data: {"multiselect_to_2":multiselect_to_2+"","multiselect_to_3":multiselect_to_3,"multiselect_to_4":multiselect_to_4},
                    type: "POST",
                    dataType: "json",
                    success: function (msg) {
                        var num = 1;
                        $("#list").html("");
                        var html = "";
                        var totalRecord = msg.totalRecord;    //总条数
                        var totalPage = msg.totalPage;
                        var pageNum = msg.pageNum;
                        $(".record").each(function(){
                            if ($(this).val()=="上页"){
                                $(this).prev().val((pageNum-1));
                            }else if ($(this).val()=="下页") {
                                $(this).prev().val((pageNum+1));
                            }else if ($(this).val()=="跳转") {
                                $(this).prev().val((pageNum));
                            }
                        });
                        var m1 = 0;
                        var m2 = 0;
                        $("#multiselect_to_1 option").each(function () {
                            if ($(this).attr("selected")){
                                m1+=1;
                            }
                        });
                        $("#multiselect_to_2 option").each(function () {
                            if ($(this).attr("selected")){
                                m2+=1;
                            }
                        });
                        $("#record0").html("共"+m1+"个代理商,"+m2+"个项目,"+totalRecord+"台设备");
                        $("#totalPage").html(pageNum+"/"+totalPage);
                        var list = msg.list;
                        $.each(list, function (num) {
                            var grantCode = list[num].grantCode;
                            var equipmentId = list[num].equipmentId;
                            var appId = list[num].appId;
                            var projectName = list[num].projectName;
                            var companyName = list[num].companyName;
                            var systemEdition = list[num].systemEdition;
                            var apkEdition = list[num].apkEdition;
                            var foundtime = list[num].foundtime;
                            var logintime = list[num].logintime;
                            var equipmentValidity = list[num].equipmentValidity;
                            html = html + "<div style=\"width: 100%;float: left;border-bottom: 1px solid #D6D8DB;height: 80px;font-size: 14px;\">\n" +
                                "             <div style=\"float: left;width: 15%;text-align: center;padding-top:15px;color: #0069D9;\">"+grantCode+"</div>\n" +
                                "            <div style=\"float: left;width: 9%;text-align: center;padding-top:15px;color: #0069D9;\">"+companyName+"</div>\n" +
                                "            <div style=\"float: left;width: 9%;text-align: center;padding-top:15px;color: #0069D9;\">"+projectName+"</div>\n" +
                                "            <div style=\"float: left;width: 9%;text-align: center;line-height: 80px;\"><span style=\"padding: 5px 5px;";
                            if (logintime==null||logintime=="") {
                                html = html + "background-color: #FF0000;color: #fff;line-height: 55px;border-radius:5px ;\">暂未使用";
                            }else {
                                html = html + "background-color: #5cb85c;color: #fff;line-height: 55px;border-radius:5px ;\">正常使用";
                            }
                            html = html + "</span></div><div style=\"float: left;width: 9%;text-align: center;padding-top:15px;color: #0069D9;\">";
                            if (systemEdition==null||systemEdition=="") {
                                html = html + "暂无数据";
                            }else {
                                html = html + systemEdition;
                            }
                            html = html + "</div><div style=\"float: left;width: 9%;text-align: center;padding-top:15px;color: #0069D9;\">";
                            if (apkEdition==null||apkEdition=="") {
                                html = html + "暂无数据";
                            }else {
                                html = html + apkEdition;
                            }
                            html = html + "</div><div style=\"float: left;width: 15%;text-align: center;padding-top:15px;color: #0069D9;\">"+foundtime+"</div>\n"+
                                    "            <div style=\"float: left;width: 15%;text-align: center;padding-top:15px;color: #0069D9;\">";
                            if (logintime==null||logintime=="") {
                                html = html + "暂无数据";
                            }else {
                                html = html + logintime;
                            }
                            html = html + "</div>\n" ;
                            if($("#cId").val()==0){
                                html = html +     "<input type='hidden' value='"+equipmentId+"' ><div class='deleteEquipment' style=\"float: left;width: 9%;text-align: center;line-height: 80px;\">" +
                                    "<span style=\"padding: 5px 5px;background-color: #dd4b39;color: #fff;cursor: pointer;line-height: 55px;border-radius:5px ;\">删除设备</span></div>\n" +
                                    "        </div>";
                            }else{
                                html = html +     "            <div style=\"float: left;width: 9%;text-align: center;line-height: 80px;\"><span style=\"padding: 5px 5px;background-color: #dd4b39;color: #fff;line-height: 55px;border-radius:5px ;\">暂无操作</span></div>\n" +
                                    "        </div>";
                            }
                        })
                        $("#list").append(html);
                        $(".deleteEquipment").click(function () {
                            var equipment = $(this);
                            var equipmentId = equipment.prev().val();
                            if (confirm("确定要删除该设备吗？")) {
                                $.ajax({
                                    url: "/api/equipment/deleteEquipment",
                                    data: {"equipmentId":equipmentId+""},
                                    type: "POST",
                                    dataType: "json",
                                    success: function (msg) {
                                        if (msg>0) {
                                            alert("删除成功");
                                            equipment.parent().remove();
                                        }else {
                                            alert("删除失败");
                                        }
                                    }
                                })
                            }
                        });
                    }
                });
            });

            $(".record").click(function () {
                var multiselect_to_2 = $("#multiselect_to_2").val();
                var multiselect_to_3 = $("#multiselect_to_3").val();
                var multiselect_to_4 = $("#multiselect_to_4").val();
                var pageNum = $(this).prev().val();
                $.ajax({
                    url: "/api/equipment/getEquipment",
                    data: {"multiselect_to_2":multiselect_to_2+"","multiselect_to_3":multiselect_to_3,"multiselect_to_4":multiselect_to_4,"pageNum":pageNum},
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
                            }
                        });
                        $("#totalPage").html(pageNum+"/"+totalPage);
                        /*$("#totalRecord").html("共"+totalRecord+"条记录  ");*/
                        var list = msg.list;
                        var num = 1;
                        var html = "";
                        $.each(list, function (num) {
                            var grantCode = list[num].grantCode;
                            var equipmentId = list[num].equipmentId;
                            var appId = list[num].appId;
                            var projectName = list[num].projectName;
                            var companyName = list[num].companyName;
                            var systemEdition = list[num].systemEdition;
                            var apkEdition = list[num].apkEdition;
                            var foundtime = list[num].foundtime;
                            var logintime = list[num].logintime;
                            var equipmentValidity = list[num].equipmentValidity;
                            html = html + "<div style=\"width: 100%;float: left;border-bottom: 1px solid #D6D8DB;height: 80px;font-size: 14px;\">\n" +
                                "             <div style=\"float: left;width: 15%;text-align: center;padding-top:15px;color: #0069D9;\">"+grantCode+"</div>\n" +
                                "            <div style=\"float: left;width: 9%;text-align: center;padding-top:15px;color: #0069D9;\">"+companyName+"</div>\n" +
                                "            <div style=\"float: left;width: 9%;text-align: center;padding-top:15px;color: #0069D9;\">"+projectName+"</div>\n" +
                                "            <div style=\"float: left;width: 9%;text-align: center;line-height: 80px;\"><span style=\"padding: 5px 5px;";
                            if (logintime==null||logintime=="") {
                                html = html + "background-color: #FF0000;color: #fff;line-height: 55px;border-radius:5px ;\">暂未使用";
                            }else {
                                html = html + "background-color: #5cb85c;color: #fff;line-height: 55px;border-radius:5px ;\">正常使用";
                            }
                            html = html + "</span></div><div style=\"float: left;width: 9%;text-align: center;padding-top:15px;color: #0069D9;\">";
                            if (systemEdition==null||systemEdition=="") {
                                html = html + "暂无数据";
                            }else {
                                html = html + systemEdition;
                            }
                            html = html + "</div><div style=\"float: left;width: 9%;text-align: center;padding-top:15px;color: #0069D9;\">";
                            if (apkEdition==null||apkEdition=="") {
                                html = html + "暂无数据";
                            }else {
                                html = html + apkEdition;
                            }
                            html = html + "</div><div style=\"float: left;width: 15%;text-align: center;padding-top:15px;color: #0069D9;\">"+foundtime+"</div>\n"+
                                "            <div style=\"float: left;width: 15%;text-align: center;padding-top:15px;color: #0069D9;\">";
                            if (logintime==null||logintime=="") {
                                html = html + "暂无数据";
                            }else {
                                html = html + logintime;
                            }
                            html = html + "</div>\n" ;
                            if($("#cId").val()==0){
                                html = html +     "<input type='hidden' value='"+equipmentId+"' ><div class='deleteEquipment' style=\"float: left;width: 9%;text-align: center;line-height: 80px;\">" +
                                    "<span style=\"padding: 5px 5px;background-color: #dd4b39;color: #fff;cursor: pointer;line-height: 55px;border-radius:5px ;\">删除设备</span></div>\n" +
                                    "        </div>";
                            }else{
                                html = html +     "            <div style=\"float: left;width: 9%;text-align: center;line-height: 80px;\"><span style=\"padding: 5px 5px;background-color: #dd4b39;color: #fff;line-height: 55px;border-radius:5px ;\">暂无操作</span></div>\n" +
                                    "        </div>";
                            }
                        })
                        $("#list").html("");
                        $("#list").append(html);
                        $(".deleteEquipment").click(function () {
                            var equipment = $(this);
                            var equipmentId = equipment.prev().val();
                            if (confirm("确定要删除该设备吗？")) {
                                $.ajax({
                                    url: "/api/equipment/deleteEquipment",
                                    data: {"equipmentId":equipmentId+""},
                                    type: "POST",
                                    dataType: "json",
                                    success: function (msg) {
                                        if (msg>0) {
                                            alert("删除成功");
                                            equipment.parent().remove();
                                        }else {
                                            alert("删除失败");
                                        }
                                    }
                                })
                            }
                        });
                    }
                });
            });
        });
    </script>
</body>
</html>
