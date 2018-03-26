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
        <form id="werke-form" action="${pageContext.request.contextPath}/Admin/Literature?action=edit&id=<%= request.getParameter("id") %>" method="post" role="form" style="display: block;" accept-charset="UTF-8">
			<%@ include file="/WEB-INF/parts/literature_inputs.jsp" %>
            <div class="form-group">
                <textarea class="form-control" id="admincomment" name="admincomment" rows="5" placeholder="Admin-Kommentar"><c:out value='${literature.admincomment}'/></textarea>
            </div>
			<div class="form-group">
				<label for="status">Status</label>
				<select name="status" class="form-control" id="status">
					<c:forEach var="s" items="${statusValues}">
						<option value="${s.key}" ${literature.status == s.key ? 'selected="selected"' : ''}>${s.value}</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<input type="checkbox" name="keep" id="keep" ${literature.keep ? 'checked="checked"' : ''}>
				<label for="keep">Stammpräparat</label>
			</div>
	        <div class="form-group">
	            <div class="row">
	                <div class="col-sm-6 col-sm-offset-3">
	                    <input type="submit" name="literature-submit" id="literature-submit" tabindex="4"
	                           class="form-control btn btn-login">
	                </div>
	            </div>
	        </div>
	     </form>
	     
	     			<h3>Beschwerden</h3>
					<c:forEach var="c" items="${complains}">
						<div class="user_comment"><p><c:out value="${c.complain}"/></p></div>
					</c:forEach>
	</div>
	
	<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>
