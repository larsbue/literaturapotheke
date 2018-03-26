<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="de">
<head>
<jsp:include page="/WEB-INF/parts/meta.jsp">
	<jsp:param name="title" value="Literatur Apotheke - Admin"/>
</jsp:include>
</head>
<body>
	<%@ include file="/WEB-INF/parts/header.jsp" %>
	<%@ include file="/WEB-INF/parts/literature_header.jsp" %>
	
	<div class="container">
    <div class="row">
        <div class="col-md-12">
		
		<c:if test="${not empty message}">
			<p class="litap-message">${ message }</p>
		</c:if>
		
    	<form class="form" method="post">
		  <div class="form-group">
		    <textarea class="form-control" name="complain" placeholder="Complain"></textarea>
		  </div>
		  
  			<div class="form-group">
				<label for="reason">Status</label>
				<select name="reason" class="form-control" id="reason">
					<c:set var="reasons">Rassismus,Frauenfeindlichkeit,Plagiat</c:set>
					<c:forTokens items="${reasons}" delims="," var="reason">
					    <option ${ complain.reason == reason ? 'selected="selected"' : ''}>${reason}</option>
					</c:forTokens>
				</select>
			</div>
			
		  <div class="form-group">
		    <textarea class="form-control" name="source" placeholder="Origin source"></textarea>
		  </div>
			
		  <button type="submit" class="btn btn-primary" name="complain-submit">Send</button>
    	</form>
		
		</div>
	</div>
	</div>
	<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>
