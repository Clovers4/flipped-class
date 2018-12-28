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
    <script src="/static/js/student/course/seminar/enrollList.js"></script>
    <title>讨论课报名</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark">
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
                    <a class="nav-link" onclick="window.location='/student/index'">
                        <i class="material-icons">person</i>个人首页
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="main main-raised no-footer">
    <div class="container">
        <div class="row">
            <#list enrollList as attendance>
                <div class="col-xl-4 col-md-6">
                    <div class="card enroll-card">
                        <#if attendance??>
                            <div class="card-body">
                                <div class="body-header flex-space-between">
                                    <div class="body-title">第${attendance?counter}组</div>
                                    <div class="line team-line">
                                        <label style="width: 50px">队伍</label>
                                        <div class="sep"></div>
                                        <div class="content">${attendance.team.teamName}</div>
                                    </div>
                                </div>
                                <div class="body-content">
                                    <hr>
                                    <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                        <#if (team??) && (attendance.team.id = team.id)>
                                            <li class="nav-item" id="uploadPPTBtn" data-atdId="${attendance.id}" data-toggle="modal" data-target="#pptModal">
                                                <a class="nav-link" style="padding: 0;">
                                                    <i class="material-icons">cloud_upload</i>
                                                    上传PPT
                                                </a>
                                            </li>
                                            <li class="nav-item" id="cancelEnroll">
                                                <form hidden id="cancelEnrollForm">
                                                    <input name="attendanceId" value="${attendance.id}" placeholder="">
                                                </form>
                                                <a class="nav-link" style="padding: 0;">
                                                    <i class="material-icons color-red" id="cancelEnroll">cancel</i>
                                                    取消报名
                                                </a>
                                            </li>
                                        <#else >
                                            <li <#if attendance.preFile??>class="nav-item download-ppt" data-fileName="${attendance.preFile}" <#else >class="nav-item disabled"</#if>>
                                                <a class="nav-link" style="padding: 0;">
                                                    <i class="material-icons">cloud_download</i>
                                                    下载PPT
                                                </a>
                                            </li>
                                        </#if>
                                    </ul>
                                </div>
                            </div>
                        <#else>
                            <div class="card-body">
                                <div class="body-header">
                                    <div class="body-title">第${attendance?counter}组</div>
                                    <div class="line team-line">
                                        <div class="content">尚无报名</div>
                                    </div>
                                </div>
                                <div class="body-content">
                                    <hr>
                                    <div class="flex-center not-enroll">
                                        <button data-idx="${attendance?counter}"
                                                class="btn btn-lg btn-round bg-dark enroll" style="width: 100%"
                                                <#if !team?? || !canEnroll>disabled</#if>>报名
                                        </button>
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
<#if (team??)>
    <form hidden id="enrollForm">
        <input name="ksId" placeholder="" value="${ksId}">
        <input name="teamId" placeholder="" value="${team.id}">
        <input name="sn" id="snInput" placeholder="">
    </form>
    <div class="modal fade" id="pptModal">
        <div class="modal-dialog">
            <div class="modal-content" style="height: 329px;">
                <div class="modal-header">
                    <h5 class="modal-title">上传PPT</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <i class="material-icons">clear</i>
                    </button>
                </div>
                <div id="formBody" class="modal-body" style="margin-top: 20px;margin-bottom: 10px;">
                    <div class="row" style="height: 208px;">
                        <div class="col-md-12 ml-auto mr-auto">
                            <form hidden id="teamPPT" enctype="multipart/form-data">
                                <input id="fileInput" name="file" type="file" placeholder="" class="form-control empty-verify" data-emptyMessage="请选择文件">
                                <input id="attendanceId" name="attendanceId" type="text" placeholder="">
                            </form>
                            <div class="file-frame">
                                <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                    <li class="nav-item" id="upload" style="width: 100%;">
                                        <a class="nav-link">
                                            <div class="icon">
                                                <i class="material-icons">folder</i>
                                            </div>
                                            <div id="uploadName" style="text-transform: none">
                                                上传文件
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="flex-space-around" style="margin-top: 20px">
                                <button id="confirmUpload" class="btn btn-dark btn-round bg-dark confirm" style="width: 40%">
                                    <i class="material-icons">arrow_upward</i>
                                    上传
                                </button>
                                <button data-dismiss="modal" class="btn btn-danger btn-round cancel" style="width: 40%">
                                    <i class="material-icons">close</i>
                                    取消
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#if>

<form hidden id="returnForm" action="/student/course/seminarList" method="post">
    <input id="returnCourseId" name="courseId" placeholder="">
</form>
<form hidden id="downloadFileForm" action="/student/course/seminar/downloadPPT" method="get">
    <input id="fileNameInput" name="fileName" placeholder="">
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