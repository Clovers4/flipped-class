var createKlassForm;
$(function () {
    createKlassForm = $("#createKlassForm");

    var courseId = sessionStorage.getItem("courseId");
    $("#courseId").val(courseId);
    $("#returnCourseId").val(courseId);

    $(".cancel").click(back);
    $("#backBtn").click(back);
    $(".confirm").click(function () {
        var verify = util.verifyWithAlert($(".form"));
        if(verify == null){
            console.log(createKlassForm.serialize());
            $.ajax({
                type: "put",
                url: "/teacher/course/klass",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(createKlassForm.serializeObject()),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        window.location = "/teacher/course/klassList";
                    }
                },
                error: function (xhr) {//xhr, textStatus, errorThrown
                    if (xhr.status === 400) {
                        util.showAlert("danger", "创建失败，班级相同", 3);
                    }else{
                        util.showAlert("danger", "创建失败，未知错误", 3);
                    }
                }
            })
        }else{
            verify.registerDanger();
        }
    });
});

function back() {
    $("#returnForm").submit();
}