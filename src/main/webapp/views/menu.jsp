<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
<c:forEach items="${menu}" var="item">
    <li><c:if test = "${current_page.title != item.title}"><a href="${item.link}"></c:if>${item.title}
    <c:if test = "${current_page.title != item.title}"></a></c:if><br>
</c:forEach>
</ul>