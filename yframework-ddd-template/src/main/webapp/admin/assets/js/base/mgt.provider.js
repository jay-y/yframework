/**
 * Created by ysj on 2018/2/9.
 */
mgt.provider = (function ($) {
    return {
        getAccount: function (cb) {
            var key = _KEY_API_ACCOUNT;
            var account = $.storage.get(key);
            if ($.validator.isEmpty(account)) {
                $.http.get(_API_ACCOUNT ? _API_ACCOUNT : "/api/account", null,
                    function (res) {
                        $.storage.set(key, JSON.stringify(res));
                        cb && cb(res);
                    },
                    function (res) {
                        console.warn(res);
                    });
            }
            else {
                var res = JSON.parse(account);
                cb && cb(res);
            }
        },
        queryMenus: function (cb) {
            var key = _KEY_MGT_MENUS;
            var menus = $.storage.get(key);
            if ($.validator.isEmpty(menus)) {
                $.http.get((_MGT_MENUS ? _MGT_MENUS : "/mgt/menus"), null,
                    function (res) {
                        $.storage.set(key, JSON.stringify(res));
                        cb && cb(res);
                    },
                    function (res) {
                        console.warn(res);
                    });
            }
            else {
                var res = JSON.parse(menus);
                cb && cb(res);
            }
        },
        queryMenus4Root: function (cb) {
            var key = _KEY_MGT_MENUS + "_root";
            var menus = $.storage.get(key);
            if ($.validator.isEmpty(menus)) {
                $.http.get((_MGT_MENUS ? _MGT_MENUS : "/mgt/menus") + "/query/1", null,
                    function (res) {
                        $.storage.set(key, JSON.stringify(res));
                        cb && cb(res);
                    },
                    function (res) {
                        console.warn(res);
                    });
            }
            else {
                var res = JSON.parse(menus);
                cb && cb(res);
            }
        },
        queryMenus4Account: function (account, cb) {
            var key = _KEY_MGT_MENUS + "_account";
            var menus = $.storage.get(key);
            if ($.validator.isEmpty(menus)) {
                $.http.get((_MGT_MENUS ? _MGT_MENUS : "/mgt/menus") + "/account", account,
                    function (res) {
                        $.storage.set(key, JSON.stringify(res));
                        cb && cb(res);
                    },
                    function (res) {
                        console.warn(res);
                    });
            }
            else {
                var res = JSON.parse(menus);
                cb && cb(res);
            }
        },
        queryDicts: function (type, cb) {
            var key = _KEY_MGT_DICTS + "_" + type;
            var dicts = $.storage.get(key);
            if ($.validator.isEmpty(dicts)) {
                $.http.get((_MGT_DICTS ? _MGT_DICTS : "/mgt/dicts") + "/type/" + type, null,
                    function (res) {
                        $.storage.set(key, JSON.stringify(res));
                        cb && cb(res);
                    },
                    function (res) {
                        console.warn(res);
                    });
            }
            else {
                var res = JSON.parse(dicts);
                cb && cb(res);
            }
            return dicts;
        },
        queryRoles: function (cb) {
            var key = _KEY_MGT_ROLES;
            var roles = $.storage.get(key);
            if ($.validator.isEmpty(roles)) {
                $.http.get(_MGT_ROLES ? _MGT_ROLES : "/mgt/roles", null,
                    function (res) {
                        $.storage.set(key, JSON.stringify(res));
                        cb && cb(res);
                    },
                    function (res) {
                        console.warn(res);
                    });
            }
            else {
                var res = JSON.parse(roles);
                cb && cb(res);
            }
        }
    };
})(yutil);