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
    <title>设置</title>
</head>
<body class="card-page sidebar-collapse">
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" onclick="window.location='/student/index'">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">账户设置</div>
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
<div class="main main-raised info-main">
    <div class="container">
        <div class="row">
            <div class="col-md-6 ml-auto mr-auto">
                <div class="profile">
                    <div class="container">
                        <div class="row">
                            <div class="col-4">
                                <div class="avatar">
                                    <img src="/static/imgs/Avatar.png" class="img-raised rounded-circle img-fluid">
                                </div>
                            </div>
                            <div class="col-8 avatar-side">
                                <h3 class="title">${student.studentName}</h3>
                                <hr>
                                <h4 class="title">${student.studentNum}</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="row" style="margin-top: 30px">
            <div class="col-md-6 ml-auto mr-auto">
                <button class="btn bg-dark flex-space-between" onclick="window.location='/student/modifyEmail'" style="width: 100%;text-transform: none">
                    <span>电子邮箱：<#if student.email??>${student.email}<#else >暂无</#if></span>
                    <i class="material-icons">chevron_right</i>
                </button>
                <button class="btn bg-dark flex-space-between" onclick="window.location='/student/modifyPassword'" style="width: 100%;">
                    <span>修改密码</span>
                    <i class="material-icons">chevron_right</i>
                </button>
            </div>
        </div>
    </div>
</div>
<div class="container foot-container flex-center">
    <button onclick="window.location='/logout'" class="btn bg-red" style="margin: 0">
        <i class="material-icons">exit_to_app</i>
        退出登录
    </button>
</div>
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