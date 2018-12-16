    var courseIdForm = {};
$(function () {
    courseIdForm.form = $("#courseIdForm");
    courseIdForm.courseIdInput = $("#courseIdInput");

    $("#addRound").click(function () {
        window.location='/teacher/course/create';
    });
    $(".klass-nav").click({url:'/teacher/course/klassList'},navClick);
    $(".team-nav").click({url:'/teacher/course/teamList'},navClick);
    $(".seminar-nav").click({url:'/teacher/course/seminarList'},navClick);
    $(".grade-nav").click({url:'/teacher/course/grade'},navClick);
    $(".info-nav").click({url:'/teacher/course/info'},navClick);
    $(".share-nav").click({url:'/teacher/course/share'},navClick);

    $(".nav-item").click(function (ev) {
        ev.stopPropagation();//Prevent ev to be cached by upper dom
    });
});

function navClick(e) {
    var courseId = $(this).parents(".card-body").attr("data-courseId");
    sessionStorage.setItem("courseId" , courseId);

    courseIdForm.form.attr("action", e.data.url);
    courseIdForm.courseIdInput.val(courseId);
    courseIdForm.form.submit();
}