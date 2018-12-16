var client = null;
var msgList;
var socketAddr = "/seminar-socket";
var clientAddr = '/topic/client/';
var serverAddr = "/app/teacher/klassSeminar/";
var ksId;
$(function () {
    msgList = $("#msgList");
    ksId = $("#main").attr("data-ksId");
    serverAddr += ksId;
    clientAddr += ksId;
    $("#connect").click(function () {
        var socket = new SockJS(socketAddr);
        client = Stomp.over(socket);
        client.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            client.subscribe(clientAddr, function (m) {
                var json = JSON.parse(m.body);
                appendMessage(json["user"],json["message"]);
            });
        });
    });
    $("#disconnect").click(function () {
        if(client!=null){
            client.connect()
        }
        console.log("disconnect");
    });
    $("#send").click(function () {
        var toServer = $("#toServer");
        var msg = JSON.stringify(toServer.val());
        client.send(serverAddr, {}, msg.substring(1,msg.length - 1));
        toServer.val("");
    });
});

function appendMessage(user, msg) {
    var dom = $("<h6 style='margin: 0 20px 0 0;'>"+user+":</h6><h5 style='margin: 0;text-wrap: unrestricted '>" + msg + "</h5>");
    var line = $("<div style='display: flex;align-items: center;justify-content: flex-start'></div>");
    line.append(dom);
    console.log(line);
    msgList.append(line);
    msgList.append($("<hr>"));
}


$(function () {
    $("#seminarIdInput").val(sessionStorage.getItem("seminarId"));
    $("#klassIdInput").val(sessionStorage.getItem("klassId"));
    $("#backBtn").click(function () {
        $("#seminarForm").submit();
    })
});


