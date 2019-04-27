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

<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <link rel="stylesheet" href="css/layui.css">
    <link rel="stylesheet" href="css/date.css">
    <style>
        .xmxq_1{
            float: left;
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
            background-color: #796AEE;
            margin: 5px 0 0 15px;
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
            margin: 5px 0 0 15px;
            color: #fff;
        }
    </style>
</head>
<body>
    <div style=" width: 99%;height:150px;float: left;border-top: #796AEE 2px solid;background-color: #fff;margin-top: 8px;margin-left: 8px;">
        <div>
            <div style="float: left;margin-top: 20px;margin-left: 10px;">
                <span style="float: left;">代理商：</span>
                <select  class="form-control1" id="multiselect_to_1" multiple="multiple" size="200" style="height: 100px;width: 200px;">
                    <c:forEach items="${CompanyList}" var="company">
                        <c:if test="${company.companyUserType==0}">
                            <option value="${company.companyId}">${company.companyName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div style="float: left;margin-top: 20px;margin-left: 10px;">
                <span style="float: left;">项目：</span>
                <select  class="form-control" id="multiselect_to_2" multiple="multiple" size="200"style="height: 100px;width: 200px;">
                </select>
            </div>
            <div style="float: left;margin-top: 20px;margin-left: 20px;">
                <span style="float: left;">时间：</span>
                <div style="float: left;" class="layui-input-inline ui-time">
                    <input type="text" id="multiselect_to_3" class="layui-input ui-time-text" value="" kssj="" jssj="" autocomplete="off" readonly="readonly" />
                </div>
                <div class="xmxq_1 record">
                    <span>查詢</span>
                </div>
            </div>
                    <%--起始时间：</span><input type="text" id="multiselect_to_3" placeholder="YYYY-MM-DD"><br>
                <span>截至时间：</span><input type="text" id="multiselect_to_4" placeholder="YYYY-MM-DD"><br>
                <input type="date" style="height: 32px;" class="form-control" id="multiselect_to_3" />
                 <input type="date" style="height: 32px;" class="form-control" id="multiselect_to_4" />--%>
        </div>
    </div>


    <div style="width: 99%;height: 660px;float: left;border-top: #796AEE 2px solid;background-color: #fff;margin-left: 8px;margin-top: 8px;">
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

    <script src="plugin/layui-v1.0.7/layui.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">

        layui.use(['laydate','dateLay'], function(){
            var  layer = layui.layer,laydate = layui.laydate;
            var obj={
                init:function(){
                    this.dp11=$('#multiselect_to_3');
                    this.dp12=$('#dp12');
                    this.initEvent();
                },
                initEvent:function(){
                    this.dp11.dateLay();
                    this.dp12.dateLay();

                }
            }
            obj.init();
        });
    </script>
    <%--<script type="text/javascript" src="js/rolldate.min.js"></script>
    <script>
        window.onload = function(){
            new rolldate.Date({
                el:'#multiselect_to_3',
                format:'YYYY-MM-DD',
                beginYear:2000,
                endYear:2100
            })
            new rolldate.Date({
                el:'#multiselect_to_4',
                format:'YYYY-MM-DD',
                beginYear:2000,
                endYear:2100
            })
        }
    </script>--%>

    <script type="text/javascript">
        $(function () {
            $("#multiselect_to_1").change(function () {
                var multiselect_to_1 = $(this).val();
                $("#multiselect_to_2").html("");
                $.ajax({
                    url: "api/equipment/getProjectList",
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

            function conditionalQuery(pojo){//自定义函数
                var multiselect_to_2 = $("#multiselect_to_2").val();
                var multiselect_to_3 = $("#multiselect_to_3").val();
                $.ajax({
                    url: "/api/equipment/getEquipment",
                    data: {"multiselect_to_2":multiselect_to_2+"","date":multiselect_to_3,"pageNum":pojo.prev().val()+""},
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
            }

            $("#multiselect_to_2").change(function () {
                conditionalQuery($(this));
            });
            $(".record").click(function () {
                conditionalQuery($(this));
            });
        });
    </script>
</body>
</html>
