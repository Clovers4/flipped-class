var datetimepicker;
var createCourseForm;
var prePer;
var quePer;
var repPer;
$(function () {
    datetimepicker = $(".datetimepicker");
    createCourseForm = $("#createCourseForm");
    quePer = $("#quePer");
    prePer = $("#prePer");
    repPer = $("#repPer");
    datetimepicker.datetimepicker({
        format: 'YYYY-MM-D H:mm',
        icons: {
            time: "fa fa-clock-o",
            date: "fa fa-calendar",
            up: "fa fa-chevron-up",
            down: "fa fa-chevron-down",
            previous: 'fa fa-chevron-left',
            next: 'fa fa-chevron-right',
            today: 'fa fa-screenshot',
            clear: 'fa fa-trash',
            close: 'fa fa-remove'
        }
    });

    $(".confirm").click(function () {
        var verify = util.verifyWithAlert($(".form"));
        if (verify == null) {
            var sum = parseInt(prePer.val()) + parseInt(quePer.val()) + parseInt(repPer.val());
            if(sum!==100){
                util.showAlert("warning", "成绩权重和非一百", 3);
                dropdown(prePer.parents(".dropdown-card"));
                prePer.focus();
                return;
            }
            $.ajax({
                type: "put",
                url: "/teacher/course",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(createCourseForm.serializeObject()),
                success: function () {
                    window.location='/teacher/courseList';
                },
                error: function () {
                    util.showAlert("danger", "创建失败，未知错误", 3);
                }
            })
        } else {
            dropdown(verify.parents(".dropdown-card"));
            verify.focus();
        }
    });
    $("#gradeCard").click(function (ev) {
        var offsetY = ev.pageY - $(this).offset().top;
        if (offsetY > 0 && offsetY < 50) {
            toggleDrop($(this));
        }
    });
    $("#groupCard").click(function (ev) {
        var offsetY = ev.pageY - $(this).offset().top;
        if (offsetY > 0 && offsetY < 50) {
            toggleDrop($(this));
        }
    });
    datetimepicker.bind("focus", function () {
        $(this).parent().addClass("on-date")
    });
    datetimepicker.bind("blur", function () {
        $(this).parent().removeClass("on-date")
    });
});

function toggleDrop(card) {
    var content = $(card.find(".body-content"));
    var triangle = $(card.find(".triangle"));
    content.slideToggle();
    if (triangle.hasClass("rightward")) {
        triangle.attr("class", "triangle downward");
    } else {
        triangle.attr("class", "triangle rightward");
    }
}

function dropdown(card) {
    if (card !== undefined) {
        $(card.find(".body-content")).slideDown();
        $(card.find(".triangle")).attr("class", "triangle downward");
    }
}