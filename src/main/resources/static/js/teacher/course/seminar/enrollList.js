

$(function () {
    $("#seminarIdInput").val(sessionStorage.getItem("seminarId"));
    $("#klassIdInput").val(sessionStorage.getItem("klassId"));
    $("#backBtn").click(function () {
        $("#seminarForm").submit();
    })
});