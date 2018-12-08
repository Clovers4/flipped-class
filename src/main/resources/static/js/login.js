var loginBtn;
var nameInput;
var passwordInput;
$(function () {
    loginBtn = $("#login");
    nameInput = $("#username");
    passwordInput = $("#password");
    loginBtn.click(function () {
        login();
    });
});

function login() {
    var verify = util.verifyWithAlert($("#loginForm"));
    if (verify == null) {
        $.ajax({
            type: "post",
            url: "/login",
            data: {
                "account": nameInput.val(),
                "password": passwordInput.val()
            },
            success: function (result, status, xhr) {
                if (xhr.status === 200) {
                    var response = xhr.responseText;
                    var auth = response.substring(response.indexOf('_') + 1, response.lastIndexOf(']'));
                    window.location = "/" + auth + "/index";
                }
            },
            error: function (xhr) {//xhr, textStatus, errorThrown
                if (xhr.status === 401) {
                    util.showAlert("danger", "登录失败，用户名或者密码错误", 3);
                }else{
                    util.showAlert("danger", "登录失败，未知错误", 3);
                }
            }
        })
    } else {
        verify.focus();
    }
}