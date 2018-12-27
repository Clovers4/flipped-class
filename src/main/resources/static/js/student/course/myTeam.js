$(function () {
    $("#courseIdInput").val(sessionStorage.getItem("courseId"));
    $("#returnBtn").click(function () {
        $("#courseIdForm").submit();
    });
    $("#addBtn").click(function () {
        util.showLoading();
        $.ajax({
            type:"post",
            url:"/student/course/myTeam/addMembers",
            data: $("#chosenStudentForm").serialize(),
            success:function () {
                window.location.reload();
            },
            error:function () {
                util.hideLoading();
                util.showAlert("danger", "新增失败，未知原因", 3);
            }
        });
    });
    $("#sendBtn").click(function () {
        util.showLoading();
        $.ajax({
            type:"post",
            url:"/student/course/myTeam/validApplication",
            data: $("#validApplicationForm").serialize(),
            success:function () {
                window.location.reload();
            },
            error:function () {
                util.hideLoading();
                util.showAlert("danger", "发送失败，未知原因", 3);
            }
        });
    });
    $("#dissolveBtn").click(function () {
        util.showLoading();
        $.ajax({
            type:"post",
            url:"/student/course/myTeam/dissolveTeam",
            data: {teamId:$("body").attr("data-teamId")},
            success:function () {
                $("#courseIdForm").submit();
            },
            error:function () {
                util.hideLoading();
                util.showAlert("danger", "解散失败，未知原因", 3);
            }
        });
    });
    $("#quitBtn").click(function () {
        util.showLoading();
        $.ajax({
            type:"post",
            url:"/student/course/myTeam/quitTeam",
            data: {teamId:$("body").attr("data-teamId")},
            success:function () {
                $("#courseIdForm").submit();
            },
            error:function () {
                util.hideLoading();
                util.showAlert("danger", "退出失败，未知原因", 3);
            }
        });
    });
    $(".delete").click(function () {
        util.showLoading();
        $.ajax({
            type:"post",
            url:"/student/course/myTeam/deleteMember",
            data: {studentId:$(this).attr("data-sid"),teamId:$("body").attr("data-teamId")},
            success:function () {
                window.location.reload();
            },
            error:function () {
                util.hideLoading();
                util.showAlert("danger", "移除失败，未知原因", 3);
            }
        });
    });
});