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
    <script src="/static/lib/jquery-3.3.1.js"></script>
    <script src="/static/js/util.js"></script>
    <script src="/static/js/teacher/courseList.js"></script>
    <title>课程</title>
</head>
<body class="card-page sidebar-collapse">
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" onclick="window.location='/teacher/index'">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">课程</div>
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
<div class="main main-raised no-footer">
    <#if courses?size ==0>
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
            <#list courses as course>
            <div class="col-md-6">
                <div class="card content-card">
                    <div class="card-body" data-courseID="${course.id}" data-toggle="modal" data-target="#courseModal">
                        <div class="body-header">
                            <div class="body-title">${course.courseName}</div>
                        </div>
                        <div class="body-content">
                            <hr>
                            <div class="row">
                                <div class="col-md-12 ml-auto mr-auto">
                                    <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                        <li class="nav-item seminar-nav">
                                            <a class="nav-link">
                                                <i class="material-icons">event_note</i>
                                                讨论课
                                            </a>
                                        </li>
                                        <li class="nav-item klass-nav">
                                            <a class="nav-link">
                                                <i class="material-icons">class</i>
                                                班级
                                            </a>
                                        </li>
                                        <li class="nav-item team-nav">
                                            <a class="nav-link">
                                                <i class="material-icons">group</i>
                                                分组
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12 ml-auto mr-auto">
                                    <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                        <li class="nav-item info-nav">
                                            <a class="nav-link">
                                                <i class="material-icons">description</i>
                                                课程信息
                                            </a>
                                        </li>
                                        <li class="nav-item grade-nav">
                                            <a class="nav-link">
                                                <i class="material-icons">equalizer</i>
                                                成绩
                                            </a>
                                        </li>
                                        <li class="nav-item share-nav">
                                            <a class="nav-link">
                                                <i class="material-icons">share</i>
                                                课程共享
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </#list>
                <div class="col-md-6">
                    <a class="btn bg-transparent add-card-btn" id="addRound" style="margin-bottom: 10px">
                        <i class="material-icons add-icon">add_circle</i>
                    </a>
                </div>
            </div>
        </div>
    </#if>
</div>
<form hidden id="courseIdForm" method="post">
    <input id="courseIdInput" name="courseId" title="">
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