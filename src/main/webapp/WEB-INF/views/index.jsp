<%@ page pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!DOCTYPE html>
<html>
    <head>
        <%@include file="../fragment/meta.jsp"%>
        <link rel="stylesheet" href="resources/normalize.css" />
        <link rel="stylesheet" href="resources/default.css" />
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
        <script src="resources/autocomplete.js"></script>
        <title>Home</title>
    </head>
    <body>
        <div id="wrapper">
            <h1>John Metro</h1>
            <form action="" method="post">
                <input id="txtKey"type="text" name="station" placeholder="駅名を入力..." required />
                <input type="submit" name="search" value="Search" />
            </form>
            <c:if test="${station_name != null}">
                <p>User input: ${station_name}</p>
            </c:if>
            <c:if test="${error != null}">
                <p class="error">${error}</p>
            </c:if>
            <form action="john" method="get">
                <c:forEach var="facilityGroup" items="${facilities}">
                    <h2>${facilityGroup.getUniqueName()}駅</h2>
                    <table border="1" style="font-size:20px">
                        <c:forEach var="platformLocation" items="${facilityGroup.getPlatformLocations()}">
                            <tr>
                                <td>${platformLocation.getRailDirectionText()}方面</td>
                                <td>${platformLocation.getCarNumber()} 号車</td>
                                <c:forEach var="facility" items="${platformLocation.getToilets()}">
                                    <td>
                                        <input type="hidden" name="uniqueName" value="${facility.getUniqueName()}" />
                                        <input type="submit" value="　　　${facility.getPlaceName()}" class="inline, ${facility.getRailway()}" />
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </c:forEach>
            </form>

        </div>
        <%@include file="../fragment/footer.jsp"%>
    </body>
</html>