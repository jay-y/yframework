/**
 * Created by ysj on 2018/2/9.
 */
mgt.i18n = (function (win, doc, $) {
    var i18nData;
    $.ajaxSettings.async = false;
    $.getJSON(_LANG, function (v) {
        i18nData = v;
    });
    $.ajaxSettings.async = true;
    return {
        transform: function (v) {
            return i18nData[v] || v;
        },
        load: function () {
            var elements = doc.getElementsByClassName("mgt-i18n");
            for (var i in elements) {
                var e = elements[i];
                var v = this.transform(e.value || e.innerText || e.innerHTML);
                if (e.value) {
                    e.value = v;
                }
                else if (e.innerText) {
                    e.innerText = v;
                }
                else {
                    e.innerHTML = v;
                }
            }
        }
    };
})(window, document, $ || jQuery);