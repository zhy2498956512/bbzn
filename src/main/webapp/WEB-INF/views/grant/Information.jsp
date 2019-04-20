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
</head>
<body>
<div style="border: 0px solid #000000;height: 150px;width: 1200px;margin: 10px auto;"> <!-- 进度条区域一个大框border删除去掉边框，margin调整位置 -->
    <div style="float: left;font-size: 20px;">当前代理授权数使用情况:(可用数量<span style="font-size: 15px; padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;" id="maxValue">${length2}</span>)
    </div>
    <div class="blank1"><input type="hidden" id="hideButton"></div><br>
    <input type="hidden" value="1"><div class="record" data-toggle="modal" style="float: left;color: skyblue;cursor: pointer;font-size: 18px;"  data-target="#myModal3">(查看授权记录)</div>
    <div class="blank1"></div>
    <!-- 进度条 -->
    <div class="progress" style="width: 500px;float: left;">
        <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" id="strip">
        </div>
    </div>
    <!-- 进度条结束 -->
    <input type="hidden" value="${length1}" id="total"><input type="hidden" value="${length2}" id="already">
    <div style="float: left;margin: -5px 0 0 0;">
        <span style="padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;">${length2}</span>/
        <span style="padding: 5px 10px;background-color: skyblue;border-radius:5px;color: #fff;margin: 0 10px;">${length1}</span>
        <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
            充值授权数
        </button>
        <button id="generate" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal4">
            生成序列号
        </button>
        <button id="uploadFiles" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal8">
            上传序列号文件
        </button>
    </div>
</div>

<div style="margin: 0 0 5px 83px"><button id="savaProject" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal2">
    新建项目
</button></div>
<div style="width: 90%;height: 400px;border: 1px solid #B9BBBE;border-radius:5px ;background-color: #fff;margin: 0 auto;">
    <div style="width: 100%;height: 50px;border-bottom: 2px solid #B9BBBE;float: left;font-size: 15px;font-weight: bold;line-height: 50px;">
        <div style="width: 30%;height: 50px;float: left;text-align: center;">项目名称</div>
        <div style="width: 10%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">项目类型</div>
        <div style="width: 15%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">创建时间</div>
        <div style="width: 15%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">修改时间</div>
        <div style="width: 30%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">操作</div>
    </div>
   <div class="row pre-scrollable" style="float: left;width: 99.3%;height: 400px;margin: 0px 0 0 10px;">
       <div style="float: left;width: 99.3%;height: 300px;margin: 0px 0 0 0 ;">
        <c:forEach items="${projectList}" var="project">
                <div style="width: 30%;height: 50px;float: left;border-bottom: 1px solid #B9BBBE;line-height: 50px;">
                    <input type="hidden" value="${project.projectId}">
                    <div style="float: left;color: skyblue;cursor: pointer;font-size: 18px;text-indent: 170px;" class="jump3">${project.projectName}</div>
                </div>
                <input type="hidden" value="${project.projectId}">
                <div style="width: 10%;height: 50px;float: left;cursor: pointer;border-bottom: 1px solid #B9BBBE;text-align: center;line-height: 50px;" class="updateProjectName">${project.projectTypeName}</div>
                <div style="width: 15%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">
                    <fmt:formatDate value="${project.projectNewdate}" type="date" pattern="yyyy-MM-dd　HH:mm:ss"/>
                </div>
                <div style="width: 15%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">
                    <c:if test="${project.projectUpdatedate==null}">暂无记录</c:if>
                    <c:if test="${project.projectUpdatedate!=null}">
                        <fmt:formatDate value="${project.projectUpdatedate}" type="date" pattern="yyyy-MM-dd　HH:mm:ss"/>
                    </c:if>
                </div>
                <div style="width: 30%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">
                    <input type="button" value="配置语言" style=""
                           class="btn btn-primary configure" data-toggle="modal" data-target="#myModal6">
                    <input type="hidden" value="${project.projectId}">
                    <input type="button" value="推送APK升级" class="btn btn-primary upload"  data-toggle="modal" data-target="#myModal7">
                    <input type="button" value="项目配置" class="btn btn-primary projectConfigure">
                </div>
        </c:forEach>
       </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 600px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>充值授权数量</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border-top:3px solid skyblue ; margin: 30px 30px 0 30px;">
                <!-- 设置代理信息 -->
            </div>
            <%
                com.example.bbzn.pojo.Company company=(com.example.bbzn.pojo.Company)session.getAttribute("Company");
            %>
            <form action="api/grantNumberRecord/purchaseNumber" method="post" id="jump">
                <div style="margin: 10px 0 0 60px;">
                    <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 代理名称：</span>
                    <span style=" margin: 0px 30px 0 30px;font-size: 13px; font-weight: bold;"><%= company.getCompanyName() %></span>
                </div>
                <div style="margin: 10px 0 0 60px;" >
                    <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 授权数量：</span>
                    <input type="text" name="grantNumberRecordAmount" style="width: 300px;margin: 0px 30px 0 30px;" onkeyup="value=value.replace(/[^0-9]/g,'')"
                           placeholder="请输入数量（数量不能小于0）" id="grantNumberRecordAmount" />
                </div>
                <div style="margin: 10px 0 0 60px;">
                    <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 备注说明：</span>
                    <textarea rows="3" cols="47" name="grantNumberRecordRemark" style="margin: 0px 30px 0 30px;" ></textarea>
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

