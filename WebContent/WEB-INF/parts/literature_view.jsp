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
		    <h1>${ literature.title }</h1>
		  </header>
		  <section class="litap-motivation litap-motivation-italic">
		    <p>${literature.motivation}</p>
		  </section>
		  <section class="litap-content">
		    <p>${literature.content}</p>
		  </section>
		  <footer>
		    <p>
		      <a href="${pageContext.request.contextPath}/LiteratureServlet?action=complain&id=${lit.id}">Werk Melden</a>
		    </p>
		  </footer>
		  <section class="user_comments">
		  <header>
		    <h3>Comments</h3>
		    <c:if test="${ sessionScope.user.hasCapability('rate+comment-literature') }">
			    <section class="form">
			    	<form class="form" method="post">
					  <input type="hidden" name="id" value="${ literature.id }" />
					  <div class="form-group">
					    <textarea class="form-control" name="comment" placeholder="Comment"></textarea>
					  </div>
					  <button type="submit" class="btn btn-primary" name="comment-submit">Send</button>
			    	</form>
			    </section>
		    </c:if>
		  </header>
			<c:forEach var="c" items="${comments}">
			    <article class="user_comment">
			      <p>${ c.comment }</p>
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
