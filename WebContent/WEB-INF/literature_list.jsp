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
	<%@ include file="/WEB-INF/parts/literature_header.jsp" %>
	
	<div class="container">
    <div class="row">
        <div class="col-md-12">
		<h1>Liste der Werke</h1>
		
		<c:if test="${not empty message}">
			<p class="litap-message">${ message }</p>
		</c:if>
		
		
  		<form class="form" action="${pageContext.request.contextPath}/LiteratureServlet" method="post" role="search">
   			<div class="form-group">
   				<input type="text" class="form-control" placeholder="Suche" id="search" name="search"/>
   			</div>
   			<button name="search-submit" type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Suchen</button>
  		</form>
		
		
		</div>
		<div class="col-md-12">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<c:forEach var="lit" items="${literature}">
				<article class="litap-literature">
				  <header>
				    <h2><a href="${pageContext.request.contextPath}/LiteratureServlet?action=view&id=${lit.id}"><c:out value='${lit.title}'/></a></h2>
				    <p><c:out value='${ lit.author }'/></p>
				  </header>
				  <section class="litap-motivation">
				    <p><c:out value='${ lit.motivation }'/></p>
				  </section>
				  <footer>
				    <p>
				      Eingestellt am <time datetime="${ lit.postdate }">${ lit.postdate }</time>
				    </p>
				  </footer>
				</article>
			</c:forEach>
		</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>
