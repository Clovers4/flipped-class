var modifyEmailForm;
var emailInput;
var getCaptchaBtn;
var count;
var countInterval;
$(function () {
    modifyEmailForm = $("#modifyEmailForm");
    emailInput = $("#email");

    getCaptchaBtn = $("#getCaptchaBtn");
    getCaptchaBtn.click(function () {
        if (util.singleVerifyWithAlert(emailInput)) {
            getCaptcha();
        }
    });
    $("#confirmBtn").click(function () {
        var verify = util.verifyWithAlert(modifyEmailForm);
        if(verify == null){
            $.ajax({
                type: "post",
                url: "/teacher/modifyEmail",
                data: modifyEmailForm.serialize(),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        window.location="/teacher/setting";
                    }
                },
                error: function (xhr) {
                    if (xhr.status === 409) {
                        util.showAlert("danger", "验证码错误", 3);
                    }else{
                        util.showAlert("danger", "修改失败，未知错误", 3);
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
        url: "/teacher/captcha/modifyEmail",
        data: {
            email:emailInput.val()
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