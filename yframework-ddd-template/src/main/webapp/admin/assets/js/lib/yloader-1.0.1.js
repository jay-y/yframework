(function (win, dom) {
    var load = function (index, e, max) {
        var element = dom.createElement("div");
        if (0 == index) {
            element.id = "bf-yloader";
        }
        else if (1 == index) {
            element.className = "yloader"
        }
        if (index < max) {
            e.appendChild(element);
            return load((index + 1), element, max);
        }
    };
    win.yloader = {
        show: function (size) {
            size = size || 7;
            load(0, dom.body, size);
        },
        hide: function () {
            dom.body.removeChild(dom.getElementById("bf-yloader"));
        }
    };
    dom.yloader = win.yloader;
    return win.yloader && dom.yloader;
})(window, document);