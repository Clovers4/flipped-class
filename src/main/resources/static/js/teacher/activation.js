var actForm = {};
var getCaptchaBtn;
var count;
var countInterval;
$(function () {
    actForm.form = $("#activationForm");
    actForm.password = $("#password");
    actForm.confirmPwd = $("#confirmPassword");
    actForm.email = $("#email");
    actForm.captcha = $("#captcha");

    getCaptchaBtn = $("#getCaptchaBtn");
    getCaptchaBtn.click(function () {
        if (util.singleVerifyWithAlert(actForm.email)) {
            getCaptcha();
        }
    });

    $("#activationBtn").click(function () {
        var password = actForm.password.val();
        var confirmPwd = actForm.confirmPwd.val();
        if(password !== confirmPwd){
            util.showAlert("warning", "两次密码不一致", 3);
            actForm.confirmPwd.registerDanger();
            return;
        }
        var verify = util.verifyWithAlert(actForm.form);
        if(verify == null){
            $.ajax({
                type: "post",
                url: "/teacher/activation",
                data: actForm.form.serialize(),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        window.location="/teacher/index";
                    }
                },
                error: function (xhr) {//xhr, textStatus, errorThrown
                    if (xhr.status === 409) {
                        actForm.captcha.registerDanger();
                        util.showAlert("danger","验证码错误",3);
                    } else {
                        util.showAlert("danger", "激活失败，未知错误", 3);
                    }
                }
            });
        }else{
            verify.registerDanger();
        }
    })
});

function getCaptcha() {
    $.ajax({
        type: "post",
        url: "/teacher/captcha/activation",
        data: {
            email:actForm.email.val()
        },
        success: function () {
            count = 60;
            getCaptchaBtn.attr("disabled", true);
            countInterval=setInterval("countDown()", 1000);
        }
    })
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