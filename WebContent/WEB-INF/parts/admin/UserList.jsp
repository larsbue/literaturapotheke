<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table">
	<tr>
		<th>id</th>
		<th>email</th>
		<th>status</th>
		<th>role</th>
		<th>edit</th>
		<th>delete</th>
		<th>confirm register</th>
	</tr>
	<c:forEach var="u" items="${users}">
	    <tr>
	        <td><c:out value="${u.id}"/></td>
	        <td><c:out value="${u.email}"/></td>
	        <td><c:out value="${u.status}"/></td>
	        <td><c:out value="${u.role}"/></td>
	        <td><a href="${pageContext.request.contextPath}/Admin/User?action=edit&id=${u.id}">Edit User</a></td>
	        <td><a href="${pageContext.request.contextPath}/Admin/User?action=delete&id=${u.id}">Delete User</a></td>
	        <td><a href="${pageContext.request.contextPath}/Admin/User?action=register&id=${u.id}">Confirm Registration</a></td>
	    </tr>
	</c:forEach>
</table>