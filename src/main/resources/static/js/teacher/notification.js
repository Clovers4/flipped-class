var dropdownCard;
var appHandleForm = {};
$(function () {
    dropdownCard = $(".dropdown-card");
    appHandleForm.form = $("#appHandleForm");
    appHandleForm.appId = $("#appIdInput");
    appHandleForm.mainCourseId = $("#mainCourseIdInput");
    appHandleForm.subCourseId = $("#subCourseIdInput");
    appHandleForm.teamId = $("#teamIdInput");
    appHandleForm.appType = $("#appTypeInput");
    appHandleForm.operationType = $("#operationTypeInput");

    $(".accept").click(function () {
        var operationDiv = $(this).parents(".operation-div");
        appHandleForm.appId.val(operationDiv.attr("data-appId"));
        appHandleForm.appType.val(operationDiv.attr("data-appType"));
        appHandleForm.operationType.val(1);
        if(operationDiv.attr("data-appType") !== "2") {
            appHandleForm.mainCourseId.val(operationDiv.attr("data-mainCourseId"));
            appHandleForm.subCourseId.val(operationDiv.attr("data-subCourseId"));
        }else{
            //TODO:Team validation
        }
        ajaxSubmitForm();
    });

    $(".reject").click(function () {
        var operationDiv = $(this).parents(".operation-div");
        appHandleForm.appId.val(operationDiv.attr("data-appId"));
        appHandleForm.appType.val(operationDiv.attr("data-appType"));
        appHandleForm.operationType.val(0);
        if(operationDiv.attr("data-appType") !== "2") {
            appHandleForm.mainCourseId.val(operationDiv.attr("data-mainCourseId"));
            appHandleForm.subCourseId.val(operationDiv.attr("data-subCourseId"));
            appHandleForm.operationType.val(0);
        }else{
            //TODO:Team validation
        }
        ajaxSubmitForm();
    });




    dropdownCard.click(function (ev) {
        var offsetY = ev.pageY - $(this).offset().top;
        if(offsetY>0&&offsetY<165){
            $(this).find(".operation-div").slideToggle();
            dropdownCard.not($(this)).find(".operation-div").slideUp();
        }
    });
});

function ajaxSubmitForm(){
    console.log(appHandleForm.form.serializeObject());
    $.ajax({
        type: "post",
        url: "/teacher/notification/handle",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(appHandleForm.form.serializeObject()),
        success: function () {
            window.location="/teacher/notification";
        },
        error: function () {
            util.showAlert("danger", "操作失败", 3);
        }
    })
}