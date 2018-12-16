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
    <script src="https://cdn.bootcss.com/sockjs-client/1.3.0/sockjs.js"></script>
    <script src="/static/lib/stomp.js"></script>
    <script src="/static/lib/jquery-3.3.1.js"></script>
    <script src="/static/js/util.js"></script>
    <script src="/static/js/teacher/course/seminar/progressing.js"></script>
    <title>讨论课报名</title>
</head>
<body class="card-page sidebar-collapse">
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" id="backBtn">
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
                    <a class="nav-link">
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
<div class="main main-raised" id="main" data-ksId="${ksId}">
    <form class="form" id="loginForm">
        <div class="container">
            <div class="row" style="margin-top: 30px;">
                <div class="col-8">
                    <div class="form-group bmd-form-group" style="padding-top: 10px;">
                        <input id="toServer" name="toServer" type="text" placeholder="消息" autocomplete="off"
                               class="form-control empty-verify" data-emptyMessage="请输入消息">
                    </div>
                </div>
                <div class="col-4 flex-right" style="padding-right: 3%;">
                    <button type="button" id="send" class="btn bg-dark">
                        Send
                    </button>
                </div>
            </div>
        </div>
    </form>

    <div class="container">
        <div class="card"  style="height: calc(100% - 140px);">
            <div id="msgList" class="card-body" style="overflow: scroll;flex-wrap: wrap">

            </div>
        </div>
    </div>
</div>

<div class="container foot-container flex-center">
    <div class="left-button">
        <button class="btn bg-dark" id="connect" style="margin: 0">
            Connect
        </button>
    </div>
    <div class="right-button">
        <button class="btn bg-dark" id="disconnect" style="margin: 0">
            Disconnect
        </button>
    </div>
</div>

<form hidden id="seminarForm" action="/teacher/course/seminar/info" method="post">
    <input id="seminarIdInput" name="seminarId" title="">
    <input id="klassIdInput" name="klassId" title="">
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