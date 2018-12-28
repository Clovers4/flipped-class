var file;
var uploadName;
var teamPPT;
var downloadFileForm;
var fileNameInput;
$(function () {
    file = $("#fileInput");
    uploadName = $("#uploadName");
    teamPPT = $("#teamPPT");
    downloadFileForm = $("#downloadFileForm");
    fileNameInput = $("#fileNameInput");

    var uploadPPTBtn = $("#uploadPPTBtn");
    if(uploadPPTBtn.length > 0){
        $("#attendanceId").val(uploadPPTBtn.attr("data-atdId"));
    }

    $("#cancelEnroll").click(function () {
        $.ajax({
            type: "post",
            url: "/student/course/seminar/cancelEnroll",
            data: $("#cancelEnrollForm").serialize(),
            success: function () {
                window.location.reload();
            },
            error: function () {
                util.showAlert("danger", "取消失败，未知原因", 3)
            }
        })
    });

    $("#returnCourseId").val(sessionStorage.getItem("courseId"));
    $("#backBtn").click(function () {
        $("#returnForm").submit();
    });
    $(".enroll").click(function () {
        $("#snInput").val($(this).attr("data-idx"));
        $.ajax({
            type: "post",
            url: "/student/course/seminar/enroll",
            data: $("#enrollForm").serialize(),
            success: function () {
                window.location.reload();
            },
            error: function () {
                util.showAlert("danger", "报名失败，已报名或该位置已被报名", 3)
            }
        })
    });
    $("#upload").click(function () {
        console.log(file);
        file.trigger("click");
    });
    file.on("input propertychange", function () {
        var files = file.get(0).files;
        if(files.length > 0) {
            uploadName.html(files[0].name);
        }
    });

    $("#confirmUpload").click(function () {
        var verify = util.verifyWithAlert(teamPPT);
        if(verify == null){
            util.showLoading();
            $.ajax({
                type:"post",
                url:"/student/course/seminar/uploadPPT",
                contentType: false,
                processData: false,
                data: new FormData(teamPPT.get(0)),
                success:function(){
                    util.hideLoading();
                    $("#pptModal").modal("hide");
                    util.showAlert("success", "上传成功", 3);
                },
                error:function () {
                    util.hideLoading();
                    util.showAlert("danger", "上传失败，请重试", 3);
                }
            })
        }
    });
    $(".download-ppt").click(function () {
        console.log(this);
        fileNameInput.val($(this).attr("data-fileName"));
        downloadFileForm.submit();
    })
});