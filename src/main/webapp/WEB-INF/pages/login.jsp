<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Aslı Börek Sipariş Dağıtım</title>

		<link rel='stylesheet prefetch' href='css/lib/bootstrap/3.3.6/bootstrap.min.css'/>
		<link rel="stylesheet" href="css/login.css" type="text/css" media="screen" title="default" />
		
	</head>
<body>

	<form:form action="login" method="post" class="form-signin" modelAttribute="loginAttribute">
		<div class="wrapper">
			<h2 class="form-signin-heading">Giriş</h2>

			<c:if test="${error}">
				<div class="clear"></div>
				<div id="message-red">
					<table border="0" width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td class="red-left">Kullanıcı Adı yada Parola hatalı!</td>
							<td class="red-right">
								<a class="close-red" onclick="document.getElementById('message-red').style.visibility = 'hidden'">
									<img src="images/login/icon_close_red.gif" alt="" />
								</a>
							</td>
						</tr>
					</table>
				</div>
			</c:if>
			<form:input type="text" class="form-control" path="username" placeholder="Kullanıcı Adı" required="true" autofocus="true" />
			<form:input type="password" class="form-control" path="password" placeholder="Parola" required="true" />
			<button class="btn btn-lg btn-primary btn-block" type="submit">Giriş Yap</button>
		</div>
	</form:form>

</body>
</html>