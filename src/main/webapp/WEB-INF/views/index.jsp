<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/2/28
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base target="rightFrame" href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>贝铂智能开发者平台</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="robots" content="all,follow">
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
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
  </head>
  <body>
    <div class="page">
      <!-- Main Navbar-->
      <header class="header" >
        <nav class="navbar" >
          <!-- Search Box-->
          <div class="search-box" >
            <button class="dismiss"><i class="icon-close"></i></button>
            <form id="searchForm" action="#" role="search">
              <input type="search" placeholder="What are you looking for..." class="form-control">
            </form>
          </div>
          <div class="container-fluid">
            <div class="navbar-holder d-flex align-items-center justify-content-between" >
              <!-- Navbar Header-->
              <div class="navbar-header" >
                <!-- Navbar Brand --><a href="http://119.23.210.209:80/officialWebsite/index.html" target="_blank" class="navbar-brand d-none d-sm-inline-block">
                  <div class="brand-text d-none d-lg-inline-block"><span>贝铂智能开发平台</span></div>
                  <div class="brand-text d-none d-sm-inline-block d-lg-none"><strong>BB</strong></div></a>
                <!-- Toggle Button--><a id="toggle-btn" href="#" class="menu-btn active"><span></span><span></span><span></span></a>
              </div>
              <!-- Navbar Menu -->
              <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                <!-- Search-->
                <!-- <li class="nav-item d-flex align-items-center"><a id="search" href="#"><i class="icon-search"></i></a></li>-->
                <!-- Notifications-->
                <c:if test="${sessionScope.Company.companyUserType==0}">
                <li class="nav-item dropdown"> <a id="notifications" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-bell-o"></i>
                  <c:if test="${page.totalRecord>0}">
                    <span class="badge bg-red badge-corner">${page.totalRecord}</span>
                  </c:if>
                  </a>
                  <ul aria-labelledby="notifications" class="dropdown-menu">
                    <c:if test="${page.list == null}">
                      <li><span rel="nofollow" href="#" class="dropdown-item">
                        <div class="notification">
                          <div class="notification-content">暂无数据</div>
                        </div></span></li>
                    </c:if>
                    <c:if test="${page.list != null}">
                      <c:forEach items="${page.list}" var="grantNumberRecord">
                        <li><a rel="nofollow" href="api/grantNumberRecord/getNewsDetails?grantNumberRecordId=${grantNumberRecord.grantNumberRecordId}" class="dropdown-item">
                          <div class="notification">
                            <div class="notification-content"><i class="fa fa-envelope bg-green"></i>
                              <c:if test="${grantNumberRecord.applytime!=null&&grantNumberRecord.applytime!=''}">
                                您${grantNumberRecord.applytime}申请的${grantNumberRecord.grantNumberRecordAmount}个序列数已过审
                              </c:if>
                              <c:if test="${grantNumberRecord.applytime==null||grantNumberRecord.applytime==''}">
                                管理员${grantNumberRecord.feedbacktime}给您添加了${grantNumberRecord.grantNumberRecordAmount}个序列数
                              </c:if>
                            </div>
                            <div class="notification-time"><small>反馈时间：[${grantNumberRecord.feedbacktime}]</small></div>
                            <c:if test="${grantNumberRecord.grantNumberRecordSee==0}">
                              <span style="color: red;position:absolute;right:6px;margin-top: -25px;">*</span>
                            </c:if>
                          </div></a></li>
                      </c:forEach>
                    </c:if>
                    <li id="more"><a rel="nofollow" href="api/grantNumberRecord/jumpNewsList" class="dropdown-item all-notifications text-center"> <strong>查看更多信息</strong></a></li>
                  </ul>
                </li>
                </c:if>
                <!-- Logout    -->
                <li class="nav-item"><a href="" id="quit" class="nav-link logout"> <span class="d-none d-sm-inline">注销</span><i class="fa fa-sign-out"></i></a></li>
              </ul>
            </div>
          </div>
        </nav>
      </header>
      <%
        com.example.bbzn.pojo.Company company=(com.example.bbzn.pojo.Company)session.getAttribute("Company");
      %>
      <div class="page-content d-flex align-items-stretch"> 
        <!-- Side Navbar -->
        <nav class="side-navbar" style="">
          <!-- Sidebar Header-->
          <div class="sidebar-header d-flex align-items-center">
            <div class="avatar"><img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>
            <div class="title">
              <h1 class="h4">贝铂</h1>
              <p>用户名：${sessionScope.Company.companyUserName}</p>
            </div>
          </div>
          <!-- Sidebar Navidation Menus<span class="heading">Main</span>-->
          <ul class="list-unstyled">
            <li  class="active"><a href="#xmgl_1" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>信息总览</a>
              <ul id="xmgl_1" class="collapse list-unstyled ">
                <c:if test="${sessionScope.Company.companyUserType!=null}"><li><li class="judge"><a href="api/company/getCompany">代理信息</a></li></c:if>
              </ul>
            </li>
            <li><a href="#xmgl_2" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>设备管理 </a>
              <ul id="xmgl_2" class="collapse list-unstyled ">
                <c:if test="${sessionScope.Company.companyUserType==1}"><li><a href="api/equipment/jump?UserType=1" class="judge">设备速查</a></li></c:if>
                <c:if test="${sessionScope.Company.companyUserType==0}"><li><a href="api/equipment/jump?UserType=0" class="judge">设备速查</a></li></c:if>
              </ul>

            </li>
            <li ><a href="#xmgl_3" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>代理商管理 </a>
              <ul id="xmgl_3" class="collapse list-unstyled ">
                <c:if test="${sessionScope.Company.companyUserType==1}"><li><a href="api/company/getCompanyList" class="judge">代理商列表</a></li>
                                                                        <li><a href="api/grantNumberRecord/toBeAuthorized" class="judge">待审核列表</a></li></c:if>
                <c:if test="${sessionScope.Company.companyUserType==0}"><li><a href="api/grantNumberRecord/jump" class="judge">代理商信息</a></li></c:if>
                <%--<li id="test">删除Session</li>--%>
                <%--<li><a href="api/grantNumberRecord/display">授权申请</a></li>
                <li><a href="api/grantNumberRecord/display">生成授权码</a></li>--%>
              </ul>
            </li>
          </ul>
        </nav>
            
				<div class="content-inner">
          <!-- Page Header-->
          <%--<header class="page-header">
            <div class="container-fluid">
              <h2 class="no-margin-bottom">首页</h2>
            </div>
          </header>--%>
          <!-- Dashboard Counts Section-->
          <%--<section class="dashboard-counts no-padding-bottom">
          
          </section>--%>
                  <jsp:include page="xxx.jsp" />
                  <footer class="main-footer">
                    <div class="container-fluid">
                      <div class="row">
                        <div class="col-sm-6">
                          <p>Your company &copy; 2019-2021</p>
                        </div>
                        <div class="col-sm-6 text-right">
                          <p><a target="_blank" title="">深圳市贝铂智能科技有线公司</a><a target="_blank"></a></p>
                          <!-- Please do not remove the backlink to us unless you support further theme's development at https://bootstrapious.com/donate. It is part of the license conditions. Thank you for understanding :)-->
                        </div>
                      </div>
                    </div>
                  </footer>
				</div>
      </div>
    </div>
    <!-- JavaScript files-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/jquery-validation/jquery.validate.min.js"></script>
    <script src="js/charts-home.js"></script>
    <!-- Main File-->
    <script src="js/front.js"></script>
    <script type="text/javascript">
      function Background(id) {
        $.ajax({
          url: "api/grantNumberRecord/getNewsList",
          data: {"pageNum":"1","pageSize":"4"},
          type: "POST",
          dataType: "json",
          success: function (msg) {
            var data = msg.list;
            var totalRecord = msg.totalRecord;
            if(totalRecord>0){
              $(".badge-corner").html(totalRecord);
            }else{
              $(".badge-corner").hide();
            }
            var num = 1;
            var html = "";
            if(data.length>0){
              $.each(data, function (num) {
                var grantNumberRecordId = data[num].grantNumberRecordId;
                var grantNumberRecordAmount = data[num].grantNumberRecordAmount;
                var applytime = data[num].applytime;
                var feedbacktime = data[num].feedbacktime;
                var grantNumberRecordSee = data[num].grantNumberRecordSee;
                html = html + "<li><a rel='nofollow' href='api/grantNumberRecord/getNewsDetails?grantNumberRecordId="+grantNumberRecordId+"' class='dropdown-item'>" +
                        "                          <div class='notification'>" +
                        "                            <div class='notification-content'><i class='fa fa-envelope bg-green'></i>";
                if(applytime!=null&&applytime!=""){
                  html = html + "您"+applytime+"申请的"+grantNumberRecordAmount+"个序列数已过审";
                }
                if(applytime==null||applytime==""){
                  html = html + "管理员"+feedbacktime+"给您添加了"+grantNumberRecordAmount+"个序列数";
                }
                html = html + "                            </div>" +
                        "                            <div class='notification-time'><small>反馈时间：["+feedbacktime+"]</small></div>";
                if(grantNumberRecordSee==0){
                  html = html + "<span style='color: red;position:absolute;right:6px;margin-top: -25px;'>*</span>";
                }
              })
              html = html + "<li id='more'><a rel='nofollow' href='api/grantNumberRecord/jumpNewsList' class='dropdown-item all-notifications text-center'><strong>查看更多信息</strong></a></li>";
              /*html = html + "<li><a rel='nofollow' href='api/grantNumberRecord/getNewsDetails?grantNumberRecordId=11' class='dropdown-item'>" +
                      "                          <div class='notification'>" +
                      "                            <div class='notification-content'><i class='fa fa-envelope bg-green'></i>"+
              "                            </div>" +
                      "                            <div class='notification-time'><small>反馈时间：[6666666666666]</small></div>";*/
              /*"<span style='color: red;position:absolute;right:6px;margin-top: -25px;'>*</span></div></a></li>";*/
            }else{
              html = html + "<li><span rel='nofollow' class='dropdown-item'>" +
                      "                        <div class='notification'>" +
                      "                          <div class='notification-content'>暂无数据</div>" +
                      "                        </div></span></li>";
              $("#more").hide();
            }
            $(".dropdown-menu").html("");
            $(".dropdown-menu").append(html);
          }
        });
      }

      $(function () {
        $("#quit").click(function () {
          location.href="/api/login/quit"
        });
        /*
        $(".judge").click(function () {
          $.ajax({
            url: "api/company/judgeSession",
            data: {},
            type: "POST",
            dataType: "json",
            success: function (msg) {
              if(msg==1){
                $("#quit").click();
              }
            }
          });
        });*/
        /*$("#test").click(function () {
          $.ajax({
            url: "api/company/test",
            data: {},
            type: "POST",
            dataType: "json",
            success: function (msg) {
              if(msg==1){
                alert("删除");
              }
            }
          });
        });*/
      });
    </script>
  </body>
</html>