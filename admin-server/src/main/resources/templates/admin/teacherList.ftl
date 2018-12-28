<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="apple-touch-icon" sizes="76x76" href="/static/imgs/apple-icon.png">
    <link rel="icon" type="image/png" href="/static/imgs/favicon.png">
    <link rel="stylesheet" href="http://cdn.staticfile.org/material-kit/2.0.4/css/material-kit.min.css">
    <link rel="stylesheet" href="/static/css/admin.css">
    <link rel="stylesheet" href="/static/css/icon.css">
    <script src="http://cdn.staticfile.org/jquery/3.3.1/jquery.js"></script>
    <script src="/static/js/util.js"></script>
    <script src="/static/js/admin/teacherList.js"></script>
    <title>教师列表</title>
</head>
<body class="iframe-page table-page">

<table class="table" data-page="${page}" data-pages="${sumPage}" data-new="${newFilter?string("true","false")}">
    <colgroup>
        <col/>
        <col width="5%"/>
        <col width="20%" class="left-align"/>
        <col width="15%" class="left-align"/>
        <col width="25%" class="left-align"/>
        <col/>
        <col width="10%" style="margin-right: -20px"/>
    </colgroup>
    <tbody>
    <#if teachers?size == 0>
    <tr class="empty">
        <td colspan="7">空荡荡的...</td>
    </tr>
    <#else >
        <#list teachers as teacher>
        <tr class="item" data-ID="${teacher.id}">
            <td class="checkbox">
                <div class="form-check single">
                    <label class="form-check-label">
                        <input class="form-check-input" type="checkbox" value="">
                        <span class="form-check-sign"><span class="check"></span></span>
                    </label>
                </div>
            </td>
            <td class="counter">${teacher?counter + fromIndex}</td>
            <td class="teacherNum">${teacher.teacherNum}</td>
            <td class="name">${teacher.teacherName}</td>
            <td class="email">${teacher.email}</td>
            <td>
                <#if teacher.activated>
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
                        data-target="#resetPwdModal" data-gist="${teacher.teacherNum}" style="background-color: #f39c12">
                    <i class="material-icons">settings_backup_restore</i>
                </button>
                <button class="btn btn-danger btn-fab btn-fab-mini btn-round" data-toggle="modal"
                        data-target="#deleteItemModal" data-gist="${teacher.teacherNum}">
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
                        <h4 id="modalTitle" class="card-title">修改教师</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <i class="material-icons">clear</i>
                    </button>
                    <form class="form">
                        <input id="id" name="id" style="display: none">
                        <div class="card-body">
                            <div class="form-group bmd-form-group">
                                <label for="teacherName" class="bmd-label-floating">教师姓名</label>
                                <input id="teacherName" name="teacherName" type="text" autocomplete="off"
                                       class="form-control empty-verify"
                                       data-toggle="popover" data-trigger="manual" data-emptyMessage="请输入教师姓名">
                            </div>

                            <div class="form-group bmd-form-group">
                                <label for="teacherNum" class="bmd-label-floating">教工号</label>
                                <input id="teacherNum" name="teacherNum" type="text" autocomplete="off"
                                       class="form-control empty-verify"
                                       data-toggle="popover" data-trigger="manual" data-emptyMessage="请输入教工号">
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