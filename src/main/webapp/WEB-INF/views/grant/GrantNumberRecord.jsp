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

<div style="width: 100%;height: 50px;float: left;background-color: #fff;">
    <div class="xmxq_1">
        <span>消息列表</span>
    </div>
</div>
<div style="width: 100%;height: 600px;float: left;border-top: #00F7DE 2px solid;background-color: #fff;">
    <div style="float: left;width: 96%;height: 40px;border:1px solid #c8cbcf;margin-left: 2%;background-color: #fff;margin-top: 30px;">
        <div style="width: 10%;height: 40px;float: left;line-height: 40px;text-align: center ;font-size: 13px;font-weight: bold; ">授权数量</div>
        <div style="width: 30%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">授权备注</div>
        <div style="width: 30%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">授权反馈</div>
        <div style="width: 15%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">申请时间</div>
        <div style="width: 15%;height: 40px;float: left;line-height: 40px; border-left:1px solid #c8cbcf;text-align: center ;font-size: 13px;font-weight: bold; ">反馈时间</div>
    </div>
    <div class="row pre-scrollable" style="float: left;width: 97.5%;height: 400px;margin-left: 2%;overflow-y:scroll;" id="list">
    </div>
    <div class="m-style M-box3" style="float: right;margin-top:20px ;">
        <span id="record0"></span>
        <input type="hidden"><input class="record" type="button" value="上页"><span id="totalPage"></span>
        <input type="hidden"><input class="record" type="button" value="下页">
        <input type="text" width="20px" onkeyup="value=value.replace(/[^0-9]/g,'')"><input type="button" class="record" value="跳转">
    </div>
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
        $.ajax({
            url: "api/grantNumberRecord/getNewsList",
            data: {"pageNum":"1"},
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
</script>

</body>
</html>
