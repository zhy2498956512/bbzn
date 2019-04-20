$(function () {
    function JSONToExcelConvertor(fileName, jsonData) {
        ///<summary>json转excel下载</summary>
        ///<param name="fileName">文件名</param>
        ///<param name="jsonData">数据</param>
        //json
        var arrData = typeof jsonData != 'object' ? JSON.parse(jsonData) : jsonData;
        // #region 拼接数据
        var excel = '<table>';
        //设置表头
        var row = "<tr>";
        for (var name in arrData[0]) {
            //每个单元格都可以指定样式. eg color:red   生成出来的就是 红色的字体了.
            row += "<td style='color:red;text-align:center;'>" + name + '</td>';
        }
        //列头结束
        excel += row + "</tr>";
        //设置数据
        for (var i = 0; i < arrData.length; i++) {
            var row = "<tr>";
            for (var index in arrData[i]) {

                var value = arrData[i][index] === "." ? "" : arrData[i][index];

                row += '<td style="text-align:center;">' + value + '</td>';//将值放入td
            }
            //将td 放入tr,将tr放入table
            excel += row + "</tr>";
        }
        //table结束
        excel += "</table>";
        // #endregion
        // #region 拼接html
        var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
        excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
        excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel';
        excelFile += '; charset=UTF-8">';
        excelFile += "<head>";
        excelFile += "<!--[if gte mso 9]>";
        excelFile += "<xml>";
        excelFile += "<x:ExcelWorkbook>";
        excelFile += "<x:ExcelWorksheets>";
        excelFile += "<x:ExcelWorksheet>";
        excelFile += "<x:Name>";
        excelFile += "{worksheet}";
        excelFile += "</x:Name>";
        excelFile += "<x:WorksheetOptions>";
        excelFile += "<x:DisplayGridlines/>";
        excelFile += "</x:WorksheetOptions>";
        excelFile += "</x:ExcelWorksheet>";
        excelFile += "</x:ExcelWorksheets>";
        excelFile += "</x:ExcelWorkbook>";
        excelFile += "</xml>";
        excelFile += "<![endif]-->";
        excelFile += "</head>";
        excelFile += "<body>";
        excelFile += excel;//将table 拼接
        excelFile += "</body>";
        excelFile += "</html>";
        // #endregion
        var uri = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(excelFile);
        //创建a标签
        var link = document.createElement("a");
        //指定url
        link.href = uri;
        //设置为隐藏
        link.style = "visibility:hidden";
        //指定文件名和文件后缀格式
        link.download = fileName + ".xls";
        //追加a标签
        document.body.appendChild(link);
        //触发点击事件
        link.click();
        //移除a标签
        document.body.removeChild(link);
    }

    var total = $("#total").val();
    var already = $("#already").val();
    var i = (already/total)*100;
    $("#strip").attr("style","width:"+i+ "%;");


    $("#jump").submit(function () {
        if($("#grantNumberRecordAmount").val()<0||$("#grantNumberRecordAmount").val()==""||$("#grantNumberRecordAmount").val()==null){
            return false;
        }
        return true;
    });

    $("#savaProject").click(function () {
        $("#projectType").html("");
        $.ajax({
            url: "api/project/getProjectType",
            data: {},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                var data = eval(msg);//将msg化为数
                var num = 1;
                var html = "";
                $.each(data, function (num) {
                    var projectTypeId = data[num].projectTypeId;
                    var projectTypeName = data[num].projectTypeName;
                    html = html + "<option value='"+projectTypeId+"'>"+projectTypeName+"</option>";
                })
                $("#projectType").append(html);
            }
        });
    });


    $(".record").focus(function(){
        $(this).css("background-color","#D6D6FF");
    });
    $(".record").blur(function(){
        $(this).css("background-color","#ffffff");
    });

    $(".record").click(function () {
        $.ajax({
            url: "api/grantNumberRecord/record",
            data: {"pageNum": $(this).prev().val(),"recordType":1},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                var pageNum = msg.pageNum;              //当前页
                var totalRecord = msg.totalRecord;      //总条数
                var totalPage = msg.totalPage;          //总页数
                var pageSize = msg.pageSize;            //每页显示的记录数
                $(".record").each(function(){
                    if ($(this).val()=="上页"){
                        $(this).prev().val((pageNum-1));
                    }else if ($(this).val()=="下页") {
                        $(this).prev().val((pageNum+1));
                    }else if ($(this).val()=="跳转") {
                        $(this).prev().val((pageNum));
                    }else{
                        $(this).prev().val(1);
                    }
                });
                $("#totalPage").html(pageNum+"/"+totalPage);
                /*$("#totalRecord").html("共"+totalRecord+"条记录  ");*/
                var data = msg.list;
                var num = 1;
                var html = "";
                $.each(data, function (num) {
                    var companyName = data[num].companyName;
                    var grantNumberRecordAmount = data[num].grantNumberRecordAmount;
                    var grantNumberRecordRemark = data[num].grantNumberRecordRemark;
                    var applytime = data[num].applytime;
                    /*applytime = (applytime || "").split(" ");*/
                    $("#companyName").html("");
                    $("#companyName").html(companyName);
                    var feedbacktime = data[num].feedbacktime;
                    html = html + "<div style='width: 96%;float: left;border-bottom: 1px solid #D6D8DB;height: 80px;font-size: 14px;'>"+
                    "<div style='float: left;width: 5%;text-align: center;line-height: 80px;'>"+grantNumberRecordAmount+"</div>"+
                        "<div style='float: left;width: 35%;text-align: center;line-height: 80px;'>";
                        if(grantNumberRecordRemark==null||grantNumberRecordRemark==""){
                            html = html + "<span style='padding: 5px 5px;background-color: #28A745;color: #fff;line-height: 15px;border-radius:5px ;margin-top:30px; '>暂时数据</span>";
                        }else {
                            html = html + grantNumberRecordRemark;
                        }
                        html = html + "</div>"+
                        "<div style='float: left;width: 25%;text-align: center;line-height: 80px;'>";
                        if(applytime==null||applytime==""){
                            html = html + "<span style='padding: 5px 5px;background-color: #28A745;color: #fff;line-height: 15px;border-radius:5px ;margin-top:30px; '>暂时数据</span>";
                        }else{
                            html = html + applytime;
                        }
                    html = html + "</div>"+
                        "<div style='float: left;width: 25%;text-align: center;line-height: 80px;'>"+feedbacktime+"</div>"+
                        "<div style='float: left;width: 8%;text-align: center;line-height: 80px;'><span style='padding: 5px 5px;background-color: #28A745;color: #fff;line-height: 15px;border-radius:5px ;margin-top:30px; '>审核通过</span></div>"+
                    "</div>";
                })
                $("#viewRecord").html("");
                $("#viewRecord").append(html);
            }
        });
    });

    $("#generate").click(function () {
        $.ajax({
            url: "api/grant/getAgentProject",
            data: {},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                $("#number").attr("placeholder","最多可填"+$("#maxValue").html()+"个")
                var data = eval(msg);//将msg化为数
                var num = 1;
                var html = "";
                $.each(data, function (num) {
                    var projectId = data[num].projectId;
                    var projectName = data[num].projectName;
                    html = html + "<option value='"+projectId+"'>"+projectName+"</option>";
                })
                $("#project").append(html);
            }
        });
    });

    $("#number").change(function () {
        var x = $(this);
        var y = $("#maxValue").html();
        if ((x.val()*1)>(y*1)){
            x.val(y);
            alert("可用的授权码数量不足，请及时充值");
        }
    });
    
    $("#code").click(function () {
        if ($("#number").val()==null||$("#number").val()==""||$("#number").val()==0) {
            alert("请输入要生成的授权码数量！！！");
            return;
        }
        if ($("#project").val()==null||$("#project").val()==""||$("#project").val()==0){
            alert("请选择所属项目！！！");
            return;
        }
        var prefix = $("#prefix").val();        //前缀
        var length = $("#length").val();        //授权码长度
        var number = $("#number").val();        //授权码数量
        var binary = $("#binary").val();        //授权码进制
        var projectId = $("#project").val();        //授权码进制
        $.ajax({
            url: "api/grant/getCode",
            data: {"prefix":prefix,"length":length,"number":number,"binary":binary},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                $("#submitCode").prev().val(prefix);
                $("#submitCode").prev().prev().val(length);
                $("#submitCode").prev().prev().prev().val(binary);
                $("#submitCode").prev().prev().prev().prev().val(projectId);
                $("#number").attr("placeholder","最多可填"+$("#maxValue").html()+"个")
                var data = eval(msg);//将msg化为数
                var num = 1;
                var html = "";
                $.each(data, function (num) {
                    var code = data[num];
                    html = html + "<option value='"+code+"'>"+code+"</option>";
                })
                $("#multiselect_to_1").html("");
                $("#multiselect_to_1").append(html);
            }
        });
    });
    
    $(".upload").click(function () {
        $("[name='projectId']").val($(this).prev().val());
    });

    $("#submitCode").click(function () {
        var x = $(this);
        $("#multiselect_to_1 option").each(function(){
            $(this).attr("selected","selected");
        })
        if($("#multiselect_to_1").val()!=""&&$("#multiselect_to_1").val()!=null){
            if (confirm("确定要生成授权码吗？")) {
                var json = "[" ;
                $("#multiselect_to_1 option").each(function () {
                    json = json + '{"序列号":"'+$(this).text()+'"},';
                });
                json = json + '{"序列号":""}]';
                var dataJson = JSON.parse(json);
                JSONToExcelConvertor("我的excel",dataJson);
                var prefix = $("#submitCode").prev().val();
                var length = $("#submitCode").prev().prev().val();
                var binary = $("#submitCode").prev().prev().prev().val();
                var projectId = $("#submitCode").prev().prev().prev().prev().val();
                $.ajax({
                    url: "api/grant/uploadCode",
                    data: {"prefix":prefix,"length":length,"binary":binary,"projectId":projectId,"codeList":$('#multiselect_to_1').val()+""},
                    type: "POST",
                    dataType: "json",
                    success: function (msg) {
                        alert("序列号上传成功");
                        location.href="api/grantNumberRecord/jump";
                    }
                });
            }
        }else{
            alert("未检测到授权码，请先生成!!!");
        }
    });

    $(".updateProjectName").click(function () {
       $(this).prev().val();
    });

    $(".jump3").click(function () {
        location.href="api/equipment/jumpEquipment?projectId="+$(this).prev().val()+"&equipmentType=1&searchEquipment=";
    });

    $(".updateProjectName").click(function () {
        $.ajax({
            url: "api/project/updateProjectName",
            data: {"projectId":$(this).prev().val()},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                var data = eval(msg);//将msg化为数
                var projectId = data.projectId;
                var projectName = data.projectName;
                $("#pName").val(projectName);
                $("#pId").val(projectId);
            }
        })
    });

    $("#submitAPK").submit(function () {
        var len = $("#submitAPK [name='fileName']").val();
        if (len.substring(len.length,len.length-3)!="apk"){
            alert("只能上传APK文件！！！");
            return false;
        }
        return true;
    });

    function LeftRight(){
        $("#leftNumber").html($("#multiselect_to_3").find("option").length);
        $("#rightNumber").html($("#multiselect_to_2").find("option").length);
    };

    $("#uploadFiles").click(function () {
        $.ajax({
            url: "api/grant/getAgentProject",
            data: {},
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
                $("[name='info']").append(html);
            }
        });
    });

    $("#uploadButton").click(function () {
        if($("[name='file']").val()==""){
            alert("请先选择序列号文件!!!");
            return;
        }
        var formData = new FormData($("#uploadForm")[0]);
        $("#multiselect_to_4").html("");
        $("#multiselect_to_5").html("");
        $.ajax({
            url: "api/grant/uploadCodeFile",
            type: 'POST',
            /*async: false,*/
            data: formData,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (data) {
                var availableList = data.availableList;
                var unavailableList = data.unavailableList;
                var num1 = 1;
                var num2 = 1;
                var html1 = "";
                var html2 = "";
                var availableNumber = 0;
                var unavailableNumber = 0;
                $.each(availableList, function (num1) {
                    var code = availableList[num1];
                    if(code!=num1&&code!=""){
                        html1 = html1 + "<option>"+code+"</option>";
                        ++availableNumber;
                    }
                });
                $.each(unavailableList, function (num2) {
                    var code = unavailableList[num2];
                    html2 = html2 + "<option>"+code+"</option>";
                    ++unavailableNumber;
                });
                if(html1!=num1&&html1!=""){
                    $("#multiselect_to_4").append(html1);
                }
                $("#multiselect_to_5").append(html2);
                $("#availableNumber").html(availableNumber);
                $("#unavailableNumber").html(unavailableNumber);
            },
            error: function (err) {
                alert("系统异常");
            }
        });
    });
    
    $("#uploadFileCode").click(function () {
        $("#multiselect_to_4 option").each(function(){
            $(this).attr("selected","selected");
        });
        if($("#multiselect_to_4").val()==""){
            alert("未检测到序列号!!!");
            return;
        }
        if (confirm("确定要生成授权码吗？")) {
            var info = $("[name='info']").val();
            $.ajax({
                url: "api/grant/uploadFileCode",
                data: {"codeList":$('#multiselect_to_4').val()+"","info":info},
                type: "POST",
                dataType: "json",
                success: function (msg) {
                    if(msg==1){
                        alert("序列号上传成功");
                        location.href="api/grantNumberRecord/jump";
                    }else {
                        alert("序列号上传失败!!!");
                    }
                }
            });
        }
    });

    $(".configure").click(function () {
        var projectId = $(this).next().val();
        $("#cId").val(projectId);
        $.ajax({
            url: "api/projectLanguage/getProjectLanguage",
            data: {"projectId":""+projectId},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                $("#multiselect_to_3").html("");
                $("#multiselect_to_2").html("");
                var languageList = msg.languageList;
                var companyLanguageList = msg.companyLanguageList;
                var num1 = 1;
                var num2 = 1;
                var html1 = "";
                var html2 = "";
                $.each(languageList, function (num1) {
                    var languageId = languageList[num1].languageId;
                    var languageName = languageList[num1].languageName;
                    html1 = html1 + "<option value='"+languageId+"' id='"+languageId+"'>"+languageName+"</option>";
                })
                $("#multiselect_to_3").append(html1);
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
        $("#multiselect_to_2").append($("#multiselect_to_3").html());
        $("#multiselect_to_3").html("");
        LeftRight();
    });

    $("#allLeft").click(function () {
        $("#multiselect_to_3").append($("#multiselect_to_2").html());
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
        $("#multiselect_to_3").append(html);
        LeftRight();
    });

    $("#right").click(function () {
        var html = "";
        $("#multiselect_to_3 option:selected").each(function () {
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
    
    
    $(".projectConfigure").click(function () {
        var projectId = $(this).prev().prev().val();
        location.href = "api/project/projectConfigure?projectId="+projectId;
    });

});
