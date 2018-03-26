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
		
		<c:if test="${not empty message}">
			<p class="litap-message">${ message }</p>
		</c:if>
		
		</div>
		<div class="col-md-12">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<article class="litap-literature">
		  <header>
		    <h1><c:out value='${ literature.title }'/></h1>
		  </header>
		  <section class="litap-motivation litap-motivation-italic">
		    <p><c:out value='${literature.motivation}'/></p>
		  </section>
		  <section class="litap-content">
		    <p><c:out value='${literature.content}'/></p>
		  </section>
		  <footer>
		  	<c:if test="${ sessionScope.user.hasCapability('rate+comment-literature') }">
		    <p>
		      <a href="${pageContext.request.contextPath}/LiteratureServlet?action=complain&id=${literature.id}">Werk melden</a>
		    </p>
		    </c:if>
		  </footer>
		  <section class="user_comments">
		  <header>
		    <h3>Comments</h3>
		    <c:if test="${ sessionScope.user.hasCapability('rate+comment-literature') }">
			    <section class="form">
			    	<form class="form" method="post">
					  <div class="form-group">
					    <textarea class="form-control" name="comment" placeholder="Kommentar eingeben"></textarea>
					  </div>
					  <button type="submit" class="btn btn-primary" name="comment-submit">Absenden</button>
			    	</form>
			    </section>
		    </c:if>
		  </header>
			<c:forEach var="c" items="${comments}">
			    <article class="user_comment">
			      <p><c:out value='${ c.comment }'/></p>
			      <footer>
			      </footer>
			    </article>
		    </c:forEach>
		  </section>
		</article>

		</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>
