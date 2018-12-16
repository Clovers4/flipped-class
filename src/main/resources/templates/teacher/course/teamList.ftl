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
    <title>分组</title>
</head>
<body class="card-page sidebar-collapse">
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" onclick="window.location='/teacher/courseList'">
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
                    <a class="nav-link" onclick="window.location='/teacher/index'">
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
<div class="main main-raised no-footer">
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
        <div class="container">
            <div class="row">
                <#list teams as team>
                <div class="col-lg-4 col-md-6">
                    <div class="card content-card">
                        <div class="card-body" data-courseID="${team.id}" data-toggle="modal" data-target="#teamModal${team.id}">
                            <div class="body-header">
                                <div class="body-title">${team.teamName}</div>
                            </div>
                            <div class="body-content">
                                <hr>
                                <div class="line">
                                    <label>序号</label>
                                    <div class="sep"></div>
                                    <div class="content">${team.serial}</div>
                                </div>
                                <div class="line">
                                    <label>队长</label>
                                    <div class="sep"></div>
                                    <div class="content">${team.leader.studentName}</div>
                                </div>
                                <div class="line">
                                    <label>合法性</label>
                                    <div class="sep"></div>
                                    <div class="content">${team.valid?string("合法","不合法")}</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </#list>
            </div>
        </div>
    </#if>
</div>

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
                            <div class="body-content">
                                队长：${team.leader.studentName}
                            </div>
                        </div>
                    </div>
                    <div class="card content-card">
                        <div class="card-body">
                            <div class="body-content">
                            <#if (team.students?size > 0)>
                                <#list team.students as student>
                                    <#if team.leader.studentNum != student.studentNum>
                                    <div>队员：${student.studentName}</div>
                                    </#if>
                                </#list>
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