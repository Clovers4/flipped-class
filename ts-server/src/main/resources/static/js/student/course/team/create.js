var createTeamForm;
$(function () {
    createTeamForm = $("#createTeamForm");

    var courseId = sessionStorage.getItem("courseId");
    var klassId = sessionStorage.getItem("klassId");
    $("#returnCourseId").val(courseId);
    $("#returnKlassId").val(klassId);
    $("#courseId").val(courseId);


    $(".cancel").click(back);
    $("#backBtn").click(back);

    $(".confirm").click(function () {
        var verify = util.verifyWithAlert($(".form"));
        if (verify == null) {
            util.showLoading();
            $.ajax({
                type: "put",
                url: "/student/course/team",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(createTeamForm.serializeObject()),
                success: function () {
                    util.hideLoading();
                    back();
                },
                error: function () {//xhr, textStatus, errorThrown
                    util.hideLoading();
                    util.showAlert("danger", "创建失败，未知错误", 3);
                }
            })
        } else {
            verify.registerDanger();
        }
    });
});

function back() {
    $("#returnForm").submit();
}