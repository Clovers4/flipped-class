var teacherFilter;
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
    newFilter: false,
    name: "",
    teacherNum: "",
    page: 1,
    count: 20
};

var tns = [];
$(function () {
    teacherFilter = {
        form: $("#teacherFilter"),
        newFilter: $("#newFilter"),
        name: $("#nameFilter"),
        teacherNum: $("#tnFilter"),
        page: $("#pageFilter"),
        count: $("#countFilter")
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
        newFilter: true,
        count: $(countChoice.find("#pageCount")).attr("data-count")
    });


    groupCheck.click(function () {
        var check = $(this);
        if (check.hasClass("groupChecked")) {
            tableIframe.contents().find(".single .form-check-sign.checked").trigger("click");
            check.removeClass("groupChecked");
        } else {
            tableIframe.contents().find(".single .form-check-sign:not(.checked)").trigger("click");
            check.addClass("groupChecked");
        }
    });
    $("#filter-choice-name").click(function () {
        filterChoice.html($(this).html());
        filterChoice.attr("data-filter", $(this).attr("data-filter"));
    });
    $("#filter-choice-tn").click(function () {
        var choice = $(this).html();
        filterChoice.html(choice);
        filterChoice.attr("data-filter", $(this).attr("data-filter"));
    });
    $("#searchBtn").click(function () {
        var filter = {
            newFilter: true,
            page: 1
        };
        if (filterContent.val().length > 0) {
            if (filterChoice.attr("data-filter") === "name") {
                $.extend(filter, {
                    name: filterContent.val(),
                    teacherNum: ""
                })
            } else if (filterChoice.attr("data-filter") === "teacherNum") {
                $.extend(filter, {
                    name: "",
                    teacherNum: filterContent.val()
                })
            } else {
                $.extend(filter, {
                    name: "",
                    teacherNum: ""
                })
            }
            sendDataRequest(filter);
            clearBtn.show();
        }
    });
    clearBtn.click(function () {
        filterContent.val("");
        sendDataRequest({
            newFilter: true,
            name: "",
            teacherNum: "",
            page: 1
        });
        $(this).hide();
    });

    $(".previous-item").click(function () {
        var curPage = parseInt(pagination.find(".page-item.active").children("a").html());
        if (curPage !== 1) {
            refreshPageState(curPage - 1);
            sendDataRequest({
                newFilter: false,
                page: curPage - 1
            });
        }
    });
    $(".next-item").click(function () {
        var curPage = parseInt(pagination.find(".page-item.active").children("a").html());
        var pageNum = parseInt(pagination.attr("data-pages"));
        if (curPage !== pageNum) {
            refreshPageState(curPage + 1);
            sendDataRequest({
                newFilter: false,
                page: curPage + 1
            });
        }
    });
    $(countChoice).find(".dropdown-item").click(function () {
        var pageCount = $(countChoice).find("#pageCount");
        pageCount.attr("data-count", $(this).attr("data-count"));
        pageCount.html($(this).html());
        sendDataRequest({
            newFilter: true,
            page: 1,
            count: pageCount.attr("data-count")
        });
    });

    deleteItemsModal.on("show.bs.modal", function () {
        tns = [];
        var chosenBadgeNums = $("#tableIframe").contents().find(".chosen .teacherNum");
        for (var i = 0; i < chosenBadgeNums.length; ++i) {
            tns.push($(chosenBadgeNums[i]).html());
        }
    });
    deleteItemsModal.find(".confirm").click(function () {
        $.ajax({
            type: "delete",
            url: "/admin/teacher",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(tns),
            success: function () {
                deleteItemsModal.modal("hide");
                if (groupCheck.hasClass("groupChecked")) {
                    groupCheck.trigger("click");
                }
                $(document).trigger("showNotification", ["删除成功", "success"]);
                sendDataRequest(defaultFilter);
            },
            error: function () {
                $(deleteItemsModal.find(".confirm")).delayedPop("删除失败", 3);
            }
        });
    });
    $(createModal.find(".confirm")).click(function () {
        var btn = $(this);
        var form = createModal.find(".form");
        if (util.verifyWithPop(form)) {
            $.ajax({
                type: "put",
                url: "/admin/teacher",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(form.serializeObject()),
                success: function (result, status, xhr) {
                    if (xhr.status === 200) {
                        createModal.modal("hide");
                        $(document).trigger("showNotification", ["创建成功", "success"]);
                        sendDataRequest(defaultFilter);
                        form.get(0).reset();
                    }
                },
                error: function () {
                    if (xhr.status === 409) {
                        btn.delayedPop("创建失败，工号已存在", 3);
                    } else {
                        btn.delayedPop("创建失败，未知错误", 3);
                    }
                }
            });
        }
    });

    $(document).bind("pageReset", function (event, pageNum, page) {
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
    if (filter !== undefined) {
        $.extend(defaultFilter, filter);
    }
    teacherFilter.newFilter.val(defaultFilter.newFilter);
    teacherFilter.name.val(defaultFilter.name);
    teacherFilter.teacherNum.val(defaultFilter.teacherNum);
    teacherFilter.page.val(defaultFilter.page);
    teacherFilter.count.val(defaultFilter.count);

    teacherFilter.form.submit();
}

//Pagination
function refreshPageState(page) {
    pagination.find(".page-item:not(.action-item)").remove();
    var pageNum = parseInt(pagination.attr("data-pages"));
    var next = pagination.find(".next-item");
    for (var i = 1; i <= pageNum;) {
        if (i === 1 || i === pageNum || (i >= page - 1 && i <= page + 1)) {
            next.before(getPageDom(i));
            ++i;
        } else {
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
    if (pageIndex !== '...') {
        liDom.click(function () {
            var page = parseInt(liDom.find("a").html());
            refreshPageState(page);
            sendDataRequest({
                newFilter: false,
                page: page
            });
        });
    }
    return liDom;
}

function setPageActive(page) {
    page = "" + page;
    pagination.find('.active').removeClass('active');
    var pages = pagination.find(".page-item:not(.action-item)");
    for (var i = 0; i < pages.length; ++i) {
        if (page === $(pages.get(i)).find("a").html()) {
            $(pages.get(i)).addClass('active');
            break;
        }
    }
}
