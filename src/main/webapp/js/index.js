window.onload = function () {
    select();
    $("but1").onclick = function () {
        removeClass($("box1"), "hidden");
    }
    $("but2").onclick = function () {
        removeClass($("box2"), "hidden");
    }
    $("but3").onclick = function () {
        remove();
    }
    $("checkbox").onclick = function () {
        if ($("checkbox").checked == true) {
            CheckAll(1);
        } else {
            CheckAll(0);
        }
    }
}

function select() {
    addClass($("box1"), "hidden");
    if ($("topic1").value == null || $("topic1").value == "") {
        ajax({
            method: "GET",
            url: basePath + "findAll.action",
            success: function (data) {
                var list = eval("(" + data + ")");
                console.log(list);
                var str = "";
                for (var i = 0; i < list.data.length; i++) {
                    str += "<tr>" +
                        "<td><input type='checkbox' name='checkbox' class='checkbox'></td>" +
                        "<td>" + list.data[i].topic + "</td>" +
                        "<td>" + list.data[i].from + "</td>" +
                        "<td>" + list.data[i].time + "</td>" +
                        "</tr>"
                }
                $("text").innerHTML = str;
            }
        })
    } else {
        ajax({
            method: "GET",
            url: basePath + "select.action",
            data: {
                topic: $("topic1").value
            },
            success: function (data) {
                var list = eval("(" + data + ")");
                console.log(list);
                var str = "";
                for (var i = 0; i < list.user.length; i++) {
                    str += "<tr>" +
                        "<td><input type='checkbox' name='checkbox' class='checkbox'></td>" +
                        "<td>" + list.user[i].topic + "</td>" +
                        "<td>" + list.user[i].from + "</td>" +
                        "<td>" + list.user[i].time + "</td>" +
                        "</tr>"
                }
                $("text").innerHTML = str;
                $("topic1").value = "";
            }
        })
    }
}

function save() {
    var date = new Date();
    var month = date.getMonth() < 10 ? "0" + date.getMonth() : date.getMonth();
    var ri = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    var time = date.getFullYear() + "-" + month + "-" + ri + " " + hours + ":" + minutes + ":" + seconds;
    if ($("topic").value == null || $("topic").value == ""
        && $("from").value == null || $("from").value == "") {
        alert("topic和from不能为空！");
    } else {
        ajax({
            method: "GET",
            url: basePath + "save.action",
            data: {
                topic: $("topic").value,
                from: $("from").value,
                time: time
            },
            success: function (data) {
                console.log(data);
                addClass($("box2"), "hidden");
            }
        })
    }
}

//删除
function remove() {
    for (var i = 1; i < document.querySelectorAll(".checkbox").length; i++) {
        if (document.querySelectorAll(".checkbox")[i].checked == true) {
            ajax({
                method: "GET",
                url: basePath + "delete.action",
                data: {
                    topic: document.querySelectorAll(".checkbox")[i].parentNode.parentNode.childNodes[1].innerHTML
                },
                success: function (data) {
                    console.log(data);
                }
            })
        }
    }
}

//全部选择
function CheckAll(target) {
    for (var i = 0; i < document.querySelectorAll(".checkbox").length; i++) {
        if (target == 1) {
            document.querySelectorAll(".checkbox")[i].checked = true;
        } else {
            document.querySelectorAll(".checkbox")[i].checked = false;
        }

    }
}
