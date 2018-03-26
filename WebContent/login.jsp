<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="de">
<head>
<jsp:include page="/WEB-INF/parts/meta.jsp">
	<jsp:param name="title" value="Literatur Apotheke"/>
</jsp:include>
</head>
<body>
	<%@ include file="/WEB-INF/parts/header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div id="alert_placeholder">
                <script type="text/javascript">getEvent()</script>
            </div>
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Registrierung</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                        	<p>${ message }</p>
                            <form id="login-form" action="LoginServlet" method="post" role="form"
                                  style="display: block;">
                                <div class="form-group">
                                    <input type="text" name="email" id="email" tabindex="1" class="form-control"
                                           placeholder="E-Mail" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2"
                                           class="form-control" placeholder="Passwort">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                   class="form-control btn btn-login" value="Ok">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-center">
                                                <a tabindex="5" class="forgot-password" data-toggle="collapse"
                                                href="#collapsePasswort" aria-expanded="false"
                                                aria-controls="collapseBeispiel">Passwort vergessen?</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>


                            <form id="register-form" action="RegisterServlet" method="post" role="form"
                                  style="display: none;">
                                <%@ include file="/WEB-INF/parts/user_inputs.jsp" %>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit"
                                                   tabindex="4" class="form-control btn btn btn-login" value="Ok">
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <div class="collapse" id="collapsePasswort">
                                <hr>
                                <h4>Passwort zurücksetzen?</h4>
                                <form id="password-form" action="PasswordResetServlet" method="GET" role="form"
                                      style="display: block;">
                                    <div class="form-group">
                                        <label for="email">Geben Sie hier ihre E-Mail Adresse ein.</label>
                                        <input type="text" name="email" id="email" tabindex="1" class="form-control"
                                               placeholder="E-Mail" value="">
                                    </div>
                                    <div class="form-group">
                                        <div class="row">
                                            <div class="col-sm-6 col-sm-offset-3">
                                                <input type="submit" name="" id="" tabindex="4"
                                                       class="form-control btn btn-login" value="Ok">
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/parts/footer.jsp" %>

<script type="text/javascript">
    $(function () {
        $('#login-form-link').click(function (e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('#register-form-link').click(function (e) {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });

    });
</script>

</body>
</html>