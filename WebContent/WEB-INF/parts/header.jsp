<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
	<div class="container">
		<a href="index.html">
			<img class="img-responsive img-rounded" id="site-banner" src="${pageContext.request.contextPath}/images/wettbewerb1/header.jpg" alt="Literaturapotheke"/>
		</a>
	</div>
	<div class="container-fluid">
    <nav class="navbar navbar-default">
      	<div class="container">
        	<div class="navbar-header">
          		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-2">
            		<span class="sr-only">Toggle navigation</span>
           			<span class="icon-bar"></span>
            		<span class="icon-bar"></span>
            		<span class="icon-bar"></span>
          		</button>
        	</div>
        	<div class="collapse navbar-collapse" id="navbar-collapse-2">
          		<ul class="nav navbar-nav navbar-left">
            		<li><a href="${pageContext.request.contextPath}/index.jsp">Start</a></li>
            		<li><a href="${pageContext.request.contextPath}/bestenliste.jsp">Bestenliste</a></li>
            		<li><a href="${pageContext.request.contextPath}/wettbewerbe.jsp">Wettbewerbe</a></li>
					<li><a href="${pageContext.request.contextPath}/LiteratureServlet">Werke</a></li>
          		</ul>
          		<ul class="nav navbar-nav navbar-right">
					<% if (session.getAttribute("user") != null) { %>
          				<li><a>Willkommen <%= ((elvaan.litap.model.User)session.getAttribute("user")).getEmail() %></a></li>
					<% } %>
       				<li><a href="${pageContext.request.contextPath}/ueber_uns.jsp">Über uns</a></li>
          			<% if (session.getAttribute("user") == null) { %>
          				<li><a href="${pageContext.request.contextPath}/login.jsp">Login / Registrieren</a>
            		<% } else { %>
            			<li><a href="${pageContext.request.contextPath}/LoginServlet?action=logout">Logout</a>
            		<% } %>
            		<li>
              			<a class="btn btn-default btn-outline-dark btn-circle" data-toggle="collapse" href="#nav-collapse3" aria-expanded="false" aria-controls="nav-collapse3">Suche</a>
            		</li>
         		</ul>
           		<div class="collapse nav navbar-nav nav-collapse navbar-right" id="nav-collapse3">
			  		<form class="navbar-form navbar-right form-inline" action="${pageContext.request.contextPath}/LiteratureServlet" method="post" role="search">
		    			<div class="form-group">
		      				<input type="text" class="form-control" placeholder="Suche" id="search" name="search"/>
		    			</div>
		    			<button name="search-submit" type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
			  		</form>
          		</div>
          		<div class="collapse nav navbar-nav nav-collapse navbar-right" id="nav-collapse2">
          		</div>
        	</div>
      	</div>
    </nav>
    </div>
</header>