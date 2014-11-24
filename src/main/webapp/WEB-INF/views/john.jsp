<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
    <head>
        <%@include file="../fragment/meta.jsp"%>
        <link rel="stylesheet" href="resources/normalize.css" />
        <link rel="stylesheet" href="resources/default.css" />
        <title>John</title>
    </head>
    <body>
        <div id="wrapper">
            <h1>John !!</h1>
            <p>uniqueName = ${uniqueName}</p>
        </div>
        <%@include file="../fragment/footer.jsp"%>
    </body>
</html>