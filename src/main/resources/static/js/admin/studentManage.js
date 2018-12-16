var studentFilter;
var filterChoice;
var filterContent;
var clearBtn;
var groupCheck;
var tableIframe;
var createModal;
var deleteItemsModal;
var pagination;
var countChoice;
var defaultFilter = {
    newFilter:false,
    name:"",
    studentNum:"",
    page:1,
    count:20
};

var sns = [];
$(function () {
    studentFilter ={
        form: $("#studentFilter"),
        newFilter:$("#newFilter"),
        name: $("#nameFilter"),
        studentNum:$("#snFilter"),
        page:$("#pageFilter"),
        count:$("#countFilter")
    };
    filterChoice = $("#filter-choice");
    filterContent = $("#filter-content");
    clearBtn = $("#clearBtn");
    groupCheck = $(".group .form-check-sign");
    tableIframe = $("#tableIframe");
    createModal = $("#createModal");
    deleteItemsModal = $("#deleteItemsModal");
    pagination = $("#pagination");
    countChoice = $("#countChoice");
    sendDataRequest({
        newFilter:true,
        count:$(countChoice.find("#pageCount")).attr("data-count")
    });


    groupCheck.click(function () {
        var check = $(this);
        if(check.hasClass("groupChecked")){
            tableIframe.contents().find(".single .form-check-sign.checked").trigger("click");
            check.removeClass("groupChecked");
        }else{
            tableIframe.contents().find(".single .form-check-sign:not(.checked)").trigger("click");
            check.addClass("groupChecked");
        }
    });
    $("#filter-choice-name").click(function () {
        filterChoice.html($(this).html());
        filterChoice.attr("data-filter",$(this).attr("data-filter"));
    });
    $("#filter-choice-sn").click(function () {
        var choice = $(this).html();
        filterChoice.html(choice);
        filterChoice.attr("data-filter",$(this).attr("data-filter"));
    });
    $("#searchBtn").click(function () {
        var filter = {
            newFilter:true,
            page:1
        };
        if(filterContent.val().length > 0) {
            if (filterChoice.attr("data-filter") === "name") {
                $.extend(filter, {
                    name: filterContent.val(),
                    studentNum: ""
                })
            } else if (filterChoice.attr("data-filter") === "sn") {
                $.extend(filter, {
                    name: "",
                    studentNum: filterContent.val()
                })
            } else {
                $.extend(filter, {
                    name: "",
                    studentNum: ""
                })
            }
            sendDataRequest(filter);
            clearBtn.show();
        }
    });
    clearBtn.click(function () {
        filterContent.val("");
        sendDataRequest({
            newFilter:true,
            name:"",
            studentNum:"",
            page:1
        });
        $(this).hide();
    });

    $(".previous-item").click(function () {
        var curPage = parseInt(pagination.find(".page-item.active").children("a").html());
        if(curPage !== 1){
            refreshPageState(curPage - 1);
            sendDataRequest({
                newFilter:false,
                page:curPage - 1
            });
        }
    });
    $(".next-item").click(function () {
        var curPage = parseInt(pagination.find(".page-item.active").children("a").html());
        var pageNum = parseInt(pagination.attr("data-pages"));
        if(curPage !== pageNum){
            refreshPageState(curPage + 1);
            sendDataRequest({
                newFilter:false,
                page:curPage + 1
            });
        }
    });
    $(countChoice).find(".dropdown-item").click(function () {
        var pageCount = $(countChoice).find("#pageCount");
        pageCount.attr("data-count", $(this).attr("data-count"));
        pageCount.html($(this).html());
        sendDataRequest({
            newFilter:true,
            page:1,
            count:pageCount.attr("data-count")
        });
    });

    $(createModal.find(".confirm")).click(function () {
        var btn = $(this);
        var form = createModal.find(".form");
        if(util.verifyWithPop(form)) {
            $.ajax({
                type: "put",
                url: "/admin/student",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(form.serializeObject()),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        createModal.modal("hide");
                        $(document).trigger("showNotification", ["创建成功", "success"]);
                        sendDataRequest(defaultFilter);
                        form.get(0).reset();
                    } else if (xhr.status === 204) {
                        btn.delayedPop("创建失败，学号已存在", 3);
                    }
                },
                error: function () {
                    btn.delayedPop("创建失败，未知错误", 3);
                }
            });
        }
    });
    deleteItemsModal.on("show.bs.modal",function () {
        sns = [];
        var chosenStuNums = $("#tableIframe").contents().find(".chosen .studentNum");
        for(var i = 0; i < chosenStuNums.length ; ++i ){
            sns.push($(chosenStuNums[i]).html());
        }
    });
    deleteItemsModal.find(".confirm").click(function () {
        $.ajax({
            type:"delete",
            url:"/admin/student",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(sns),
            success:function () {
                deleteItemsModal.modal("hide");
                if (groupCheck.hasClass("groupChecked")) {
                    groupCheck.trigger("click");
                }
                $(document).trigger("showNotification", ["删除成功", "success"]);
                sendDataRequest(defaultFilter);
            },
            error:function () {
                $(deleteItemsModal.find(".confirm")).delayedPop("删除失败", 3);
            }
        });
    });

    $(document).bind("pageReset",function (event,pageNum,page) {
        pagination.attr("data-pages", pageNum);
        refreshPageState(page);
        pagination.show();
    });
    $(document).bind("showNotification", function (event, message, type) {
        var alert = $("#" + type + "Alert");
        $(alert.find("b")).html(message);
        alert.slideDown();
        setTimeout(function () {
            alert.slideUp();
        }, 3000);
    });
});

//Data Request
function sendDataRequest(filter) {
    if(filter!==undefined){
        $.extend(defaultFilter, filter);
    }
    studentFilter.newFilter.val(defaultFilter.newFilter);
    studentFilter.name.val(defaultFilter.name);
    studentFilter.studentNum.val(defaultFilter.studentNum);
    studentFilter.page.val(defaultFilter.page);
    studentFilter.count.val(defaultFilter.count);

    studentFilter.form.submit();
}

//Pagination
function refreshPageState(page) {
    pagination.find(".page-item:not(.action-item)").remove();
    var pageNum = parseInt(pagination.attr("data-pages"));
    var next = pagination.find(".next-item");
    for(var i = 1 ; i <= pageNum;){
        if(i === 1 || i === pageNum || (i>=page-1 && i<=page+1)) {
            next.before(getPageDom(i));
            ++i;
        }else{
            next.before(getPageDom("..."));
            while (!(i === 1 || i === pageNum || (i >= page - 1 && i <= page + 1))) {
                ++i;
            }
        }
    }
    setPageActive(page);
}
function getPageDom(pageIndex) {
    var aDom = $("<a></a>");
    aDom.addClass("page-link");
    aDom.html(pageIndex);
    var liDom = $("<li></li>");
    liDom.addClass("page-item");
    liDom.append(aDom);
    if(pageIndex !== '...') {
        liDom.click(function () {
            var page = parseInt(liDom.find("a").html());
            refreshPageState(page);
            sendDataRequest({
                newFilter:false,
                page:page
            });
        });
    }
    return liDom;
}
function setPageActive(page) {
    page = "" + page;
    pagination.find('.active').removeClass('active');
    var pages = pagination.find(".page-item:not(.action-item)");
    for(var i = 0 ; i < pages.length; ++i){
        if(page === $(pages.get(i)).find("a").html()){
            $(pages.get(i)).addClass('active');
            break;
        }
    }
}
