<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<footer>
		<div class="footer-left">
			<br><br>
			<p>&copy 2018 Forum Literaturbüro e.V.</p>
       		<p><a href="${pageContext.request.contextPath}/rechtliche_hinweise.jsp">Rechtliche Hinweise</a> | <a href="${pageContext.request.contextPath}/datenschutz.jsp">Datenschutz</a> | <a href="${pageContext.request.contextPath}/impressum.jsp">Impressum</a> | <a href="${pageContext.request.contextPath}/kontakt_formular.jsp">Kontaktformular</a></p>
		</div>
		<div class="footer-right">
			<c:if test="${ sessionScope.user.getRole().equals('admin') }">
				<a href="${pageContext.request.contextPath}/Admin">Administration</a>
			</c:if>
		</div>
	</footer>
</div>
