var loginForm;
$(function () {
    loginForm = $("#loginForm");

    //Login logy
    $("#login").click(login);
    $("#forgetPwd").click(function () {
        window.location = '/forgetPassword';
    })
});

function login() {
    var verify = util.verifyWithAlert(loginForm);
    if (verify == null) {
        $.ajax({
            type: "post",
            url: "/login",
            data: loginForm.serialize(),
            success: function (result, status, xhr) {
                var response = xhr.responseText;
                var auth = response.substring(response.indexOf('_') + 1, response.lastIndexOf(']'));
                window.location = "/" + auth + "/index";
            },
            error: function (xhr) {//xhr, textStatus, errorThrown
                switch (xhr.status) {
                    case 401:
                        util.showAlert("danger", "登录失败，用户名或者密码错误", 3);
                        break;
                    case 409:
                        util.showAlert("danger", "登录失败，请勿重复登录", 3);
                        break;
                    default:
                        util.showAlert("danger", "登录失败，未知错误", 3);
                        break;
                }
            }
        })
    } else {
        verify.registerDanger();
    }
}