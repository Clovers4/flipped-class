var forgetPwdForm = {};
var getCaptchaBtn;
var count;
var countInterval;
$(function () {
    forgetPwdForm.form = $("#forgetPwdForm");
    forgetPwdForm.account = $("#account");
    forgetPwdForm.captcha = $("#captcha");
    getCaptchaBtn = $("#getCaptchaBtn");

    getCaptchaBtn.click(getCaptcha);
    $("#confirmBtn").click(captchaVerify);
    $("#returnBtn").click(function () {
        window.location = '/login';
    })
});
function getCaptcha() {
    if (util.singleVerifyWithAlert(forgetPwdForm.account)) {
        $.ajax({
            type: "post",
            url: "/captcha/forgetPassword",
            data: {
                account:forgetPwdForm.account.val()
            },
            success: function () {
                count = 60;
                getCaptchaBtn.attr("disabled", true);
                countInterval=setInterval("countDown()", 1000);
            },
            error:function (xhr) {
                util.showAlert("danger", xhr.responseText, 3);
            }
        })
    }
}
function captchaVerify() {
    var verify = util.verifyWithAlert(forgetPwdForm.form);
    if(verify == null){
        $.ajax({
            type: "post",
            url: "/forgetPassword",
            data: forgetPwdForm.form.serialize(),
            success: function (result, status, xhr) {
                if (xhr.status === 200) {
                    window.location="/modifyPassword";
                }
            },
            error: function (xhr) {//xhr, textStatus, errorThrown
                if(xhr.status === 409){
                    forgetPwdForm.captcha.registerDanger();
                    util.showAlert("warning","验证码错误",3);
                }else{
                    util.showAlert("danger", "未知错误", 3);
                }
            }
        });
    }else{
        verify.registerDanger();
    }
}
function countDown() {
    getCaptchaBtn.html(count+"s");
    count--;
    if (count <= 0) {
        getCaptchaBtn.html("发送验证码");
        getCaptchaBtn.attr("disabled", false);
        clearInterval(countInterval);
    }
}