<!-- 模态框（Modal1） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 900px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>修改代理信息</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border-top:3px solid skyblue ; margin: 30px 30px 0 30px;">
                <!-- 设置代理信息 -->
            </div>
            <form action="api/grantNumberRecord/purchaseNumber" method="post" id="jump1">
                <div style="width: 90%;height: 330px;border: 1px solid #B9BBBE;border-radius:5px ;background-color: #fff;margin: 0 auto;">
                    <div style="width: 100%;height: 50px;border-bottom: 2px solid #B9BBBE;float: left;font-size: 15px;font-weight: bold;line-height: 50px;">
                        <div style="width: 20%;height: 50px;float: left;text-align: center;">代理名称</div>
                        <div style="width: 12%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">授权数量</div>
                        <div style="width: 12%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">备注说明</div>
                        <div style="width: 20%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">申请时间</div>
                        <div style="width: 20%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">反馈时间</div>
                        <div style="width: 12%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">授权状态</div>
                    </div>
                    <div class="row pre-scrollable" style="float: left;width: 99.3%;height: 170px;margin: 0 0 0 10px;" id="show">
                        <div style="width: 100%;">
                            <div style="width: 19.7%;height: 85px;float: left;border-bottom: 1px solid #E2E6EA;text-align: center;color: #0062CC;font-weight: bold;font-size: 15px;line-height: 85px;">
                                贝铂智能<span style="padding: 5px;background-color: #495057;color: #fff;height: 20px;line-height: 10px;border-radius:15px ;">3731</span>
                            </div>
                            <div style="width: 12.2%;height: 85px;border-left: 1px solid #E2E6EA;border-bottom: 1px solid #E2E6EA;float: left;text-align: center;line-height: 85px;">
										<span style="padding: 2px 5px;background-color: skyblue;border-radius:5px ;color: #fff;font-size: 15px;line-height: 20px;font-weight: bold;">15338</span>
                            </div>
                            <div style="width: 12.2%;height: 85px;border-left: 1px solid #E2E6EA;border-bottom: 1px solid #E2E6EA;float: left;text-align: center;color:skyblue ;line-height: 85px;font-size: 15px;">
                                翻译机</div>
                            <div style="width: 20.5%;height: 85px;border-left: 1px solid #E2E6EA;border-bottom: 1px solid #E2E6EA;float: left;text-align: center;color:skyblue ;line-height: 85px;font-size: 15px;">
                                2018/08/10 14:15:06</div>
                            <div style="width: 20.3%;height: 85px;border-left: 1px solid #E2E6EA;border-bottom: 1px solid #E2E6EA;float: left;text-align: center;color:skyblue ;line-height: 85px;font-size: 15px;">
                                无记录</div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="margin: 20px 0 0 0 ; ">

                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 600px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>新建项目</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border-top:3px solid skyblue ; margin: 30px 30px 0 30px;">
                <!-- 设置代理信息 -->
            </div>
            <form action="api/project/saveProject" method="post">
                <div style="margin: 10px 0 0 60px;" >
                    <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 项目名称：</span>
                    <input type="text" name="projectName" style="width: 300px;margin: 0px 30px 0 30px;" />
                </div>
                <div style="margin: 10px 0 0 60px;">
                    <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 项目类型：</span>
                    <select style="width: 300px;margin: 0px 30px 0 30px;" id="projectType" name="projectType"></select>
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


