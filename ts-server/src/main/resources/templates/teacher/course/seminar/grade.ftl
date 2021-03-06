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
    <script>
        $(function () {
            window.onbeforeunload = function () {
                util.showLoading();
            };
            $("#backBtn").click(function () {
                $("#returnForm").submit();
            });
            $(".modify").click(function () {
                var btn = $(this);
                $("#atdId").val(btn.attr("data-atdId"));
                $("#preScore").val(btn.attr("data-preScore"));
                $("#reportScore").val(btn.attr("data-reportScore"));
                $("#queScore").val(btn.attr("data-queScore"));
            });
            $("#confirmBtn").click(function () {
                util.showLoading();
                $.ajax({
                    type:"post",
                    url:"/teacher/course/seminar/grade/modify",
                    data: $("#modifyScoreForm").serialize(),
                    success:function () {
                        window.location.reload();
                    },
                    error:function () {
                        util.hideLoading();
                        reportScoreModal.modal("hide");
                        util.showAlert("danger", "修改失败，未知原因", 3);
                    }
                });
            })
        })
    </script>
    <style>
        .form-control{
            text-align: center;
        }
        .nav-link{
            padding: 0 !important;
        }
    </style>
    <title>成绩</title>
</head>
<body class="card-page sidebar-collapse">
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-fab-mini btn-round" id="backBtn">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">讨论课成绩</div>
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
        <div class="row">
            <#list attendances as attendance>
                <div class="col-lg-12">
                    <div class="card content-card">
                        <div class="card-body">
                            <div class="body-header">
                                <div class="body-title">${attendance.team.teamName}</div>
                                <button data-atdId="${attendance.id}"
                                        data-queScore="<#if seminarScore[attendance.id?c].questionScore??>${seminarScore[attendance.id?c].questionScore}<#else >无数据</#if>"
                                        data-preScore="<#if seminarScore[attendance.id?c].presentationScore??>${seminarScore[attendance.id?c].presentationScore}<#else >无数据</#if>"
                                        data-reportScore="<#if seminarScore[attendance.id?c].reportScore??>${seminarScore[attendance.id?c].reportScore}<#else >无数据</#if>"
                                        class="btn btn-danger modify" style="padding: 5px 10px" data-toggle="modal"
                                        data-target="#modifyScoreModal">修改
                                </button>
                            </div>
                            <div class="body-content">
                                <hr>
                                <div class="row">
                                    <div class="grade-area">
                                        <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                            <li class="nav-item">
                                                <a class="nav-link">
                                                    <i class="material-icons">mic</i>
                                                    展示分
                                                    <h6><#if seminarScore[attendance.id?c].presentationScore??>${seminarScore[attendance.id?c].presentationScore}分<#else >无数据</#if></h6>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link">
                                                    <i class="material-icons">comment</i>
                                                    提问分
                                                    <h6><#if seminarScore[attendance.id?c].questionScore??>${seminarScore[attendance.id?c].questionScore}分<#else >无数据</#if></h6>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link">
                                                    <i class="material-icons">description</i>
                                                    报告分
                                                    <h6><#if seminarScore[attendance.id?c].reportScore??>${seminarScore[attendance.id?c].reportScore}分<#else >无数据</#if></h6>
                                                </a>
                                            </li>
                                        </ul>
                                        <div class="vertical-separator"></div>
                                        <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                            <li class="nav-item">
                                                <a class="nav-link">
                                                    <i class="material-icons">done_all</i>
                                                    总分
                                                    <h6><#if seminarScore[attendance.id?c].totalScore??>${seminarScore[attendance.id?c].totalScore}分<#else >无数据</#if></h6>
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>

<div class="modal fade" id="modifyScoreModal">
    <div class="modal-dialog" style="margin-top: 100px">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom: #EEEEEE 1px solid;border-collapse: collapse">
                <h5 class="modal-title">打分</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <div class="modal-body flex-center" style="height: 20%;padding: 0 24px">
                <form class="form" id="modifyScoreForm">
                    <input hidden id="atdId" name="attendanceId" placeholder="">
                    <div class="row">
                        <div class="col-4">
                            <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                <li class="nav-item">
                                    <a class="nav-link">
                                        <i class="material-icons">mic</i>
                                        展示分
                                        <input autocomplete="off" class="form-control" id="preScore" name="preScore" placeholder="">
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-4">
                            <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                <li class="nav-item">
                                    <a class="nav-link">
                                        <i class="material-icons">comment</i>
                                        提问分
                                        <input autocomplete="off" class="form-control" id="queScore" name="queScore" placeholder="">
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-4">
                            <ul class="nav nav-pills nav-pills-icons flex-space-around">
                                <li class="nav-item">
                                    <a class="nav-link">
                                        <i class="material-icons">description</i>
                                        报告分
                                        <input autocomplete="off" class="form-control" id="reportScore" name="reportScore" placeholder="">
                                    </a>
                                </li>
                            </ul>
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
<form hidden id="returnForm" action="/teacher/course/seminar/info" method="post">
    <input name="ksId" value="${ksId}" placeholder="">
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