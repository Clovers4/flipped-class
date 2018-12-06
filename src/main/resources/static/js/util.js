var util = {
    _iconMapper:{
        success:"check",
        info:"info_outline",
        warning:"warning",
        danger:"clear"
    },
    popWithDelay: function (pop, message, s) {
        pop.attr("data-content", message);
        pop.popover("show");
        setTimeout(function () {
            pop.popover("hide");
        }, s * 1000);
    },
    verify:function (form) {
        var emptyVerifies = form.find(".empty-verify");
        for(var i = 0 ; i < emptyVerifies.length; ++i){
            var emptyVerify = $(emptyVerifies.get(i));
            if(emptyVerify.val() == null || emptyVerify.val() ===""){
                this.popWithDelay(emptyVerify, emptyVerify.attr("data-emptyMessage"), 3);
                emptyVerify.focus();
                return false;
            }
        }
        var regVerifies = form.find(".reg-verify");
        for(i = 0 ; i < regVerifies.length; ++i){
            var regVerify = $(regVerifies.get(i));
            if(!(new RegExp(regVerify.attr("data-reg")).test(regVerify.val()))){
                this.popWithDelay(regVerify, regVerify.attr("data-regMessage"), 3);
                regVerify.focus();
                return false;
            }
        }
        return true;
    },
    _generateAlertDom:function (type, message) {
        var iconDOM = $("<div class='alert-icon'><i class='material-icons'>"+ this._iconMapper[type]+ "</i></div>");
        var messageDOM = $("<b>" + message + "</b>");
        var containerDOM = $("<div class='container'></div>");
        var alertDOM = $("<div class='alert' style='display: none'></div>");
        alertDOM.addClass("alert-"+type);
        containerDOM.append(iconDOM);
        containerDOM.append(messageDOM);
        alertDOM.append(containerDOM);
        return alertDOM;
    },
    showAlert:function (type, message, second) {
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
    verifyWithAlert:function(form){
        var emptyVerifies = form.find(".empty-verify");
        for(var i = 0 ; i < emptyVerifies.length; ++i){
            var emptyVerify = $(emptyVerifies.get(i));
            if(emptyVerify.val() == null || emptyVerify.val() ===""){
                this.showAlert("warning", emptyVerify.attr("data-emptyMessage"), 3);
                return emptyVerify;
            }
        }
        var regVerifies = form.find(".reg-verify");
        for(i = 0 ; i < regVerifies.length; ++i){
            var regVerify = $(regVerifies.get(i));
            if(!(new RegExp(regVerify.attr("data-reg")).test(regVerify.val()))){
                this.showAlert("warning", regVerify.attr("data-regMessage"), 3);
                return regVerify;
            }
        }
        return null;
    }
};
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};