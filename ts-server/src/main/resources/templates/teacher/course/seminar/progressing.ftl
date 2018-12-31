<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="apple-touch-icon" sizes="76x76" href="/static/imgs/apple-icon.png">
    <link rel="icon" type="image/png" href="/static/imgs/favicon.png">
    <link rel="stylesheet" href="http://cdn.staticfile.org/material-kit/2.0.4/css/material-kit.min.css">
    <link rel="stylesheet" href="/static/css/user.css">
    <link rel="stylesheet" href="https://fonts.lug.ustc.edu.cn/icon?family=Material+Icons">
    <link rel="stylesheet" href="/static/css/countup.css">
    <script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.js"></script>
    <script src="http://cdn.staticfile.org/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="http://cdn.staticfile.org/jquery/3.3.1/jquery.js"></script>
    <script src="/static/lib/countup.js"></script>
    <script src="/static/js/util.js"></script>
    <script src="/static/js/teacher/course/seminar/progressing.js"></script>
    <title>进行讨论课</title>
    <style>
        input::-ms-input-placeholder {
            text-align: center;
        }

        input::-webkit-input-placeholder {
            text-align: center;
        }

        #score {
            text-align: center;
            width: 100px;
        }
    </style>
</head>
<body class="card-page sidebar-collapse" data-ksId="${ksId}">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" id="backBtn">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">讨论课</div>
            <button class="navbar-toggler" type="button" data-toggle="collapse">
                <!--All are needed here. Please do not remove anything.-->
                <span class="sr-only">Toggle navigation</span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" onclick="window.location='/teacher/index'">
                        <i class="material-icons">person</i>个人首页
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" onclick="window.location='/teacher/notification'">
                        <i class="material-icons">notifications</i>
                        待办
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<#if hasEnd>
    <div class="main main-raised no-footer">
        <div class="empty-tag">
            <div class="info">
                <div class="icon icon-rose flex-center">
                    <i class="material-icons color-grey">portable_wifi_off</i>
                </div>
                <h4 class="info-title">该讨论课已经结束</h4>
            </div>
        </div>
    </div>
    <#else >
        <div class="left-side side-raised">
            <#list monitor.enrollList as enroll>
                <#if enroll??>
                    <button data-score="${monitor.preScoreMap[enroll.id?c]}" data-idx="${enroll?index}"
                            data-tab="#tab${enroll.id}" data-teamName="${enroll.team.teamName}"
                            class="btn btn-fab btn-round btn-team <#if (enroll?index < monitor.onPreAttendanceIndex)>passed-team<#elseif (enroll?index = monitor.onPreAttendanceIndex)>active-team<#else>preparatory-team</#if>">
                        ${enroll.team.serial}
                    </button>
                </#if>
            </#list>
        </div>
        <div class="right-upper-side side-raised">
            <button id="questionCount" class="btn btn-fab btn-round btn-team static-question" disabled>
                ${monitor.raisedQuestionsCount}
            </button>
        </div>
        <div class="right-downer-side side-raised">
            <div id="tabContent">
                <#list monitor.enrollList as enroll>
                    <#if enroll??>
                        <div data-idx="${enroll?index}" class="tab-pane" id="tab${enroll.id}"
                             <#if (enroll?index != monitor.onPreAttendanceIndex)>style="display: none" </#if>>
                            <#list monitor.askedQuestion[enroll.id?c] as question>
                                <button data-idx="${question?index}" data-score="${question.score}"
                                        class="btn btn-fab btn-round btn-team question">${question.team.serial}</button>
                            </#list>
                        </div>
                    </#if>
                </#list>
            </div>
        </div>
        <div class="flex-center main-area">
            <div class="container">
                <div class="row">
                    <div class="col-6 col-md-4 ml-auto mr-auto team-brand">
                        <h3 id="teamName"
                            style="text-align: center;margin-bottom: 0">${monitor.onPreAttendance.team.teamName}</h3>
                        <hr>
                        <h4 id="teamOperation" style="text-align: center">
                            暂停中...
                        </h4>
                    </div>
                </div>
                <div class="row">
                    <div id="timer"></div>
                </div>
                <div class="row" style="margin-bottom: 43px">
                    <div class="col-6 col-md-4 ml-auto mr-auto">
                        <div id="operation" class="flex-space-around" style="width: 100%;">
                            <button id="start" class="btn bg-dark btn-fab btn-lg btn-round control-btn">
                                <i class="material-icons">play_arrow</i>
                            </button>
                            <button id="pause" class="btn bg-dark btn-fab btn-lg btn-round control-btn" style="display: none">
                                <i class="material-icons">pause</i>
                            </button>
                            <button id="stopQuestion" class="btn bg-dark btn-fab btn-lg btn-round control-btn"
                                    style="display: none">
                                <i class="material-icons">stop</i>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6 col-md-4 ml-auto mr-auto team-brand">
                        <form class="form flex-center" id="scoreForm" style="flex-direction: column;margin-bottom: 5px;">
                            <div class="form-group bmd-form-group" style="padding-top: 5px">
                                <input id="score" name="score" type="text" placeholder="分数" autocomplete="off"
                                       class="form-control empty-verify" data-emptyMessage="请输入分数">
                            </div>
                            <button type="button" id="giveScore" class="btn bg-dark btn-fab btn-round">
                                <i class="material-icons">
                                    adjust
                                </i>
                            </button>
                            <button type="button" id="patchScore" class="btn bg-dark btn-fab btn-round" style="display: none">
                                <i class="material-icons">
                                    arrow_upward
                                </i>
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="container foot-operation" style="max-width: 100%;">
            <div class="row  flex-space-around">
                <button id="pullQuestion" class="btn bg-dark btn-round">
                    <i class="material-icons">
                        toll
                    </i>
                    抽取提问
                </button>
                <button id="switchTeam" class="btn bg-dark btn-round">
                    <i class="material-icons">
                        arrow_forward
                    </i>
                    切换小组
                </button>
                <button id="endPre" class="btn bg-dark btn-round" style="display: none">
                    <i class="material-icons">
                        input
                    </i>
                    结束展示
                </button>
            </div>
        </div>
</#if>

<form hidden id="seminarForm" action="/teacher/course/seminar/info" method="post">
    <input id="seminarIdInput" name="seminarId" placeholder="">
    <input id="klassIdInput" name="klassId" placeholder="">
</form>
<!--   Core JS Files   -->
<script src="http://cdn.staticfile.org/popper.js/1.12.6/umd/popper.min.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.min.js" type="text/javascript"></script>
<script src="http://cdn.staticfile.org/moment.js/2.14.1/moment.min.js"></script>
<!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
<script src="http://cdn.staticfile.org/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="http://cdn.staticfile.org/noUiSlider/9.1.0/nouislider.min.js" type="text/javascript"></script>
<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
<!-- Control Center for Material Kit: parallax effects, scripts for the example pages etc -->
<script src="http://cdn.staticfile.org/material-kit/2.0.4/js/material-kit.min.js" type="text/javascript"></script>
</body>
</html>