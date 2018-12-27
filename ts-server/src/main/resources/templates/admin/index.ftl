<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="apple-touch-icon" sizes="76x76" href="/static/imgs/apple-icon.png">
    <link rel="icon" type="image/png" href="/static/imgs/favicon.png">
    <link rel="stylesheet" href="/static/css/material-kit.css?v=2.0.4">
    <link rel="stylesheet" href="/static/css/admin.css">
    <script src="/static/lib/jquery-3.3.1.js"></script>
    <script src="/static/js/admin/index.js"></script>
    <title>主页</title>
</head>
<body class="index-page">
<nav class="navbar navbar-inverse navbar-expand-lg bg-dark">
    <div class="navbar-translate">
        <a class="navbar-brand" style="cursor: default;">翻转课堂管理平台
            <div class="ripple-container"></div>
        </a>
    </div>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto">
            <li class="dropdown nav-item">
                <a class="dropdown-toggle nav-link" data-toggle="dropdown" style="cursor: pointer;">管理员</a>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item" href="/admin/logout">退出</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<div class="page-body">
    <nav class="navbar sidebar navbar-inverse">
        <ul class="nav nav-pills nav-pills-rose flex-column">
            <li class="nav-item">
                <a class="nav-link active iref" data-toggle="tab" data-href="/admin/teacherManage">
                    教师管理
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link iref" data-toggle="tab" data-href="/admin/studentManage">
                    学生管理
                </a>
            </li>
        </ul>
    </nav>
    <div class="page-content" id = "page-content">
    </div>

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