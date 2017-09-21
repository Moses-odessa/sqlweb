<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test = "${connected}">
        <p>
            Connected to  <b> ${connected_base} </b> as <b> ${connected_user} </b><br>
            For reconnect use the form below:
        </p>
</c:if>
<form action="connect" method="post">
    <table>
        <tr>
            <td>Database name</td>
            <td><input type="text" name="dbname"/></td>
        </tr>
        <tr>
            <td>User name</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="connect"/></td>
        </tr>
    </table>
</form>
