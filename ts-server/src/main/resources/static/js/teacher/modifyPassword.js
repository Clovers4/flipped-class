var modifyPwdForm;
var passwordInput;
var confirmPwdInput;
$(function () {
    modifyPwdForm = $("#modifyPwdForm");
    passwordInput = $("#password");
    confirmPwdInput = $("#confirmPwd");

    $("#confirmBtn").click(function () {
        var password = passwordInput.val();
        var confirmPwd = confirmPwdInput.val();
        if(password !== confirmPwd){
            util.showAlert("warning", "两次密码不一致", 3);
            confirmPwdInput.registerDanger();
            return;
        }
        var verify = util.verifyWithAlert(modifyPwdForm);
        if(verify == null){
            $.ajax({
                type: "post",
                url: "/teacher/modifyPassword",
                data: modifyPwdForm.serialize(),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        window.location="/teacher/setting";
                    }
                },
                error: function () {
                    util.showAlert("danger", "修改失败，未知错误", 3);
                }
            });
        }else{
            verify.registerDanger();
        }
    })
});