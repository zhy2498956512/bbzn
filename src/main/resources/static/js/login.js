$(function(){
    $('.noRemember').click(function() {
        alert("请联系管理员");
    });

    $("#login").click(function(){
        if($.trim($("#login-username").val())!=""&&$.trim($("#login-password").val())!=""){
            $.ajax({
                url: "api/login/loginVerification",
                data: {"companyUserName": $("#login-username").val(), "companyUserPass": $("#login-password").val()},
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if (data == 1) {
                        location.href = "api/login/index";
                    } else if (data == 2) {
                        alert("该用户已被停用，请联系管理员！！！");
                    } else {
                        alert("用户名或密码错误！！！");
                    }
                }
            });
        }
    });
});