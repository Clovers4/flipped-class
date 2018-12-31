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
    <script src="/static/js/teacher/course/klassList.js"></script>
    <title>班级</title>
</head>
<body class="card-page sidebar-collapse">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" onclick="window.location='/teacher/courseList'">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">班级</div>
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
<div class="main main-raised no-footer">
    <div class="container">
        <div class="row">
            <#if klasses?size != 0>
                <#list klasses as klass>
                    <div class="col-lg-4 col-md-6">
                        <div class="card content-card">
                            <div class="card-body" data-klassID="${klass.id}" data-toggle="modal"
                                 data-target="#klassModal">
                                <div class="body-header">
                                    <div class="body-title">${klass.klassName}</div>
                                </div>
                                <div class="body-content">
                                    <hr>
                                    <div class="line">
                                        <label>讨论课时间</label>
                                        <div class="sep"></div>
                                        <div class="content">${klass.time}</div>
                                    </div>
                                    <div class="line">
                                        <label>讨论课地点</label>
                                        <div class="sep"></div>
                                        <div class="content">${klass.location}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            </#if>
            <div class="col-lg-4 col-md-6">
                <a class="btn bg-transparent add-card-btn" onclick="window.location='/teacher/course/klass/create'"
                   style="height: 135px;margin-top: 10px;margin-bottom: 10px;">
                    <i class="material-icons add-icon">add_circle</i>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="klassModal">
    <div class="modal-dialog">
        <div class="modal-content" style="height: 329px;">
            <div class="modal-header">
                <h5 class="modal-title"></h5>
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <div id="operationBody" class="modal-body" style="margin-top: 20px;margin-bottom: 10px;">
                <div class="row">
                    <div class="col-md-12 ml-auto mr-auto">
                        <ul class="nav nav-pills nav-pills-icons flex-space-around">
                            <li class="nav-item" id="import">
                                <a class="nav-link">
                                    <i class="material-icons">save_alt</i>
                                    导入名单
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 ml-auto mr-auto">
                        <ul class="nav nav-pills nav-pills-icons flex-space-around">
                            <li class="nav-item" id="deleteKlass">
                                <a class="nav-link">
                                    <i class="material-icons" style="color: #f44336">delete</i>
                                    删除班级
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div id="formBody" class="modal-body" style="margin-top: 20px;margin-bottom: 10px;display: none">
                <div class="row" style="height: 208px;">
                    <div class="col-md-12 ml-auto mr-auto">
                        <form id="studentFiles" enctype="multipart/form-data">
                            <input hidden id="fileInput" name="file" type="file" placeholder=""
                                   class="form-control empty-verify" data-emptyMessage="请选择名单">
                            <input hidden id="klassIdInput" name="klassId" type="text" placeholder="">
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
                            <button id="confirmUpload" class="btn btn-dark btn-round bg-dark confirm"
                                    style="width: 40%">
                                <i class="material-icons">arrow_upward</i>
                                上传
                            </button>
                            <button id="cancelUpload" class="btn btn-danger btn-round cancel" style="width: 40%">
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