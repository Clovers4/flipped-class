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
    <script src="/static/js/forgetPassword.js"></script>
    <title>忘记密码</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<div class="container" style="margin-top: 10vh">
    <div class="row">
        <div class="col-md-10 ml-auto mr-auto">
            <div class="card seminar-card">
                <div class="card-header card-dark">
                    <div class="row">
                        <div class="col-9 flex-center ml-auto mr-auto">
                            <h4 class="card-title"
                                style="color: #FFFFFF; font-size: 20px; margin-top: 0; font-weight: 500">找回密码</h4>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="container">
                        <div class="col-md-8 ml-auto mr-auto">
                            <form id="forgetPwdForm">
                                <div class="form-group">
                                    <label for="account">学号/教工号</label>
                                    <input name="account" type="text" id="account" autocomplete="off"
                                           class="form-control empty-verify" data-emptyMessage="请输入学号/教工号">
                                </div>
                                <div class="form-row" style="margin-left: 0;margin-right:0; ">
                                    <div class="form-group col-7">
                                        <label for="captcha">验证码</label>
                                        <input name="captcha" id="captcha" autocomplete="off"
                                               class="form-control empty-verify" data-emptyMessage="请输入验证码">
                                    </div>
                                    <div class="form-group col-5 flex-center">
                                        <button id="getCaptchaBtn" type="button"
                                                class="btn btn-sm btn-inline bg-dark captcha-btn">
                                            发送验证码
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="card-footer">
                    <div class="col-md-12 flex-space-around" style="margin-bottom: -49px">
                        <button class="btn btn-fab btn-fab-mini btn-round btn-lg bg-dark" id="returnBtn">
                            <i class="material-icons">chevron_left</i>
                        </button>
                        <button class="btn btn-fab btn-fab-mini btn-round btn-lg bg-dark" id="confirmBtn">
                            <i class="material-icons">chevron_right</i>
                        </button>
                    </div>
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