<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>充值授权记录列表</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border:1px solid skyblue ;background-color:skyblue ; margin: 25px auto;width: 95%;height: 100px;">
                <p style="color: #fff;font-weight: bold;">授权列表信息</p>
                <p style="color: #fff;text-indent: 20px;">。显示授权列表信息,如有疑问请联系贝铂工作人员</p>
            </div>
            <p style="text-indent: 30px;">当前代理：<span id="companyName"></span></p>
            <div class="blank"></div>
            <div style="margin: 1px auto;width: 96%;" >
                <div style="width: 96%;float: left;border-bottom: 1px solid #686A76;border-top: 1px solid #686A76;height: 30px;font-size: 14px;line-height: 30px;">
                    <div style="float: left;width: 10%;text-align: center;">授权数量</div>
                    <div style="float: left;width: 28%;text-align: center;">备注信息</div>
                    <div style="float: left;width: 25%;text-align: center;">申请时间</div>
                    <div style="float: left;width: 25%;text-align: center;">通过时间</div>
                    <div style="float: left;width: 10%;text-align: center;">授权状态</div>
                </div>
                <div id="viewRecord" class="row pre-scrollable" style="float: left;width: 99.3%;height: 250px;margin: 0 0 0 10px;">
                    <%--<div style="width: 96%;float: left;border-bottom: 1px solid #D6D8DB;height: 80px;font-size: 14px;">
                        <div style="float: left;width: 12.5%;text-align: center;padding-top:15px;color: #0069D9;">深圳市贝铂科技有限公司</div>
                        <div style="float: left;width: 12.5%;text-align: center;line-height: 80px;"><span style="padding: 5px 5px;background-color: #0069D9;color: #fff;line-height: 15px;border-radius:5px ;margin-top:30px; ">译家小僧</span></div>
                        <div style="float: left;width: 13.5%;text-align: center;line-height: 80px;">授权数量</div>
                        <div style="float: left;width: 13.5%;text-align: center;line-height: 80px;">备注信息</div>
                        <div style="float: left;width: 17%;text-align: center;line-height: 80px;">申请时间</div>
                        <div style="float: left;width: 17%;text-align: center;line-height: 80px;">通过时间</div>
                        <div style="float: left;width: 13.5%;text-align: center;line-height: 80px;"><span style="padding: 5px 5px;background-color: #28A745;color: #fff;line-height: 15px;border-radius:5px ;margin-top:30px; ">审核通过</span></div>
                    </div>--%>
                </div>
                <div class="m-style M-box3" style="float: right;margin-top:20px ;">
                    <input type="hidden"><input class="record" type="button" value="上页"><span id="totalPage"></span>
                    <input type="hidden"><input class="record" type="button" value="下页">
                    <input type="text" onkeyup="value=value.replace(/[^0-9]/g,'')"><input type="button" class="record" value="跳转">
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>生成序列号</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border:1px solid skyblue ;background-color:skyblue ; margin: 25px auto;width: 95%;height: 100px;">
                <p style="color: #fff;font-weight: bold;">自动生成序列号提示</p>
                <p style="color: #fff;text-indent: 20px;">。点击后可在左侧生成序列号点击后可在左侧生成序列号点击后可在左侧生成序列号点击后可在左侧生成序列号点击后可在左侧生成序列号</p>
            </div>
            <p style="margin:20px 0 0 30px ;border-bottom: 2px solid #999;width: 90%;">当前代理：<span>${sessionScope.Company.companyName}</span></p>
            <div class="blank"></div>
            <%--<div style="width: 90%;margin: 0 0 0 30px;">
                <div style="border-bottom: 2px solid #999;width: 40%;height: 40px;float: left;"><span>序列号生产器</span></div>
                <div style="border-bottom: 2px solid #999;width: 50%;height: 40px;float: right;"><span>序列号生产器</span></div>
            </div>
            <div class="blank"></div>--%>
            <div style="width: 90%;margin: 0 0 0 30px;">
                <div style="width: 40%;height: 40px;float: left;"><span>序列号生成规则</span></div>
                <div style="width: 50%;height: 40px;float: right;">
                    <%--<button type="button" class="btn btn-info">重新生产</button>--%>
                        <input type="hidden"><input type="hidden"><input type="hidden"><input type="hidden">
                    <button type="button" class="btn btn-success" style="padding: 6px 25% ;float: right;" id="submitCode">提交上传</button>
                </div>
            </div>

            ，<div style="float: left;width: 90%;margin: 0 0 0 30px;">
            <div style="width: 40%;height: 300px;float: left;">
                <div style="width: 100%;height: 25px;">
                    <strong style="float: left;line-height: 25px;">项目：</strong>
                    <select style="float: left;margin-left:20px ;padding: 2px 15px;" id="project">
                    </select>
                </div>
                <div style="width: 100%;height: 25px;margin-top:10px ;">
                    <strong style="float: left;line-height: 25px;">前缀：</strong>
                    <input id="prefix" type="text" placeholder="最多可填3位" maxlength="3" style="float: left;margin-left:20px ;padding: 2px 4px;" />
                </div>

                <div style="width: 100%;height: 25px;margin-top:10px ;">
                    <strong style="float: left;line-height: 25px;">长度：</strong>
                    <select style="float: left;margin-left:20px ;padding: 2px 47px;" id="length">
                        <option value="15">15位</option>
                        <option value="16">16位</option>
                        <option value="17">17位</option>
                    </select>
                </div>
                <div style="width: 100%;height: 25px;margin-top:10px ;">
                    <strong style="float: left;line-height: 25px;">规则：</strong>
                    <select style="float: left;margin-left:20px ;padding: 2px 42px;" id="binary">
                        <option value="10">十进制</option>
                        <option value="16">十六进制</option>
                    </select>
                </div>
                <div style="width: 100%;height: 25px;margin-top:10px ;">
                    <strong style="float: left;line-height: 25px;">数量：</strong>
                    <input type="text" onkeyup="value=value.replace(/[^0-9]/g,'')" id="number" placeholder="最多可填" style="float: left;margin-left:20px ;padding: 2px 4px;" />
                </div>
                <div class="blank"></div>
                <button type="button" class="btn btn-info" style="padding:5px 35%  ;" id="code">生成序列号</button>
            </div>
            <div style="width: 50%;float: right;">
                <select class="form-control" id="multiselect_to_1"multiple="multiple"size="200"style="height: 200px;width: 100%;float: left;">
                </select>
            </div>
        </div>
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>

