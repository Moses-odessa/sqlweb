<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p align = "center"><h3>${table_name}</h3></p>
<table border = "1">
<tr bgcolor = "gray">
    <c:forEach items="${table_columns}" var="column">
        <td align = "center">
            <b>${column}</b><br>
            <a href="${command_del_column.link}?table_name=${table_name}&column_name=${column}">${command_del_column.title}</a>
        </td>
    </c:forEach>
</tr>
<c:forEach items="${table_data}" var="row">
<tr>
    <c:forEach items="${row}" var="item">
    <td align = "center"> ${item}</td>
    </c:forEach>
</tr>
</c:forEach>
</table>
<p align = "center">
<form action="${command_add_column.link}" method="post">
    ${command_add_column.title}:
    <input type="hidden" name="table_name" value="${table_name}"/>
    <input type="text" name="column_name"/>
    <input type="submit" value="OK"/>
</form>
</p>