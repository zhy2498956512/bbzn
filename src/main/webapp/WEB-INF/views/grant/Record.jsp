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
            background-color: #796AEE;
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
        <a href="api/grantNumberRecord/jumpNewsList"><span>返回消息列表</span></a>
    </div>
</div>
<div style="width: 100%;height: 760px;float: left;border-top: #796AEE 2px solid;background-color: #fff;margin-top: 10px">
    <div style="width: 98%;height: 170px;border-radius:8px ;border: 1px solid #1d2124;margin: 0 auto;margin-top: 30px;margin-left: 1%;">
        <div style="width:94% ;margin-left: 3%;height: 60px;border-bottom: 1px solid #818181;">
            <span style="margin-left: 40%;font-size: 24px;">序列码数量变更提醒</span><br>
            <span style="margin-left: 41.5%;font-size: 18px;">${grantNumberRecord.feedbacktime}</span>
        </div>
        <div style="font-size: 18px;margin-left: 1%;">
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
<script type="text/javascript">
$(function () {
    window.parent.Background(${grantNumberRecord.grantNumberRecordId});
});
</script>
</body>
</html>
