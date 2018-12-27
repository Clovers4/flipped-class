var parentFrame;
var singleChecks;
var modifyModal;
var resetPwdModal;
var deleteItemModal;
$(function () {
    parentFrame = parent.$(window.parent.document);
    singleChecks = $(".single .form-check-sign");
    modifyModal = {
        modal : $("#modifyModal"),
        id : $("#id"),
        teacherNum : $("#teacherNum"),
        name : $("#teacherName"),
        email : $("#email")
    };
    resetPwdModal = $("#resetPwdModal");
    deleteItemModal = $("#deleteItemModal");

    singleChecks.click(function () {
        //A little messed...
        // 确认自己为checked，并修改所在列的显示效果
        var item = $(this).parent().parent().parent().parent();
        var check = $(this);
        if(check.hasClass("checked")){
            item.removeClass("chosen");
            check.removeClass("checked");
        }else{
            item.addClass("chosen");
            check.addClass("checked");
        }
    });

    modifyModal.modal.on("show.bs.modal",function (event) {
        var item = $(event.relatedTarget).parent().parent();
        modifyModal.id.val(item.attr("data-id"));
        modifyModal.name.val(item.find(".name").html());
        modifyModal.name.parent().addClass("is-filled");
        modifyModal.teacherNum.val(item.find(".teacherNum").html());
        modifyModal.teacherNum.parent().addClass("is-filled");
        modifyModal.email.val(item.find(".email").html());
        modifyModal.email.parent().addClass("is-filled");
    });
    resetPwdModal.on("show.bs.modal",function (event) {
        $(this).find(".confirm").attr("data-gist", $(event.relatedTarget).attr("data-gist"));
    });
    deleteItemModal.on("show.bs.modal",function (event) {
        $(this).find(".confirm").attr("data-gist", $(event.relatedTarget).attr("data-gist"));
    });
    modifyModal.modal.find(".confirm").click(function () {
        var btn = $(this);
        var form = modifyModal.modal.find(".form");
        if(util.verifyWithPop(form)) {
            $.ajax({
                type: "patch",
                url: "/admin/teacher",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(form.serializeObject()),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        modifyModal.modal.modal("hide");
                        parentFrame.trigger("showNotification", ["修改成功", "success"]);
                        location.reload();
                    }
                },
                error: function () {
                    if (xhr.status === 409) {
                        btn.delayedPop("修改失败，工号不存在", 3);
                    }else{
                        btn.delayedPop("修改失败，未知错误", 3);
                    }
                }
            });
        }
    });
    resetPwdModal.find(".confirm").click(function () {
        $.ajax({
            type:"patch",
            url:"/admin/teacher/" + $(this).attr("data-gist") + "/resetPwd",
            success:function () {
                resetPwdModal.modal("hide");
                parentFrame.trigger("showNotification", ["重置密码成功", "success"]);
                location.reload();
            },
            error:function () {
                $(resetPwdModal.find(".confirm")).delayedPop("重置密码失败", 3);
            }
        });
    });
    deleteItemModal.find(".confirm").click(function () {
        $.ajax({
            type:"delete",
            url:"/admin/teacher/" + $(this).attr("data-gist"),
            success:function () {
                deleteItemModal.modal("hide");
                parentFrame.trigger("showNotification", ["删除成功", "success"]);
                location.reload();
            },
            error:function () {
                $(deleteItemModal.find(".confirm")).delayedPop("删除失败", 3);
            }
        });
    });

    var table = $(".table");
    if(table.attr("data-new") === "true") {
        parentFrame.trigger("pageReset", [table.attr("data-pages"), 1]);
    }
    var page = table.attr("data-page");
    if(page !== parent.$("#pagination").find(".active>a").html()){
        parentFrame.trigger("pageReset", [table.attr("data-pages"), page]);
    }

});
