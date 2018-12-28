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
    <script src="/static/js/teacher/course/seminar/enrollList.js"></script>
    <title>进行讨论课</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark" id="sectionsNav">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" id="backBtn">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">讨论课报名</div>
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
                    <a class="nav-link">
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
<div class="main main-raised no-footer">
    <div class="container">
        <div class="row">
            <#assign i = 0/>
            <#list enrollList as attendance>
                <#assign i = i+1/>
                <div class="col-xl-4 col-md-6">
                    <div class="card enroll-card">
                        <#if attendance??>
                            <div class="card-body">
                                <div class="body-header flex-space-between">
                                    <div class="body-title">第${i}组</div>
                                    <div class="line team-line">
                                        <label style="width: 50px">队伍</label>
                                        <div class="sep"></div>
                                        <div class="content">${attendance.team.teamName}</div>
                                    </div>
                                </div>
                                <div class="body-content">
                                    <hr>
                                    <#if !hasEnd>
                                        <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                            <li <#if attendance.preFile??>class="nav-item download-file"
                                                data-fileName="${attendance.preFile}" data-teamId="${attendance.teamId}"
                                                <#else >class="nav-item disabled" </#if>>
                                                <a class="nav-link" style="padding: 0;">
                                                    <i class="material-icons">cloud_download</i>
                                                    下载PPT
                                                </a>
                                            </li>
                                        </ul>
                                    <#else >
                                        <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                            <li <#if attendance.reportFile??>class="nav-item download-file"
                                                data-fileName="${attendance.reportFile}" data-teamId="${attendance.teamId}"
                                                <#else >class="nav-item disabled" </#if>>
                                                <a class="nav-link" style="padding: 0;">
                                                    <i class="material-icons">cloud_download</i>
                                                    下载报告
                                                </a>
                                            </li>
                                            <li <#if attendance.reportFile??>data-toggle="modal" data-target="#reportScoreModal"
                                                class="nav-item score" data-teamId="${attendance.teamId}"
                                                <#else >class="nav-item disabled"</#if>>
                                                <a class="nav-link" style="padding: 0;">
                                                    <i class="material-icons">edit</i>
                                                    打分
                                                </a>
                                            </li>
                                        </ul>
                                    </#if>
                                </div>
                            </div>
                        <#else>
                            <div class="card-body">
                                <div class="body-header">
                                    <div class="body-title">第${i}组</div>
                                </div>
                                <div class="body-content">
                                    <hr>
                                    <div class="flex-center not-enroll">
                                        尚未报名
                                    </div>
                                </div>
                            </div>
                        </#if>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>

<div class="modal fade" id="reportScoreModal">
    <div class="modal-dialog" style="margin-top: 100px">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom: #EEEEEE 1px solid;border-collapse: collapse">
                <h5 class="modal-title">打分</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <div class="modal-body" style="overflow: scroll;height: 20%;padding: 0 24px">
                <form class="form" id="giveScoreForm">
                    <input hidden name="ksId" value="${ksId}" placeholder="">
                    <input hidden id="teamId" name="teamId" placeholder="">
                    <div class="card-body">
                        <div class="form-group bmd-form-group">
                            <label for="score">分数</label>
                            <input id="score" name="score" type="text" autocomplete="off"
                                   class="form-control empty-verify" data-emptyMessage="请输入分数">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="padding: 10px;border-top: #EEEEEE 1px solid">
                <button id="confirmBtn" class="btn bg-dark" style="margin-right: 20px">确定</button>
                <button class="btn btn-danger" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<form hidden id="seminarForm" action="/teacher/course/seminar/info" method="post">
    <input id="seminarIdInput" name="seminarId" placeholder="">
    <input id="klassIdInput" name="klassId" placeholder="">
</form>
<form hidden id="downloadFileForm" action="/teacher/course/seminar/downloadPPT" method="get">
    <input id="fileNameInput" name="fileName" placeholder="">
    <input id="teamIdInput" name="teamId" placeholder="">
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