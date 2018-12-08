//class value
var showFrame = "on-show";

var pageContent;
var refs;

$(function () {
    pageContent = $("#page-content");
    refs = $(".iref");
    addIframe(refs.filter(".active").attr("data-href"));
    refs.click(function () {
        var dom = $(this);
        var href = dom.attr("data-href");
        // noinspection JSValidateTypes
        var iframes = pageContent.children().filter("[data-url='" + href + "']");
        if(iframes.length === 0){
            addIframe(href);
        }else{
            activeFrame($(iframes.get(0)));
        }
    });
});
//inner method
function activeFrame(frame) {
    pageContent.children("." + showFrame).removeClass(showFrame);
    frame.addClass(showFrame);
}
function addIframe(url) {
    var iframe = $("<iframe></iframe>");
    iframe.attr("src",url);
    var div = $("<div></div>");
    div.addClass("tab");
    div.attr("data-url", url);
    div.append(iframe);
    pageContent.append(div);

    activeFrame(div);
}