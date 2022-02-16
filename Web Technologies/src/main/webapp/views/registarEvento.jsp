<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title> Registar um evento </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../static/css/GetYourRace.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body class="formBody">

        <!-- Navigation Bar -->
        <jsp:include page="navBar.jsp">
            <jsp:param name="utilizador" value="${utilizador}"/>
        </jsp:include>

        <h2>Registar um evento</h2>

        <fieldset>
            <form id="eventRegistration">
        
                <p>Nome evento:</p>
                <input name="nomeEvento" type="text">

                <p>Data evento:</p>
                <input id="inputData" name="data" type="date">

                <p>Preço de inscrição:</p>
                <input name="preco" type="number">
                
                <p>Breve descrição:</p>
                <textarea name="descricao" rows="5" cols="50"></textarea>

                <p><input class="formButton" type="submit" value="Submeter"> <input class="formButton" type="reset"></p> 

            </form>

            <a href="${pageContext.request.contextPath}/"><button class="backButton">Voltar atrás</button></a>

        </fieldset>

        <p id="creditos">Made by: José Santos<br> <a href="mailto: l43017@alunos.uevora.pt">l43017@alunos.uevora.pt</a></p>
        
        <script>

            // Colocar a data de hoje como a data miníma para criar um evento
            document.getElementById("inputData").min = new Date().toISOString().substring(0, 10);

            $(document).ready(function(){
                $("#eventRegistration").on("submit", function(event){

                    event.preventDefault();

                    if(validateForm()){

                        var formValues= $(this).serialize();

                        $.post("/inserirEvento", formValues, function(respostaInserirEvento){

                            // É enviada uma string no formato JSON, é necessário converter
                            let jsonRespostaInserirEvento = JSON.parse(respostaInserirEvento);
                            
                            if(jsonRespostaInserirEvento.status === "ok"){
                                confirm('Evento registado com sucesso');
                                $("#eventRegistration")[0].reset();
                            }
                            else if(jsonRespostaInserirEvento.status == "erro"){
                                alert(jsonRespostaInserirEvento.msg);     
                            }
                            else{
                                alert("Ocorreu um erro.\n\nPor favor não utilize acentos nem cedilhas.\n\nSe o problema persistir tente mais tarde.");
                            }   
                        });

                        return true;
                    }

                    alert("Por favor preencha todos os campos sem utilizar ( \" \' - )");
                    return false;
                });
            });

            function validateForm(){

                let form = document.forms["eventRegistration"];
                
                let preco = form['preco'].value
                let nomeEvento = form['nomeEvento'].value;  
                let descricao = form['descricao'].value;
                let data = form['data'].value;

                if( nomeEvento == "" || descricao == "" || preco == "" || data == "" || 
                    descricao.includes("\"") || descricao.includes("\'") || descricao.includes("-")||
                    nomeEvento.includes("\"") || nomeEvento.includes("\'") || nomeEvento.includes("-")){

                    return false;
                }
                
                return true;
            }

        </script>
    </body>
</html>
