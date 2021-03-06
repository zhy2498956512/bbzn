<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/3/4
  Time: 11:42
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
    <style>
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

<div style=" width: 100%;height:50px;float: left;border-top: #796AEE 2px solid;background-color: #fff;">
    <div class="xmxq_1">
        <span>消息列表</span>
    </div>
</div>
<div style="width: 100%;height: 760px;float: left;border-top: #796AEE 2px solid;background-color: #fff;margin-top: 10px">
    <div style="float: left;width: 96%;height: 40px;margin-left: 2%;background-color: #fff;margin-top: 30px;">
        <div style="width: 10%;height: 40px;float: left;line-height: 40px;text-align: center ;font-size: 18px;font-weight: bold; ">授权数量</div>
        <div style="width: 60%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 18px;font-weight: bold; ">授权备注</div>
        <div style="width: 15%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 18px;font-weight: bold; ">申请时间</div>
        <div style="width: 14%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 18px;font-weight: bold; ">反馈时间</div>
    </div>
    <div style="float: left;width: 97.5%;height: 400px;margin-left: 2%;overflow-y:scroll;" id="list">

    </div>
    <div style="float: right;margin-top:20px ;">
        <span id="record0"></span>
        <input type="hidden" value="1"><input class="record" type="button" value="上页"><span id="totalPage"></span>
        <input type="hidden" value="1"><input class="record" type="button" value="下页">
        <input type="text" style="height:30px;width:40px" value="1" onkeyup="value=value.replace(/[^0-9]/g,'')">&nbsp;<input type="button" class="record" value="跳转">
    </div>
</div>

<script src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            url: "/api/grantNumberRecord/getNewsList",
            data: {"pageNum":"1"},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                var pageNum = msg.pageNum;              //当前页
                var totalRecord = msg.totalRecord;      //总条数
                var totalPage = msg.totalPage;          //总页数
                var pageSize = msg.pageSize;            //每页显示的记录数
                $("#totalPage").html(pageNum+"/"+totalPage);
                $("#record0").html("共"+totalRecord+"条记录");
                var data = msg.list;
                var num = 1;
                var html = "";
                $.each(data, function (num) {
                    var grantNumberRecordId = data[num].grantNumberRecordId;
                    var grantNumberRecordAmount = data[num].grantNumberRecordAmount;
                    var grantNumberRecordRemark = data[num].grantNumberRecordRemark;
                    var applytime = data[num].applytime;
                    var feedbacktime = data[num].feedbacktime;
                    var grantNumberRecordSee = data[num].grantNumberRecordSee;
                    html = html + "<a  style='width: 100%;height: 50px;' href='api/grantNumberRecord/getNewsDetails?grantNumberRecordId="+grantNumberRecordId+"'><div>" +
                        "                <div style='border-bottom: 1px solid #B9BBBE;cursor: pointer;width: 10%;height: 50px;" +
                        "                        border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>";
                        if(grantNumberRecordSee==0){
                            html = html + "<span style='color: red;margin-left: 5px;margin-top: -25px;'>*</span>";
                        }
                        html = html + grantNumberRecordAmount+"                </div>" +
                        "                <div style='width: 60%;height: 50px;float: left;border-bottom: 1px solid #B9BBBE;text-align: center;line-height: 50px;'>";
                        if(grantNumberRecordRemark==""||grantNumberRecordRemark==null){
                            html = html + "暂无数据";
                        }else{
                            html = html + grantNumberRecordRemark;
                        }
                        html = html + "                </div>" +
                        "                <div style='width: 15%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>" ;
                        if(applytime==""||applytime==null){
                            html = html + "暂无数据";
                        }else{
                            html = html + applytime;
                        }
                        html = html + "                </div>" +
                        "                <div style='width: 15%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>" +
                        feedbacktime+"                </div>" +
                        "            </div></a>";
                })
                $("#list").html("");
                $("#list").append(html);
            }
        });
    });
</script>

</body>
</html>
