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
    <link rel="stylesheet" href="/static/css/icon.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
    <script src="http://cdn.staticfile.org/jquery/3.3.1/jquery.js"></script>
    <script src="/static/js/util.js"></script>
    <script src="/static/js/teacher/course/create.js"></script>
    <title>创建课程</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" onclick="window.location='/teacher/courseList'">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">创建课程</div>
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
            <div class="col-md-6">
                <form class="form" id="createCourseForm">
                    <div class="container">
                        <div class="form-group bmd-form-group">
                            <input id="courseName" name="courseName" type="text" placeholder="课程名称" autocomplete="off"
                                   class="form-control empty-verify" data-emptyMessage="请输入课程名称">
                        </div>
                        <div class="form-group bmd-form-group">
                            <label for="intro" class="bmd-label">课程要求</label>
                            <textarea id="intro" name="intro" type="text" rows="4" autocomplete="off"
                                      class="form-control empty-verify" data-emptyMessage="请输入课程要求"></textarea>
                        </div>
                        <div class="form-group bmd-form-group">
                            <input id="teamStartDate" name="teamStartDate" type="text" autocomplete="off"
                                   placeholder="组队开始时间"
                                   class="form-control datetimepicker empty-verify" data-emptyMessage="请输入组队开始时间">
                        </div>
                        <div class="form-group bmd-form-group">
                            <input id="teamEndDate" name="teamEndDate" type="text" autocomplete="off"
                                   placeholder="组队结束时间"
                                   class="form-control datetimepicker empty-verify" data-emptyMessage="请输入组队结束时间">
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-12">
                                <div id="gradeCard" class="card form-card dropdown-card">
                                    <div class="card-body">
                                        <div class="body-header">
                                            <div class="body-title">成绩权重</div>
                                            <div class="flex-center">
                                                <div class="triangle rightward"></div>
                                            </div>
                                        </div>
                                        <div class="body-content" style="display: none">
                                            <hr>
                                            <div class="line">
                                                <label for="prePer">课堂展示</label>
                                                <div class="sep"></div>
                                                <div class="content">
                                                    <div class="form-group bmd-form-group">
                                                        <input id="prePer" name="prePer" type="text" autocomplete="off"
                                                               class="form-control empty-verify reg-verity"
                                                               data-reg="[0-9]*"
                                                               data-regMessage="非合法数字"
                                                               data-emptyMessage="请输入课堂展示权重">
                                                    </div>
                                                </div>
                                                <div class="gadget">%</div>
                                            </div>
                                            <div class="line">
                                                <label for="quePer">课堂提问</label>
                                                <div class="sep"></div>
                                                <div class="content">
                                                    <div class="form-group bmd-form-group">
                                                        <input id="quePer" name="quePer" type="text" autocomplete="off"
                                                               class="form-control empty-verify reg-verity"
                                                               data-reg="[0-9]*"
                                                               data-regMessage="非合法数字"
                                                               data-emptyMessage="请输入课堂提问权重">
                                                    </div>
                                                </div>
                                                <div class="gadget">%</div>
                                            </div>
                                            <div class="line">
                                                <label for="repPer">书面报告</label>
                                                <div class="sep"></div>
                                                <div class="content">
                                                    <div class="form-group bmd-form-group">
                                                        <input id="repPer" name="repPer" type="text" autocomplete="off"
                                                               class="form-control empty-verify reg-verity"
                                                               data-reg="[0-9]*"
                                                               data-regMessage="非合法数字"
                                                               data-emptyMessage="请输入书面报告权重">
                                                    </div>
                                                </div>
                                                <div class="gadget">%</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-12">
                                <div id="groupCard" class="card form-card dropdown-card">
                                    <div class="card-body">
                                        <div class="body-header">
                                            <div class="body-title">队伍人数限制</div>
                                            <div class="flex-center">
                                                <div class="triangle rightward"></div>
                                            </div>
                                        </div>
                                        <div class="body-content" style="display: none">
                                            <hr>
                                            <div class="line">
                                                <label for="teamMax">人数上限</label>
                                                <div class="sep"></div>
                                                <div class="content">
                                                    <div class="form-group bmd-form-group" style="display: inline">
                                                        <input id="teamMax" name="teamMax" type="text"
                                                               autocomplete="off"
                                                               class="form-control empty-verify reg-verify"
                                                               data-emptyMessage="请输入课堂展示权重"
                                                               data-reg="[1-6]"
                                                                data-regMessage="队伍上限不合法">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="line">
                                                <label for="teamMin">人数下限</label>
                                                <div class="sep"></div>
                                                <div class="content">
                                                    <div class="form-group bmd-form-group" style="display: inline">
                                                        <input id="teamMin" name="teamMin" type="text"
                                                               autocomplete="off"
                                                               class="form-control empty-verify"
                                                               data-emptyMessage="请输入课堂展示权重">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="container flex-center">
                            <button type="button" class="btn bg-dark" style="width: 80%" data-toggle="modal" data-target="#courseModal">
                                <i class="material-icons">add</i>
                                新增选修课程要求
                            </button>
                        </div>
                        <div class="container flex-center">
                            <button type="button" class="btn bg-dark" style="width: 80%" data-toggle="modal" data-target="#courseModal">
                                <i class="material-icons">add</i>
                                新增冲突课程要求
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="courseModal">
    <div class="modal-dialog" style="margin-top: 30px">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom: #EEEEEE 1px solid;border-collapse: collapse">
                <h5 class="modal-title">课程列表</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <div class="modal-body" style="overflow: scroll;height: 70%;padding: 0 24px">
                <div class="row">
                    <div class="container">
                        <#if (courses?size > 0)>
                            <form id="chosenStudentForm" style="margin-bottom: 0">
                                <table class="table team-table" style="margin-top: 0;">
                                    <tbody>
                                    <#list courses as course>
                                        <tr>
                                            <td class="name select">${course.courseName}</td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            </form>
                        <#else>
                            <div class="empty-tag" style="height: 200px;">
                                <div class="info">
                                    <div class="icon icon-rose flex-center">
                                        <i class="material-icons color-grey">portable_wifi_off</i>
                                    </div>
                                    <h4 class="info-title">没有现有班级</h4>
                                </div>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="padding: 10px;border-top: #EEEEEE 1px solid">
                <button id="addBtn" class="btn bg-dark" style="margin-right: 20px">添加</button>
                <button class="btn btn-danger" data-dismiss="modal">取消</button>
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
        <button class="btn btn-danger btn-round cancel" onclick="window.location='/teacher/courseList'"
                style="margin: 0">
            <i class="material-icons">clear</i>
            取消
        </button>
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