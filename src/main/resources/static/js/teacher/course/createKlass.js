var createKlassForm;
var file;
$(function () {
    createKlassForm = $("#createKlassForm");
    file = $("#file");

    var courseId = sessionStorage.getItem("courseId");
    $("#courseId").val(courseId);
    $("#returnCourseId").val(courseId);

    $(".cancel").click(back);
    $("#backBtn").click(back);
    $("#fileProxy").click(function () {
        file.trigger("click");
    });
    file.on("input propertychange", function () {
        console.log(file.val());
    });
    $(".confirm").click(function () {
        var verify = util.verifyWithAlert($(".form"));
        if(verify == null){
            $.ajax({
                type: "put",
                url: "/teacher/course/klass",
                contentType: false,
                processData: false,
                data: new FormData(createKlassForm.get(0)),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        back();
                    }
                },
                error: function (xhr) {//xhr, textStatus, errorThrown
                    if (xhr.status === 409) {
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