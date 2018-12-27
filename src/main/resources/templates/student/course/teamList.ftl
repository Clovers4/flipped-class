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
    <script>
        $(function () {
            $(".courseId").val(sessionStorage.getItem("courseId"));
        })
    </script>
    <title>组队</title>
</head>
<body class="card-page sidebar-collapse" data-parallax="true">
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" onclick="window.location='/student/courseList'">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">分组</div>
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
<div class="main main-raised <#if (!myTeam?? && course.teamMainCourseId??)>no-footer</#if>">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="card content-card">
                    <div class="card-body" data-toggle="modal" data-target="#notTeamedModal">
                        <div class="body-header">
                            <div class="body-title" style="text-align: center">未组队学生</div>
                        </div>
                        <div class="body-content">
                            <hr>
                            <div class="line">
                                <label>数量</label>
                                <div class="sep"></div>
                                <div class="content">${students?size}</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="row">
            <#if teams?size ==0>
                <div class="empty-tag">
                    <div class="info">
                        <div class="icon icon-rose flex-center">
                            <i class="material-icons color-grey">portable_wifi_off</i>
                        </div>
                        <h4 class="info-title">这里空荡荡的</h4>
                    </div>
                </div>
            <#else>
                <#list teams as team>
                    <div class="col-lg-4 col-md-6">
                        <div class="card content-card">
                            <div class="card-body" data-toggle="modal" data-target="#teamModal${team.id}">
                                <div class="body-header">
                                    <div class="body-title">${team.teamName}</div>
                                </div>
                                <div class="body-content">
                                    <hr>
                                    <div class="line">
                                        <label>序号</label>
                                        <div class="sep"></div>
                                        <div class="content">${team.klass.serial}-${team.serial}</div>
                                    </div>
                                    <div class="line">
                                        <label>队长</label>
                                        <div class="sep"></div>
                                        <div class="content">${team.leader.studentName}</div>
                                    </div>
                                    <div class="line">
                                        <label>合法性</label>
                                        <div class="sep"></div>
                                        <div class="content">${team.teamStatus}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
        </div>
    </div>
</div>
<div class="container foot-container flex-center" style="padding-bottom: 0">
    <#if (myTeam??)>
        <form hidden id="myTeamForm" action="/student/course/myTeam" method="post">
            <input class="courseId" name="courseId" placeholder="">
            <input name="teamId" value="${myTeam.id}" placeholder="">
        </form>
        <button class="btn bg-dark" onclick="$('#myTeamForm').submit()">
            进入我的小组
            <i class="material-icons">arrow_forward_ios</i>
        </button>
    <#elseif !course.teamMainCourseId??>
        <button class="btn bg-dark" id="createTeam" onclick="$('#createTeamForm').submit();" <#if !permitCreate>disabled</#if>>
            <i class="material-icons">add</i>
            创建小组
        </button>
    </#if>
</div>
<form hidden id="createTeamForm" action="/student/course/team/create" method="post">
    <input class="courseId" name="courseId" placeholder="">
</form>

<#list teams as team>
    <div class="modal fade" id="teamModal${team.id}">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">${team.teamName}</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <i class="material-icons">clear</i>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="card content-card">
                            <div class="card-body">
                                <div class="body-header">
                                    <div class="body-title" style="text-align: center">队长</div>
                                </div>
                                <div class="body-content">
                                    <table class="table team-table">
                                        <tbody>
                                        <tr>
                                            <td class="name">${team.leader.studentName}</td>
                                            <td class="num">${team.leader.studentNum}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="card content-card">
                            <div class="card-body">
                                <div class="body-header">
                                    <div class="body-title" style="text-align: center">队员</div>
                                </div>
                                <div class="body-content">
                                    <#if (team.students?size > 0)>
                                        <table class="table team-table">
                                            <tbody>
                                            <#list team.students as student>
                                                <#if team.leader.studentNum != student.studentNum>
                                                    <tr>
                                                        <td class="name">${student.studentName}</td>
                                                        <td class="num">${student.studentNum}</td>
                                                    </tr>
                                                </#if>
                                            </#list>
                                            </tbody>
                                        </table>
                                    <#else>
                                        <div class="empty-tag" style="height: 200px;">
                                            <div class="info">
                                                <div class="icon icon-rose flex-center">
                                                    <i class="material-icons color-grey">portable_wifi_off</i>
                                                </div>
                                                <h4 class="info-title">没有队员</h4>
                                            </div>
                                        </div>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#list>
<div class="modal fade" id="notTeamedModal">
    <div class="modal-dialog" style="margin-top: 30px">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">未组队学生</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <div class="modal-body" style="overflow: scroll;height: 80%;">
                <div class="row">
                    <#if (students?size > 0)>
                        <table class="table team-table" style="margin-top: 0;">
                            <tbody>
                            <#list students as student>
                                <tr>
                                    <td class="serial">${student?counter}</td>
                                    <td class="name">${student.studentName}</td>
                                    <td class="num">${student.studentNum}</td>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    <#else>
                        <div class="empty-tag" style="height: 200px;">
                            <div class="info">
                                <div class="icon icon-rose flex-center">
                                    <i class="material-icons color-grey">portable_wifi_off</i>
                                </div>
                                <h4 class="info-title">没有未组队学生</h4>
                            </div>
                        </div>
                    </#if>
                </div>
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