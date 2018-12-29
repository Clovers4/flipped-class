<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="apple-touch-icon" sizes="76x76" href="/static/imgs/apple-icon.png">
    <link rel="icon" type="image/png" href="/static/imgs/favicon.png">
    <link rel="stylesheet" href="/static/css/other/style.css">
    <link rel="stylesheet" href="/static/css/other/framework.css">
    <link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/lib/layui/css/layui.css">
    <script src="http://cdn.staticfile.org/jquery/1.11.0/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/jqueryui/1.9.2/i18n/jquery-ui-i18n.min.js"></script>
    <script src="/static/js/other/framework.js"></script>
    <script src="/static/js/other/framework.launcher.js"></script>
    <script src="/static/lib/layui/layui.js"></script>

    <title>创建课程</title>
</head>
<body>

<div id="preloader">
    <div id="status">
        <p class="center-text">
            Loading the content...
            <em>Loading depends on your connection speed!</em>
        </p>
    </div>
</div>

<div class="top-deco"></div>
<div class="navigation-back">
    <h1 class="navigation-back">新建课程</h1>
    <a href="/teacher/courseList" class="button-close" style="margin-top:-45px;">x</a>
</div>
<div class="distace3"></div>
<div class="decoration"></div>

<div class="content">
    <div class="distance3"></div>
    <div class="container no-bottom">
        <form class="layui-form contactForm" method="post" id="contactForm">
            <div class="formSuccessMessageWrap" id="formSuccessMessageWrap">
                <div class="static-notification-green tap-dismiss-notification">
                    <p style="color:#d44950;">课程创建失败！</p>
                </div>
            </div>
            <div class="formFieldWrap">
                <label class="field-title contactNameField" for="contactNameField">课程名称：<span>(required)</span></label>
                <input type="text" name="courseName" value="" class="contactField requiredField" id="contactNameField"/>
            </div>
            <div class="formValidationError" id="contactNameFieldError">
                <div class="static-notification-red tap-dismiss-notification">
                    <p class="uppercase">请填写课程名!</p>
                </div>
            </div>
            <div class="formTextareaWrap">
                <label class="field-title contactNameField"
                       for="contactMessageTextarea">课程要求：<span>(required)</span></label>
                <textarea name="introduction" class="contactTextarea requiredField"
                          id="contactMessageTextarea"></textarea>
            </div>
            <div class="formValidationError" id="contactMessageTextareaError">
                <div class="static-notification-red tap-dismiss-notification">
                    <p class="uppercase">请填写课程要求</p>
                </div>
            </div>
            <div class="decoration"></div>
            <div class="distance3"></div>
            <label class="field-title contactMessageTextarea"
                   for="contactMessageTextarea">成绩计算规则：<span>(required)</span></label>
            <div>
                <div class="distance3"></div>
                <div class="layui-form-item">
                    <label class="layui-form-label">课堂展示</label>
                    <div class="layui-input-block">
                        <select name="presentationPercentage" lay-filter="aihao">
                            <option value="0">0</option>
                            <option value="10">10%</option>
                            <option value="20">20%</option>
                            <option value="30">30%</option>
                            <option value="40" selected>40%</option>
                            <option value="50">50%</option>
                            <option value="60">60%</option>
                            <option value="70">70%</option>
                            <option value="80">80%</option>
                            <option value="90">90%</option>
                            <option value="100">100%</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">课堂提问</label>
                    <div class="layui-input-block">
                        <select name="questionPercentage" lay-filter="aihao">
                            <option value="0">0</option>
                            <option value="10">10%</option>
                            <option value="20">20%</option>
                            <option value="30" selected>30%</option>
                            <option value="40">40%</option>
                            <option value="50">50%</option>
                            <option value="60">60%</option>
                            <option value="70">70%</option>
                            <option value="80">80%</option>
                            <option value="90">90%</option>
                            <option value="100">100%</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">书面报告</label>
                    <div class="layui-input-block">
                        <select name="reportPercentage" lay-filter="aihao">
                            <option value="0">0</option>
                            <option value="10">10%</option>
                            <option value="20">20%</option>
                            <option value="30" selected>30%</option>
                            <option value="40">40%</option>
                            <option value="50">50%</option>
                            <option value="60">60%</option>
                            <option value="70">70%</option>
                            <option value="80">80%</option>
                            <option value="90">90%</option>
                            <option value="100">100%</option>
                        </select>
                    </div>
                </div>

                <div class="distance4"></div>
            </div>
            <div class="decoration"></div>
            <div class="distance3"></div>
            <label class="field-title contactMessageTextarea"
                   for="contactMessageTextarea">组队相关规则：<span>(required)</span></label>
            <div>
                <div class="distance3"></div>
                <div class="layui-form-item">
                    <label class="layui-form-label">小组人数上限</label>
                    <div class="layui-input-block">
                        <select name="maxTeamMember" lay-filter="aihao">
                            <option value="3">3人</option>
                            <option value="4">4人</option>
                            <option value="5" selected>5人</option>
                            <option value="6">6人</option>
                            <option value="7">7人</option>
                            <option value="8">8人</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item margin1 ">
                    <label class="layui-form-label">小组人数下限</label>
                    <div class="layui-input-block">
                        <select name="minTeamMember" lay-filter="aihao">
                            <option value="1" selected>1人</option>
                            <option value="2">2人</option>
                            <option value="3">3人</option>
                            <option value="4">4人</option>
                            <option value="5">5人</option>
                        </select>
                    </div>
                </div>
                <div class="layui-form-item margin2">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input name="teamStartTime" type="text" class="layui-input requiredField" id="test5"
                                   placeholder="yyyy-MM-dd HH:mm:ss">
                        </div>
                    </div>
                    <label class="layui-form-label moveUp">组队开始时间</label>
                </div>
                <div class="distance6"></div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input name="teamEndTime" type="text" class="layui-input requiredField" id="test5"
                                   placeholder="yyyy-MM-dd HH:mm:ss">
                        </div>
                    </div>
                    <label class="layui-form-label moveUp">组队截止时间</label>
                    <div class="formValidationError" id="test5Error">
                        <div class="static-notification-red tap-dismiss-notification">
                            <p class="uppercase">请填写组队相关时间</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="decoration"></div>
            <div class="distance3"></div>
            <label id="memberLimit" class="field-title contactMessageTextarea"
                   for="contactMessageTextarea">组内选修课程人数：<span>(required)</span></label>

            <div class="memberLimit">
                <div class="memberLimitation">
                    <div class="distance3"></div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修课程</label>
                        <div class="my-layui-input">
                            <select name="optionCourse" lay-filter="aihao">
                                <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="my-form-label">选修人数上限</label>
                        <div class="my-layui-input">
                            <select name="maxTeamMember" lay-filter="aihao">
                                <option value="2">2人</option>
                                <option value="3">3人</option>
                                <option value="4">4人</option>
                                <option value="5" selected>5人</option>
                                <option value="6">6人</option>
                                <option value="7">7人</option>
                                <option value="8">8人</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修人数下限</label>
                        <div class="my-layui-input">
                            <select name="minTeamMember" lay-filter="aihao">
                                <option value="1">1人</option>
                                <option value="2">2人</option>
                                <option value="3" selected>3人</option>
                                <option value="4">4人</option>
                                <option value="5">5人</option>
                            </select>
                        </div>
                    </div>
                    <div class="my-decoration"></div>
                </div>
                <div class="memberLimitation">
                    <div class="distance3"></div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修课程</label>
                        <div class="my-layui-input">
                            <select name="optionCourse" lay-filter="aihao">
                                <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="my-form-label">选修人数上限</label>
                        <div class="my-layui-input">
                            <select name="maxTeamMember" lay-filter="aihao">
                                <option value="2">2人</option>
                                <option value="3">3人</option>
                                <option value="4">4人</option>
                                <option value="5" selected>5人</option>
                                <option value="6">6人</option>
                                <option value="7">7人</option>
                                <option value="8">8人</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修人数下限</label>
                        <div class="my-layui-input">
                            <select name="minTeamMember" lay-filter="aihao">
                                <option value="1">1人</option>
                                <option value="2">2人</option>
                                <option value="3" selected>3人</option>
                                <option value="4">4人</option>
                                <option value="5">5人</option>
                            </select>
                        </div>
                    </div>
                    <div class="my-decoration"></div>
                </div>
                <div class="memberLimitation">
                    <div class="distance3"></div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修课程</label>
                        <div class="my-layui-input">
                            <select name="optionCourse" lay-filter="aihao">
                                <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="my-form-label">选修人数上限</label>
                        <div class="my-layui-input">
                            <select name="maxTeamMember" lay-filter="aihao">
                                <option value="2">2人</option>
                                <option value="3">3人</option>
                                <option value="4">4人</option>
                                <option value="5" selected>5人</option>
                                <option value="6">6人</option>
                                <option value="7">7人</option>
                                <option value="8">8人</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修人数下限</label>
                        <div class="my-layui-input">
                            <select name="minTeamMember" lay-filter="aihao">
                                <option value="1">1人</option>
                                <option value="2">2人</option>
                                <option value="3" selected>3人</option>
                                <option value="4">4人</option>
                                <option value="5">5人</option>
                            </select>
                        </div>
                    </div>
                    <div class="my-decoration"></div>
                </div>
                <div class="memberLimitation">
                    <div class="distance3"></div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修课程</label>
                        <div class="my-layui-input">
                            <select name="optionCourse" lay-filter="aihao">
                                <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="my-form-label">选修人数上限</label>
                        <div class="my-layui-input">
                            <select name="maxTeamMember" lay-filter="aihao">
                                <option value="2">2人</option>
                                <option value="3">3人</option>
                                <option value="4">4人</option>
                                <option value="5" selected>5人</option>
                                <option value="6">6人</option>
                                <option value="7">7人</option>
                                <option value="8">8人</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修人数下限</label>
                        <div class="my-layui-input">
                            <select name="minTeamMember" lay-filter="aihao">
                                <option value="1">1人</option>
                                <option value="2">2人</option>
                                <option value="3" selected>3人</option>
                                <option value="4">4人</option>
                                <option value="5">5人</option>
                            </select>
                        </div>
                    </div>
                    <div class="my-decoration"></div>
                </div>
                <div class="memberLimitation">
                    <div class="distance3"></div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修课程</label>
                        <div class="my-layui-input">
                            <select name="optionCourse" lay-filter="aihao">
                                <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="my-form-label">选修人数上限</label>
                        <div class="my-layui-input">
                            <select name="maxTeamMember" lay-filter="aihao">
                                <option value="2">2人</option>
                                <option value="3">3人</option>
                                <option value="4">4人</option>
                                <option value="5" selected>5人</option>
                                <option value="6">6人</option>
                                <option value="7">7人</option>
                                <option value="8">8人</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修人数下限</label>
                        <div class="my-layui-input">
                            <select name="minTeamMember" lay-filter="aihao">
                                <option value="1">1人</option>
                                <option value="2">2人</option>
                                <option value="3" selected>3人</option>
                                <option value="4">4人</option>
                                <option value="5">5人</option>
                            </select>
                        </div>
                    </div>
                    <div class="my-decoration"></div>
                </div>
                <div class="memberLimitation">
                    <div class="distance3"></div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修课程</label>
                        <div class="my-layui-input">
                            <select name="optionCourse" lay-filter="aihao">
                                <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="my-form-label">选修人数上限</label>
                        <div class="my-layui-input">
                            <select name="maxTeamMember" lay-filter="aihao">
                                <option value="2">2人</option>
                                <option value="3">3人</option>
                                <option value="4">4人</option>
                                <option value="5" selected>5人</option>
                                <option value="6">6人</option>
                                <option value="7">7人</option>
                                <option value="8">8人</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修人数下限</label>
                        <div class="my-layui-input">
                            <select name="minTeamMember" lay-filter="aihao">
                                <option value="1">1人</option>
                                <option value="2">2人</option>
                                <option value="3" selected>3人</option>
                                <option value="4">4人</option>
                                <option value="5">5人</option>
                            </select>
                        </div>
                    </div>
                    <div class="my-decoration"></div>
                </div>
                <div class="memberLimitation">
                    <div class="distance3"></div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修课程</label>
                        <div class="my-layui-input">
                            <select name="optionCourse" lay-filter="aihao">
                                <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="my-form-label">选修人数上限</label>
                        <div class="my-layui-input">
                            <select name="maxTeamMember" lay-filter="aihao">
                                <option value="2">2人</option>
                                <option value="3">3人</option>
                                <option value="4">4人</option>
                                <option value="5" selected>5人</option>
                                <option value="6">6人</option>
                                <option value="7">7人</option>
                                <option value="8">8人</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修人数下限</label>
                        <div class="my-layui-input">
                            <select name="minTeamMember" lay-filter="aihao">
                                <option value="1">1人</option>
                                <option value="2">2人</option>
                                <option value="3" selected>3人</option>
                                <option value="4">4人</option>
                                <option value="5">5人</option>
                            </select>
                        </div>
                    </div>
                    <div class="my-decoration"></div>
                </div>
                <div class="memberLimitation">
                    <div class="distance3"></div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修课程</label>
                        <div class="my-layui-input">
                            <select name="optionCourse" lay-filter="aihao">
                                <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="my-form-label">选修人数上限</label>
                        <div class="my-layui-input">
                            <select name="maxTeamMember" lay-filter="aihao">
                                <option value="2">2人</option>
                                <option value="3">3人</option>
                                <option value="4">4人</option>
                                <option value="5" selected>5人</option>
                                <option value="6">6人</option>
                                <option value="7">7人</option>
                                <option value="8">8人</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item margin2 ">
                        <label class="my-form-label">选修人数下限</label>
                        <div class="my-layui-input">
                            <select name="minTeamMember" lay-filter="aihao">
                                <option value="1">1人</option>
                                <option value="2">2人</option>
                                <option value="3" selected>3人</option>
                                <option value="4">4人</option>
                                <option value="5">5人</option>
                            </select>
                        </div>
                    </div>
                    <div class="my-decoration"></div>
                </div>
            </div>
            <p class="center center-text">
                <button type="button" id="addBtn1" class="layui-btn">
                    <i class="layui-icon">&#xe608;</i> 新增
                </button>
            </p>
            <div id="numberRules" class="layui-form-item margin2 ">
                <label class="layui-form-label">选修课人数要求：</label>
                <div class="layui-input-block">
                    <select name="choose" lay-filter="aihao">
                        <option value="0" selected>均满足</option>
                        <option value="1">仅一个满足</option>
                    </select>
                </div>
            </div>
            <div class="decoration"></div>
            <div class="distance3"></div>
            <label id="courseLimit" class="field-title contactMessageTextarea" for="contactMessageTextarea">冲突课程：<span>(required)</span></label>
            <div class="courseLimit">
                <div class="courseLimitation">
                    <div class="distance3"></div>
                    <div>
                        <div class="layui-row" style="margin-right:25px;margin-bottom:10px;">
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <p class="center center-text">
                        <button style="margin-bottom:20px;" type="button" class="layui-btn sub-addBtn2">
                            <i class="layui-icon">&#xe608;</i> 新增
                        </button>
                    </p>
                </div>
                <div class="courseLimitation">
                    <div class="distance3"></div>
                    <div>
                        <div class="layui-row" style="margin-right:25px;margin-bottom:10px;">
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <p class="center center-text">
                        <button style="margin-bottom:20px;" type="button" class="layui-btn sub-addBtn2">
                            <i class="layui-icon">&#xe608;</i> 新增
                        </button>
                    </p>
                </div>
                <div class="courseLimitation">
                    <div class="distance3"></div>
                    <div>
                        <div class="layui-row" style="margin-right:25px;margin-bottom:10px;">
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <p class="center center-text">
                        <button style="margin-bottom:20px;" type="button" class="layui-btn sub-addBtn2">
                            <i class="layui-icon">&#xe608;</i> 新增
                        </button>
                    </p>
                </div>
                <div class="courseLimitation">
                    <div class="distance3"></div>
                    <div>
                        <div class="layui-row" style="margin-right:25px;margin-bottom:10px;">
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <p class="center center-text">
                        <button style="margin-bottom:20px;" type="button" class="layui-btn sub-addBtn2">
                            <i class="layui-icon">&#xe608;</i> 新增
                        </button>
                    </p>
                </div>
                <div class="courseLimitation">
                    <div class="distance3"></div>
                    <div>
                        <div class="layui-row" style="margin-right:25px;margin-bottom:10px;">
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <p class="center center-text">
                        <button style="margin-bottom:20px;" type="button" class="layui-btn sub-addBtn2">
                            <i class="layui-icon">&#xe608;</i> 新增
                        </button>
                    </p>
                </div>
                <div class="courseLimitation">
                    <div class="distance3"></div>
                    <div>
                        <div class="layui-row" style="margin-right:25px;margin-bottom:10px;">
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <p class="center center-text">
                        <button style="margin-bottom:20px;" type="button" class="layui-btn sub-addBtn2">
                            <i class="layui-icon">&#xe608;</i> 新增
                        </button>
                    </p>
                </div>
                <div class="courseLimitation">
                    <div class="distance3"></div>
                    <div>
                        <div class="layui-row" style="margin-right:25px;margin-bottom:10px;">
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                            <div style="margin-bottom:8px;" class="subPanel layui-col-xs6 layui-col-sm4 layui-col-md4">
                                <select name="optionCourse" lay-filter="aihao">
                                    <option value="0" selected>无</option>
                                <#list courseList as course>
                                    <option value="${course.id}">${course.courseName}</option>
                                </#list>
                                </select>
                            </div>
                        </div>
                    </div>
                    <p class="center center-text">
                        <button style="margin-bottom:20px;" type="button" class="layui-btn sub-addBtn2">
                            <i class="layui-icon">&#xe608;</i> 新增
                        </button>
                    </p>
                </div>
                <p class="center center-text">
                    <button type="button" id="addBtn2" class="layui-btn">
                        <i class="layui-icon">&#xe608;</i> 新增
                    </button>
                </p>
            </div>
            <div class="decoration"></div>
            <div class="distance4"></div>
            <p class="center center-text"><input type="button" class="button-big button-dark" id="contactSubmitButton"
                                                 value="创建课程" data-formId="contactForm"/></p>
        </form>
        <div class="distance2"></div>
        <!--
    <div class="decoration"></div>
    <div class="footer">
        <div class="clear"></div>
        <p class="copyright">
            Copyright @2018 developed by Group 3-2.<br>
            All Rights Reserved
        </p>
    </div>
    -->
    </div>
