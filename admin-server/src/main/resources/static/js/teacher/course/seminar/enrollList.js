var fileNameInput;
var teamIdInput;
var downloadFileForm;
var reportScoreModal;

$(function () {
    downloadFileForm = $("#downloadFileForm");
    fileNameInput = $("#fileNameInput");
    teamIdInput = $("#teamIdInput");
    reportScoreModal = $("#reportScoreModal");

    $(".download-file").click(function () {
        fileNameInput.val($(this).attr("data-fileName"));
        teamIdInput.val($(this).attr("data-teamId"));
        downloadFileForm.submit();
    });
    $(".score").click(function () {
        $("#teamId").val($(this).attr("data-teamId"));
    });

    $("#seminarIdInput").val(sessionStorage.getItem("seminarId"));
    $("#klassIdInput").val(sessionStorage.getItem("klassId"));
    $("#backBtn").click(function () {
        $("#seminarForm").submit();
    });

    $("#confirmBtn").click(function () {
        util.showLoading();
        $.ajax({
            type:"post",
            url:"/teacher/course/seminar/enrollList/giveScore",
            data: $("#giveScoreForm").serialize(),
            success:function () {
                util.hideLoading();
                reportScoreModal.modal("hide");
                util.showAlert("success", "给分成功", 3);
            },
            error:function () {
                util.hideLoading();
                reportScoreModal.modal("hide");
                util.showAlert("danger", "给分失败，未知原因", 3);
            }
        });
    });
});