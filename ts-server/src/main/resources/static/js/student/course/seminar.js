var seminarModalNavs;
var seminarModals;
var seminarForm = {};

$(function () {
    seminarModals = $(".seminar-modal");
    seminarModalNavs = $(seminarModals.find(".seminar-link"));

    seminarForm.form = $("#seminarForm");
    seminarForm.seminarIdInput = $("#seminarIdInput");
    seminarForm.klassIdInput = $("#klassIdInput");

    seminarForm.klassIdInput.val(sessionStorage.getItem("klassId"));

    seminarModals.on("hidden.bs.modal", function () {
        var navCol = $(this).find(".nav-col");
        var tabCol = navCol.siblings(".tab-col");
        if (navCol.hasClass("col-4")) {
            navCol.removeClass("col-4");
            navCol.addClass("col-12");
            tabCol.removeClass("show");
        }
        $(navCol.find(".active")).removeClass("active");
    });
    seminarModalNavs.click(function () {
        var navCol = $(this).parent().parent().parent();
        var tabCol = navCol.siblings(".tab-col");
        if (navCol.hasClass("col-12")) {
            navCol.removeClass("col-12");
            navCol.addClass("col-4");
            setTimeout(function () {
                if (navCol.hasClass("col-4")) {
                    tabCol.addClass("show");
                }
            }, 600);
        } else if (navCol.hasClass("col-4") && $(this).hasClass("active")) {
            navCol.removeClass("col-4");
            navCol.addClass("col-12");
            tabCol.removeClass("show");
        }
        seminarForm.seminarIdInput.val($(this).attr("data-seminarId"));
        console.log(seminarForm.form.serializeObject());
    });

    $(".seminarInfo").click({url:'/student/course/seminar/info'}, moduleClick);
    $(".enroll").click({url:'/student/course/seminar/enrollList'}, moduleClick);
    $(".grade").click({url:'/student/course/seminar/grade'}, moduleClick);
    $(".report").click({url:'/student/course/seminar/report'}, moduleClick);
    $(".progressing").click({url:'/student/course/seminar/progressing'}, moduleClick);
});


function moduleClick(e) {
    seminarForm.form.attr("action", e.data.url);
    seminarForm.form.submit();
}