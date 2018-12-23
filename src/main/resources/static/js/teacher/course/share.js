var dropdownCard;
$(function () {
    $("#courseIdInput").val(sessionStorage.getItem("courseId"));

    dropdownCard = $(".dropdown-card");
    dropdownCard.click(function (ev) {
        var offsetY = ev.pageY - $(this).offset().top;
        if(offsetY>0&&offsetY<135){
            $(this).find(".operation-div").slideToggle();
            dropdownCard.not($(this)).find(".operation-div").slideUp();
        }
    });

    $("#addShare").click(function () {
        $("#createShareForm").submit();
    });
});