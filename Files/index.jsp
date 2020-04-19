<%@page import="java.util.*" %>
<%@page contentType="text/html; charset=utf-8" %>
<html style="background-color: #e2e2e2;">
<head>
    <meta name="keywords" content="{{ lay.base.keywords }}">
    <meta name="description" content="{{ lay.base.description }}">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>PSM-同校任务互助平台</title>
    <link rel="stylesheet" href="../res/layui/css/layui.css">
    <link rel="stylesheet" href="../res/css/global.css">

</head>
<body class="fly-full">
<%--导航栏--%>
<%@ include file="head.jsp" %>

<%--主页主体--%>
<%@ include file="main.jsp" %>

<%--页脚--%>
<%@include file="foot.jsp" %>
</div>


<script src="../res/layui/layui.js"></script>
<script>
    layui.cache.page = 'case';
    layui.cache.user = {
        username: '游客'
        , uid: -1
        , avatar: '../res/images/avatar/00.jpg'
        , experience: 83
        , sex: '男'
    };
    layui.config({
        version: "3.0.0"
        , base: '../res/mods/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');
</script>
</body>
</html> }).use('fly');
</script>
</body>
</html>