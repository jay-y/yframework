(function (win, doc, jq) {
    win.yutil = {
        location: {
            /**
             * 转href参数成键值对
             * @param href {string} 指定的href，默认为当前页href
             * @returns {object} 键值对
             */
            getSearch: function (href) {
                href = href || win.location.search;
                var data = {}, reg = new RegExp("([^?=&]+)(=([^&]*))?", "g");
                href && href.replace(reg, function ($0, $1, $2, $3) {
                    data[$1] = $3;
                });
                return data;
            }
        },
        json: JSON || win.JSON,
        validator: {
            isEmpty: function (obj) {
                return (obj == null || obj == undefined || obj == "");
            },
            isNotEmpty: function (obj) {
                return !this.isEmpty(obj);
            }
        },
        string: {
            isBlank: function (obj) {
                return yutil.validator.isEmpty(obj);
            },
            isNotBlank: function (obj) {
                return yutil.validator.isNotEmpty(obj);
            }
        },
        storage: {
            get: function (k) {
                return sessionStorage && sessionStorage.getItem(k) || localStorage && localStorage.getItem(k) || ("" && console.log("获取缓存失败"));
            },
            set: function (k, v, r) {
                r = r ? r : false;
                if ((r == false) && sessionStorage) {
                    sessionStorage.setItem(k, v);
                }
                else if ((r == true) && localStorage) {
                    localStorage.setItem(k, v)
                }
                else {
                    console.log("设置缓存失败");
                }
            },
            remove: function (k) {
                sessionStorage && sessionStorage.removeItem(k);
                localStorage && localStorage.removeItem(k);
            },
            clear: function () {
                sessionStorage && sessionStorage.clear();
                localStorage && localStorage.clear();
            }
        },
        http: {
            settings: {
                url: win.location.pathname,
                method: 'get',
                async: true,
                contentType: "text/html"
            },
            ajax: function (settings) {
                if (!settings || typeof settings != 'object') {
                    return false;
                }
                var success = settings.success || this.settings.success;
                var error = settings.error || this.settings.error;
                var complete = settings.complete || this.settings.complete;
                //请求的类型
                var method = settings.method || this.settings.method;
                //请求地址
                var url = settings.url || this.settings.url;
                //是异步的还是同步
                var async = settings.async || this.settings.async;
                //请求内容的格式
                var contentType = settings.contentType || this.settings.contentType;

                //传输的数据
                var data = settings.data || {};
                var dataStr = '';
                //数据字符串
                for (var key in data) {
                    dataStr += key + '=' + data[key] + '&';
                }
                dataStr = dataStr && dataStr.slice(0, -1);
                var idToken = yutil.storage.get('id_token');
                if (jq) {
                    jq.ajax({
                        url: (method == 'get' && yutil.string.isNotBlank(dataStr) ? url + '?' + dataStr : url),
                        method: method,
                        data: method == 'get' ? null : contentType == "json" ? JSON.stringify(data) : dataStr,
                        async: async,
                        contentType: 'get' != method ? (contentType == 'json' ? 'application/json' : 'application/x-www-form-urlencoded') : "text/html",
                        headers: {
                            'Client-Type': 'I',
                            'Authorization': 'Bearer ' + idToken
                        },
                        success: success,
                        error: error,
                        complete: complete
                    });
                }
                else {
                    console.error("未加载到jQuery, 请检查代码");
                    // console.warn("未加载到jQuery");
                    // //ajax 编程
                    // var xhr = new XMLHttpRequest();
                    // //请求行
                    // //判断当前的请求类型
                    // xhr.open(method, (method == 'get' && yutil.string.isNotBlank(dataStr) ? url + '?' + dataStr : url), async);
                    // //请求头
                    // if (method != 'get') {
                    //     if (contentType == "json") {
                    //         xhr.setRequestHeader('Content-Type', 'application/json');
                    //     }
                    //     else {
                    //         xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    //     }
                    // }
                    // xhr.setRequestHeader('Client-Type', 'I');
                    // if (yutil.string.isNotBlank(idToken)) {
                    //     xhr.setRequestHeader('Authorization', 'Bearer ' + idToken);
                    // }
                    // //请求主体
                    // //需要判断请求类型
                    // xhr.send(method == 'get' ? null : contentType == "json" ? JSON.stringify(data) : dataStr);
                    //
                    // //监听响应状态的改变  响应状态
                    // xhr.onreadystatechange = function () {
                    //     var result = '';
                    //     var contentType = xhr.getResponseHeader('Content-Type');
                    //     //请求响应完成并且成功
                    //     if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 201)) {
                    //         //如果我们服务器返回的是xml
                    //         if (contentType.indexOf('xml') > -1) {
                    //             result = xhr.responseXML;
                    //         }
                    //         //如果我们的服务器返回的是json字符串
                    //         else if (contentType.indexOf('json') > -1) {
                    //             //转化json对象
                    //             result = JSON.parse(xhr.responseText);
                    //         }
                    //         //否则的话他就是字符串
                    //         else {
                    //             result = xhr.responseText;
                    //         }
                    //         //success
                    //         success && success(result);
                    //     }
                    //     //计时请求xhr.status不成功  他也需要的响应完成才认作是一个错误的请求
                    //     else if (xhr.readyState == 4) {
                    //         //如果我们服务器返回的是xml
                    //         if (contentType.indexOf('xml') > -1) {
                    //             result = xhr.responseXML;
                    //         }
                    //         //如果我们的服务器返回的是json字符串
                    //         else if (contentType.indexOf('json') > -1) {
                    //             /*转化json对象*/
                    //             result = JSON.parse(xhr.responseText);
                    //         }
                    //         //否则的话他就是字符串
                    //         else {
                    //             result = xhr.responseText;
                    //         }
                    //         //error
                    //         error && error(result, xhr.status);
                    //     }
                    //     complete && complete(xhr, xhr.status);
                    // }
                }
            },
            post: function (url, data, success, error, complete) {
                var settings = {
                    url: url,
                    method: 'post',
                    data: data,
                    success: success,
                    error: error || success,
                    complete: complete
                };
                this.ajax(settings);
            },
            put: function (url, data, success, error, complete) {
                var settings = {
                    url: url,
                    method: 'put',
                    data: data,
                    success: success,
                    error: error || success,
                    complete: complete
                };
                this.ajax(settings);
            },
            get: function (url, data, success, error, complete) {
                var settings = {
                    url: url,
                    method: 'get',
                    data: data,
                    success: success,
                    error: error || success,
                    complete: complete
                };
                this.ajax(settings);
            },
            delete: function (url, data, success, error, complete) {
                var settings = {
                    url: url,
                    method: 'delete',
                    data: data,
                    success: success,
                    error: error || success,
                    complete: complete
                };
                this.ajax(settings);
            }
        },
        random: {
            uuid: function () {
                /**
                 * @returns {string}
                 * @constructor
                 */
                function S4() {
                    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
                }

                return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
            }
        },
        event: function () {
            var e,
                _default = 'default';

            e = function () {
                var _listen,
                    _trigger,
                    _remove,
                    _slice = Array.prototype.slice,
                    _shift = Array.prototype.shift,
                    _unshift = Array.prototype.unshift,
                    namespaceCache = {},
                    _creat,
                    find,
                    each = function (ary, fn) {
                        var ret;
                        for (var i = 0, l = ary.length; i < l; i++) {
                            var n = ary[i];
                            ret = fn.call(n, i, n);
                        }
                        return ret;
                    };

                _listen = function (key, fn, cache) {
                    if (!cache[key]) {
                        cache[key] = [];
                    }
                    cache[key].push(fn);
                };

                _remove = function (key, cache, fn) {
                    if (cache[key]) {
                        if (fn) {
                            for (var i = cache[key].length; i >= 0; i--) {
                                if (cache[key][i] == fn) {
                                    cache[key].splice(i, 1);
                                }
                            }
                        } else {    // 若没有fn,表示全部清空
                            cache[key] = [];
                        }
                    }
                };

                _trigger = function () {
                    var cache = _shift.call(arguments),
                        key = _shift.call(arguments),
                        args = arguments,
                        _self = this,
                        ret,
                        stack = cache[key];

                    if (!stack || !stack.length) {
                        return;
                    }

                    return each(stack, function () {
                        return this.apply(_self, args);
                    })
                };

                _create = function (nmspace) {
                    var namespace = nmspace || _default;
                    var cache = {},
                        offlineStack = [],
                        ret = {
                            listen: function (key, fn, last) {
                                _listen(key, fn, cache);
                                if (offlineStack == null) {
                                    return;
                                }
                                if (last == 'last') {
                                    offlineStack.length && offlineStack.pop()();
                                } else {
                                    each(offlineStack, function () {
                                        this();
                                    });
                                }
                                offlineStack = null;
                            },
                            one: function (key, fn, last) {
                                _remove(key, cache);
                                this.listen(key, fn, last);
                            },
                            remove: function (key, fn) {
                                _remove(key, cache, fn);
                            },
                            trigger: function () {
                                var fn,
                                    args,
                                    _self = this;

                                _unshift.call(arguments, cache);
                                args = arguments;
                                fn = function () {
                                    return _trigger.apply(_self, args);
                                };
                                if (offlineStack) {
                                    return offlineStack.push(fn);
                                }
                                return fn();
                            }
                        };

                    return namespace ? (namespaceCache[namespace] ? namespaceCache[namespace] : namespaceCache[namespace] = ret) : ret;
                };
                return {
                    create: _create,
                    one: function (key, fn, last) {
                        var event = this.create();
                        event.one(key, fn, last);
                    },
                    remove: function (key, fn) {
                        var event = this.create();
                        event.one(key, fn);
                    },
                    listen: function (key, fn, last) {
                        var event = this.create();
                        event.listen(key, fn, last);
                    },
                    trigger: function () {
                        var event = this.create();
                        event.trigger.apply(this, arguments);
                    }
                };
            }();
            return e;
        }
    };
    doc.yutil = win.yutil;
    return win.yutil && doc.yutil;
})(window, document, $ || jQuery);