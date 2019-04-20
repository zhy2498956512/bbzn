$(function () {
    $("#regression").click(function () {
        location.href = "api/grantNumberRecord/jump";
    });

    $("#updateProjectName").click(function () {
        if($("#projectName").val()==""){
            alert("项目名不能为空")
        }else {
            location.href = "api/project/updateProjectName?projectId="+$("#projectId").val()+"&projectName="+$("#projectName").val();
        }
    });


    $(".record").click(function () {
        $.ajax({
            url: "api/pushRecord/showPushRecord",
            data: {"pageNum": $(this).prev().val(),"projectId":$("#projectId").val()},
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
                $("#record").html("共"+totalRecord+"条记录");
                var data = msg.list;
                var num = 1;
                var html = "";
                $.each(data, function (num) {
                    var grantCode = data[num].grantCode;
                    var apkEdition = data[num].apkEdition;
                    var apkRoute = data[num].apkRoute;
                    var timeString = data[num].timeString;
                    html = html + "<div style='float: left;width: 25%;text-align: center;'>"+grantCode+"</div>\n" +
                                  "<div style='float: left;width: 25%;text-align: center;'>"+apkEdition+"</div>\n" +
                                  "<div style='float: left;width: 25%;text-align: center;'>"+apkRoute+"</div>\n" +
                                  "<div style='float: left;width: 25%;text-align: center;'>"+timeString+"</div>";
                })
                $("#viewRecord").html("");
                $("#viewRecord").append(html);
            }
        });
    });

    $(".record1").click(function () {
        $.ajax({
            url: "api/pushRecord/showProjectPush",
            data: {"pageNum": $(this).prev().val(),"projectId":$("#projectId").val()},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                var pageNum = msg.pageNum;              //当前页
                var totalRecord = msg.totalRecord;      //总条数
                var totalPage = msg.totalPage;          //总页数
                var pageSize = msg.pageSize;            //每页显示的记录数
                $(".record1").each(function(){
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
                $("#totalPage1").html(pageNum+"/"+totalPage);
                $("#record1").html("共"+totalRecord+"条记录");
                var data = msg.list;
                var num = 1;
                var html = "";
                $.each(data, function (num) {
                    var apkId = data[num].apkId;
                    var apkRoute = data[num].apkRoute;
                    var timeString = data[num].timeString;
                    html = html + "<ul class='xuanz'>\n" +
                        "                        <li style='width: 10%;'><input type='radio' value='"+apkId+"' name='fruit' style='margin-left:10px ;'/></li>\n" +
                        "                        <li style='width: 15%;'>"+apkId+"</li>\n" +
                        "                        <li style='width: 40%;'>"+apkRoute+"</li>\n" +
                        "                        <li style='width: 35%; color: #6CBEDA;'>"+timeString+"</li>\n" +
                        "                    </ul>";
                })
                $("#viewRecord1").html("");
                $("#viewRecord1").append(html);
            }
        });
    });


    $("#uploadButton").click(function () {
        if($("[name='file']").val()==""){
            alert("请先选择序列号文件!!!");
            return;
        }
        var val=$('input:radio[name="fruit"]:checked').val();
        if(val==null){
            alert("请选择要推送的APK!!!");
            return;
        }
        var formData = new FormData($("#uploadForm")[0]);
        $.ajax({
            url: "api/pushRecord/pushRecord",
            type: 'POST',
            /*async: false,*/
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                if(data=="推送成功"){
                    alert("推送成功");
                    location.href = "api/project/projectConfigure?projectId="+$("#projectId").val();
                }else {
                    alert(data);
                }
            },
            error: function (err) {
                alert("系统异常");
            }
        });
    });


    $("#uploadButton1").click(function () {
        if($("[name='file1']").val()==""){
            alert("请先选择序列号文件!!!");
            return;
        }
        var z = 0;
        $("#multiselect_to_2 option").each(function(){
            $(this).attr("selected","selected");
            ++z;
        });
        if(z==0){
            alert("请先选择配置语言!!!");
            return;
        }
        var formData = new FormData($("#uploadForm1")[0]);
        $.ajax({
            url: "api/equipmentLanguage/configurationLanguage",
            type: 'POST',
            /*async: false,*/
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                if(data=="配置成功"){
                    alert("配置成功");
                    location.href = "api/project/projectConfigure?projectId="+$("#projectId").val();
                }else {
                    alert(data);
                }
            },
            error: function (err) {
                alert("系统异常");
            }
        });
    });


    $("#configurationLanguage").click(function () {
        $.ajax({
            url: "api/projectLanguage/getProjectLanguageList",
            data: {"projectId":$("#projectId").val()},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                var data = eval(msg);//将msg化为数
                var num = 1;
                var html = "";
                var i = 0;
                $.each(data, function (num) {
                    var languageId = data[num].languageId;
                    var languageName = data[num].languageName;
                    html = html + "<option value='"+languageId+"' id='"+languageId+"'>"+languageName+"</option>";
                    ++i;
                })
                $("#leftNumber").html(i);
                $("#multiselect_to_1").append(html);
            }
        });
    });

    function LeftRight(){
        $("#leftNumber").html($("#multiselect_to_1").find("option").length);
        $("#rightNumber").html($("#multiselect_to_2").find("option").length);
    };

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

});