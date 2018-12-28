var client = null, ksId, timer, startBtn, pauseBtn, stopBtn, scoreInput,
    giveScoreBtn, patchScoreBtn, switchTeamBtn, endPreBtn, pullQuestionBtn;

var questionCount, teams, questions, teamName, teamOperation;

var preTimeStamp;

var tabContent, curActive, curOnfocus, curAttendanceIdx;
$(function () {
    timer = $("#timer");
    startBtn = $("#start");
    pauseBtn = $("#pause");
    stopBtn = $("#stopQuestion");
    scoreInput = $("#score");
    giveScoreBtn = $("#giveScore");
    patchScoreBtn = $("#patchScore");
    switchTeamBtn = $("#switchTeam");
    pullQuestionBtn = $("#pullQuestion");
    endPreBtn = $("#endPre");
    questionCount = $("#questionCount");
    teams = $(".btn-team:not(.question)");
    questions = $(".btn-team.question");
    teamName = $("#teamName");
    teamOperation = $("#teamOperation");
    tabContent = $("#tabContent");
    curActive = curOnfocus = $(".active-team");
    curAttendanceIdx = curActive.attr("data-idx");
    timer.create();

    teams.click(function () {
        var team = $(this);
       /* TODO:可能有问题
        if (parseInt(team.attr("data-idx")) > curAttendanceIdx) {
            return;
        }*/
        curAttendanceIdx=parseInt(team.attr("data-idx"));
        changeFocus(team);
    });
    questions.click(function () {
        changeFocus($(this));
    });
    ksId = $("body").attr("data-ksId");
    changeScore(parseInt(curActive.attr("data-score")));
});

function changeFocus(target, none) {
    if (curOnfocus !== undefined) {
        curOnfocus.removeClass("cur-focus");
    }
    curOnfocus = target;
    if (none === undefined) {
        curOnfocus.addClass("cur-focus");
    }

    var tab = curOnfocus.attr("data-tab");
    if (tab !== undefined) {
        tabContent.children(".tab-pane").hide();
        $(tab).show();
    }
    changeScore(parseInt(curOnfocus.attr("data-score")));
}

function changeActive(target) {
    if (curActive !== undefined) {
        curActive.removeClass("active-team");
    }
    curActive = target;
    curActive.addClass("active-team");
    changeFocus(curActive, 0);
}

var socketAddr = "/seminar-socket";
var clientAddr = '/topic/client/';
var serverAddr = "/app/teacher/klassSeminar/";
var socket;
$(function () {
    serverAddr += ksId;
    clientAddr += ksId;
    connect();
    startBtn.click(function () {
        timer.start();
        startBtn.hide();
        pauseBtn.show();
        teamOperation.text("进行中...");
        sendRequest("SeminarStateRequest", {request: "START", timeStamp: timer.getTime()});
    });
    pauseBtn.click(function () {
        timer.pause();
        pauseBtn.hide();
        startBtn.show();
        teamOperation.text("暂停中...");
        sendRequest("SeminarStateRequest", {request: "PAUSE", timeStamp: timer.getTime()});
    });
    switchTeamBtn.click(function () {
        sendRequest("SwitchTeamRequest", {});
    });
    pullQuestionBtn.click(function () {
        if (parseInt(questionCount.text()) <= 0) {
            util.showAlert("warning", "当前提问数为零", 3);
            return;
        }
        pullQuestionBtn.hide();
        switchTeamBtn.hide();
        sendRequest("PullQuestionRequest", {});
    });
    $([giveScoreBtn, patchScoreBtn]).each(function () {
        this.click(function () {
            var score = parseFloat(scoreInput.val());
            if (isNaN(score)) {
                util.showAlert("warning", "请输入分数", 3);
                return;
            }
            curOnfocus.attr("data-score", score);
            if (curOnfocus.hasClass("question")) {
                sendRequest("ScoreRequest", {
                    score: score,
                    type: "Question",
                    attendanceIdx: curAttendanceIdx,
                    questionIdx: curOnfocus.attr("data-idx")
                });
            } else {
                sendRequest("ScoreRequest", {
                    score: score,
                    type: "Attendance",
                    attendanceIdx: curAttendanceIdx
                });
            }
        });
    });
    stopBtn.click(function () {
        pullQuestionBtn.show();
        switchTeamBtn.show();
        sendRequest("EndQuestionRequest", {});
    });
    endPreBtn.click(function () {
        sendRequest("EndSeminarRequest", {});
    })
});