<div class="modal fade" id="myModal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
            <form  action="api/project/updateName" method="post">
                <div style="margin: 10px 0 0 60px;" >
                    <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;">代理商：${sessionScope.Company.companyName}</span>
                </div>
                <div style="margin: 10px 0 0 60px;">
                    <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 项目名：</span>
                    <input type="text" id="pName" name="projectName" style="width: 300px;margin: 0px 30px 0 30px;"/>
                </div>
                <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <input type="hidden" name="projectId" id="pId">
                    <button type="submit" class="btn btn-primary">
                        提交
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 675px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>配置代理语言种类</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border-top:3px solid skyblue ; margin: 30px 30px 0 30px;">
                <!-- 设置代理信息 -->
            </div>
            <div style="margin: -5px 0 0 30px;">
                <%--<h3 style="float: left;">设置当前代理的语言</h3>--%>
                <div style="float: left;margin: 0 0 0 30px;">当前左侧可选的数量:<span style="padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;" id="leftNumber"></span></div>
                <div style="float: left;margin: 0 0 0 30px;">当前右侧可选的数量:<span style="padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;" id="rightNumber"></span></div>
            </div>
            <div style="width: 700px;height: 210px;margin: 10px 0 0 30px;">
                <select class="form-control" id="multiselect_to_3"multiple="multiple"size="200"style="height: 170px;width: 200px;float: left;">

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
                <select class="form-control" id="multiselect_to_2"multiple="multiple"size="200"style="height: 170px;width: 200px;float: left;">

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
            <form action="api/projectLanguage/saveProjectLanguage" method="post" id="submission">
                <input type="hidden" id="languageIdList" name="languageIdList">
                <input type="hidden" id="cId" name="cId">
                <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal" style="background-color: crimson;color: #fff;">取消当前修改
                    </button>
                    <button type="submit" class="btn btn-primary">
                        确认提交修改
                    </button>

                </div>
            </form>
        </div><!-- /.modal-content -->
    </div>
