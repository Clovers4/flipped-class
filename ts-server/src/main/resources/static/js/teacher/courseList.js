var courseIdForm = {};
$(function () {
    courseIdForm.form = $("#courseIdForm");
    courseIdForm.courseIdInput = $("#courseIdInput");

    $(".seminar-nav").click({url:'/teacher/course/seminarList'},navClickWithLoading);
    $(".klass-nav").click({url:'/teacher/course/klassList'},navClickWithLoading);
    $(".team-nav").click({url:'/teacher/course/teamList'},navClickWithLoading);
    $(".info-nav").click({url:'/teacher/course/info'},navClickWithLoading);
    $(".grade-nav").click({url:'/teacher/course/grade'},navClickWithLoading);
    $(".share-nav").click({url:'/teacher/course/share'},navClickWithLoading);

    $(".nav-item").click(function (ev) {
        ev.stopPropagation();//Prevent ev to be cached by upper dom
    });
    $("#createCourse").click(function () {
        window.location = "/teacher/course/create";
    });
});

function navClickWithLoading(e) {
    util.showLoading();
    var courseId = $(this).parents(".card-body").attr("data-courseId");
    sessionStorage.setItem("courseId" , courseId);

    courseIdForm.form.attr("action", e.data.url);
    courseIdForm.courseIdInput.val(courseId);
    courseIdForm.form.submit();
}