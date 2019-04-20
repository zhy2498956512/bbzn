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
        <a href="/api/grantNumberRecord/jumpNewsList"><span>返回消息列表</span></a>
    </div>
</div>
<div style="width: 100%;height: 600px;float: left;border-top: #00F7DE 2px solid;background-color: #fff;">
    <div style="width: 96%;height: 170px;border-radius:8px ;border: 1px solid #cf5642;margin: 0 auto;margin-top: 30px;margin-left: 2%;">
        <div style="width:94% ;margin-left: 3%;height: 60px;border-bottom: 1px solid #818181;">
            <span style="margin-left: 50%;font-size: 24px;">序列码数量变更提醒</span><br>
            <span style="margin-left: 50%;font-size: 18px;">${grantNumberRecord.feedbacktime}</span>
        </div>
        <div style="font-size: 18px;">
            尊敬的${sessionScope.Company.companyName}
            <c:if test="${grantNumberRecord.applytime!=null&&grantNumberRecord.applytime!=''}">
                您${grantNumberRecord.applytime}申请的${grantNumberRecord.grantNumberRecordAmount}个序列数已过审
            </c:if>
            <c:if test="${grantNumberRecord.applytime==null||grantNumberRecord.applytime==''}">
                管理员${grantNumberRecord.feedbacktime}给您添加了${grantNumberRecord.grantNumberRecordAmount}个序列数
            </c:if>
            ,序列码数量在代理商信息页查看。
        </div>
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
</body>
</html>
