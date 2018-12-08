var createClbumForm;
$(function () {
    createClbumForm = $("#createClbumForm");
    $(".confirm").click(function () {
        var verify = util.verifyWithAlert($(".form"));
        if(verify == null){
            console.log(createClbumForm.serialize());
            $.ajax({
                type: "put",
                url: "/teacher/course/clbum",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(createClbumForm.serializeObject()),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        window.location = "/teacher/course/clbumList";
                    }
                },
                error: function (xhr) {//xhr, textStatus, errorThrown
                    if (xhr.status === 400) {
                        util.showAlert("danger", "创建失败，班级相同", 3);
                    }else{
                        util.showAlert("danger", "创建失败，未知错误", 3);
                    }
                }
            })
        }else{
            dropdown(verify.parents(".dropdown-card"));
            verify.focus();
        }
    });
    $("#introCard").click(function (ev) {
        var offsetY = ev.pageY - $(this).offset().top;
        if(offsetY>0&&offsetY<50){
            toggleDrop($(this));
        }
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