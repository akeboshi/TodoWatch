<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
    <head>
        <%@include file="../fragment/meta.jsp"%>
        <link rel="stylesheet" href="resources/normalize.css" />
        <link rel="stylesheet" href="resources/default.css" />
        <title>ERROR</title>
    </head>
    <body>
        <div id="wrapper">
            <h1 class="error">ERROR</h1>
            <p class="error">${message} (${exception})</p>
            <p>サーバーでエラーが発生しました。繰り返し発生する場合は、下記の情報を添えて管理者にお問い合わせください。</p>
            <div id="stacktrace">${stacktrace}</div>
        </div>
        <%@include file="../fragment/footer.jsp"%>
    </body>
</html>