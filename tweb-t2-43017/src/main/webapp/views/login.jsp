<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="../static/css/GetYourRace.css">
</head>
<body class="formBody">

	<!-- Navigation Bar -->
	<jsp:include page="navBar.jsp">
		<jsp:param name="utilizador" value="${utilizador}"/>
	</jsp:include>

	<h2>Login</h2>

	<fieldset>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		<c:if test="${not empty success}">
			<div class="success">${success}</div>
		</c:if>
		<form name="loginForm"
			action="<c:url value='j_spring_security_check' />" method="POST">

				<p>E-mail:</p>
				<input type="text" name="username" value="">

				<p>Password:</p>
				<input type="password" name="password" />


				<p><input id="submitButton" class="formButton" type="submit" value="Submeter"> <input type="reset" class="formButton"></p>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

		<a href="${pageContext.request.contextPath}/"><button class="backButton">Voltar atrás</button></a>
	</fieldset>
</body>
</html>