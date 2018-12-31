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
    <script src="/static/js/student/course/team/create.js"></script>
    <style>
        .circle, .check{
            height: 20px !important;
            width: 20px !important;
        }
        .klass{
            font-size: 20px;
            padding-left: 35px !important;
        }
    </style>
    <title>首页</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" id="backBtn">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">创建队伍</div>
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
<div class="main main-raised">
    <div class="container">
        <div class="row flex-center">
            <div class="col-md-8">
                <form class="form" id="createTeamForm">
                    <input hidden id="courseId" name="courseId" placeholder="">
                    <input hidden id="klassId" name="klassId" placeholder="">
                    <input hidden id="leaderId" name="leaderId" placeholder="" value="${leaderId}">
                    <div class="container">
                        <div class="form-group bmd-form-group">
                            <input id="teamName" name="teamName" type="text" autocomplete="off" placeholder="队伍名"
                                   class="form-control empty-verify" data-emptyMessage="请输入队伍名字">
                        </div>
                    </div>
                    <div class="container">
                        <label style="margin-top: 30px">选择班级</label>
                        <div style="margin-top: 10px;">
                            <#list klasses as klass>
                                <div class="form-check form-check-radio" style="margin-bottom: 20px">
                                    <label class="form-check-label klass">
                                        <input class="form-check-input" type="radio" <#if klass?index = 0>checked</#if>
                                               name="klassId" value="${klass.id}">${klass.klassName}
                                        <span class="circle"><span class="check"></span></span>
                                    </label>
                                </div>
                            </#list>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container foot-container flex-center">
    <div class="left-button">
        <button class="btn btn-dark btn-round bg-dark confirm" style="margin: 0">
            <i class="material-icons">add_circle</i>
            创建
        </button>
    </div>
    <div class="right-button">
        <button class="btn btn-danger btn-round cancel" style="margin: 0">
            <i class="material-icons">clear</i>
            取消
        </button>
    </div>
</div>

<form id="returnForm" action="/student/course/teamList" method="post">
    <input id="returnCourseId" name="courseId" placeholder="">
    <input id="returnKlassId" name="klassId" placeholder="">
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