</div>
<style>
    @media screen and (min-width: 768px) and (max-width: 1024px) {
        .layui-form-item .layui-inline {
            margin-bottom: 5px;
            margin-right: 10px;
            margin-left: 11%;
        }
    }

    @media screen and (max-width: 768px) {
        .layui-form-item .layui-inline {
            display: block;
            margin-right: -70px;
            margin-left: 50px;
            margin-bottom: 10px;
            clear: both;
        }
    }

    @media screen and (min-width: 1024px) {
        .layui-form-item .layui-inline {
            margin-bottom: 5px;
            margin-right: 10px;
            margin-left: 17%;
        }
    }

    .memberLimitation {
        background-color: #fff;
        box-shadow: 0 3px 5px rgba(0, 0, 0, 0.12), 0 3px 5px rgba(0, 0, 0, 0.24);
    }

    .courseLimitation {
        background-color: #fff;
        box-shadow: 0 3px 5px rgba(0, 0, 0, 0.12), 0 3px 5px rgba(0, 0, 0, 0.24);
    }

    .my-layui-input {
        margin-right: 5%;
    }

    .my-form-label {
        float: left;
        display: block;
        padding-left: 8%;
        /* padding: 9px 95px; */
        font-weight: 400;
        line-height: 20px;
        text-align: right;
        margin-bottom: -20px;
    }

    @media screen and (min-width: 1024px) {
        .memberLimitation {
            background-color: #fff;
            box-shadow: 0 3px 5px rgba(0, 0, 0, 0.12), 0 3px 5px rgba(0, 0, 0, 0.24);
            margin-right: 25%;
            margin-left: 25%;
        }

        .courseLimitation {
            background-color: #fff;
            box-shadow: 0 3px 5px rgba(0, 0, 0, 0.12), 0 3px 5px rgba(0, 0, 0, 0.24);
            margin-right: 25%;
            margin-left: 25%;
        }

        .my-layui-input {
            margin-right: 10%;
        }

        .my-form-label {
            float: left;
            display: block;
            padding-left: 8%;
            /* padding: 9px 95px; */
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            margin-bottom: -20px;
        }
    }

    @media screen and (min-width: 768px) and (max-width: 1024px) {
        .memberLimitation {
            background-color: #fff;
            box-shadow: 0 3px 5px rgba(0, 0, 0, 0.12), 0 3px 5px rgba(0, 0, 0, 0.24);
            margin-right: 25%;
            margin-left: 25%;
        }

        .courseLimitation {
            background-color: #fff;
            box-shadow: 0 3px 5px rgba(0, 0, 0, 0.12), 0 3px 5px rgba(0, 0, 0, 0.24);
            margin-right: 25%;
            margin-left: 25%;
        }

        .my-layui-input {
            margin-right: 10%;
        }

        .my-form-label {
            float: left;
            display: block;
            padding-left: 8%;
            /* padding: 9px 95px; */
            font-weight: 400;
            line-height: 20px;
            text-align: right;
            margin-bottom: -20px;
        }
    }

    .layui-input-block {
        margin-left: 110px;
        min-height: 36px;
    }

    .my-decoration {
        height: 1px;
        background-color: rgba(0, 0, 0, 0.2);
        margin-bottom: 20px;
        display: block;
        clear: both;
        width: 85%;
        margin-left: auto;
        margin-right: auto;
    }

    .margin2 {
        margin-bottom: 10px;
    }