</div>

<div class="modal fade" id="myModal7" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px; height:370px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>推送APK版本</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="" style="border:1px solid skyblue ;background-color:skyblue ; margin: 25px auto;width: 95%;height: 100px;">
                <p style="color: #fff;font-weight: bold;">上传APK提示</p>
                <p style="color: #fff;text-indent: 20px;">。选择要上传的APK文件和配置好版本信息后点击上传即可</p>
            </div>
            <div class="blank"></div>
            <div style="width: 90%;margin: 0 0 0 30px;">
            </div>
            <div class="blank"></div>
            <form action="api/project/saveAPK" method="post" enctype="multipart/form-data" id="submitAPK">
                <strong style="float: left;line-height: 25px;text-indent: 70px;">APK文件：</strong><input style="float: left;line-height: 25px; " type="file" name="fileName">
                <strong style="float: left;line-height: 25px;">APK版本号：</strong><input style="float: left;line-height: 25px;" type="text" name="edition" onkeyup="value=value.replace(/[^0-9]/g,'')" placeholder="只能输入数字">
                <input type="hidden" name="projectId">
                <button type="submit" class="btn btn-success" style="padding: 6px 25% ;float: left; margin: 25px 0 0 20%;">提交上传</button>
            </form>
        </div>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="myModal8" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px;">
            <div class="modal-header" style="    background-color: #796AEE; color: #fff;border-radius:0px ;">
                <h5>上传序列号文件</h5>
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
            </div>
            <div class="modal-body" style="border:1px solid skyblue ;background-color:skyblue ; margin: 25px auto;width: 95%;height: 140px;">
                <p style="color: #fff;font-weight: bold;">上传序列号/IMEI提示</p>
                <p style="color: #fff;text-indent: 20px;">。目前仅支持txt文档上传，请保证每一行是一个序列号，且只能包含字母和数字,长度【10-20】，否则提示文档不规范</p>
                <p style="color: #fff;text-indent: 20px;">。上传的文档将会直接入库注册，请谨慎选择对应的项目，并且请在上传前保证可用数量大于文本记录数量</p>
                <p style="color: #fff;text-indent: 20px;">。系统审核上传的文件需要几分钟时间，请耐心等待</p>
            </div>
            <p style="margin:20px 0 0 30px ;border-bottom: 2px solid #999;width: 90%;">当前代理：<span>${sessionScope.Company.companyName}</span></p>
            <div class="blank"></div>
            <div style="width: 100%;">
                <form id="uploadForm" enctype="multipart/form-data">
                <div style="width: 35%;float: left;height: 200px;margin-left: 1.3%;">
                    <div style="width: 100%;height: 25px;">
                        <strong style="float: left;line-height: 25px;">项目：</strong>
                        <select style="float: left;margin-left:20px ;padding: 2px 15px;" name="info">
                        </select>
                    </div>
                    <div style="width: 100%;height: 25px;">
                        <strong style="float: left;line-height: 25px;">文件：</strong>
                        <input type="file" style="float: left;" name="file">
                    </div>
                    <button type="button" id="uploadButton" class="btn btn-info" style="padding: 6px 25% ;width: 100%;">解析序列文件</button>
                </div>
                </form>
                <div style="width: 30%;float: left;height: 200px;margin-left: 1.3%;">
                    <p>通过审核：</p><span style="padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;" id="availableNumber">0</span>
                    <select class="form-control" id="multiselect_to_4" multiple="multiple" size="200" style="height: 160px;width: 100%;float: left;">
                    </select>
                </div>
                <div style="width: 30%;float: left;height: 200px;margin-left: 1.3%;">
                    <p>未通过审核：</p><span style="padding: 5px 10px;background-color: yellowgreen;border-radius:5px;color: #fff;margin: 0 10px;" id="unavailableNumber">0</span>
                    <select class="form-control" id="multiselect_to_5" multiple="multiple"size="200"style="height: 160px;width: 100%;float: left;">
                    </select>
                </div>
                <div style="float: left;width: 30%;margin-left: 37.7%;">
                    <button type="button" id="uploadFileCode" class="btn btn-success" style="padding: 6px 25% ;float: right;width: 100%;">提交上传</button>
                </div>
            </div>
        </div>
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
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
<script src="js/grantNumberRecord.js"></script>
</body>
</html>
