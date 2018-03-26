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
        <form id="werke-form" action="${pageContext.request.contextPath}/Admin/User?action=edit&id=<%= request.getParameter("id") %>" method="post" role="form" style="display: block;" accept-charset="UTF-8">
	        <%@ include file="/WEB-INF/parts/user_inputs.jsp" %>
	        
			<div class="form-group">
				<label for="role">Rolle</label>
				<select name="role" class="form-control" id="role">
					<c:set var="roles">guest,user,admin</c:set>
					<c:forTokens items="${roles}" delims="," var="role">
					    <option ${ user_edit.role == role ? 'selected="selected"' : ''}>${role}</option>
					</c:forTokens>
				</select>
			</div>
			
			<div class="form-group">
				<label for="status">Status</label>
				<select name="status" class="form-control" id="status">
					<c:set var="statuses">new,active,deactive</c:set>
					<c:forTokens items="${statuses}" delims="," var="status">
					    <option ${ user_edit.status == status ? 'selected="selected"' : ''}>${status}</option>
					</c:forTokens>
				</select>
			</div>
	        
	        <div class="form-group">
	            <div class="row">
	                <div class="col-sm-6 col-sm-offset-3">
	                    <input type="submit" name="user-submit" id="user-submit" tabindex="4"
	                           class="form-control btn" value="Save">
	                </div>
	            </div>
	        </div>
	     </form>
	</div>
	
	<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>
