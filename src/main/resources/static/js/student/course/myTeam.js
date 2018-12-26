$(function () {
    $("#courseIdInput").val(sessionStorage.getItem("courseId"));
    $("#returnBtn").click(function () {
        $("#courseIdForm").submit();
    });
    $("#addBtn").click(function () {
        $.ajax({
            type:"post",
            url:"/student/course/myTeam/addMembers",
            data: $("#chosenStudentForm").serialize(),
            success:function () {
                window.location.reload();
            },
            error:function () {
                util.showAlert("danger", "新增失败，未知原因", 3);
            }
        });
    });
    $(".delete").click(function () {
        $.ajax({
            type:"post",
            url:"/student/course/myTeam/deleteMember",
            data: {studentId:$(this).attr("data-sid")},
            success:function () {
                window.location.reload();
            },
            error:function () {
                util.showAlert("danger", "移除失败，未知原因", 3);
            }
        });
    });
});