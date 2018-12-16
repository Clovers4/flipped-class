var klassModal;
$(function () {
    klassModal = $("#klassModal");

    klassModal.on("show.bs.modal",function (event) {
        var item = $(event.relatedTarget);
        klassModal.attr("data-klassID", item.attr("data-klassID"));
        $(klassModal.find(".modal-title")).html($(item.find(".body-title")).html());
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
});