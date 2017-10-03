<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 align = "center">${table_name}</h3>
<form action="${view_table_data.link}?command=${command_update.link}" method="post">
<input type="hidden" value="${table_name}" name="table_name">
<table>
    <c:forEach items="${values}" var="item" varStatus="status">
    <tr>
        <td align = "center">
            <input type="hidden" value="${table_columns[status.index]}" name="columns[]">
            <b>${table_columns[status.index]}</b>
        </td>
        <td align = "center">
            <input type="hidden" value="${item}" name="old_values[]">
            <input type="text" value="${item}" name="new_values[]">
        </td>
    </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td>
            <input type="submit" value="${command_update.title}"/>
        </td>
    </tr>
</table>
</form>