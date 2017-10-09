<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>SQL Web - ${current_page.title}</title>
    </head>
<body>

<table style="width:100%">
    <tr>
        <td colspan = "2" align = "center" bgcolor = "lightblue">
                <h2>
                    ${current_page.title}
                </h2>
        </td>
    </tr>
    <tr>
        <td style="width:10%" bgcolor = "gray">
            <%@ include file = "menu.jsp" %>
        </td>
        <td align = "center" style="width:90%" bgcolor = "lightgray">
            <jsp:include page = "${current_page.link}.jsp" flush="true" />
        </td>
    </tr>
</table>
</body>
</html>
