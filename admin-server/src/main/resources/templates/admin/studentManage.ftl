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
    <script src="/static/js/admin/studentManage.js"></script>
    <title>学生管理</title>
</head>
<body>
<div class="alert-area">
    <div class="alert alert-success" id="successAlert" style="display: none">
        <div class="container">
            <div class="alert-icon">
                <i class="material-icons">check</i>
            </div>
            <b>Hello</b>
        </div>
    </div>
    <div class="alert alert-info" id="infoAlert" style="display: none">
        <div class="container">
            <div class="alert-icon">
                <i class="material-icons">info</i>
            </div>
            <b>Hello</b>
        </div>
    </div>
    <div class="alert alert-danger" id="errorAlert" style="display: none">
        <div class="container">
            <div class="alert-icon">
                <i class="material-icons">clear</i>
            </div>
            <b>Hello</b>
        </div>
    </div>
</div>
<div class="iframe-page">
    <nav class="navbar navbar-default navbar-expand-lg">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                       aria-expanded="false" id="filter-choice" data-filter="name">
                        姓名
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <h6 class="dropdown-header">筛选</h6>
                        <a class="dropdown-item" id="filter-choice-name" data-filter="name">姓名</a>
                        <a class="dropdown-item" id="filter-choice-sn" data-filter="sn">学号</a>
                    </div>
                </li>
            </ul>
            <form class="form-inline">
                <div class="form-group no-border">
                    <input id="filter-content" type="text" class="form-control" placeholder="搜索" autocomplete="off">
                </div>
                <button id="searchBtn" type="button" class="btn btn-white btn-just-icon btn-round">
                    <i class="material-icons">search</i>
                </button>
                <button id="clearBtn" type="button" class="btn btn-white btn-just-icon btn-round" style="display: none">
                    <i class="material-icons" style="color:#f44336">clear</i>
                </button>
            </form>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <button class="btn btn-primary" id="create" data-toggle="modal" data-target="#createModal">
                        <i class="material-icons">add</i>
                        创建
                    </button>
                </li>
                <li class="nav-item">
                    <button class="btn btn-danger" id="groupDelete" data-toggle="modal" data-target="#deleteItemsModal">
                        <i class="material-icons">delete</i>
                        删除
                    </button>
                </li>
                <li class="nav-item">
                    <button class="btn btn-default" id="refresh" onclick="location.replace(location.href);">
                        <i class="material-icons">refresh</i>
                        刷新
                    </button>
                </li>
            </ul>
        </div>
    </nav>

    <div class="page-body">
        <div class="card">
            <div class="card-body">
                <div class="table-content">
                    <form hidden id="studentFilter" action="/admin/studentList" method="post" target="tableIframe">
                        <input id="newFilter" name="newFilter" title="newFilter">
                        <input id="nameFilter" name="name" title="name">
                        <input id="snFilter" name="studentNum" title="studentNum">
                        <input id="pageFilter" name="page" title="page">
                        <input id="countFilter" name="count" title="count">
                    </form>
                    <table class="table">
                        <colgroup>
                            <col/>
                            <col width="5%"/>
                            <col width="20%" class="left-align"/>
                            <col width="20%" class="left-align"/>
                            <col width="20%" class="left-align"/>
                            <col/>
                            <col width="10%"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th class="checkbox group">
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input class="form-check-input" type="checkbox" value="">
                                        <span class="form-check-sign"><span class="check"></span></span>
                                    </label>
                                </div>
                            </th>
                            <th>序号</th>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>邮箱</th>
                            <th>激活状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                    <iframe id="tableIframe" name="tableIframe"></iframe>
                </div>
            </div>
            <div class="card-footer">
                <ul class="navbar-nav count-limit">
                    <li class="nav-item dropdown" id="countChoice">
                        <a class="navbar-brand">每页显示：</a>
                        <a class="nav-link dropdown-toggle" id="pageCount" data-toggle="dropdown" data-count="20">
                            20
                        </a>
                        <div class="dropdown-menu">
                            <h6 class="dropdown-header">分页条目</h6>
                            <a class="dropdown-item" data-count="5">5</a>
                            <a class="dropdown-item" data-count="10">10</a>
                            <a class="dropdown-item" data-count="15">15</a>
                            <a class="dropdown-item" data-count="20">20</a>
                        </div>
                    </li>
                </ul>
                <ul class="pagination" id="pagination" style="display: none;" data-pages="">
                    <li class="page-item action-item previous-item">
                        <a class="page-link">Previous</a>
                    </li>
                    <li class="page-item action-item next-item">
                        <a class="page-link">Next</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="createModal">
    <div class="modal-dialog modal-login">
        <div class="modal-content">
            <div class="card card-signup card-plain">
                <div class="modal-header">
                    <div class="card-header card-header-primary text-center">
                        <h4 id="modalTitle" class="card-title">创建学生</h4>
                    </div>
                </div>
                <div class="modal-body">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        <i class="material-icons">clear</i>
                    </button>
                    <form class="form">
                        <div class="card-body">
                            <div class="form-group bmd-form-group">
                                <label for="studentName" class="bmd-label-floating">学生姓名</label>
                                <input id="studentName" name="studentName" type="text" autocomplete="off"
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
                                       data-toggle="popover" data-trigger="manual" data-emptyMessage="请输入电子邮箱"
                                       data-reg="^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$"
                                       data-regMessage="请输入正确的电子邮箱">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="createModalConfirm" type="button" class="btn btn-primary confirm">确认创建</button>
                    <a class="btn btn-primary btn-link btn-wd btn-lg" data-dismiss="modal">取消</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteItemsModal">
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
                <p>即将删除选中用户，请确认!</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary confirm" data-toggle="popover" data-trigger="manual"
                        data-placement="left">确认删除
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