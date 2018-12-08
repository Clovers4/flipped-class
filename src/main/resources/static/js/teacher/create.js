var datetimepicker;
var createCourseForm;
$(function () {
    datetimepicker = $(".datetimepicker");
    createCourseForm = $("#createCourseForm");
    datetimepicker.datetimepicker({
        format: 'L'
    });

    $(".confirm").click(function () {
        var verify = util.verifyWithAlert($(".form"));
        if(verify == null){
            console.log(createCourseForm.serialize());
        }else{
            dropdown(verify.parents(".dropdown-card"));
            verify.focus();
        }
    });
    $("#gradeCard").click(function (ev) {
        var offsetY = ev.pageY - $(this).offset().top;
        if(offsetY>0&&offsetY<50){
            toggleDrop($(this));
        }
    });
    $("#groupCard").click(function (ev) {
        var offsetY = ev.pageY - $(this).offset().top;
        if(offsetY>0&&offsetY<50){
            toggleDrop($(this));
        }
    });
    datetimepicker.bind("focus",function () {
        console.log($(this).parent());
        $(this).parent().addClass("on-date")
    });
    datetimepicker.bind("blur",function () {
        $(this).parent().removeClass("on-date")
    });
});

function toggleDrop(card) {
    var content = $(card.find(".body-content"));
    var triangle = $(card.find(".triangle"));
    content.slideToggle();
    if(triangle.hasClass("rightward")){
        triangle.attr("class", "triangle downward");
    }else{
        triangle.attr("class", "triangle rightward");
    }
}

function dropdown(card) {
    if(card !== undefined) {
        $(card.find(".body-content")).slideDown();
        $(card.find(".triangle")).attr("class", "triangle downward");
    }
}