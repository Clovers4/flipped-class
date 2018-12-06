<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="apple-touch-icon" sizes="76x76" href="/static/imgs/apple-icon.png">
    <link rel="icon" type="image/png" href="/static/imgs/favicon.png">
    <link rel="stylesheet" href="/static/css/material-kit.css?v=2.0.4">
    <link rel="stylesheet" href="/static/css/admin.css">
    <link rel="stylesheet" href="/static/css/icon.css">
    <script src="/static/lib/jquery-3.3.1.js"></script>
    <script src="/static/js/util.js"></script>
    <script src="/static/js/admin/studentList.js"></script>
    <title>教师列表</title>
</head>
<body class="iframe-page table-page">

<table class="table" data-page="${page}" data-pages="${sumPage}" data-new="${newFilter?string("true","false")}">
    <colgroup>
        <col/>
        <col width="5%"/>
        <col width="20%" class="left-align"/>
        <col width="20%" class="left-align"/>
        <col width="20%" class="left-align"/>
        <col/>
        <col width="10%"/>
    </colgroup>
    <tbody>
    <#if students?size == 0>
    <tr class="empty">
        <td colspan="7">空荡荡的...</td>
    </tr>
    <#else >
        <#list students as student>
        <tr class="item" data-ID="${student.id}">
            <td class="checkbox">
                <div class="form-check single">
                    <label class="form-check-label">
                        <input class="form-check-input" type="checkbox" value="">
                        <span class="form-check-sign"><span class="check"></span></span>
                    </label>
                </div>
            </td>
            <td class="counter">${student?counter + fromIndex}</td>
            <td class="studentNum">${student.studentNum}</td>
            <td class="name">${student.studentName}</td>
            <td class="email">${student.email}</td>
            <td>
                <#if student.activated>
                    <i class="material-icons" style="color: #4caf50">record_voice_over</i>
                <#else >
                    <i class="material-icons" style="color: #999999">voice_over_off</i>
                </#if>
            </td>
            <td class="btnColumn">
                <button class="btn btn-primary btn-fab btn-fab-mini btn-round" data-toggle="modal"
                        data-target="#modifyModal">
                    <i class="material-icons">create</i>
                </button>
                <button class="btn btn-fab btn-fab-mini btn-round" data-toggle="modal"
                        data-target="#resetPwdModal" data-gist="${student.studentNum}" style="background-color: #f39c12">
                    <i class="material-icons">settings_backup_restore</i>
                </button>
                <button class="btn btn-danger btn-fab btn-fab-mini btn-round" data-toggle="modal"
                        data-target="#deleteItemModal" data-gist="${student.studentNum}">
                    <i class="material-icons">delete</i>
                </button>
            </td>
        </tr>
        </#list>
    </#if>
    </tbody>
</table>
<div class="modal fade" id="modifyModal">
    <div class="modal-dialog modal-login">
        <div class="modal-content">
            <div class="card card-signup card-plain">
                <div class="modal-header">
                    <div class="card-header card-header-primary text-center">
                        <h4 id="modalTitle" class="card-title">修改学生</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <i class="material-icons">clear</i>
                    </button>
                    <form class="form">
                        <input id="id" name="id" title="" style="display: none">
                        <div class="card-body">
                            <div class="form-group bmd-form-group">
                                <label for="name" class="bmd-label-floating">学生姓名</label>
                                <input id="name" name="name" type="text" autocomplete="off"
                                       class="form-control empty-verify"
                                       data-toggle="popover" data-trigger="manual" data-emptyMessage="请输入学生姓名">
                            </div>

                            <div class="form-group bmd-form-group">
                                <label for="studentNum" class="bmd-label-floating">学号</label>
                                <input id="studentNum" name="studentNum" type="text" autocomplete="off"
                                       class="form-control empty-verify"
                                       data-toggle="popover" data-trigger="manual" data-emptyMessage="请输入学号">
                            </div>

                            <div class="form-group bmd-form-group">
                                <label for="email" class="bmd-label-floating">电子邮箱</label>
                                <input id="email" name="email" type="text" autocomplete="off"
                                       class="form-control empty-verify reg-verify"
                                       data-toggle="popover" data-trigger="manual" data-emptyMessage="请输入教师姓名"
                                       data-reg="^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$"
                                       data-regMessage="请输入正确的电子邮箱">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary confirm">确认修改</button>
                    <a class="btn btn-primary btn-link btn-wd btn-lg" data-dismiss="modal">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="resetPwdModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <i class="material-icons color-warning" style="margin-right: 10px">warning</i>
                <h5 class="modal-title">重置密码</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <div class="modal-body">
                <p>即将重置密码，请确认!</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirm">确定</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteItemModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <i class="material-icons color-danger" style="margin-right: 10px">warning</i>
                <h5 class="modal-title">删除用户</h5>
                <button type="button" class="close" data-dismiss="modal">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <div class="modal-body">
                <p>即将删除该用户，请确认!</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirm" data-toggle="popover" data-trigger="manual"
                        data-placement="left">确定
                </button>
                <button type="button" class="btn btn-secondary cancel" data-dismiss="modal">取消</button>
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