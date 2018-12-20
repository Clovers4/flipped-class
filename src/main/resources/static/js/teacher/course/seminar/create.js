var createSeminarForm;
var datetimepicker;
var roundIdInput;
var roundNum;
$(function () {
    datetimepicker = $(".datetimepicker");
    createSeminarForm = $("#createSeminarForm");
    roundIdInput = $("#roundId");
    roundNum = $("#roundNum");
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

    var courseId = sessionStorage.getItem("courseId");
    $("#courseId").val(courseId);
    $("#returnCourseId").val(courseId);

    $(".cancel").click(back);
    $("#backBtn").click(back);
    $(".confirm").click(function () {
        var verify = util.verifyWithAlert($(".form"));
        if(verify == null){
            $.ajax({
                type: "put",
                url: "/teacher/course/seminar",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(createSeminarForm.serializeObject()),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        back();
                    }
                },
                error: function () {
                    util.showAlert("danger", "创建失败，未知错误", 3);
                }
            })
        }else{
            verify.registerDanger();
        }
    });
    $(".round-num").click(function () {
        var item = $(this);
        roundNum.html(item.html());
        roundIdInput.val(item.attr("data-roundId"))
    });
    datetimepicker.bind("focus",function () {
        console.log($(this).parent());
        $(this).parent().addClass("on-date")
    });
    datetimepicker.bind("blur",function () {
        $(this).parent().removeClass("on-date")
    });
});

function back() {
    $("#returnForm").submit();
}