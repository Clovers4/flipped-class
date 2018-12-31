var createKlassForm;
var file;
var uploadName;
$(function () {
    createKlassForm = $("#createKlassForm");
    file = $("#file");
    uploadName = $("#uploadName");

    var courseId = sessionStorage.getItem("courseId");
    $("#courseId").val(courseId);
    $("#returnCourseId").val(courseId);

    $(".cancel").click(back);
    $("#backBtn").click(back);
    $("#upload").click(function () {
        file.trigger("click");
    });
    file.on("input propertychange", function () {
        var files = file.get(0).files;
        if (files.length > 0) {
            uploadName.html(files[0].name);
        }
    });
    $(".confirm").click(function () {
        var verify = util.verifyWithAlert($(".form"));
        if (verify == null) {
            util.showLoading();
            $.ajax({
                type: "put",
                url: "/teacher/course/klass",
                contentType: false,
                processData: false,
                data: new FormData(createKlassForm.get(0)),
                success: function () {
                    util.hideLoading();
                    back();
                },
                error: function (xhr) {//xhr, textStatus, errorThrown
                    util.hideLoading();
                    if (xhr.status === 409) {
                        util.showAlert("danger", "创建失败，班级相同", 3);
                    } else {
                        util.showAlert("danger", "创建失败，未知错误", 3);
                    }
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