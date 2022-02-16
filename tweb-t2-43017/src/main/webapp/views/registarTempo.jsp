<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Registar tempo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../static/css/GetYourRace.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../static/js/script.js"></script>
        
    </head>

    <body class="formBody">

        <!-- Navigation Bar -->
        <jsp:include page="navBar.jsp">
            <jsp:param name="utilizador" value="${utilizador}"/>
        </jsp:include>
        
        <h2>Registar tempo de conclusão</h2>

        <fieldset>

            <form id="registarTempoForm">
                
                <p>Evento:</p>
                <div id="eventoDiv"></div>

                <p>Número participante:</p>
                <input name="dorsal" type="number">

                <!-- start, P1, P2, P3, finish -->
                <p>Local:</p>
                <select name="local">
                    <option value="default" disabled selected> -- Selecione uma opção -- </option>
                    <option value="start"> Start </option>
                    <option value="p1"> P1 </option>
                    <option value="p2"> P2 </option>
                    <option value="p3"> P3 </option>
                    <option value="finish"> Finish </option>
                </select>
                
                <p>Tempo de conclusão:</p>
                <input name="tempo" type="text" onchange="validateTempo(this.value)">
            
                <p><input id="submitButton" class="formButton" type="submit" value="Submeter"> <input id="resetButton" class="formButton" type="reset"></p> 
                
            </form>

            <a href="${pageContext.request.contextPath}/"><button class="backButton">Voltar atrás</button></a>

        </fieldset>

        <p id="creditos">Made by: José Santos<br> <a href="mailto: l43017@alunos.uevora.pt">l43017@alunos.uevora.pt</a></p>

        <script>

            var tempoValido = false;
            let eventosDropDown;

            $(document).ready(function(){

        
                // Buscar todos os eventos que já aconteceram
                $.post("/getEventos",function(dadosServer){

                    let eventosDecorridos = filterDecorridos(dadosServer);
                    eventosDropDown = eventosDecorridos;

                    // Se existem eventos por acontecer, colocá-los na dropdown
                    if( eventosDecorridos.length > 0 ){

                        // Dado um array de eventos coloca cada um como opção de uma dropDown
                        $("#eventoDiv").html(eventoParaDropdown(eventosDecorridos));
                    }
                    else{ // Nenhum aconteceu

                        $("#eventoDiv").html("Ainda nenhum aconteceu<br>Por favor tente mais tarde".bold());
                        $("#submitButton").prop('disabled', true); 
                    }
                });

                $("#registarTempoForm").on("submit", function(event){

                    event.preventDefault();

                    if(validateForm()){
                        var formValues= $(this).serialize();

                        // Guardar o tempo
                        $.post("/saveTime", formValues, function(resultadoSaveTime){

                            switch(resultadoSaveTime){

                                case "dorsalNaoExiste":
                                    alert("Dorsal não existe\n\nPor favor verifique o valor inserido");
                                    break;
                                case "tempoExiste":  // TODO perguntar ao staff se quer substituir (se houver tempo)
                                    alert("Já existe um valor de tempo associado a esse atleta para essa prova e nesse local.");
                                    break;
                                case "ok":
                                    alert("Tempo inserido com sucesso!");
                                    $("#registarTempoForm")[0].reset();  // Reset dos valores introduzidos
                                    break;
                                case "default":
                                    alert("Ocorreu um erro\n\nPor favor tente mais tarde.");
                            }
                        });
                    }
                });
            });

            function validateForm(){

                let form = document.forms["registarTempoForm"];  

                let evento = form['evento'].value;
                let numero = form["dorsal"].value;
                let tempo = form["tempo"].value;
                let local = form["local"].value;

                if( evento == "default" || numero == "" || tempo == "" || local == "default"){

                    alert("Por favor preencha todos os campos");
                    return false;
                }
                else if(!tempoValido){
                    alert("Tempo inserido não é valido\n\nEx válido.: 10:06:50 (hh:mm:ss)")
                    return false;
                }

                return true;
            }

            function validateTempo(tempoInserido){

                let partesTempo = String(tempoInserido).split(':');

                if(partesTempo.length!=3){
                    tempoValido = false;
                    return;
                }

                for(let i=0; i<3; i++){

                    let numero = partesTempo[i];
        
                    if(isNaN(Number(numero)) || numero.includes(" ") || numero.includes('.') || (numero/60 >= 1) || numero.length != 2){
                        tempoValido = false;
                        return;
                    }

                }
                tempoValido = true;
                
            }

            function mudarEvento(evento){
                // dummy mudar evento
            }

        </script>
    </body>
</html>
