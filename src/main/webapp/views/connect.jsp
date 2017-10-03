<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test = "${connected}">
        <p>
            Connected to  <b> ${connected_base} </b> as <b> ${connected_user} </b><br>
            For reconnect use the form below:
        </p>
</c:if>
<form action="${href_tables.link}?command=${command_connect.link}" method="post">
    <table>
        <tr>
            <td>${db_name.title}:</td>
            <td><input type="text" name="${db_name.link}"/></td>
        </tr>
        <tr>
            <td>${user_name.title}:</td>
            <td><input type="text" name="${user_name.link}"/></td>
        </tr>
        <tr>
            <td>${user_password.title}:</td>
            <td><input type="password" name="${user_password.link}"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="${command_connect.title}"/></td>
        </tr>
    </table>
</form>
