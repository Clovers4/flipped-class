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
    <title>课程信息</title>
</head>
<body class="card-page sidebar-collapse">
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-fab-mini btn-round" onclick="window.location='/teacher/courseList'">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">课程信息</div>
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
<div class="container" style="margin-top: 40px">
    <div class="row">
        <div class="col-md-10 ml-auto mr-auto">
            <div class="card seminar-card" style="height: 77%;">
                <div class="card-header">
                    <div class="container flex-center">
                        <h4 class="card-title">${course.courseName}</h4>
                    </div>
                </div>
                <div class="card-body">
                    <div class="container">
                        <div class="col-md-6 ml-auto mr-auto">
                            <div class="line">
                                <label>轮次</label>
                                <div class="sep"></div>
                            <#--TODO:{}-->
                                <div class="content">｛第二轮｝</div>
                            </div>
                            <div class="line">
                                <label>班级</label>
                                <div class="sep"></div>
                            <#--TODO:{}-->
                                <div class="content">｛2016(1)｝</div>
                            </div>
                            <div class="line">
                                <label>小组人数</label>
                                <div class="sep"></div>
                                <div class="content" style="margin-left: 10px">{6~8}</div>
                            </div>
                            <div class="line content-line">
                                <label>组队开始时间</label>
                                <div class="sep"></div>
                                <div class="content">${course.teamStartDate?date}</div>
                            </div>
                            <div class="line content-line">
                                <label>组队结束时间</label>
                                <div class="sep"></div>
                                <div class="content">${course.teamEndDate?date}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container foot-container flex-center">
    <button onclick="window.location='/logout'" class="btn bg-red" style="margin: 0">
        <i class="material-icons">delete</i>
        删除课程
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