<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Navigation bar</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../static/css/GetYourRace.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../static/js/script.js"></script>
    </head>
    <body>

        <h1><a id="stiarTitle" href="${pageContext.request.contextPath}/">Stiar!</a></h1>

        <div class="navigation">
            <c:if test="${utilizador.getRole() eq 'ROLE_ATLETA'}">
                <a class="nav_option" href="${pageContext.request.contextPath}/registarParticipante"> Inscrever </a>
                <a class="nav_option" href="${pageContext.request.contextPath}/verInscricoes"> As minhas inscrições </a>
            </c:if>
            <c:if test="${utilizador.getRole() eq 'ROLE_STAFF'}">
                <a class="nav_option" href="${pageContext.request.contextPath}/registarEvento"> Registar evento </a>
                <a class="nav_option" href="${pageContext.request.contextPath}/registarTempo"> Registar tempo </a>
            </c:if>
            <a class="nav_option" href="${pageContext.request.contextPath}/procurarEvento"> Procurar eventos </a>

            <c:choose>
                <c:when test="${not empty utilizador}">
                    <a class="nav_option" style="float:right" href="<c:url value='logout'/>">Logout</a>   
                    <a class="nav_option" style="float:right">${utilizador.getUsername()}</a>
                </c:when>
                <c:otherwise>
                    <a class="nav_option" style="float:right" href="${pageContext.request.contextPath}/signup"> Criar conta </a>
                    <a class="nav_option" style="float:right" href="${pageContext.request.contextPath}/login"> Login </a>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>