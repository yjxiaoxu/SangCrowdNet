<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#resultBtn").click(function() {
                //相当于浏览器的后退按钮
                window.history.back();
            });
        });
    </script>
    <style>

    </style>
</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
            </div>
        </div>
    </nav>
    <div class="container">
        <h2 class="form-signin-heading" style="text-align: center">
            <i class="glyphicon glyphicon-log-in"></i> 错误提示信息
        </h2>
        <h3 style="text-align: center">${requestScope.exception.message}</h3>
        <button id="resultBtn" style="width: 150px;margin: 50px auto 0px auto" class="btn btn-lg btn-success btn-block">返回上一步</button>
    </div>
</body>
</html>
