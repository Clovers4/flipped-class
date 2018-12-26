var file;
var uploadName;
var teamReport;
$(function () {
    file = $("#fileInput");
    uploadName = $("#uploadName");
    teamReport = $("#teamReport");

    $("#returnCourseId").val(sessionStorage.getItem("courseId"));
    $("#backBtn").click(function () {
        $("#returnForm").submit();
    });
    $("#upload").click(function () {
        file.trigger("click");
    });
    file.on("input propertychange", function () {
        var files = file.get(0).files;
        console.log(files);
        if(files.length > 0) {
            uploadName.html(files[0].name);
        }
    });

    $("#confirmUpload").click(function () {
        var verify = util.verifyWithAlert(teamReport);
        if(verify == null){
            util.showLoading();
            $.ajax({
                type:"post",
                url:"/student/course/seminar/uploadReport",
                contentType: false,
                processData: false,
                data: new FormData(teamReport.get(0)),
                success:function(){
                    util.hideLoading();
                    util.showAlert("success", "上传成功", 3);
                },
                error:function () {
                    util.hideLoading();
                    util.showAlert("danger", "上传失败，请重试", 3);
                }
            })
        }
    });
});