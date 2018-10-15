/**
 * Created by ysj on 2018/2/8.
 */
const _VERSION = "201802080407";
const _TITLE = "中国银行";
const _COPYRIGHT = "Copyright ©2018 BECYPRESS All Rights Reserved";

const _BASE_PATH = "/admin/assets/js/base/";
const _LIB_PATH = "/admin/assets/js/lib/";

var mgt = {};

document.title = _TITLE;
document.writeln('<script type="text/javascript" src="' + _LIB_PATH + 'yutil-2.0.0.js?v=' + _VERSION + '"></script>');
document.writeln('<script type="text/javascript" src="' + _BASE_PATH + 'mgt.consts.js?v=' + _VERSION + '"></script>');
document.writeln('<script type="text/javascript" src="' + _BASE_PATH + 'mgt.service.js?v=' + _VERSION + '"></script>');
document.writeln('<script type="text/javascript" src="' + _BASE_PATH + 'mgt.provider.js?v=' + _VERSION + '"></script>');
document.writeln('<script type="text/javascript" src="' + _BASE_PATH + 'mgt.i18n.js?v=' + _VERSION + '"></script>');
document.writeln('<script type="text/javascript" src="' + _BASE_PATH + 'mgt.form.js?v=' + _VERSION + '"></script>');

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};