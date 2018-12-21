<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="apple-touch-icon" sizes="76x76" href="/static/imgs/apple-icon.png">
    <link rel="icon" type="image/png" href="/static/imgs/favicon.png">
    <link rel="stylesheet" href="/static/css/material-kit.css?v=2.0.4">
    <link rel="stylesheet" href="/static/css/user.css">
    <link rel="stylesheet" href="/static/css/icon.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
    <script src="/static/lib/jquery-3.3.1.js"></script>
    <script src="/static/js/util.js"></script>
    <script src="/static/js/teacher/course/seminar/option.js"></script>
    <title>首页</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" id="backBtn">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">创建讨论课</div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" aria-expanded="false"
                    aria-label="Toggle navigation">
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
                    <a class="nav-link">
                        <i class="material-icons">notifications</i>
                        待办
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="main main-raised">
    <div class="container">
        <div class="row flex-center">
            <div class="col-md-8">
                <form class="form" id="updateSeminarForm">
                    <input hidden id="id" name="id" value="${seminar.id}" placeholder="">
                    <input hidden id="courseId" name="courseId" value="${seminar.courseId}" placeholder="">
                    <div class="container" style="margin-top: 20px">
                        <div class="form-row" style="margin-top: 20px;">
                            <div class="form-group col-2" style="padding-left: 0;">
                                <label for="serial">序号</label>
                                <input id="serial" name="serial" type="text" autocomplete="off"
                                       value="${seminar.serial}"
                                       class="form-control empty-verify" data-emptyMessage="请输入讨论课序号">
                            </div>
                            <div class="form-group col-8">
                                <label for="theme" style="padding-left: 5px;">讨论课主题</label>
                                <input id="theme" name="theme" type="text" autocomplete="off" value="${seminar.theme}"
                                       class="form-control empty-verify" data-emptyMessage="请输入讨论课主题">
                            </div>
                            <div class="form-group col-2 flex-center" style="padding: 0;margin-top: 10px">
                                <a id="deleteBtn" class="btn btn-link btn-fab btn-round btn-danger">
                                    <i class="material-icons" style="font-size: 32px">delete_outline</i>
                                </a>
                            </div>
                        </div>
                        <div class="form-group bmd-form-group">
                            <label for="content" class="bmd-label">讨论课主要内容</label>
                            <textarea id="content" name="content" type="text" rows="4" autocomplete="off"
                                      class="form-control empty-verify"
                                      data-emptyMessage="请输入讨论课主要内容">${seminar.content}</textarea>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="maxTeam">报名组数</label>
                                    <input id="maxTeam" name="maxTeam" type="text" autocomplete="off"
                                           value="${seminar.maxTeam}"
                                           class="form-control empty-verify" data-emptyMessage="请输入报名组数"
                                           style="margin-top: 5px">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group" style="padding-top: 5px">
                                    <label for="roundId">所属Round</label>
                                    <input hidden id="roundId" name="roundId" value="${seminar.roundId}">
                                    <ul class="nav nav-pills">
                                        <li class="nav-item dropdown" style="width: 100%">
                                            <a id="roundNum" class="nav-link dropdown-toggle"
                                               data-toggle="dropdown" style="padding-top: 5px">
                                                <#list rounds as round>
                                                    <#if round.id == seminar.roundId>
                                                        ${round.roundNum}
                                                    </#if>
                                                </#list>
                                            </a>
                                            <div class="dropdown-menu">
                                                <#list rounds as round>
                                                    <a class="dropdown-item round-num" data-roundId="${round.id}">${round.roundNum}</a>
                                                </#list>
                                                <div class="dropdown-divider"></div>
                                                <a class="dropdown-item round-num">新建</a>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="enrollStartDate">报名开始时间</label>
                            <input id="enrollStartDate" name="enrollStartDate" type="text" autocomplete="off"
                                   value="${seminar.enrollStartDate?datetime}"
                                   class="form-control datetimepicker empty-verify" data-emptyMessage="请输入报名开始时间">
                        </div>
                        <div class="form-group">
                            <label for="enrollEndDate">报名结束时间</label>
                            <input id="enrollEndDate" name="enrollEndDate" type="text" autocomplete="off"
                                   value="${seminar.enrollEndDate?datetime}"
                                   class="form-control datetimepicker empty-verify" data-emptyMessage="请输入报名结束时间">
                        </div>
                        <div class="form-check" style="margin-top: 30px">
                            <label class="form-check-label">
                                <input class="form-check-input" type="checkbox" name="visible" value="true"
                                       <#if seminar.visible>checked</#if>>
                                讨论课是否可见
                                <span class="form-check-sign">
                                    <span class="check"></span>
                                </span>
                            </label>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container foot-container flex-center">
    <div class="left-button">
        <button class="btn btn-dark btn-round bg-dark confirm" style="margin: 0">
            <i class="material-icons">arrow_upward</i>
            更新
        </button>
    </div>
    <div class="right-button">
        <button class="btn btn-danger btn-round cancel" style="margin: 0">
            <i class="material-icons">arrow_back</i>
            返回
        </button>
    </div>
</div>

<form hidden id="returnForm" action="/teacher/course/seminarList" method="post">
    <input id="returnCourseId" name="courseId" placeholder="">
</form>
<!--   Core JS Files   -->
<script src="/static/lib/core/popper.min.js" type="text/javascript"></script>
<script src="/static/lib/core/bootstrap-material-design.min.js" type="text/javascript"></script>
<script src="/static/lib/plugins/moment.min.js"></script>
<!--	Plugin for the Datepicker, full documentation here: https://github.com/Eonasdan/bootstrap-datetimepicker -->
<script src="/static/lib/plugins/bootstrap-datetimepicker.js" type="text/javascript"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="/static/lib/plugins/nouislider.min.js" type="text/javascript"></script>
<!-- Place this tag in your head or just before your close body tag. -->
<script async defer src="https://buttons.github.io/buttons.js"></script>
<!-- Control Center for Material Kit: parallax effects, scripts for the example pages etc -->
<script src="/static/lib/material-kit.js?v=2.0.4" type="text/javascript"></script>
</body>
</html>