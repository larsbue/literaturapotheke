<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="table">
	<tr>
		<th>id</th>
		<th>title</th>
		<th>author</th>
		<th>user</th>
		<th>keep</th>
		<th>status</th>
		<th>edit</th>
		<th>delete</th>
		<th>set keep</th>
	</tr>
	<c:forEach var="lit" items="${literature}">
	    <tr>
	        <td><c:out value="${lit.id}"/></td>
	        <td><c:out value="${lit.title}"/></td>
	        <td><c:out value="${lit.author}"/></td>
	        <td><c:out value="${lit.user_id}"/></td>
	        <td><c:out value="${lit.keep}"/></td>
	        <td><c:out value="${lit.status}"/></td>
	        <td><a href="${pageContext.request.contextPath}/Admin/Literature?action=edit&id=${lit.id}">Edit</a></td>
	        <td><a href="${pageContext.request.contextPath}/Admin/Literature?action=delete&id=${lit.id}">Delete</a></td>
	        <td><a href="${pageContext.request.contextPath}/Admin/Literature?action=setkeep&id=${lit.id}">Setzte als Stammpräparat</a></td>
	    </tr>
	</c:forEach>
</table>