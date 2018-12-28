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
    <script src="http://cdn.staticfile.org/jquery/3.3.1/jquery.js"></script>
    <script src="/static/js/util.js"></script>
    <script src="/static/js/teacher/course/roundSetting.js"></script>
    <title>轮次设置</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" id="backBtn">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">第${round.roundNum}轮</div>
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
                    <a class="nav-link" onclick="window.location='/teacher/index'">
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
            <div class="col-lg-10 col-md-8">
                <div class="container">
                    <div class="row" style="padding-top: 20px;">
                        <div class="col-md-12">
                            <div class="card content-card">
                                <div class="card-body">
                                    <div class="body-header">
                                        <div class="body-title" style="text-align: center">讨论课</div>
                                    </div>
                                    <div class="body-content">
                                        <hr>
                                        <ul class="nav nav-pills nav-pills-rose flex-column">
                                            <#if round.seminars?size == 0>
                                                <div class="empty-tag"
                                                     style="height: 200px;padding-left: 10%;padding-right: 10%;">
                                                    <div class="info">
                                                        <div class="icon icon-rose flex-center">
                                                            <i class="material-icons color-grey">portable_wifi_off</i>
                                                        </div>
                                                        <h4 class="info-title">尚无讨论课</h4>
                                                    </div>
                                                </div>
                                            <#else >
                                                <#list round.seminars as seminar>
                                                    <li class="nav-item"><a class="nav-link">${seminar.theme}</a></li>
                                                </#list>
                                            </#if>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form id="roundSettingForm">
                        <input hidden name="roundId" value="${round.id}" placeholder="">
                        <div class="row">
                            <div class="col-lg-6 col-md-12">
                                <div class="card form-card dropdown-card">
                                    <div class="card-body">
                                        <div class="body-header">
                                            <div class="body-title">成绩取算设置</div>
                                            <div class="flex-center">
                                                <div class="triangle downward"></div>
                                            </div>
                                        </div>
                                        <div class="body-content">
                                            <hr>
                                            <div class="form-group line">
                                                <label class="bmd-label">课堂展示</label>
                                                <div class="sep"></div>
                                                <div class="content">
                                                    <div class="form-check form-check-radio form-check-inline">
                                                        <label class="form-check-label">
                                                            <input class="form-check-input" type="radio"
                                                                   name="preScoreType" value="0"
                                                                   <#if round.preScoreType==0>checked</#if>>最高分
                                                            <span class="circle"><span class="check"></span></span>
                                                        </label>
                                                    </div>
                                                    <div class="form-check form-check-radio form-check-inline">
                                                        <label class="form-check-label">
                                                            <input class="form-check-input" type="radio"
                                                                   name="preScoreType" value="1"
                                                                   <#if round.preScoreType==1>checked</#if>>平均分
                                                            <span class="circle"><span class="check"></span></span>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group line">
                                                <label class="bmd-label">课堂提问</label>
                                                <div class="sep"></div>
                                                <div class="content">
                                                    <div class="form-check form-check-radio form-check-inline">
                                                        <label class="form-check-label">
                                                            <input class="form-check-input" type="radio"
                                                                   name="quesScoreType" value="0"
                                                                   <#if round.quesScoreType==0>checked</#if>>最高分
                                                            <span class="circle"><span class="check"></span></span>
                                                        </label>
                                                    </div>
                                                    <div class="form-check form-check-radio form-check-inline">
                                                        <label class="form-check-label">
                                                            <input class="form-check-input" type="radio"
                                                                   name="quesScoreType" value="1"
                                                                   <#if round.quesScoreType==1>checked</#if>>平均分
                                                            <span class="circle"><span class="check"></span></span>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group line">
                                                <label class="bmd-label">书面报告</label>
                                                <div class="sep"></div>
                                                <div class="content">
                                                    <div class="form-check form-check-radio form-check-inline">
                                                        <label class="form-check-label">
                                                            <input class="form-check-input" type="radio"
                                                                   name="reportScoreType" value="0"
                                                                   <#if round.reportScoreType==0>checked</#if>>最高分
                                                            <span class="circle"><span class="check"></span></span>
                                                        </label>
                                                    </div>
                                                    <div class="form-check form-check-radio form-check-inline">
                                                        <label class="form-check-label">
                                                            <input class="form-check-input" type="radio"
                                                                   name="reportScoreType" value="1"
                                                                   <#if round.reportScoreType==1>checked</#if>>平均分
                                                            <span class="circle"><span class="check"></span></span>
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12">
                                <div class="card form-card dropdown-card">
                                    <div class="card-body">
                                        <div class="body-header">
                                            <div class="body-title">报名次数限制</div>
                                            <div class="flex-center">
                                                <div class="triangle downward"></div>
                                            </div>
                                        </div>
                                        <div class="body-content">
                                            <hr>
                                            <#list klasses as klass>
                                                <input hidden name="klassId" value="${klass.id}"
                                                       placeholder="">
                                                <div class="line">
                                                    <label>${klass.klassName}</label>
                                                    <div class="sep"></div>
                                                    <div class="content">
                                                        <div class="form-group bmd-form-group" style="display: inline">
                                                            <input type="text" autocomplete="off" name="enrollLimit"
                                                                   placeholder=""
                                                                   class="form-control empty-verify klass-round-limit"
                                                                   value="${klassRoundMap[klass.id?c].enrollLimit}"
                                                                   data-emptyMessage="请输入课堂展示权重">
                                                        </div>
                                                    </div>
                                                </div>
                                            </#list>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
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