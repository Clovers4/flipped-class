var courseModal;
var courseIdForm = {};
$(function () {
    courseModal = $("#courseModal");
    courseIdForm.form = $("#courseIdForm");
    courseIdForm.courseIdInput = $("#courseIdInput");

    courseModal.on("show.bs.modal",function (event) {
        var item = $(event.relatedTarget);
        courseModal.attr("data-courseID", item.attr("data-courseID"));
        $(courseModal.find(".modal-title")).html($(item.find(".body-title")).html());
    });
    $(".clbum-nav").click({url:'/teacher/course/clbumList'},courseCardNavClick);
    $(".team-nav").click({url:'/teacher/course/teamList'},courseCardNavClick);
    $(".seminar-nav").click({url:'/teacher/course/seminarList'},courseCardNavClick);
    $("#gradeNav");
    $("#infoNav").click({url:'/teacher/course/info'},modalCardNavClick);
    $("#optionNav");
    $("#shareNav");

    $(".nav-item").click(function (ev) {
        ev.stopPropagation();//Prevent ev to be cached by upper dom
    });
});

function courseCardNavClick(e) {
    postCourseId(e.data.url,$(this).parents(".card-body").attr("data-courseId"));
}
function modalCardNavClick(e) {
    postCourseId(e.data.url,courseModal.attr("data-courseID"));
}
function postCourseId(url,id) {
    courseIdForm.form.attr("action", url);
    courseIdForm.courseIdInput.val(id);
    courseIdForm.form.submit();
}