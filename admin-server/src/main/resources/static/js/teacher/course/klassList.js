var klassModal;
var formBody;
var operationBody;
var file;
var uploadName;
var studentFilesForm;
$(function () {
    klassModal = $("#klassModal");
    formBody = $("#formBody");
    operationBody = $("#operationBody");
    file = $("#fileInput");
    uploadName = $("#uploadName");
    studentFilesForm = $("#studentFiles");

    klassModal.on("show.bs.modal",function (event) {
        var item = $(event.relatedTarget);
        klassModal.attr("data-klassID", item.attr("data-klassID"));
        $(klassModal.find("#klassIdInput")).val(item.attr("data-klassID"));
        $(klassModal.find(".modal-title")).html($(item.find(".body-title")).html());
    });
    klassModal.on("hidden.bs.modal", function () {
        file.val("");
        uploadName.html("上传文件");
        operationBody.show();
        formBody.hide();
    });
    $("#import").click(function(){
        operationBody.hide();
        formBody.slideDown();
    });
    $("#upload").click(function () {
        file.trigger("click");
    });
    $("#cancelUpload").click(function () {
        formBody.hide();
        operationBody.slideDown();
    });
    file.on("input propertychange", function () {
        var files = file.get(0).files;
        if(files.length > 0) {
            uploadName.html(files[0].name);
        }
    });

    $("#deleteKlass").click(function () {
        $.ajax({
            type:"delete",
            url:"/teacher/course/klass/" + klassModal.attr("data-klassID"),
            success:function () {
                location.reload();
            },
            error:function () {
                util.showAlert("danger", "删除失败，请重试", 3);
            }
        })
    });
    $("#confirmUpload").click(function () {
        var verify = util.verifyWithAlert(studentFilesForm);
        if(verify == null){
            util.showLoading();
            $.ajax({
                type:"post",
                url:"/teacher/course/klass/insertStudents",
                contentType: false,
                processData: false,
                data: new FormData(studentFilesForm.get(0)),
                success:function(){
                    util.hideLoading();
                    console.log("success");
                    klassModal.modal("hide");
                    util.showAlert("success", "导入成功", 3);
                },
                error:function () {
                    util.hideLoading();
                    console.log("fail");
                    util.showAlert("danger", "插入失败，请重试", 3);
                }
            })
        }
    });

});