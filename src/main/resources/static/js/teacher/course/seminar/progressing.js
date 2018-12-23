var client = null;
var ksId;
var timer;
var startBtn;
var pauseBtn;
var questionCount;
var teams;
var teamName;
var teamOperation;
$(function () {
    timer = $("#timer");
    startBtn = $("#start");
    pauseBtn = $("#pause");
    questionCount = $("#questionCount");
    teams = $(".btn-team");
    teamName = $("#teamName");
    teamOperation = $("#teamOperation");
    timer.create();


    ksId = $("body").attr("data-ksId");
});

var socketAddr = "/seminar-socket";
var clientAddr = '/topic/client/';
var serverAddr = "/app/teacher/klassSeminar/";
var socket;
$(function () {
    serverAddr += ksId;
    clientAddr += ksId;
    startBtn.click(function () {
        timer.start();
        startBtn.hide();
        pauseBtn.show();
        teamOperation.text("进行中...");
        sendRequest("SeminarStateRequest", {request:"START",timeStamp:timer.getTime()});
    });
    pauseBtn.click(function () {
        timer.pause();
        pauseBtn.hide();
        startBtn.show();
        teamOperation.text("暂停中...");
        sendRequest("SeminarStateRequest", {request:"PAUSE",timeStamp:timer.getTime()});
    });
    $("#switchTeam").click(function () {
        sendRequest("SwitchTeamRequest", {});
    });
    $("#pullQuestion").click(function () {
        sendRequest("PullQuestionRequest", {});
    });
    connect();
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
function handleSeminarStateResponse(content){}
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
        teamName.text(onTeam.attr("data-teamName"));
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
    pauseBtn.hide();
    startBtn.show();
    progressState = 'PAUSE';
    teamOperation.text("暂停中...");
}
function startAt(timeStamp){
    timer.setTime(timeStamp);
    timer.start();
    startBtn.hide();
    pauseBtn.show();
    progressState = 'PROCESSING';
    teamOperation.text("进行中...");
}
$(function () {
    $("#seminarIdInput").val(sessionStorage.getItem("seminarId"));
    $("#klassIdInput").val(sessionStorage.getItem("klassId"));
    $("#backBtn").click(function () {
        $("#seminarForm").submit();
    });
});
