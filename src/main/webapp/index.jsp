<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>FromList</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="page-view-size" content="1280*720"/>
    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/common.css">
    <link type="text/css" rel="stylesheet" href="<%=basePath%>css/index.css">
</head>
<body>
<div>
    <div id="box1" class="hidden box">
        <label>topic:</label><input id="topic1" type="text" value="">
        <button id="chaxuntijiao" onclick="select()">提交</button>
    </div>
    <div id="box2" class="hidden box">
        <form id="form1" onsubmit="save()">
            <label>topic:</label><input id="topic" type="text" value="">
            <label>from:</label><input id="from" type="text" value="">
            <input id="baocuntijiao" type="submit" value="提交">
            <input type="reset" value="清空">
        </form>
    </div>
    <button id="but1">查询</button>
    <button id="but2">保存/修改</button>
    <button id="but3">删除</button>
    <table>
        <thead>
        <tr>
            <th><input id="checkbox" type="checkbox" name="checkbox" class="checkbox"></th>
            <th>topic</th>
            <th>from</th>
            <th>操作时间</th>
        </tr>
        </thead>
        <tbody id="text"></tbody>
    </table>
</div>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
</script>
<script type="text/javascript" src="<%=basePath%>js/tools.js"></script>
<script type="text/javascript" src="<%=basePath%>js/index.js"></script>

</body>
</html>