</style>
<!--<div class="bottom-deco"></div>-->
<script src="/layui/layui.js"></script>
<script>
    layui.use('form', function () {
        var form = layui.form();
        //各种基于事件的操作，下面会有进一步介绍
    });
</script>
<script>
    $('#formSuccessMessageWrap').hide(0);
    $('.formValidationError').fadeOut(0);
    // fields focus function starts
    $('input[type="text"], input[type="password"], textarea').focus(function () {
        if ($(this).val() == $(this).attr('data-dummy')) {
            $(this).val('');
        }
        ;
    });
    // fields focus function ends
    // fields blur function starts
    $('input, textarea').blur(function () {
        if ($(this).val() == '') {
            $(this).val($(this).attr('data-dummy'));
        }
        ;
    });
    $(".courseLimitation").hide();
    $(".subPanel").hide();
    $(".memberLimitation").hide();
    $("#numberRules").hide();
    //添加选课人数限制
    //count1,count2,subCount2Y用于记录已用的下拉框index
    //需要传给后端的为：0~index的下拉框数据，
    //因为Index后的下拉框仍被隐藏，未投入使用
    var count1 = 0;
    $('#addBtn1').click(function () {
        var currentLimit = $(".memberLimitation").eq(count1);
        $(currentLimit).fadeIn();
        $(currentLimit).addClass("memberShow");
        if (count1 == 1) {
            $("#numberRules").fadeIn();
        }
        count1++;
    });
    //添加课程冲突组
    var count2 = 0;
    $('#addBtn2').click(function () {
        $(".courseLimitation").eq(count2).fadeIn();
        $(".courseLimitation").eq(count2).addClass("courseShow");
        count2++;
    });
    //课程冲突组中，添加课程
    var subCount2 = [0, 0, 0, 0, 0, 0];
    $('.sub-addBtn2').click(function () {
        var tempIndex = $(this).parents().parents().index();//第tempIndex个冲突课程组的按钮，发起新增课程请求
        $(".courseLimitation").eq(tempIndex).find(".subPanel").eq(subCount2[tempIndex]).fadeIn();
        $(".courseLimitation").eq(tempIndex).find(".subPanel").eq(subCount2[tempIndex]).addClass("subCourseShow");
        subCount2[tempIndex]++;
        // alert(subCount2[tempIndex]);
    });
    //数组初始化
    var memberArray = []; //二维数组，外层为每个课程组员人数要求，内层为课程号、人数上限、人数下限
    var courseArray = []; //二维数组，外层为每个课程冲突组，内层为每个组内包含的课程号
    // for(var k=0;k<8;k++){
    //     memberArray[k]=[];
    //     courseArray[k]=[];
    //     for(var j=0;j<3;j++){
    //         memberArray[k][j]=0;
    //         courseArray[k][j]=0;
    //     }
    // }
    var tempMemberCount = 0;
    var tempCourseCount = 0;
    var line;
    $('#contactSubmitButton').click(function () {
        if ($('#contactNameField').val() == '' || $('#contactNameField').val() == $('#contactNameField').attr('data-dummy')) {
            // $(this).val($(this).attr('data-dummy'));
            // $(this).focus();
            $('#contactNameField').addClass('fieldHasError');
            $('#contactNameFieldError').fadeIn(300);
            return false;
        }
        ;
        if ($('#contactMessageTextarea').val() == '' || $('#contactMessageTextarea').val() == $('#contactMessageTextarea').attr('data-dummy')) {
            // $(this).val($(this).attr('data-dummy'));
            // $(this).focus();
            $('#contactMessageTextarea').addClass('fieldHasError');
            $('#contactMessageTextareaError').fadeIn(300);
            return false;
        }
        ;
        $(".memberLimitation.memberShow").each(function () {
            line = $(this).index();
            memberArray[line] = [];
            tempMemberCount = 0;
            $(this).find('select option:selected').each(function () {
                memberArray[line][tempMemberCount] = $(this).attr("value");
                tempMemberCount++;
            });
            // memberArray[tempMemberCount]=$(this).find('select option:selected').attr("value");
            // alert(memberArray[tempMemberCount]);
        });
        var members = JSON.stringify(memberArray);
        $(".courseLimitation.courseShow").each(function () {
            // alert("进入课程冲突");
            line = $(this).index();
            courseArray[line] = [];
            tempCourseCount = 0;
            $(this).find('.subPanel.subCourseShow').each(function () {
                tempCourseCount = $(this).index();
                courseArray[line][tempCourseCount] = $(this).find('select option:selected').attr("value");
                tempCourseCount++;
            });
        });
        var conflicts = JSON.stringify(courseArray);
        var fd = new FormData($('#contactForm')[0]);
        var courseName = fd.get("courseName");
        var introduction = fd.get("introduction");
        var presentationPercentage = fd.get("presentationPercentage");
        var questionPercentage = fd.get("questionPercentage");
        var reportPercentage = fd.get("reportPercentage");
        var maxTeamMember = fd.get("maxTeamMember");
        var minTeamMember = fd.get("minTeamMember");
        var teamStartTime = fd.get("teamStartTime");
        var teamEndTime = fd.get("teamEndTime");
        var choose = fd.get("choose");
        $.ajax(
                {
                    url: "/teacher/course",
                    type: 'post',
                    data: {
                        "members": members,
                        "conflicts": conflicts,
                        "courseName": courseName,
                        "introduction": introduction,
                        "presentationPercentage": presentationPercentage,
                        "questionPercentage": questionPercentage,
                        "reportPercentage": reportPercentage,
                        "maxTeamMember": maxTeamMember,
                        "minTeamMember": minTeamMember,
                        "teamStartTime": teamStartTime,
                        "teamEndTime": teamEndTime,
                        "choose": choose
                    },
                    success: function (data, status, response) {
                        if (response.status == "200") {
                            var info = response.responseText;
                            window.location.href = "/teacher/courseList";
                        }
                    },
                    error: function (data, status) {
                        console.log(data);
                        console.log(status);
                        $('#formSuccessMessageWrap').fadeIn(500);
                        formSubmitted = 'false';
                    }
                }
        );
    });
