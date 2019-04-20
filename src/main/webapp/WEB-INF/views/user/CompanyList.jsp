<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/3/7
  Time: 13:37
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
    <title>代理商列表</title>
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
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
</head>
<body>
    <%--<div>--%>
        <%--<table style="border: 1px solid #cccccc;margin: 0 auto;">--%>
            <%--<tr><td>代理商</td><td>联系电话</td><td>联系地址</td><td>创建时间</td><td>操作</td></tr>--%>
            <%--<c:forEach items="${list}" var="Company">--%>
                <%--<c:if test="${Company.companyUserType==0}">--%>
                    <%--<tr><td>${Company.companyName}</td><td>${Company.companyPhone}</td><td>${Company.companyAddress}</td>--%>
                        <%--<td><fmt:formatDate value="${Company.companyFoundtime}" type="date" pattern="yyyy-MM-dd　HH:mm:ss"/></td>--%>
                        <%--<td><input type="hidden" value="${Company.companyName}"><input type="hidden" value="${Company.companyId}">--%>
                            <%--<button type="button" data-toggle="modal" data-target="#myModal1" class="btn btn-info language" style="margin: 3px auto;color: #fff;">--%>
                                <%--设置语言--%>
                            <%--</button>--%>
                            <%--<button type="button" data-toggle="modal" data-target="#myModal2" class="btn btn-info recharge" style="margin: 3px auto;color: #fff;">--%>
                                <%--充值授权--%>
                            <%--</button>--%>
                        <%--</td></tr>--%>
                <%--</c:if>--%>
            <%--</c:forEach>--%>
        <%--</table>--%>

    <%--</div>--%>
    <%----%>
    <div style="width: 90%;height: 400px;border: 1px solid #B9BBBE;border-radius:5px ;background-color: #fff;margin: 30px auto;">
        <div style="width: 100%;height: 50px;border-bottom: 2px solid #B9BBBE;float: left;font-size: 15px;font-weight: bold;line-height: 50px;">
            <div style="width: 25%;height: 50px;float: left;text-align: center;">代理商</div>
            <div style="width: 15%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">联系电话</div>
            <div style="width: 15%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">联系地址</div>
            <div style="width: 15%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">创建时间</div>
            <div style="width: 30%;height: 50px;border-left: 1px solid #B9BBBE;float: left;text-align: center;">操作</div>
        </div>
        <div class="row pre-scrollable" style="float: left;width: 99.3%;height: 400px;margin: 0px 0 0 10px;">
                <div style="width: 100%;margin: 0 0 0 0;">
                <c:forEach items="${list}" var="Company">
                    <c:if test="${Company.companyUserType==0}">
                        <div style="width: 25%;height: 50px;float: left;border-bottom: 1px solid #B9BBBE;line-height: 50px;">
                            <input type="hidden" value="${project.projectId}">
                            <div style="float: left;color: skyblue;cursor: pointer;font-size: 18px;text-indent: 170px;" class="jump3">${Company.companyName}</div>
                        </div>
                        <div style="width: 15%;height: 50px;float: left;border-bottom: 1px solid #B9BBBE;text-align: center;line-height: 50px;">${Company.companyPhone}</div>
                        <div style="width: 15%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">${Company.companyAddress}</div>
                        <div style="width: 15%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">
                            <fmt:formatDate value="${Company.companyFoundtime}" type="date" pattern="yyyy-MM-dd　HH:mm:ss"/>
                        </div>
                        <div style="width: 30%;height: 50px;border-bottom: 1px solid #B9BBBE;float: left;text-align: center;line-height: 50px;">
                            <input type="hidden" value="${Company.companyName}"><input type="hidden" value="${Company.companyId}">
                            <button type="button" data-toggle="modal" data-target="#myModal1" class="btn btn-info language" style="margin: 3px auto;color: #fff;">设置语言</button>
                            <button type="button" data-toggle="modal" data-target="#myModal2" class="btn btn-info recharge" style="margin: 3px auto;color: #fff;">充值授权</button>
                            <button type="button" data-toggle="modal" data-target="#myModal3" class="btn btn-info updateInformation" style="margin: 3px auto;color: #fff;">修改信息</button>
                            <button type="button" data-toggle="modal" class="btn btn-info stateCompany" style="margin: 3px auto;color: #fff;"
                                    <c:if test="${Company.companyState==0}"> >开启代理</button></c:if>
                                    <c:if test="${Company.companyState==1}"> >关闭代理</button></c:if>
                        </div>
                    </c:if>
                </c:forEach>
                </div>
        </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                <form action="api/companyLanguage/saveCompanyLanguage" method="post" id="submission">
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

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                <form action="api/grantNumberRecord/giveNumber" method="post" id="jump">
                    <div style="margin: 10px 0 0 60px;">
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 代理名称：</span>
                        <span style=" margin: 0px 30px 0 30px;font-size: 13px; font-weight: bold;" id="cNmae"></span>
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
                        <input type="hidden" id="cid1" name="company_id">
                        <button type="submit" class="btn btn-primary">
                            提交
                        </button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
                <form action="api/company/adminUpdateInformation" method="post">
                    <div style="margin: 10px 0 0 60px;">
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 代理名称：</span>
                        <input type="text" name="cName">
                    </div>
                    <div style="margin: 10px 0 0 60px;">
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 联系电话：</span>
                        <input type="text" name="cPhone">
                    </div>
                    <div style="margin: 10px 0 0 60px;">
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 联系地址：</span>
                        <input type="text" name="cAddress">
                    </div>
                    <div style="margin: 10px 0 0 60px;">
                        <span style=" margin: 0px 0 0 30px;font-size: 13px; font-weight: bold;"> 代理密码：</span>
                        <input type="text" name="cPass">
                    </div>
                    <div class="modal-footer" style="margin: 20px 0 0 0 ; ">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal">关闭
                        </button>
                        <input type="hidden" name="cId">
                        <button type="submit" class="btn btn-primary">
                            提交
                        </button>
                    </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/jquery-validation/jquery.validate.min.js"></script>
    <!-- Main File-->
    <script src="js/front.js"></script>
    <script type="text/javascript">
        
        $(".updateInformation").click(function () {
            var updateInformation = $(this);
            var companyId = updateInformation.prev().prev().prev().val();
            $.ajax({
                url: "api/company/adminUpdateInformationShow",
                data: {"companyId": "" + companyId},
                type: "POST",
                dataType: "json",
                success: function (msg) {
                    $("[name='cName']").val(msg.companyName);
                    $("[name='cPhone']").val(msg.companyPhone);
                    $("[name='cAddress']").val(msg.companyAddress);
                    $("[name='cPass']").val(msg.companyUserPass);
                    $("[name='cId']").val(msg.companyId);
                }
            });
        });

        $(function () {
            $(".stateCompany").each(function(){
                if($(this).html()=="开启代理") {
                    $(this).css("background", "#F00");
                }else if($(this).html()=="关闭代理"){
                    $(this).css("background","#17a2b8");
                }
            });

            function LeftRight(){
                $("#leftNumber").html($("#multiselect_to_1").find("option").length);
                $("#rightNumber").html($("#multiselect_to_2").find("option").length);
            };

            $(".language").click(function () {
                var company_id = $(this).prev().val();
                $("#cId").val(company_id);
                $.ajax({
                    url: "api/companyLanguage/getCompanyLanguage",
                    data: {"company_id":""+company_id},
                    type: "POST",
                    dataType: "json",
                    success: function (msg) {
                        var languageList = msg.languageList;
                        var companyLanguageList = msg.companyLanguageList;
                        var num1 = 1;
                        var num2 = 1;
                        var html1 = "";
                        var html2 = "";
                        $("#multiselect_to_1").html("");
                        $("#multiselect_to_2").html("");
                        $.each(languageList, function (num1) {
                            var languageId = languageList[num1].languageId;
                            var languageName = languageList[num1].languageName;
                            html1 = html1 + "<option value='"+languageId+"' id='"+languageId+"'>"+languageName+"</option>";
                        })
                        $("#multiselect_to_1").append(html1);
                        $.each(companyLanguageList, function (num2) {
                            var languageId = companyLanguageList[num2].languageId;
                            var languageName = companyLanguageList[num2].languageName;
                            html2 = html2 + "<option value='"+languageId+"' id='"+languageId+"'>"+languageName+"</option>";
                        })
                        $("#multiselect_to_2").append(html2);
                        LeftRight();
                    }
                });
            });

            $("#allRight").click(function () {
                $("#multiselect_to_2").append($("#multiselect_to_1").html());
                $("#multiselect_to_1").html("");
                LeftRight();
            });
            
            $("#allLeft").click(function () {
                $("#multiselect_to_1").append($("#multiselect_to_2").html());
                $("#multiselect_to_2").html("");
                LeftRight();
            });

            $("#left").click(function () {
                var html = "";
                $("#multiselect_to_2 option:selected").each(function () {
                    var v = $(this).val();
                    var t = $(this).text();
                    html = html + "<option value='"+v+"' id='"+v+"'>"+t+"</option>"
                    $("#"+v).remove();
                })
                $("#multiselect_to_1").append(html);
                LeftRight();
            });

            $("#right").click(function () {
                var html = "";
                $("#multiselect_to_1 option:selected").each(function () {
                    var v = $(this).val();
                    var t = $(this).text();
                    html = html + "<option value='"+v+"' id='"+v+"'>"+t+"</option>"
                    $("#"+v).remove();
                })
                $("#multiselect_to_2").append(html);
                LeftRight();
            });


            var patt1 = /^[0-9]\d*$/;
            $("#top").click(function () {
                if(patt1.test($("#multiselect_to_2 option:selected").val())){
                    $("#multiselect_to_2 option:selected").each(function () {
                        var html = "";
                        var v = $(this).val();
                        var t = $(this).text();
                        $("#" + v).remove();
                        $("#multiselect_to_2").prepend("<option value='" + v + "' id='" + v + "'>" + t + "</option>");
                    })
                }
            });

            $("#bottom").click(function () {
                if(patt1.test($("#multiselect_to_2 option:selected").val())) {
                    $("#multiselect_to_2 option:selected").each(function () {
                        var html = "";
                        var v = $(this).val();
                        var t = $(this).text();
                        $("#" + v).remove();
                        $("#multiselect_to_2").append("<option value='" + v + "' id='" + v + "'>" + t + "</option>");
                    })
                }
            });


            $("#lower").click(function () {
                if ($("#multiselect_to_2 option:selected").next().val() != null && $("#multiselect_to_2 option:selected").next().val() != ""){
                    $("#multiselect_to_2 option:selected").each(function () {
                        var v1 = $(this).val();
                        var t1 = $(this).text();
                        var v2 = $(this).next().val();
                        var t2 = $(this).next().text();
                        $(this).val(v2);
                        $(this).attr("id", v2);
                        $(this).text(t2);
                        $(this).next().val(v1);
                        $(this).next().attr("id", v1);
                        $(this).next().text(t1);
                    })
                }
            });

            $("#upper").click(function () {
                if ($("#multiselect_to_2 option:selected").prev().val() != null && $("#multiselect_to_2 option:selected").prev().val() != ""){
                    $("#multiselect_to_2 option:selected").each(function () {
                        var v1 = $(this).val();
                        var t1 = $(this).text();
                        var v2 = $(this).prev().val();
                        var t2 = $(this).prev().text();
                        $(this).val(v2);
                        $(this).attr("id", v2);
                        $(this).text(t2);
                        $(this).prev().val(v1);
                        $(this).prev().attr("id", v1);
                        $(this).prev().text(t1);
                    })
                }
            });
            
            $("#submission").submit(function () {
                var idlist = "";
                $("#multiselect_to_2 option").each(function () {
                    idlist = idlist + "," +$(this).val();
                });
                $("#languageIdList").val(idlist);
            });
            
            $(".recharge").click(function () {
                var company_id = $(this).prev().prev().val();
                var company_name = $(this).prev().prev().prev().val();
                $("#cid1").val(company_id);
                $("#cNmae").html(company_name);
            });

            $(".stateCompany").click(function () {
                var closeCompany = $(this);
                var companyType = 1;
                if(closeCompany.html()=="关闭代理"){
                    companyType = 0;
                }else {
                    companyType = 1;
                }
                var company_id = closeCompany.prev().prev().prev().prev().val();
                $.ajax({
                    url: "api/company/stateCompany",
                    data: {"companyId":""+company_id,"companyType":companyType},
                    type: "POST",
                    dataType: "json",
                    success: function (data) {
                        if(data==1){
                            if(closeCompany.html()=="关闭代理"){
                                closeCompany.css("background","#F00");
                                closeCompany.html("开启代理");
                            }else{
                                closeCompany.css("background","#17a2b8");
                                closeCompany.html("关闭代理");
                            }
                        }
                    }
                });
            });
            
        });
    </script>
</body>
</html>