function connect() {
    socket = new SockJS(socketAddr);
    client = Stomp.over(socket);
    client.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        //TODO:DELETE sendRequest("SeminarStateRequest", {request: "PAUSE", timeStamp: timer.getTime()});
        client.subscribe(clientAddr, function (m) {
            handleResponse(JSON.parse(m.body));
        });
    });
}

function sendRequest(type, request) {
    var requestOnSend = {
        type: type,
        content: JSON.stringify(request)
    };
    client.send(serverAddr, {}, JSON.stringify(requestOnSend));
}

function handleResponse(response) {
    eval("handle" + response.type + "(" + response.content + ")");
}

var SeminarStateResponse = {state: {progressState: null, timeStamp: null}};

function handleSeminarStateResponse(content) {
}

var RaiseQuestionResponse = {questionNum: null};

function handleRaiseQuestionResponse(content) {
    setQuestionCount(content.questionNum);
}

var SwitchTeamResponse = {attendanceIndex: null, state: null};

function handleSwitchTeamResponse(content) {
    curActive.removeClass("active-team").addClass("passed-team");
    if (content.attendanceIndex < teams.length - 1) {
        var onTeam = teams.eq(content.attendanceIndex);
        curAttendanceIdx = content.attendanceIndex;
        onTeam.removeClass("preparatory-team");
        teamName.text(onTeam.attr("data-teamName"));
        changeActive(onTeam);
        if (content.attendanceIndex === teams.length - 2) {
            $("#switchTeam").hide();
            $("#endPre").show();
        }
    } else {

    }
    setQuestionCount(0);
    pauseAt(content.state.timeStamp);
}

var PullQuestionResponse = {studentNum: null, teamSerial: null, teamName: null, questionCount: null};

function handlePullQuestionResponse(content) {
    preTimeStamp = timer.getTime();

    teamName.text(content.teamName);
    questionCount.text(content.questionCount);
    startAt(0);
    stopBtn.show();
    addQuestion(content.teamSerial);
}

function handleEndQuestionResponse(content) {
    stopBtn.hide();
    pauseAt(preTimeStamp);
    changeActive(teams.eq(curAttendanceIdx));
}

function handleScoreResponse(content) {
    alert("打分成功");
}

function handleEndSeminarResponse() {
    window.location.reload();
}

function setQuestionCount(count) {
    questionCount.removeClass("static-question").addClass("active-question");
    setTimeout(function () {
        questionCount.removeClass("active-question").addClass("static-question");
    }, 1000);
    questionCount.text(count);
}

function pauseAt(timeStamp) {
    timer.setTime(timeStamp);
    timer.pause();
    pauseBtn.hide();
    startBtn.show();
    teamOperation.text("暂停中...");
}

function startAt(timeStamp) {
    timer.setTime(timeStamp);
    timer.start();
    startBtn.hide();
    pauseBtn.show();
    teamOperation.text("进行中...");
}

function addQuestion(teamSerial) {
    var tab = $(curActive.attr("data-tab"));
    var btn = $("<button class='btn btn-fab btn-round btn-team question'>" + teamSerial + "</button>");
    btn.attr("data-idx", tab.children("button").length);
    btn.attr("data-score", -1);
    tab.append(btn);
    btn.click(function () {
        changeFocus($(this));
    });
    changeActive(btn);
}

function changeScore(score) {
    if (score !== -1) {
        scoreInput.val(score);
        giveScoreBtn.hide();
        patchScoreBtn.show();
    } else {
        scoreInput.val("");
        giveScoreBtn.show();
        patchScoreBtn.hide();
    }
}

$(function () {
    $("#seminarIdInput").val(sessionStorage.getItem("seminarId"));
    $("#klassIdInput").val(sessionStorage.getItem("klassId"));
    $("#backBtn").click(function () {
        $("#seminarForm").submit();
    });
});
