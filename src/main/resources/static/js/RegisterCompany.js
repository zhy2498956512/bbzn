$(function(){
    $("#register").submit(function(){
        var companyName = $("#companyName").val();
        if(companyName==""){
            alert("请输入公司名");
            return false;
        }
        return true;
    });
});