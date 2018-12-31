var modifyPwdForm = {};
$(function () {
    modifyPwdForm.form = $("#modifyPwdForm");
    modifyPwdForm.password = $("#password");
    modifyPwdForm.confirmPwd = $("#confirmPwd");

    $("#confirmBtn").click(sendModifyRequest);
    $("#returnBtn").click(function () {
        window.location = '/login';
    })
});

function sendModifyRequest() {
    if(modifyPwdForm.password.val() !== modifyPwdForm.confirmPwd.val()){
        util.showAlert("warning", "两次密码不一致", 3);
        modifyPwdForm.confirmPwd.registerDanger();
        return;
    }
    var verify = util.verifyWithAlert(modifyPwdForm.form);
    if(verify == null){
        $.ajax({
            type: "post",
            url: "/modifyPassword",
            data: modifyPwdForm.form.serialize(),
            success: function (result, status, xhr) {
                if (xhr.status === 200) {
                    window.location="/teacher/index";
                }
            },
            error: function () {
                util.showAlert("danger", "修改失败，未知错误", 3);
            }
        });
    }else{
        verify.registerDanger();
    }
}