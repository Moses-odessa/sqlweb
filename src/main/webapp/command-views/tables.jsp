<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
<tr><td bgcolor = "gray">Table name</td></tr>
<c:forEach items="${tables}" var="table">
<tr>
    <td align = "center"><b> ${table} </b></td>
    <td align = "center" bgcolor = "gray"><a href="${command_view.link}?table_name=${table}">${command_view.title}</a></td>
    <td align = "center" bgcolor = "gray"><a href="${command_delete.link}?table_name=${table}">${command_delete.title}</a></td>
    <td align = "center" bgcolor = "gray"><a href="${command_clear.link}?table_name=${table}">${command_clear.title}</a></td>
</tr>
</c:forEach>
</table>

<form action="${command_create.link}" method="post">
<p align = "center">
    ${command_create.title}:
    <input type="text" name="table_name"/>
    <input type="submit" value="OK"/>
</p>
</form>
