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
    <script src="/static/js/teacher/course/share/create.js"></script>
    <title>创建共享</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" id="backBtn">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">创建共享请求</div>
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
                    <a class="nav-link" onclick="window.location='/teacher/index'">
                        <i class="material-icons">notifications</i>
                        待办
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
                <form class="form" id="createShareForm">
                    <input hidden id="mainCourseId" name="mainCourseId" placeholder="">
                    <div class="row" style="margin-top: 27px;margin-bottom: 20px;">
                        <div class="col-4 flex-center">
                            <label style="margin-bottom: 0;">共享类型：</label>
                        </div>
                        <div class="col-8">
                            <div class="form-check form-check-radio form-check-inline" style="margin: 0 20px 0 0;">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="radio"
                                           name="shareType" value="0" checked>共享分组
                                    <span class="circle"><span class="check"></span></span>
                                </label>
                            </div>
                            <div class="form-check form-check-radio form-check-inline" style="margin: 0">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="radio"
                                           name="shareType" value="1">共享讨论课
                                    <span class="circle"><span class="check"></span></span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="card course-card">
                            <h4 style="text-align: center">可共享课程</h4>
                            <div class="container">
                                <table class="table" style="margin-bottom: 0;border: 0;">
                                    <colgroup>
                                        <col width="16%">
                                        <col width="50%">
                                        <col>
                                    </colgroup>
                                    <thead style="border: 0;">
                                    <tr style="border: 0;">
                                        <th style="border: 0;text-align: center;padding-right: 21px"><i class="material-icons" style="font-size: 15px">brightness_1</i></th>
                                        <th style="border: 0;">课程名</th>
                                        <th style="border: 0;">教师名</th>
                                    </tr>
                                    </thead>
                                </table>
                            </div>
                            <div class="card-body" style="padding: 0;overflow: scroll">
                                <div class="container">
                                    <#if otherCourses?size == 0>
                                        <div class="empty-tag">
                                            <div class="info">
                                                <div class="icon icon-rose flex-center">
                                                    <i class="material-icons color-grey">portable_wifi_off</i>
                                                </div>
                                                <h4 class="info-title">无可共享课程</h4>
                                            </div>
                                        </div>
                                    <#else >
                                        <table class="table">
                                        <colgroup>
                                            <col width="16%">
                                            <col width="50%">
                                            <col>
                                        </colgroup>
                                        <tbody>
                                        <#list otherCourses as course>
                                            <tr>
                                                <td>
                                                    <div class="form-check form-check-radio form-check-inline"
                                                         style="margin: 0">
                                                        <label class="form-check-label">
                                                            <input class="form-check-input" type="radio"
                                                                   name="subCourseId" value="${course.id}">
                                                            <span class="circle"><span class="check"></span></span>
                                                        </label>
                                                    </div>
                                                </td>
                                                <td>${course.courseName}</td>
                                                <td>${course.teacher.teacherName}</td>
                                            </tr>
                                        </#list>
                                        </tbody>
                                        </table>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="container foot-container flex-center">
    <div class="left-button">
        <button class="btn btn-dark btn-round bg-dark confirm" style="margin: 0" <#if otherCourses?size == 0>disabled</#if>>
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

<form hidden id="returnForm" action="/teacher/course/share" method="post">
    <input id="returnCourseId" name="courseId" placeholder="">
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