<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p align = "center"><h3>${table_name}</h3></p>
<table border = "1">
<tr bgcolor = "gray">
    <td>#</td>
    <c:forEach items="${table_columns}" var="column">
        <td align = "center">
            <b>${column}</b><br>
            <a href="${command_del_column.link}?table_name=${table_name}&column_name=${column}">${command_del_column.title}</a>
        </td>
    </c:forEach>
    <td></td>
</tr>

<c:forEach items="${table_data}" var="row" varStatus="rowNumber">
<tr>
    <td  align = "center">
        ${rowNumber.index + 1}
        <form id="del_form_${rowNumber.index}" method="post">
            <input form="del_form_${rowNumber.index}" type="hidden" value="${table_name}" name="table_name">
        </form>
    </td>

    <c:forEach items="${row}" var="item" varStatus="status">
        <td align = "center">
            <input form="del_form_${rowNumber.index}" type="hidden" value="${table_columns[status.index]}" name="columns[]">
            <input form="del_form_${rowNumber.index}" type="hidden" value="${item}" name="values[]">
            ${item}
        </td>
    </c:forEach>
    <td>
        <input form="del_form_${rowNumber.index}" type="submit" formaction="${command_del_record.link}" value="${command_del_record.title}"/>
    </td>
    <td>
        <input form="del_form_${rowNumber.index}" type="submit" formaction="${command_edit.link}" value="${command_edit.title}"/>
    </td>
</tr>
</c:forEach>

<c:if test = "${table_columns.size() > 0}">
<tr>
    <td>
        <form id="ins_form" action="${command_insert.link}" method="post">
            <input form="ins_form" type="hidden" value="${table_name}" name="table_name">
        </form>
    </td>

    <c:forEach items="${table_columns}" var="column">
        <td align = "center">
            <input form="ins_form" type="hidden" value="${column}" name="insert_columns[]">
            <input form="ins_form" type="text" value="" name="insert_values[]">
        </td>
    </c:forEach>
    <td><input form="ins_form" type="submit" value="${command_insert.title}"/></td>
</tr>
</c:if>

</table>

<form id="add_form" action="${command_add_column.link}" method="post">
    <p align = "center">
        ${command_add_column.title}:
        <input form="add_form" type="hidden" name="table_name" value="${table_name}"/>
        <input form="add_form" type="text" name="column_name"/>
        <input form="add_form" type="submit" value="OK"/>
    </p>
</form>
