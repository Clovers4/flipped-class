var client = null;
var ksId;
var timer;
var startBtn;
var pauseBtn;
var questionCount;
var teams;

var progressState = 'PAUSE';
$(function () {
    timer = $("#timer");
    startBtn = $("#start");
    pauseBtn = $("#pause");
    questionCount = $("#questionCount");
    teams = $(".btn-team");
    timer.create();

    ksId = $("body").attr("data-ksId");
});

var socketAddr = "/seminar-socket";
var clientAddr = '/topic/client/';
var serverAddr = "/app/student/klassSeminar/";
var socket;
$(function () {
    serverAddr += ksId;
    clientAddr += ksId;
    connect();
    $("#raiseQuestion").click(function () {
        sendRequest("RaiseQuestionRequest", {});
    });
});

function connect(){
    socket = new SockJS(socketAddr);
    client = Stomp.over(socket);
    client.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        client.subscribe(clientAddr, function (m) {
            handleResponse(JSON.parse(m.body));
        });
    });
}

function disconnect(){
    if(client!=null){
        client.disconnect()
    }
    console.log("disconnect");
}

function sendRequest(type, request){
    var requestOnSend = {
        type:type,
        content:JSON.stringify(request)
    };
    client.send(serverAddr, {}, JSON.stringify(requestOnSend));
}

function handleResponse(response){
    eval("handle" + response.type +"(" + response.content + ")");
}

var SeminarStateResponse={state:{progressState:null,timeStamp:null}};
function handleSeminarStateResponse(content){
    if(content.state.progressState === progressState)
        return;
    switch (content.state.progressState) {
        case 'PAUSE':
            pauseAt(content.state.timeStamp);
            break;
        case 'PROCESSING':
            startAt(content.state.timeStamp);
            break;
    }
}
var RaiseQuestionResponse={questionNum:null};
function handleRaiseQuestionResponse(content) {
    setQuestionCount(content.questionNum);
}
var SwitchTeamResponse = {attendanceIndex:null,state:null};
function handleSwitchTeamResponse(content){
    var preTeam = teams.eq(content.attendanceIndex-1);
    preTeam.removeClass("active-team").addClass("passed-team");
    if(content.attendanceIndex < teams.length){
        var onTeam = teams.eq(content.attendanceIndex);
        onTeam.removeClass("preparatory-team").addClass("active-team");
    }
    setQuestionCount(0);
    pauseAt(content.state.timeStamp);
}
var PullQuestionResponse = {studentId:null, teamId:null, questionCount:null}
function handlePullQuestionResponse(content) {
    console.log(content);
}
function setQuestionCount(count) {
    questionCount.removeClass("static-question").addClass("active-question");
    setTimeout(function () {
        questionCount.removeClass("active-question").addClass("static-question");
    }, 1000);
    questionCount.text(count);
}
function pauseAt(timeStamp){
    timer.setTime(timeStamp);
    timer.pause();
    progressState = 'PAUSE';
}
function startAt(timeStamp){
    timer.setTime(timeStamp);
    timer.start();
    progressState = 'PROCESSING';
}

$(function () {
    $("#courseIdInput").val(sessionStorage.getItem("courseId"));
    $("#backBtn").click(function () {
        $("#returnForm").submit();
    });
});
