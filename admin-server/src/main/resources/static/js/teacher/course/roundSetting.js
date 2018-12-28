var roundSettingForm;
$(function () {
    roundSettingForm = $("#roundSettingForm");
    var courseId = sessionStorage.getItem("courseId");
    $("#courseId").val(courseId);
    $("#returnCourseId").val(courseId);

    $(".cancel").click(back);
    $("#backBtn").click(back);


    $(".dropdown-card").click(function (ev) {
        var offsetY = ev.pageY - $(this).offset().top;
        if(offsetY>0&&offsetY<50){
            toggleDrop($(this));
        }
    });
    $(".confirm").click(function () {
        var formData = roundSettingForm.serializeObject();
        console.log(formData);
        $.ajax({
            type: "post",
            url: "/teacher/course/round/setting/update",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(roundSettingForm.serializeObject()),
            success: function (result, status, xhr) {
                if (xhr.status === 200) {
                    back();
                }
            },
            error: function () {
                util.showAlert("danger", "更新失败，未知错误", 3);
            }
        })
    });
});

function back() {
    $("#returnForm").submit();
}


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