</script>

<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //日期时间选择器
        laydate.render({
            elem: '#test5'
            , type: 'datetime'
        });
        //自定义重要日
        laydate.render({
            elem: '#test18'
            , mark: {
                '0-10-14': '生日'
                , '0-12-31': '跨年' //每年的日期
                , '0-0-10': '工资' //每月某天
                , '0-0-15': '月中'
                , '2017-8-15': '' //如果为空字符，则默认显示数字+徽章
                , '2099-10-14': '呵呵'
            }
            , done: function (value, date) {
                if (date.year === 2017 && date.month === 8 && date.date === 15) { //点击2017年8月15日，弹出提示语
                    layer.msg('这一天是：中国人民抗日战争胜利72周年');
                }
            }
        });
        //限定可选日期
        var ins22 = laydate.render({
            elem: '#test-limit1'
            , min: '2016-10-14'
            , max: '2080-10-14'
            , ready: function () {
                ins22.hint('日期可选值设定在 <br> 2016-10-14 到 2080-10-14');
            }
        });
        //前后若干天可选，这里以7天为例
        laydate.render({
            elem: '#test-limit2'
            , min: -7
            , max: 7
        });
        //限定可选时间
        laydate.render({
            elem: '#test-limit3'
            , type: 'time'
            , min: '09:30:00'
            , max: '17:30:00'
            , btns: ['clear', 'confirm']
        });
        //同时绑定多个
        lay('.test-item').each(function () {
            laydate.render({
                elem: this
                , trigger: 'click'
            });
        });
        //初始赋值
        laydate.render({
            elem: '#test19'
            , value: '1989-10-14'
            , isInitValue: true
        });
        //选中后的回调
        laydate.render({
            elem: '#test20'
            , done: function (value, date) {
                layer.alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
            }
        });
        //日期切换的回调
        laydate.render({
            elem: '#test21'
            , change: function (value, date) {
                layer.msg('你选择的日期是：' + value + '<br><br>获得的对象是' + JSON.stringify(date));
            }
        });
        //墨绿主题
        laydate.render({
            elem: '#test29'
            , theme: 'molv'
        });
    });
</script>

</body>
</html>