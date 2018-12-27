var seminarModalNavs;
var seminarModals;
var seminarForm = {};
var seminarIdOptionInput;
var courseIdForm;

$(function () {
    seminarModals = $(".seminar-modal");
    seminarModalNavs = $(seminarModals.find(".nav-link"));

    seminarForm.form = $("#seminarForm");
    seminarForm.seminarIdInput = $("#seminarIdInput");
    seminarForm.klassIdInput = $("#klassIdInput");
    seminarIdOptionInput = $("#seminarIdOptionInput");
    courseIdForm = $("#courseIdForm");

    $(".courseId").val(sessionStorage.getItem("courseId"));
    
    $(".klass-btn").click(function () {
        seminarForm.klassIdInput.val($(this).attr("data-klassId"));
        sessionStorage.setItem("klassId", seminarForm.klassIdInput.val());
        sessionStorage.setItem("seminarId", seminarForm.seminarIdInput.val());
        seminarForm.form.submit();
    });
    $(".option-btn").click(function () {
        $("#seminarOptionForm").submit();
    });
    $("#createButton").click(function () {
        courseIdForm.submit();
    });
    $(".round-setting").click(function () {
        $("#roundIdInput").val($(this).attr("data-roundId"));
        $("#roundSettingForm").submit();
    });
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
        seminarIdOptionInput.val($(this).attr("data-seminarId"));
    });
});