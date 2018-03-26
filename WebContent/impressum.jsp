<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="de">
<head>
<%-- <%@ include file="/WEB-INF/parts/meta.jsp" %>  --%>
<jsp:include page="/WEB-INF/parts/meta.jsp">
	<jsp:param name="title" value="Literatur Apotheke"/>
</jsp:include>
</head>
<body>
	<%@ include file="/WEB-INF/parts/header.jsp" %>

	<div class="container">
		<div class="ym-box"><h3>Impressum</h3>
			<div style="width:400px; float:left;">
				<h4>Betreiber der Webseite</h4>
					<p>
						Förderverein Forum Literaturbüro e.V.<br>
						Karthäuser-Str. 30<br>
						31139 Hildesheim<br>
						Vorstand: Jo Köhler / Gabriel Muscu<br>
						Tel:  05121-263775<br>
						Fax: 05121-263774<br>
						E-Mail: info@forum-literatur.de<br>
						<a href="http://www.forum-literatur.de">www.forum-literatur.de</a><br>
					</p>
			</div>
			<div style="float:left;">
				<h4>Handelsregistereintrag</h4>
					<p>
						Registerblatt VR 2164 | Amtsgericht Hildesheim <br>
						Steuernummer 30/216/41964 | Finanzamt Hildesheim<br>
					</p>
			</div>
			<h3 style="clear:left; padding-top:15px;">Webcontent</h3>
			<div style="width:400px; float:left;">
				<h4>Internet-Provider</h4>
					<p>
						Hetzner Online AG <br>
						Industriestr. 6 <br>
						91710 Gunzenhausen
					</p>
				<h4>Quellenangaben</h4>
					<p>
						Fotos: Shutterstock<br>
						Grafiken: Norbert Jaekel Hildesheim
					</p>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>