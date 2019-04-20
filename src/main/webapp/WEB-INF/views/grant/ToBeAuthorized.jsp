<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/3/8
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <div style="width: 90%;height: 400px;border: 1px solid #B9BBBE;border-radius:5px ;background-color: #fff;margin: 30px auto;">
        <div style="width: 100%;height: 50px;border-bottom: 2px solid #B9BBBE;float: left;font-size: 15px;font-weight: bold;line-height: 50px;">
            <div style="width: 30%;height: 50px;float: left;text-align: center;">代理商</div>
            <div style="width: 15%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">申请数量</div>
            <div style="width: 20%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">备注说明</div>
            <div style="width: 20%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">申请时间</div>
            <div style="width: 14%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">操作</div>
        </div>
        <div class="row pre-scrollable" style="float: left;width: 99.3%;height: 440px;margin: 0px 0 0 10px;">

            <div style="width: 100%;height: 100%;margin: 10px 0 0 0;" id="list">

            </div>
        </div>
    </div>
    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                url: "api/grantNumberRecord/record",
                data: {"recordType":2},
                type: "POST",
                dataType: "json",
                success: function (msg) {
                    var totalRecord = msg.totalRecord;
                    var totalPage = msg.totalPage;
                    var pageSize = msg.pageSize;
                    var pageNum = msg.pageNum;
                    var list = msg.list;
                    var num = 1;
                    var html = "";
                    $.each(list, function (num) {
                        var grantNumberRecordId = list[num].grantNumberRecordId;
                        var companyName = list[num].companyName;
                        var grantNumberRecordAmount = list[num].grantNumberRecordAmount;
                        var grantNumberRecordRemark = list[num].grantNumberRecordRemark;
                        var applytime = list[num].applytime;

                        html = html + "<div><div style='width: 30%;height: 50px;float: left;border-bottom: 1px solid #B9BBBE;line-height: 50px;'>" +
                            "<div style='float: left;color: skyblue;cursor: pointer;font-size: 18px;text-indent: 170px;'>"+companyName+"</div></div>" +
                            "<div style='width: 15%;height: 50px;float: left;border-bottom: 1px solid #B9BBBE;text-align: center;line-height: 50px;'>"+grantNumberRecordAmount+"</div>" +
                            "<div style='width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>"+grantNumberRecordRemark+"</div>" +
                            "<div style='width: 20%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>"+applytime+"</div>" +
                            "<div style='width: 15%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;'>"+
                            "<input type='hidden' value='"+grantNumberRecordId+"'> " +
                            "<input type='button' value='通过' class='adopt btn btn-primary'>"+
                            "</div></div>";
                    })
                    $("#list").append(html);
                    $(".adopt").bind("click",function() {
                        var t = $(this);
                        if (confirm("确定要授权吗？")) {
                            var grantNumberRecordId = t.prev().val();
                            $.ajax({
                                url: "api/grantNumberRecord/getPurchaseFeedback",
                                data: {"grantNumberRecordId": grantNumberRecordId},
                                type: "POST",
                                dataType: "json",
                                success: function (msg) {
                                    if (msg>0){
                                        t.parent().parent().remove();
                                        alert("授权成功!!!");

                                    }else{
                                        alert("授权失败,系统异常!!!");
                                    }
                                }
                            });
                        }
                    });
                }
            });
        });
    </script>
</body>
</html>
