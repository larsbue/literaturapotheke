<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="de">
<head>
<jsp:include page="/WEB-INF/parts/meta.jsp">
	<jsp:param name="title" value="Literatur Apotheke - Admin"/>
</jsp:include>
</head>
<body>
	<%@ include file="/WEB-INF/parts/header.jsp" %>
	<%@ include file="/WEB-INF/parts/admin/header.jsp" %>
		
	<div class="container">
    <div class="row">
        <div class="col-md-12">
			<h1>User List</h1>
          	<c:if test="${not empty message}">
          		<p class="litap-message">${ message }</p>
          	</c:if>
			<%@ include file="/WEB-INF/parts/admin/UserList.jsp" %>
		</div>
	</div>
	</div>
	<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>
