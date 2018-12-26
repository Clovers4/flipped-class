var courseIdForm = {};
$(function () {
    courseIdForm.form = $("#courseIdForm");
    courseIdForm.courseIdInput = $("#courseIdInput");
    courseIdForm.klassIdInput = $("#klassIdInput");

    $(".seminar-nav").click({url:'/student/course/seminarList'},navClick);
    $(".team-nav").click({url:'/student/course/teamList'},navClick);
    $(".info-nav").click({url:'/student/course/info'},navClick);
    $(".grade-nav").click({url:'/student/course/grade'},navClick);

    $(".nav-item").click(function (ev) {
        ev.stopPropagation();//Prevent ev to be cached by upper dom
    });
});

function navClick(e) {
    var courseId = $(this).parents(".card-body").attr("data-courseId");
    var klassId = $(this).parents(".card-body").attr("data-klassId");
    sessionStorage.setItem("courseId" , courseId);
    sessionStorage.setItem("klassId" , klassId);

    courseIdForm.form.attr("action", e.data.url);
    courseIdForm.courseIdInput.val(courseId);
    courseIdForm.klassIdInput.val(klassId);
    courseIdForm.form.submit();
}