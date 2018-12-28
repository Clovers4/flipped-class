var updateSeminarForm;
var datetimepicker;
var roundIdInput;
var roundNum;
$(function () {
    datetimepicker = $(".datetimepicker");
    updateSeminarForm = $("#updateSeminarForm");
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

    $("#returnCourseId").val(sessionStorage.getItem("courseId"));

    $(".cancel").click(back);
    $("#backBtn").click(back);
    $(".confirm").click(function () {
        var verify = util.verifyWithAlert($(".form"));
        if(verify == null){
            $.ajax({
                type: "patch",
                url: "/teacher/course/seminar",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(updateSeminarForm.serializeObject()),
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
    $("#deleteBtn").click(function () {
        $.ajax({
            type: "delete",
            url: "/teacher/course/seminar/" + $("#id").val(),
            success: function (result, status, xhr) {
                if (xhr.status === 200) {
                    back();
                }
            },
            error: function () {
                util.showAlert("danger", " 删除失败，未知错误", 3);
            }
        })
    });
    datetimepicker.bind("focus",function () {
        $(this).parent().addClass("on-date")
    });
    datetimepicker.bind("blur",function () {
        $(this).parent().removeClass("on-date")
    });
});

function back() {
    $("#returnForm").submit();
}