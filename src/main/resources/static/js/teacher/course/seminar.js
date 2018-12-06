var seminarModalNavs;
var seminarModals;
var clbumButtons;
var seminarForm = {};

$(function () {
    seminarModals = $(".seminar-modal");
    seminarModalNavs = $(seminarModals.find(".nav-link"));
    clbumButtons = $(".clbum-btn");
    seminarForm.form = $("#seminarForm");
    seminarForm.seminarIdInput = $("#seminarIdInput");
    seminarForm.clbumIdInput = $("#clbumIdInput");

    clbumButtons.click(function () {
        //Logy
        seminarForm.clbumIdInput.val($(this).attr("data-clbumId"));
        seminarForm.form.submit();
    });
    seminarModals.on("hidden.bs.modal", function () {
        var navCol = $(this).find(".nav-col");
        var tabCol = navCol.siblings(".tab-col");
        //Style
        if(navCol.hasClass("col-4")){
            navCol.removeClass("col-4");
            navCol.addClass("col-12");
            tabCol.removeClass("show");
        }
        $(navCol.find(".active")).removeClass("active");
    });
    seminarModalNavs.click(function () {
        var navCol = $(this).parent().parent().parent();
        var tabCol = navCol.siblings(".tab-col");
        //Style
        if(navCol.hasClass("col-12")){
            navCol.removeClass("col-12");
            navCol.addClass("col-4");
            setTimeout(function () {
                if(navCol.hasClass("col-4")) {
                    tabCol.addClass("show");
                }
            }, 600);
        }else if(navCol.hasClass("col-4") && $(this).hasClass("active")){
            navCol.removeClass("col-4");
            navCol.addClass("col-12");
            tabCol.removeClass("show");
        }
        //Logy
        seminarForm.seminarIdInput.val($(this).attr("data-seminarId"));
    });

});