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
		<div class="row">
			<div class="col-sm-6">
				<h4>Herzlich willkommen in der Literatur-Apotheke!</h4>
				<p style="line-height:2.5;"><b>Gedichte, Prosa, Romane, Märchen, Mythen, Songtexte - als erste Hilfe, Trostpflaster, Injektion oder Balsam! Mit der weltweit ersten Literatur-Apotheke wollen wir Autoren und Lesern die Möglichkeit geben, sich über die Wirkungsweise von Literatur vor dem Hintergrund der eigenen Lese- oder Schreiberfahrung auszutauschen.</b></p>
				<p style="line-height:2.5;">Literatur kann trösten und besänftigen, aufrütteln und Mut machen. Ein Gedicht kann einen Tag retten. Und eine Zeile, die einen berührt, grenzt nicht aus oder ab, sondern weitet den Horizont und öffnet ein Fenster in eine andere Welt. Schauen Sie rein, wir wünschen Ihnen viel Freude beim Stöbern und Ausprobieren!</p>
				<p style="line-height:2.5;"><i>„Ich schreibe gerne Gedichte und Geschichten, weil ich mich so in eine eigene Welt zurückziehen kann. Wenn ich Streit habe, schreibe ich mir zum Beispiel ein Gedicht zum Trösten“</i>Marie - Schülerin, 11 Jahre, Hildesheim</p>
				<p style="line-height:2.5;"><i>„Worte auf Papier und das Meer zwischen den Zeilen sind oft das einzige, was mich trägt, wenn mich nichts mehr trägt“</i>Jo Köhler - Dichter und Kulturinitiator, 55 Jahre, Hildesheim</p>
			</div>
			<div class="col">
				<img class="img-responsive img-rounded" style="float:right; width:40%" src="images/wettbewerb1/start.jpg" alt="Förderer">
			</div>
			<img class="img-responsive" src="images/foerderer/foerderer.png" alt="Förderer">
		</div>
	</div>
	<%@ include file="/WEB-INF/parts/footer.jsp" %>
</body>
</html>

