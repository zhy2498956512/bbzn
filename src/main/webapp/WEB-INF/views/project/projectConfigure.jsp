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
        .xuanz{
            float: left;
            width: 100%;
            border-bottom: #ccc 1px solid;
            margin: 0 ;
            padding: 0;
            height: 40px;
        }
        .xuanz li{
            float: left;
            border-left: #F8F8F8 1px solid;
            list-style: none;
            height: 40px;

            font-size: 15px;
            line-height: 40px;
            text-indent: 5px;
        }
        .xmxq{
            width: 99%;
            margin-left: 8px;
            margin-top: 8px;
            border-top: 2px solid #796AEE;
            height: 320px;
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
            margin-top: 20px;
        }
        .zt_bt .zt_ul{
            float: right;
        }
        .zt_ul li{
            line-height: 40px;
            list-style:none;
        }
        .zt_index{
            float: left;
            margin-top: 20px;
        }
        .zt_ul input{
            background-color: lavender;border: 1px solid #ccc;width: 324px;height: 24px;
            text-indent: 15px;
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
        <span id="regression" style="background-color: #796AEE">返回项目列表</span>
        <span>项目的详细信息</span>
    </div>
    <div class="xmxq_zt">
        <div class="zt_bt">
            <ul class="zt_ul">
                <li>项目名称</li>
                <li>项目ID</li>
                <li>创建时间</li>
                <li>修改时间</li>
            </ul>
        </div>
        <div class="zt_index">
            <ul class="zt_ul">
                <li><input type="text" value="${project.projectName}" disabled="disabled" /></li>
                <li><input type="text" value="${project.projectId}" id="projectId" disabled="disabled" /></li>
                <li><input type="text" value="<fmt:formatDate value='${project.projectNewdate}' type='date' pattern='yyyy-MM-dd　HH:mm:ss' />" disabled="disabled" /></li>
                <li><c:if test="${project.projectUpdatedate==null||project.projectUpdatedate==''}">
                        <input type="text" value="暂无更新记录" disabled="disabled" />
                    </c:if>
                    <c:if test="${project.projectUpdatedate!=null&&project.projectUpdatedate!=''}">
                        <input type="text" value="<fmt:formatDate value='${project.projectUpdatedate}' type='date' pattern='yyyy-MM-dd　HH:mm:ss' />" disabled="disabled" />
                    </c:if>
                </li>
            </ul>
        </div>
        <div class="zt_butten">
            <ul class="zt_ul" style="margin-top: 20px;">
                <li><span data-toggle="modal" data-target="#myModal1">修改项目名称</span></li>
                <li><input type="hidden" value="1"><span data-toggle="modal" data-target="#myModal2" class="record">查看推送记录</span></li>
                <li><input type="hidden" value="1"><span data-toggle="modal" data-target="#myModal3" class="record1">批量推送APK</span>
                <li><span data-toggle="modal" data-target="#myModal4" id="configurationLanguage">批量设置语言</span>
            </ul>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 600px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>修改项目名称</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border-top:3px solid skyblue ; margin: 30px 30px 0 30px;">
                <!-- 设置代理信息 -->
            </div>
            <div style="margin: 10px 0 0 60px;" >
                <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;">代理商：${sessionScope.Company.companyName}</span>
            </div>
            <div style="margin: 10px 0 0 60px;">
                <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 项目名：</span>
                <input type="text" value="${project.projectName}" id="projectName" style="width: 300px;margin: 0px 30px 0 30px;"/>
            </div>
            <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="submit" id="updateProjectName" class="btn btn-primary">
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

<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 600px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>批量推送设备</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border:1px solid skyblue ;background-color:skyblue ; margin: 25px auto;width: 95%;height: 140px;">
                <p style="color: #fff;text-indent: 20px;">。暂时只支持TXT文本格式的文件，文件中一行只允许有一个序列号</p>
                <p style="color: #fff;text-indent: 20px;">。上传序列号文件的序列号必须真实有效，恶意上传后果自负</p>
            </div>
            <form  id="uploadForm" enctype="multipart/form-data" style="float: left;">
                <div style="float: left;margin-left: 30px;">
                    <label><strong>序列号文件上传</strong></label>
                    <input type="file" style="margin-left: 30px;" name="file" />
                </div>
                <div style="float: left;width:90%;height: 40px;border: 1px solid #ccc;margin-left: 30px;border-radius: 3px;">
                    <ul class="xuanz">
                        <li style="width: 10%; "></li>
                        <li style="width: 15%; font-weight: bold; font-size: 18px;">文件ID</li>
                        <li style="width: 40%; font-weight: bold;font-size: 18px;">文件名</li>
                        <li style="width: 35%; font-weight: bold;font-size: 18px;">上传时间</li>
                    </ul>
                </div>
                <input type="hidden" name="projectId" value="${project.projectId}">
                <div style="float: left;width:90%;height: 280px;border: 1px solid #ccc;margin-left: 30px;border-radius: 3px;" id="viewRecord1">

                </div>
                <div class="m-style M-box3" style="float: right;margin-top:20px ;">
                    <span id="record1"></span>
                    <input type="hidden"><input class="record1" type="button" value="上页"><span id="totalPage1"></span>
                    <input type="hidden"><input class="record1" type="button" value="下页">
                    <input type="text" onkeyup="value=value.replace(/[^0-9]/g,'')"><input type="button" class="record1" value="跳转">
                </div>
                <div class="" style="float: right;width: 100%; margin-top: 20px;">
                    <div style="margin: 0 auto;width: 30%;">
                        <button type="button" id="uploadButton" class="btn btn-info" >
                            批量推送
                        </button>
                        <button type="button" class="btn btn-info"
                                data-dismiss="modal" style="color: #fff;">取消
                        </button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form  id="uploadForm1" enctype="multipart/form-data" style="float: left;">
        <div class="modal-content" style="width: 675px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>批量配置语言种类</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border:1px solid skyblue ;background-color:skyblue ; margin: 25px auto;width: 95%;height: 140px;">
                <p style="color: #fff;text-indent: 20px;">。暂时只支持TXT文本格式的文件，文件中一行只允许有一个序列号</p>
                <p style="color: #fff;text-indent: 20px;">。上传序列号文件的序列号必须真实有效，恶意上传后果自负</p>
            </div>
            <div style="float: left;margin-left: 30px;margin-bottom: 10px;">
                <label><strong>序列号文件上传</strong></label>
                <input type="file" style="margin-left: 30px;" name="file1" />
            </div>
            <div style="margin: -5px 0 0 30px;">
                <%--<h3 style="float: left;">设置当前代理的语言</h3>--%>
                <div style="float: left;margin: 0 0 0 30px;">当前左侧可选的数量:<span style="padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;" id="leftNumber"></span></div>
                <div style="float: left;margin: 0 0 0 30px;">当前右侧可选的数量:<span style="padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;" id="rightNumber">0</span></div>
            </div>
            <input type="hidden" name="projectId1" value="${project.projectId}">
            <div style="width: 700px;height: 210px;margin: 10px 0 0 30px;">
                <select class="form-control" id="multiselect_to_1"multiple="multiple"size="200"style="height: 170px;width: 200px;float: left;">

                </select>
                <div style="width: 100px;height: 200px;float: left;margin: 0 0 0 10px;">
                    <button type="button" id="allLeft" class="btn btn-default" style="margin: 5px auto;color: #fff;">
                        全部左移
                    </button>
                    <button type="button" id="right" class="btn btn-default" style="margin: 0 auto;color: #fff;padding: 5px 28px ;">
                        右移
                    </button>
                    <button type="button" id="left" class="btn btn-default" style="margin: 5px auto;color: #fff;padding: 5px 28px ;">
                        左移
                    </button>
                    <button type="button" id="allRight" class="btn btn-default" style="margin: 0 auto;color: #fff;">
                        全部右移
                    </button>
                </div>
                <select class="form-control" id="multiselect_to_2" name="multiselect_to_2" multiple="multiple"size="200"style="height: 170px;width: 200px;float: left;">

                </select>
                <div style="width: 100px;height: 200px;float: left;margin: 0 0 0 10px;">
                    <button type="button" id="top" class="btn btn-default" style="margin: 5px auto;color: #fff;padding: 5px 28px ;">
                        置顶
                    </button>
                    <button type="button" id="lower" class="btn btn-default" style="margin: 0 auto;color: #fff;padding: 5px 28px ;">
                        下移
                    </button>
                    <button type="button" id="upper" class="btn btn-default" style="margin: 5px auto;color: #fff;padding: 5px 28px ;">
                        上移
                    </button>
                    <button type="button" id="bottom" class="btn btn-default" style="margin: 0 auto;color: #fff;padding: 5px 28px ;">
                        置底
                    </button>
                </div>
            </div>
                <input type="hidden" id="languageIdList" name="languageIdList">
                <input type="hidden" id="cId" name="cId">
                <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal" style="background-color: crimson;color: #fff;">取消当前修改
                    </button>
                    <button type="button" id="uploadButton1" class="btn btn-primary">
                        确认提交修改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </form>
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
<script src="js/projectConfigure.js"></script>

</body>
</html>
