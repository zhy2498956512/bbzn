$(function () {
    $("#regression").click(function () {
        location.href="/api/equipment/jumpEquipment?projectId="+$("#projectId").val()+"&equipmentType=1&searchEquipment=";
    });

    $("#editionSwitchProject").click(function () {
        $.ajax({
            url: "/api/project/showProjectList",
            data: {"projectId":$("#projectId").val()},
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
                });
                $("#projectList").append(html);
            }
        })
    });

    $("#submission").click(function () {
        if($("#projectName").val()==$("#projectList option:selected").text()){
            alert("无修改");
        }else {
            $.ajax({
                url: "/api/equipment/equipmentSwitchProject",
                data: {"projectId":$("#projectId").val(),"switchProjectId":$("#projectList").val(),"equipmentId":$("#equipmentId").val()},
                type: "POST",
                dataType: "json",
                success: function (msg) {
                    if(msg==200){
                        alert("修改成功");
                        location.href="/api/equipment/jumpEquipment?projectId="+$("#projectId").val()+"&equipmentType=1&searchEquipment=";
                    }else{
                        alert("修改失败");
                    }
                }
            })
        }
    });


    $(".record").click(function () {
        $.ajax({
            url: "/api/pushRecord/showPushRecord",
            data: {"pageNum": $(this).prev().val(),"equipmentId":$("#equipmentId").val()},
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

});