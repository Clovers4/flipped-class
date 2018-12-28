(function ($) {
    var m = 0, s = 0, digitCards;
    var isPause = false;
    var tickFunc;
    $.fn.create = function () {
        init(this);
    };
    $.fn.start = function(){
        if(tickFunc !== undefined) {
            isPause = false;
        }else{
            tickFunc = setTimeout(tick, 1000);
        }
    };
    $.fn.pause = function () {
        isPause = true;
    };
    $.fn.getTime = function () {
        return m * 60 + s;
    };
    $.fn.setTime = function (time) {
        s = time % 60;
        m = Math.floor(time / 60) % 60;
        refreshDigit();
    };

    function init(elem) {
        elem.empty();
        elem.addClass('holder');
        $.each(['Minutes', 'Seconds'], function () {
            $('<span class="count' + this + '">').html(
                '<span class="digit-card">\
                    <span class="digit static">0</span>\
                </span>\
                <span class="digit-card">\
                    <span class="digit static">0</span>\
                </span>'
            ).appendTo(elem);

            if (this.toString() !== 'Seconds') {
                elem.append('<span class="countDiv countDiv"></span>');
            }
        });
        digitCards = elem.find('.digit-card');
    }
    function tick() {
        if(!isPause) {
            if ((++s) >= 60) {
                if (++m >= 60)
                    m %= 60;
                s %= 60;
            }
            updateCard(0, 1, m);
            updateCard(2, 3, s);
        }

        setTimeout(tick, 1000);
    }
    function updateCard(pre, post, value) {
        switchDigit(digitCards.eq(pre), Math.floor(value / 10) % 10);
        switchDigit(digitCards.eq(post), value % 10);
    }
    function switchDigit(digitCard, number) {
        var digit = digitCard.find('.digit');
        if (digit.is(':animated')) {
            return false;
        }
        if (digit.html() === String(number)) {
            return false;
        }
        var replacement = $('<span>', {
            'class': 'digit',
            css: {
                top: '-2.1em',
                opacity: 0
            },
            html: number
        });
        digit.before(replacement)
            .removeClass('static')
            .animate({top: '2.5em', opacity: 0}, 'fast', function () {
                digit.remove();
            });

        replacement
            .delay(100)
            .animate({top: 0, opacity: 1}, 'fast', function () {
                replacement.addClass('static');
            });
    }
    function refreshDigit() {
        digitCards.eq(0)
            .find('.digit')
            .html(Math.floor(m / 10) % 10);
        digitCards.eq(1)
            .find('.digit')
            .html(m % 10);
        digitCards.eq(2)
            .find('.digit')
            .html(Math.floor(s / 10) % 10);
        digitCards.eq(3)
            .find('.digit')
            .html(s % 10);
    }
})(jQuery);