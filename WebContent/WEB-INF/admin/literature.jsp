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
        <div class="col-md-6 col-md-offset-3">
		<h1>Literatur-Liste</h1>
		
		<c:if test="${not empty message}">
			<p class="litap-message">${ message }</p>
		</c:if>
		
        <form id="werke-form" action="${pageContext.request.contextPath}/Admin/Literature" method="post" role="form" style="display: block;" accept-charset="UTF-8">
			<div class="form-group">
				<label for="user">User</label>
				<select name="user" class="form-control" id="user">
					<option value="0"> - Alle - </option>
					<c:forEach var="u" items="${users}">
						<option value="${u.id}" ${param.user == u.id ? 'selected="selected"' : ''}>${u.email}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<input type="checkbox" name="keep" id="keep" ${param.keep != null ? 'checked="checked"' : ''}>
				<label for="keep">Deaktivierte Werke u. Werke älter als ein Jahr</label>
			</div>
			<div class="form-group">
				<input type="checkbox" name="complain" id="complain" ${param.complain != null ? 'checked="checked"' : ''}>
				<label for="complain">mit Beschwerde</label>
			</div>
			<div class="form-group">
				<label for="status">Status</label>
				<select name="status" class="form-control" id="status">
					<option value=""> - Alle - </option>
					<c:forEach var="s" items="${statusValues}">
						<option value="${s.key}" ${param.status == s.key ? 'selected="selected"' : ''}>${s.value}</option>
					</c:forEach>
				</select>
			</div>
	        <div class="form-group">
	            <div class="row">
	                <div class="col-sm-6 col-sm-offset-3">
	                    <input type="submit" name="literature-filter-submit" id="literature-submit" tabindex="4"
	                           class="form-control btn btn-login" value="Filtern">
	                </div>
	            </div>
	        </div>
	     </form>
		
		
		</div>
		<div class="col-md-12">
		<%@ include file="/WEB-INF/parts/admin/LiteratureList.jsp" %>
		</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>
