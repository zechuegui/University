<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Sign Up</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../static/css/GetYourRace.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>

        <!-- Navigation Bar -->
        <jsp:include page="navBar.jsp">
            <jsp:param name="utilizador" value="${utilizador}"/>
        </jsp:include>
        
        <fieldset>

            <form id="signUpForm" method="POST" action="/register">

                <p>Nome utilizador:</p>
                <input type="text" name="username"><br>

                <p>Password:</p>
                <input type="password" name="password"><br>

                <p>E-mail:</p>
                <input type="email" name="email1"><br>

                <p>Confirmar e-mail:</p>
                <input type="email" name="email2"><br>

                <c:if test="${not empty error}">
                    <div class="error"><strong>${error}</strong></div>
                </c:if>

                <p><input id="submitButton" class="formButton" type="submit" value="Submeter"> <input type="reset" class="formButton"></p>
            </form>

            <a href="${pageContext.request.contextPath}/"><button class="backButton">Voltar atrás</button></a>

        </fieldset>
        <p id="creditos">Made by: José Santos<br> <a href="mailto: l43017@alunos.uevora.pt">l43017@alunos.uevora.pt</a></p> 
    </body>
</html>