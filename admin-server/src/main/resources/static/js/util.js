var util = {
    _iconMapper: {
        success: "check",
        info: "info_outline",
        warning: "warning",
        danger: "clear"
    },
    singleVerifyWithPop: function (input) {
        if (input.hasClass("empty-verify")) {
            if (input.val() == null || input.val() === "") {
                input.delayedPop(input.attr("data-emptyMessage"), 3);
                input.focus();
                return false;
            }
        }
        if (input.hasClass("reg-verify")) {
            if (!(new RegExp(input.attr("data-reg")).test(input.val()))) {
                input.delayedPop(input.attr("data-regMessage"), 3);
                input.focus();
                return false;
            }
        }
        return true;
    },
    singleVerifyWithAlert: function (input) {
        if (input.hasClass("empty-verify")) {
            if (input.val() == null || input.val() === "") {
                this.showAlert("warning", input.attr("data-emptyMessage"), 3);
                input.registerDanger();
                return false;
            }
        }
        if (input.hasClass("reg-verify")) {
            if (!(new RegExp(input.attr("data-reg")).test(input.val()))) {
                this.showAlert("warning", input.attr("data-regMessage"), 3);
                input.registerDanger();
                return false;
            }
        }
        return true;
    },
    //TODO:Refine this verify function. Make it to be lazy-handled
    verifyWithPop: function (form) {
        var emptyVerifies = form.find(".empty-verify");
        for (var i = 0; i < emptyVerifies.length; ++i) {
            var emptyVerify = $(emptyVerifies.get(i));
            if (emptyVerify.val() == null || emptyVerify.val() === "") {
                emptyVerify.delayedPop(emptyVerify.attr("data-emptyMessage"), 3);
                emptyVerify.registerDanger();
                return false;
            }
        }
        var regVerifies = form.find(".reg-verify");
        for (i = 0; i < regVerifies.length; ++i) {
            var regVerify = $(regVerifies.get(i));
            if (!(new RegExp(regVerify.attr("data-reg")).test(regVerify.val()))) {
                regVerify.delayedPop(regVerify.attr("data-regMessage"), 3);
                regVerify.registerDanger();
                return false;
            }
        }
        return true;
    },
    verifyWithAlert: function (form) {
        var emptyVerifies = form.find(".empty-verify");
        for (var i = 0; i < emptyVerifies.length; ++i) {
            var emptyVerify = $(emptyVerifies.get(i));
            if (emptyVerify.val() == null || emptyVerify.val() === "") {
                this.showAlert("warning", emptyVerify.attr("data-emptyMessage"), 3);
                return emptyVerify;
            }
        }
        var regVerifies = form.find(".reg-verify");
        for (i = 0; i < regVerifies.length; ++i) {
            var regVerify = $(regVerifies.get(i));
            if (!(new RegExp(regVerify.attr("data-reg")).test(regVerify.val()))) {
                this.showAlert("warning", regVerify.attr("data-regMessage"), 3);
                return regVerify;
            }
        }
        return null;
    },

    _generateAlertDom: function (type, message) {
        var iconDOM = $("<div class='alert-icon'><i class='material-icons'>" + this._iconMapper[type] + "</i></div>");
        var messageDOM = $("<b>" + message + "</b>");
        var containerDOM = $("<div class='container'></div>");
        var alertDOM = $("<div class='alert' style='display: none'></div>");
        alertDOM.addClass("alert-" + type);
        containerDOM.append(iconDOM);
        containerDOM.append(messageDOM);
        alertDOM.append(containerDOM);
        return alertDOM;
    },
    showAlert: function (type, message, second) {
        var alertDom = this._generateAlertDom(type, message);
        var alertArea = $(".alert-area");
        $(alertArea.find(".alert")).remove();
        alertArea.append(alertDom);
        alertDom.slideDown("slow");
        setTimeout(function () {
            alertDom.slideUp("slow", function () {
                alertDom.remove();
            });
        }, second * 1000);
    },
    showLoading: function (message) {
        if ($("#loading").size !== 0) {
            var loadingDom = $("<div class='loading' id='loading'></div>");
            var animDom = $("<div class=\"sk-double-bounce\"></div>");
            animDom.append($("<div class=\"sk-child sk-double-bounce1\"></div>"));
            animDom.append($("<div class=\"sk-child sk-double-bounce2\"></div>"));
            loadingDom.append(animDom);
            if (message != null) {
                loadingDom.append($("<h4>" + message + "</h4>"));
            }
            $("body").append(loadingDom);
        }
    },
    hideLoading: function () {
        var loadingAnim = $("#loading");
        if (loadingAnim.size !== 0) {
            loadingAnim.remove();
        }
    },
    //Not useful. Just for warning eliminate
    popover: function () {
    }

};
$.fn.serializeObject = function () {
    var obj = {};
    var arr = this.serializeArray();
    $.each(arr, function () {
        if (obj[this.name]) {
            if (!obj[this.name].push) {
                obj[this.name] = [obj[this.name]];
            }
            obj[this.name].push(this.value || '');
        } else {
            obj[this.name] = this.value || '';
        }
    });
    return obj;
};
$.fn.delayedPop = function (message, s) {
    var pop = this;
    pop.attr("data-content", message);
    pop.popover("show");
    setTimeout(function () {
        pop.popover("hide");
    }, s * 1000);
};
$.fn.registerDanger = function () {
    var dom = this;
    dom.parent().addClass("has-danger");
    dom.focus();
    dom.keydown(function () {
        dom.parent().removeClass("has-danger");
    })
};