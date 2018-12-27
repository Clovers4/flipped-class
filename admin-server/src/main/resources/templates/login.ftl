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
    <script src="/static/js/login.js"></script>
    <title>登录</title>
</head>
<body class="login-page">
<div class="alert-area"></div>
<div class="container">
    <div class="row">
        <div class="col-lg-4 col-md-6 ml-auto mr-auto">
            <div class="card card-login">
                <form class="form" id="loginForm">
                    <div class="card-header card-header-primary text-center">
                        <h4 class="card-title">翻转课堂课程平台</h4>
                    </div>
                    <p class="description text-center">用户登录</p>
                    <div class="card-body">
                        <div class="form-group bmd-form-group">
                            <input id="account" name="account" type="text" placeholder= "学号/教工号" autocomplete="off"
                                   class="form-control empty-verify" data-emptyMessage="请输入学号/教工号">
                        </div>
                        <div class="form-group bmd-form-group">
                            <input id="password" name="password" type="password" placeholder="密码" autocomplete="off"
                                   class="form-control empty-verify" data-emptyMessage="请输入密码">
                        </div>
                    </div>
                    <div class="footer">
                        <button type="button" id="login" class="btn btn-primary btn-lg">
                            登录
                        </button>
                        <div class="container">
                            <a id="forgetPwd" class="btn btn-default btn-link btn-lg">
                                忘记密码
                            </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
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