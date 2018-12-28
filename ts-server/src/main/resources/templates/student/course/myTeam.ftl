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
    <script src="/static/js/student/course/myTeam.js"></script>
    <style>
        .not-valid {
            color: #ff0000;
        }
    </style>
    <title>我的队伍</title>
</head>
<body class="profile-page sidebar-collapse" data-maxMember="${maxMember}" data-teamId="${team.id}">
<div class="alert-area"></div>
<nav class="navbar navbar-color-on-scroll navbar-expand-lg bg-dark">
    <div class="container">
        <div class="navbar-translate">
            <a class="btn btn-link btn-fab btn-round" id="returnBtn">
                <i class="material-icons">arrow_back_ios</i>
            </a>
            <div class="navbar-brand brand-title">我的队伍</div>
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
<div class="main main-raised" style="margin-top: 90px">
    <div class="profile-content" style="height: calc(100% - 200px);">
        <div class="container">
            <div class="row">
                <div class="col-md-6 ml-auto mr-auto">
                    <div class="profile">
                        <div class="avatar">
                            <img src="/static/imgs/Team.png" class="img-raised rounded-circle img-fluid" alt="">
                        </div>
                        <div class="name">
                            <h3 class="title <#if team.status = 0>not-valid</#if>"
                                style="margin-bottom: 0">${team.teamName}</h3>
                            <h6>${team.klass.serial}-${team.serial}</h6>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" style="height: calc(100% - 183px);overflow: scroll">
                <div class="container">
                    <table class="table team-table" style="margin-top: 0;">
                        <tbody>
                        <tr>
                            <td class="name">
                                ${team.leader.studentName}
                                <h6 style="margin: 0">组长</h6>
                            </td>
                            <td class="num">${team.leader.studentNum}</td>
                            <#if (team.leader.id = studentId && !course.teamMainCourseId?? && canChange)>
                                <td class="operation">
                                    <button id="dissolveBtn" class="btn btn-danger btn-fab-mini"
                                            <#if (team.status = 2)>disabled</#if>>解散
                                    </button>
                                </td>
                            </#if>
                        </tr>
                        <#assign curMember = 0>
                        <#if (team.students?size > 0)>
                            <#list team.students as student>
                                <#assign curMember = curMember + 1>
                                <#if team.leader.studentNum != student.studentNum>
                                    <tr>
                                        <td class="name">
                                            ${student.studentName}
                                            <h6 style="margin: 0">组员</h6>
                                        </td>
                                        <td class="num">${student.studentNum}</td>
                                        <#if (team.leader.id = studentId && !course.teamMainCourseId?? && canChange)>
                                            <td class="operation">
                                                <button data-sid="${student.id}"
                                                        class="btn btn-danger btn-fab-mini delete"
                                                        <#if (team.status = 2 )>disabled</#if>>
                                                    移除
                                                </button>
                                            </td>
                                        </#if>
                                    </tr>
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
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="team-state">
            <#if team.status = 0>
                <span class="badge badge-pill badge-danger">不合规则</span>
            <#elseif team.status = 2>
                <span class="badge badge-pill badge-warning">审核中</span>
            </#if>
        </div>
    </div>
</div>
<#if !course.teamMainCourseId?? && canChange>
    <div class="container foot-container flex-center"
         style="bottom: 0;position: fixed;padding-bottom: 0;max-width: 100%;">
        <#if team.leader.id = studentId>
                <button class="btn bg-dark" data-toggle="modal" data-target="#notTeamedModal"
                        <#if (curMember = maxMember || team.status = 2)>disabled</#if>>
                    <i class="material-icons">add</i>
                    添加成员
                </button>
            <#if team.status = 0>
                <button class="btn btn-danger" data-toggle="modal" data-target="#applicationModal">
                    <i class="material-icons">call_merge</i>
                    提交申请
                </button>
            </#if>
        <#else >
            <button id="quitBtn" class="btn bg-danger">
                <i class="material-icons">close</i>
                退出
            </button>
        </#if>
    </div>
    <div class="modal fade" id="notTeamedModal">
        <div class="modal-dialog" style="margin-top: 30px">
            <div class="modal-content">
                <div class="modal-header" style="border-bottom: #EEEEEE 1px solid;border-collapse: collapse">
                    <h5 class="modal-title">添加成员</h5>
                    <button type="button" class="close" data-dismiss="modal">
                        <i class="material-icons">clear</i>
                    </button>
                </div>
                <div class="modal-body" style="overflow: scroll;height: 70%;padding: 0 24px">
                    <div class="row">
                        <div class="container">
                            <#if (students?size > 0)>
                                <form id="chosenStudentForm" style="margin-bottom: 0">
                                    <input hidden name="teamId" placeholder="" value="${team.id}">
                                    <table class="table team-table" style="margin-top: 0;">
                                        <tbody>
                                        <#list students as student>
                                            <tr>
                                                <td class="serial">
                                                    <div class="form-check form-check-radio"
                                                         style="margin-bottom: 20px">
                                                        <label class="form-check-label klass">
                                                            <input class="form-check-input" type="radio"
                                                                   name="studentId"
                                                                   value="${student.id}">
                                                            <span class="circle"><span class="check"></span></span>
                                                        </label>
                                                    </div>
                                                </td>
                                                <td class="name select">${student.studentName}</td>
                                                <td class="num">${student.studentNum}</td>
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
                                        <h4 class="info-title">没有未组队学生</h4>
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
    <#if team.status = 0>
        <div class="modal fade" id="applicationModal">
            <div class="modal-dialog" style="margin-top: 30px">
                <div class="modal-content">
                    <div class="modal-header" style="border-bottom: #EEEEEE 1px solid;border-collapse: collapse">
                        <h5 class="modal-title">发起申请</h5>
                        <button type="button" class="close" data-dismiss="modal">
                            <i class="material-icons">clear</i>
                        </button>
                    </div>
                    <div class="modal-body" style="overflow: scroll;height: 30%;padding: 0 24px">
                        <form class="form" id="validApplicationForm">
                            <input hidden name="teamId" value="${team.id}" placeholder="">
                            <div class="card-body">
                                <div class="form-group bmd-form-group">
                                    <label for="content" class="bmd-label">申请理由</label>
                                    <textarea id="content" name="content" type="text" rows="4" autocomplete="off"
                                              class="form-control empty-verify" data-emptyMessage="请输入申请理由"></textarea>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer" style="padding: 10px;border-top: #EEEEEE 1px solid">
                        <button id="sendBtn" class="btn bg-dark" style="margin-right: 20px">申请</button>
                        <button class="btn btn-danger" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </#if>
</#if>
<form id="courseIdForm" method="post" action="/student/course/teamList" hidden>
    <input id="courseIdInput" name="courseId" placeholder="">
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