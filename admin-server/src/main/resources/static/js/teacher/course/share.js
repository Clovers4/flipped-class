var dropdownCard;
$(function () {
    $("#courseIdInput").val(sessionStorage.getItem("courseId"));

    dropdownCard = $(".dropdown-card");
    dropdownCard.click(function (ev) {
        var offsetY = ev.pageY - $(this).offset().top;
        if(offsetY>0&&offsetY<135){
            $(this).find(".operation-div").slideToggle();
            dropdownCard.not($(this)).find(".operation-div").slideUp();
        }
    });

    $("#addShare").click(function () {
        $("#createShareForm").submit();
    });

    $(".cancel-team-share").click(function () {
        util.showLoading();
        $.ajax({
            type:"post",
            url:"/teacher/course/share/cancelTeamShare",
            data: {subCourseId:$(this).attr("data-subCourseId")},
            success:function () {
                window.location.reload();
            },
            error:function () {
                util.hideLoading();
                util.showAlert("danger", "取消失败，未知原因", 3);
            }
        });
    });
    $(".cancel-seminar-share").click(function () {
        util.showLoading();
        $.ajax({
            type:"post",
            url:"/teacher/course/share/cancelSeminarShare",
            data: {subCourseId:$(this).attr("data-subCourseId")},
            success:function () {
                window.location.reload();
            },
            error:function () {
                util.hideLoading();
                util.showAlert("danger", "取消失败，未知原因", 3);
            }
        });
    })
});