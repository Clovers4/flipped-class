<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="apple-touch-icon" sizes="76x76" href="/static/imgs/apple-icon.png">
    <link rel="icon" type="image/png" href="/static/imgs/favicon.png">
    <link rel="stylesheet" href="http://cdn.staticfile.org/material-kit/2.0.4/css/material-kit.min.css">
    <link rel="stylesheet" href="/static/css/admin.css">
    <script src="http://cdn.staticfile.org/jquery/3.3.1/jquery.js"></script>
    <script src="/static/js/util.js"></script>
    <script src="/static/js/admin/login.js"></script>
    <title>登录</title>
</head>
<body class="login-page">
<div class="page-header header-filter">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-6 ml-auto mr-auto">
                <div class="card card-login">
                    <form class="form" id="loginForm">
                        <div class="card-header card-header-primary text-center">
                            <h4 class="card-title">翻转课堂管理平台</h4>
                        </div>
                        <p class="description text-center">管理员登录</p>
                        <div class="card-body">
                            <div class="form-group bmd-form-group">
                                <label for="account" class="bmd-label-floating">请输入你的管理员名</label>
                                <input id="account" name="account" type="text" autocomplete="off" class="form-control empty-verify"
                                       data-toggle="popover" data-trigger="manual" data-emptyMessage="请输入管理员名">
                            </div>
                            <div class="form-group bmd-form-group">
                                <label for="password" class="bmd-label-floating">请输入你的密码</label>
                                <input id="password" name="password" type="password" autocomplete="off" class="form-control empty-verify"
                                       data-toggle="popover" data-trigger="manual" data-emptyMessage="请输入密码">
                            </div>
                        </div>
                        <div class="footer text-center">
                            <a id="login" class="btn btn-primary btn-link btn-wd btn-lg" data-toggle="popover"
                               data-trigger="manual" data-placement="bottom">
                                登录
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
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