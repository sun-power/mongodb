var $ = function (id) {
    return document.getElementById(id);
};

function hasClass(elements, cName) {
    return !!elements.className.match(new RegExp("(\\s|^)" + cName));
}

function addClass(elements, cName) {
    if (!hasClass(elements, cName)) {
        if (elements.className) {
            elements.className += " " + cName;
        } else {
            elements.className = cName;
        }
    }
}

function removeClass(elements, cName) {
    if (hasClass(elements, cName)) {
        elements.className = elements.className.replace(new RegExp("(\\s|^)" + cName), "");
    }
}

function replaceClass(elements, cName, aName) {
    if (hasClass(elements, cName)) {
        elements.className = elements.className.replace(new RegExp("(\\s|^)" + cName), " " + aName);
    }
}

function objLength() {
    var count = 0;
    for (var key in this) {
        if (Object.prototype.hasOwnProperty.call(this, key)) {
            count++;
        }
    }
    return count;
};

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.slice(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    } else {
        return null;
    }
}

function ajax(opt) {
    opt = opt || {};
    opt.method = opt.method.toUpperCase() || 'GET';
    opt.url = opt.url || '';
    opt.async = opt.async || true;
    opt.data = opt.data || null;
    opt.success = opt.success || function () {
        };
    var xmlHttp = createXmlHttpRequest();
    var params = [];
    for (var key in opt.data) {
        params.push(key + '=' + opt.data[key]);
    }
    var postData = params.join('&');
    if (opt.method.toUpperCase() === 'POST') {
        xmlHttp.open(opt.method, opt.url, opt.async);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
        xmlHttp.send(postData);
    } else if (opt.method.toUpperCase() === 'GET') {
        xmlHttp.open(opt.method, opt.url + '?' + postData, opt.async);
        xmlHttp.send(null);
    }
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            opt.success(xmlHttp.responseText);
        }
    };
}

function createXmlHttpRequest() {
    var xmlHttp = null;
    if (window.XMLHttpRequest) {//非IE内核浏览器
        xmlHttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {//IE内核浏览器
        try {//IE6.0
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e1) {
            try {
                xmlHttp = new ActiveXObject("MSXML2.XMLHTTP");
            } catch (e2) {
                try {
                    xmlHttp = new ActiveXObject("MSXML3.XMLHTTP");
                } catch (e3) {
                    alert("创建Ajax失败：" + e3);
                }
            }
        }
    } else {//未知浏览器
        alert("未能识别的浏览器");
    }
    return xmlHttp;
}


