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
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.pagination.js"></script>
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->

    <style>
        .xmxq{
            width: 99%;
            margin-top: 8px;
            margin-left: 8px;
            border-top: 2px solid #796AEE;
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
        .xmxq_zt{
            float: left;
            width: 100%;
            height: 220px;
            border-top: 1px solid #ccc;
        }
        .zt_bt{
            float: left;
            width: 15%;
            height: 220px;

        }
        .zt_bt .zt_ul{
            float: right;
        }
        .zt_ul li{
            list-style:none;
            margin-top: 13px;
        }
        .zt_index{
            float: left;
        }
        .zt_index ul{
            list-style:none;
        }
        .zt_index li{
            margin-top: 11px;
        }

        .zt_ul span{
            margin-left: 10px;
            padding: 6px 12px;
            background-color: #00a65a;
            color: #fff;
            border-radius:3px ;
        }
        .zt_ul span:hover{
            border-radius:3px ;
            margin-left: 10px;
            color: #fff;
            cursor:pointer;
            padding: 6px 12px;
            background-color: #008d4c;
        }
        .zt_butten{
            float: left;
            width: 30%;
            height: 220px;
        }
    </style>
</head>
<body style="background-color: #ccc;">
<div class="xmxq">
    <div class="xmxq_1">
        <span id="regression" style="background-color: #796AEE;">返回设备列表</span>
        <span>设备的详细信息</span><input type="hidden" id="projectId" value="${projectId}"><input type="hidden" id="equipmentId" value="${equipment.equipmentId}">
    </div>
    <div class="xmxq_zt">
        <div class="zt_bt">
            <ul class="zt_ul">
                <li>设备ID</li>
                <li>所在代理</li>
                <li>所在项目</li>
                <li>创建时间</li>
                <li>最近登录时间</li>
                <li>当前APK版本</li>
                <li>当前系统版本</li>
            </ul>
        </div>
        <div class="zt_index">
            <ul>
                <li><input type="text" value="${equipment.grantCode}" disabled="disabled" /></li>
                <li><input type="text" value="${equipment.companyName}" disabled="disabled" /></li>
                <li><input type="text" value="${equipment.projectName}" disabled="disabled" id="projectName" /></li>
                <li><input type="text" value="<fmt:formatDate value='${equipment.equipmentFoundtime}' type='date' pattern='yyyy-MM-dd　HH:mm:ss' />" disabled="disabled" /></li>
                <li><c:if test="${equipment.equipmentLogintime==null||equipment.equipmentLogintime==''}">
                        <input type="text" value="暂无登录记录" disabled="disabled" />
                    </c:if>
                    <c:if test="${equipment.equipmentLogintime!=null&&equipment.equipmentLogintime!=''}">
                        <input type="text" value="<fmt:formatDate value='${equipment.equipmentLogintime}' type='date' pattern='yyyy-MM-dd　HH:mm:ss' />" disabled="disabled" />
                    </c:if>
                </li>
                <li><c:if test="${edition!=''&&edition!=null&&edition!=0}">
                        <input type="text" value="${edition}" disabled="disabled" />
                    </c:if>
                    <c:if test="${edition==''||edition==null||edition==0}">
                        <input type="text" value="暂无数据" disabled="disabled" />
                    </c:if>
                </li>
                <li><c:if test="${equipment.systemEdition!=''&&equipment.systemEdition!=null}">
                    <input type="text" value="${equipment.systemEdition}" disabled="disabled" />
                    </c:if>
                    <c:if test="${equipment.systemEdition==''||equipment.systemEdition==null}">
                        <input type="text" value="暂无数据" disabled="disabled" />
                    </c:if>
                </li>
            </ul>
        </div>
        <div class="zt_butten">
            <ul class="zt_ul" style="margin-top: 20px;">
                <li><span data-toggle="modal" data-target="#myModal1" id="editionSwitchProject">切换项目</span></li>
                <li><input type="hidden" value="1"><span data-toggle="modal" data-target="#myModal2" class="record">查看推送记录</span><%--<span>修改项目名称</span></li>
                <li><span>修改项目名称</span><span>修改项目名称</span></li>
                <li><span>修改项目名称</span><span>修改项目名称</span></li>--%>
            </ul>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 600px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>切换项目</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border:1px solid skyblue ;background-color:skyblue ; margin: 25px auto;width: 95%;height: 100px;">
                <p style="color: #fff;text-indent: 20px;">。切换项目注意:此操作需要配合APK才能生效，可能会导致设备不可用，请知悉</p>
            </div>
            <div style="margin: 10px 0 0 60px;" >
                <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;">代理商：${sessionScope.Company.companyName}</span>
            </div>
            <div style="margin: 10px 0 0 60px;">
                <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 请选择你要切换到的项目：</span>
                <select id="projectList">

                </select>
            </div>
            <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button id="submission" class="btn btn-primary">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>推送记录列表</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border:1px solid skyblue ;background-color:skyblue ; margin: 25px auto;width: 95%;height: 100px;">
                <p style="color: #fff;font-weight: bold;">推送记录列表信息</p>
                <p style="color: #fff;text-indent: 20px;">。显示推送记录列表,如有疑问请联系贝铂工作人员</p>
            </div>
            <p style="text-indent: 30px;">当前代理：<span id="companyName">${sessionScope.Company.companyName}</span></p>
            <div class="blank"></div>
            <div style="margin: 1px auto;width: 96%;" >
                <div style="width: 96%;float: left;border-bottom: 1px solid #686A76;border-top: 1px solid #686A76;height: 30px;font-size: 14px;line-height: 30px;">
                    <div style="float: left;width: 25%;text-align: center;">设备ID</div>
                    <div style="float: left;width: 25%;text-align: center;">版本号</div>
                    <div style="float: left;width: 25%;text-align: center;">文件名</div>
                    <div style="float: left;width: 25%;text-align: center;">推送时间</div>
                </div>
                <div id="viewRecord" class="row pre-scrollable" style="float: left;width: 99.3%;height: 250px;margin: 0 0 0 10px;">

                </div>
                <div class="m-style M-box3" style="float: right;margin-top:20px ;">
                    <span id="record"></span>
                    <input type="hidden"><input class="record" type="button" value="上页"><span id="totalPage"></span>
                    <input type="hidden"><input class="record" type="button" value="下页">
                    <input type="text" onkeyup="value=value.replace(/[^0-9]/g,'')"><input type="button" class="record" value="跳转">
                </div>
            </div>
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
<script src="js/equipmentConfigure.js"></script>

</body>
</html>
