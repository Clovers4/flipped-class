var actForm = {};
$(function () {
    actForm.form = $("#activationForm");
    actForm.password = $("#password");
    actForm.confirmPwd = $("#confirmPassword");

    $("#activationBtn").click(function () {
        var password = actForm.password.val();
        var confirmPwd = actForm.confirmPwd.val();
        if (password !== confirmPwd) {
            util.showAlert("warning", "两次密码不一致", 3);
            actForm.confirmPwd.registerDanger();
            return;
        }
        var verify = util.verifyWithAlert(actForm.form);
        if (verify == null) {
            $.ajax({
                type: "post",
                url: "/teacher/activation",
                data: actForm.form.serialize(),
                success: function () {
                    window.location = "/teacher/index";
                },
                error: function () {
                    util.showAlert("danger", "激活失败，未知错误", 3);
                }
            });
        } else {
            verify.registerDanger();
        }
    })
});