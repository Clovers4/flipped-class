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
    <script src="/static/js/teacher/notification.js"></script>
    <title>通知</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" onclick="window.location='/teacher/index'">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">通知</div>
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
                    <a class="nav-link" onclick="window.location='/teacher/notification'">
                        <i class="material-icons">notifications</i>
                        待办
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="main main-raised no-footer">
    <div class="container">
        <div class="row">
            <#assign i = 0>
            <#list SSApps as SSApp>
                <#assign i = i + 1 >
                <div class="col-md-6">
                    <div class="card content-card dropdown-card">
                        <div class="card-body">
                            <div class="body-header app-header">
                                <div class="body-title">共享讨论课请求</div>
                                <div class="flex-center">
                                    <i class="material-icons">more_vert</i>
                                </div>
                            </div>
                            <div class="body-content">
                                <hr>
                                <div class="line">
                                    <label>源教师</label>
                                    <div class="sep"></div>
                                    <div class="content">${SSApp.mainTeacher.teacherName}</div>
                                </div>
                                <div class="line">
                                    <label>源课程</label>
                                    <div class="sep"></div>
                                    <div class="content">${SSApp.mainCourse.courseName}</div>
                                </div>
                                <div class="line">
                                    <label>您的课程</label>
                                    <div class="sep"></div>
                                    <div class="content">${SSApp.subCourse.courseName}</div>
                                </div>
                                <div class="operation-div" data-appId="${SSApp.id}"
                                     data-mainCourseId="${SSApp.mainCourseId}"
                                     data-subCourseId="${SSApp.subCourseId}"
                                     data-appType="0"
                                     style="display: none">
                                    <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                        <li class="nav-item">
                                            <a class="nav-link accept" style="padding-bottom: 0;">
                                                <div class="icon icon-success">
                                                    <i class="material-icons">check</i>
                                                </div>
                                                接受
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link reject" style="padding-bottom: 0;">
                                                <div class="icon icon-danger">
                                                    <i class="material-icons round-setting">close</i>
                                                </div>
                                                拒绝
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            <#list STApps as STApp>
                <#assign i = i + 1 >
                <div class="col-md-6">
                    <div class="card content-card dropdown-card">
                        <div class="card-body">
                            <div class="body-header app-header">
                                <div class="body-title">共享分组请求</div>
                                <div class="flex-center">
                                    <i class="material-icons">more_vert</i>
                                </div>
                            </div>
                            <div class="body-content">
                                <hr>
                                <div class="line">
                                    <label>源教师</label>
                                    <div class="sep"></div>
                                    <div class="content">${STApp.mainTeacher.teacherName}</div>
                                </div>
                                <div class="line">
                                    <label>源课程</label>
                                    <div class="sep"></div>
                                    <div class="content">${STApp.mainCourse.courseName}</div>
                                </div>
                                <div class="line">
                                    <label>您的课程</label>
                                    <div class="sep"></div>
                                    <div class="content">${STApp.subCourse.courseName}</div>
                                </div>
                                <div class="operation-div" data-appId="${STApp.id}"
                                     data-mainCourseId="${STApp.mainCourseId}"
                                     data-subCourseId="${STApp.subCourseId}"
                                     data-appType="1" style="display: none">
                                    <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                        <li class="nav-item">
                                            <a class="nav-link accept" style="padding-bottom: 0;">
                                                <div class="icon icon-success">
                                                    <i class="material-icons">check</i>
                                                </div>
                                                接受
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link reject" style="padding-bottom: 0;">
                                                <div class="icon icon-danger">
                                                    <i class="material-icons round-setting">close</i>
                                                </div>
                                                拒绝
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            <#list TVApps as TVApp>
                <#assign i = i + 1 >
                <div class="col-md-6">
                    <div class="card content-card dropdown-card">
                        <div class="card-body">
                            <div class="body-header app-header">
                                <div class="body-title">分组合法申请</div>
                                <div class="flex-center">
                                    <i class="material-icons">more_vert</i>
                                </div>
                            </div>
                            <div class="body-content">
                                <hr>
                                <div class="line">
                                    <label>队伍名</label>
                                    <div class="sep"></div>
                                    <div class="content">${TVApp.team.teamName}</div>
                                </div>
                                <div class="line">
                                    <label>队伍人数</label>
                                    <div class="sep"></div>
                                    <div class="content">${TVApp.team.students?size}</div>
                                </div>
                                <div class="line content-line">
                                    <label>申请原因</label>
                                    <div class="sep"></div>
                                    <div class="content">${TVApp.content}</div>
                                </div>
                                <div class="operation-div" data-appId="${TVApp.id}"
                                     data-teamId="${TVApp.teamId}" data-appType="2" style="display: none">
                                    <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                        <li class="nav-item">
                                            <a class="nav-link accept" style="padding-bottom: 0;">
                                                <div class="icon icon-success">
                                                    <i class="material-icons">check</i>
                                                </div>
                                                接受
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link reject" style="padding-bottom: 0;">
                                                <div class="icon icon-danger">
                                                    <i class="material-icons round-setting">close</i>
                                                </div>
                                                拒绝
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
            <#if i = 0>
                <div class="empty-tag">
                    <div class="info">
                        <div class="icon icon-rose flex-center">
                            <i class="material-icons color-grey">portable_wifi_off</i>
                        </div>
                        <h4 class="info-title">无通知</h4>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</div>
<form hidden id="appHandleForm">
    <input name="appId" id="appIdInput" placeholder="">
    <input name="mainCourseId" id="mainCourseIdInput" placeholder="">
    <input name="subCourseId" id="subCourseIdInput" placeholder="">
    <input name="teamId" id="teamIdInput" placeholder="">
    <input name="appType" id="appTypeInput" placeholder="">
    <input name="operationType" id="operationTypeInput" placeholder="">
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