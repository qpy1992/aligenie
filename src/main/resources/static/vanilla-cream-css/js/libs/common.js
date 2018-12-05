/**
 *  ajax,message转换
 */
function json2str(o) {
    var arr = [];
    var fmt = function (s) {
        if (typeof s == 'object' && s != null) {
            return json2str(s);
        }
        return /^(string)$/.test(typeof s) ? "'" + s + "'" : s;
    };
    if (o instanceof Array) {
        for (var i = 0; i < o.length; i++) {
            arr.push(fmt(o[i]));
        }
        return '[' + arr.join(',') + ']';
    } else {
        for (var i in o) {
            arr.push("'" + i + "':" + fmt(o[i]));
        }
        return '{' + arr.join(',') + '}';
    }
}