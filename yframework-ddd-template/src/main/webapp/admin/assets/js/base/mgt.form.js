mgt.form = (function ($, y) {
    var params = document.getElementsByClassName("form-param");
    var m = $("#remoteModal");
    var target;
    m.on("show.bs.modal", function (e) {
        target = $(e.relatedTarget);
    });
    return {
        get: function (id) {
            var form = $(id).serializeArray();
            var params = {};
            for (var i in form) {
                var v = form[i].value;
                if (v && v.length > 0) {
                    params[form[i].name] = v;
                }
            }
            return params;
        },
        load: function (data) {
            if (params) {
                for (var i = 0; i < params.length; i++) {
                    var p = params[i];
                    p.value = p.value || (data && data[p.name]) || null;
                }
            }
        },
        modal: {
            instance: m,
            data: function (key) {
                var d = target.data(key);
                return y.validator.isNotEmpty(d) && 'undefined' !== d ? d : null;
            }
        },
        table: {
            instance: [],
            get: function (k) {
                return this.instance[k];
            },
            set: function (k, t) {
                this.instance[k] = t;
            }
        }
    }
})
(($ || jQuery), yutil);