var createShareForm;
$(function () {
    createShareForm = $("#createShareForm");
    var courseId = sessionStorage.getItem("courseId");
    $("#mainCourseId").val(courseId);
    $("#returnCourseId").val(courseId);

    $(".cancel").click(back);
    $("#backBtn").click(back);

    $(".confirm").click(function () {
        var formData = createShareForm.serializeObject();
        if(formData["subCourseId"] === undefined){
            util.showAlert("warning", "请选择共享课程",3);
            return;
        }
        $.ajax({
            type: "put",
            url: "/teacher/course/shareApplication",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(formData),
            success: function (result, status, xhr) {
                if (xhr.status === 200) {
                    back();
                }
            },
            error: function (xhr) {
                if(xhr.status === 409){
                    util.showAlert("danger", "创建失败，该申请已存在", 3);
                }else if(xhr.status === 400){
                    util.showAlert("danger", "创建失败，该课程已共享", 3);
                }else{
                    util.showAlert("danger", "创建失败，未知错误", 3);
                }
            }
        });
    })
});


function back() {
    $("#returnForm").submit();
}