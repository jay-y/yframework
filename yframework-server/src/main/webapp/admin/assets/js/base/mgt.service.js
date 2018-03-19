/**
 * Created by ysj on 2018/2/8.
 */
mgt.service = (function ($) {
    $.settings.contentType = "json";
    $.settings.complete = function (res, status) {
        if (401 == res.status || 401 == status) {
            window.top.location.href = "/admin/login.html";
        }
    };
    return {
        get: function (url, obj, success, error) {
            $.get(url, obj, success, error);
        },
        post: function (url, obj, success, error) {
            $.post(url, obj, success, error);
        },
        put: function (url, obj, success, error) {
            $.put(url, obj, success, error);
        },
        find: function (url, obj, success, error) {
            $.get(url + '/' + obj, null, success, error);
        },
        query: function (url, obj, success, error) {
            var params = {
                query: null != obj.query ? JSON.stringify(obj.query) : '',
                page: obj.page,
                size: obj.size,
                sort: obj.sort || 'id,desc'
            };
            $.get(url + '/query', params, success, error);
        },
        delete: function (url, obj, success, error) {
            $.delete(url + '/' + obj, null, success, error);
        }
    };
})(yutil ? yutil.http : null);