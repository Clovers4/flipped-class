$(function () {
    $("#returnCourseId").val(sessionStorage.getItem("courseId"));
    $("#backBtn").click(function () {
        $("#returnForm").submit();
    })
});