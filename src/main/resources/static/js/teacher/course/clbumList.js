var clbumModal;
$(function () {
    clbumModal = $("#clbumModal");

    clbumModal.on("show.bs.modal",function (event) {
        var item = $(event.relatedTarget);
        clbumModal.attr("data-clbumID", item.attr("data-clbumID"));
        $(clbumModal.find(".modal-title")).html($(item.find(".body-title")).html());
    });
    $("#deleteClbum").click(function () {
        $.ajax({
            type:"delete",
            url:"/teacher/course/clbum/" + clbumModal.attr("data-clbumID"),
            success:function () {
                location.reload();
            },
            error:function () {
                util.showAlert("danger", "删除失败，请重试", 3);
            }
        })
    });
});