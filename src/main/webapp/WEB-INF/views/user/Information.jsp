<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/2/28
  Time: 14:15
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
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>贝铂智能</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="robots" content="all,follow">
        <!-- Bootstrap CSS-->
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
        <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
        <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
</head>
<body style="background-color: #f3f3f4;">
<%
    com.example.bbzn.pojo.Company company=(com.example.bbzn.pojo.Company)session.getAttribute("Company");
%>

<div>
    <table>
        <c:forEach items="${list}" var="a">
            <tr><td>${a}</td></tr>
        </c:forEach>
    </table>
</div>
<div style="width: 40%;height: 300px;background-color: #fff;border-top: 2px solid #796AEE;float: left;margin: 30px 0 0 20px;">
    <div style="width: 100%;height: 60px;border-bottom: 1px solid #cccccc;">
        <h5 style="text-indent: 20px;line-height: 60px;">代理信息</h5>
    </div>
    <div style="margin: 20px auto;width: 50%;font-size: 18px;font-family: 黑体;font-weight: bold;"><br>
        代理名称：<span><%= company.getCompanyName() %></span><br>
        联系电话：<span><%= company.getCompanyPhone() %></span><br>
        联系地址：<span><%= company.getCompanyAddress() %></span><br>
        创建时间：<span><fmt:formatDate value="<%= company.getCompanyFoundtime() %>" type="date" pattern="yyyy-MM-dd　HH:mm:ss"/></span><br>

    </div>
        <div style="margin: 0 auto;width: 50%;">
            <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                设置代理信息
            </button>
            <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal2">
                修改密码
            </button><br>
          </div>
        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="width: 600px;">
                    <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                        <h5>修改代理信息</h5>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">×
                        </button>
                    </div>
                    <div class="modal-body" style="border-top:3px solid skyblue ; margin: 30px 30px 0 30px;">
                        <!-- 设置代理信息 -->
                    </div>
                    <form action="api/company/updateCompany" method="post" id="updateCompany">
                    <div style="margin: 10px 0 0 60px;">
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 代理名称</span>
                        <input type="text" name="companyName" value="<%= company.getCompanyName() %>" style="width: 300px;margin: 0px 30px 0 30px;"/>
                    </div>
                    <div style="margin: 10px 0 0 60px;" >
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 联系电话</span>
                        <input type="text" name="companyPhone" value="<%= company.getCompanyPhone() %>" style="width: 300px;margin: 0px 30px 0 30px;"/>
                    </div>
                    <div style="margin: 10px 0 0 60px;">
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 联系地址</span>
                        <input type="text" name="companyAddress" value="<%= company.getCompanyAddress() %>" style="width: 300px;margin: 0px 30px 0 30px;"/>
                    </div>
                    <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">关闭
                        </button>
                        <button type="submit" class="btn btn-primary">
                            提交
                        </button>
                    </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->


        <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="width: 600px;">
                    <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                        <h5>修改代理密码</h5>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-hidden="true">×
                        </button>

                    </div>


                    <div class="modal-body" style="border-top:3px solid skyblue ; margin: 30px 30px 0 30px;">
                        <!-- 设置代理信息 -->
                    </div>
                    <form  action="api/company/updatePass" method="post" id="updatePass">
                    <div style="margin: 10px 0 0 60px;" >
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 新&nbsp;&nbsp;密&nbsp;&nbsp;码</span>
                        <input type="text" name="newPass" id="newPass" style="width: 300px;margin: 0px 30px 0 30px;"/>
                    </div>
                    <div style="margin: 10px 0 0 60px;">
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 确认密码</span>
                        <input type="text" name="surePass" id="surePass" style="width: 300px;margin: 0px 30px 0 30px;"/>
                    </div>
                    <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">关闭
                        </button>
                        <button type="submit" class="btn btn-primary">
                            提交
                        </button>
                    </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
    </div>
    <!-- JavaScript files-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/jquery-validation/jquery.validate.min.js"></script>
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <script src="js/charts-home.js"></script>
    <!-- Main File-->
    <script src="js/front.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#updatePass").submit(function () {
                var newPass = $("#newPass").val();
                var surePass = $("#surePass").val();
                if(newPass==""||newPass==null){
                    return false;
                }else if(surePass==""||surePass==null){
                    return false;
                }else if(surePass!=newPass){
                    return false;
                }else{
                    return true;
                }
            });
        });
    </script>
</body>
</html>
