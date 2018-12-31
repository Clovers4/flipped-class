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
    <script src="/static/js/student/course/seminar.js"></script>
    <title>讨论课</title>
</head>
<body class="card-page sidebar-collapse">
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" onclick="window.location='/student/courseList'">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">讨论课</div>
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
                    <a class="nav-link" onclick="window.location='/student/index'">
                        <i class="material-icons">person</i>个人首页
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="main main-raised no-footer">
    <#if rounds?size ==0>
        <div class="empty-tag">
            <div class="info">
                <div class="icon icon-rose flex-center">
                    <i class="material-icons color-grey">portable_wifi_off</i>
                </div>
                <h4 class="info-title">这里空荡荡的</h4>
            </div>
        </div>
    <#else>
        <div class="container">
            <div class="row">
            <#list rounds as round>
                <div class="col-md-6">
                    <div class="card content-card">
                        <div class="card-body">
                            <div class="body-header">
                                <div class="body-title">第${round.roundNum}轮</div>
                            </div>
                            <div class="body-content">
                                <hr>
                                <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                    <li class="nav-item" data-toggle="modal" data-target="#round${round.id}Modal">
                                        <a class="nav-link" style="padding-bottom: 0;">
                                            <i class="material-icons">ballot</i>
                                            讨论课
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            </div>
        </div>
    </#if>
</div>

<#list rounds as round>
<div class="modal seminar-modal fade" id="round${round.id}Modal" data-roundId="${round.id}">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">第${round.roundNum}轮</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <div class="modal-body" style="margin-top: 10px;margin-bottom: 10px;height: 80%">
                <#if round.seminars?size == 0>
                    <div class="empty-tag modal-tag">
                        <div class="info">
                            <div class="icon icon-rose flex-center">
                                <i class="material-icons color-grey">portable_wifi_off</i>
                            </div>
                            <h4 class="info-title">这里空荡荡的</h4>
                        </div>
                    </div>
                <#else >
                <div class="container">
                    <div class="row">
                        <div class="col-12 nav-col">
                            <ul class="nav nav-pills nav-pills-icons flex-column">
                                <#list round.seminars as seminar>
                                    <li class="nav-item">
                                        <a class="nav-link seminar-link <#if seminar?index=0>active</#if>" href="#pane${seminar.id}" data-toggle="tab"
                                           data-seminarId="${seminar.id}">
                                            <i class="material-icons">list</i>
                                            <span>${seminar.serial}</span>
                                            <span class="theme">-${seminar.theme}</span>
                                        </a>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                        <div class="col-8 tab-col" style="margin-top: -20px">
                            <div class="tab-content">
                                <#list round.seminars as seminar>
                                    <div class="tab-pane <#if seminar?index=0>active</#if>" id="pane${seminar.id}">
                                        <div class="info">
                                            <div class="icon icon-rose flex-space-between">
                                                <i class="material-icons">group_work</i>
                                                <span class="info-title" style="padding-left: 20px">${seminar.theme}</span>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </div>
                            <div class="container">
                                <hr>
                                <div class="row">
                                    <div class="col-md-12 ml-auto mr-auto" style="padding: 0">
                                        <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                            <li class="nav-item enroll">
                                                <a class="nav-link">
                                                    <i class="material-icons">ballot</i>
                                                    报名
                                                </a>
                                            </li>
                                            <li class="nav-item seminarInfo">
                                                <a class="nav-link">
                                                    <i class="material-icons">library_books</i>
                                                    信息
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12 ml-auto mr-auto" style="padding: 0">
                                        <ul class="nav nav-pills nav-pills-icons flex-space-around">

                                            <li class="nav-item report">
                                                <a class="nav-link" >
                                                    <i class="material-icons">open_in_browser</i>
                                                    提交报告
                                                </a>
                                            </li>
                                            <li class="nav-item progressing">
                                                <a class="nav-link">
                                                    <i class="material-icons">arrow_forward</i>
                                                    进入讨论
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </#if>
            </div>
        </div>
    </div>
</div>
</#list>
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