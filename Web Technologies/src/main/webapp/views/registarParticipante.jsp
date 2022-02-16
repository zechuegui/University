<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registar um participante</title>
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

        <h2> Registar-se num evento </h2>

        <fieldset>
        
            <form id="participantRegistration">
                
                <p>Evento:</p>              
                <div id="eventoDiv"></div>  

                <br>

                <label>Valor:</label>
                <label id="precoEvento"></label>

                <p>Género:</p>
                <input name="genero" id="masculino" type="radio" value="m">
                <label for="masculino"> Masculino </label>
                <input name="genero" id="feminino" type="radio" value="f">
                <label for="feminino"> Feminino</label>

                <br>

                <p>Escalão:</p>
                <select id="escalao" name="escalao">
                    <option value="default"> -- Selecione uma opção -- </option>
                    <option value="jun"> Júniores: 18 a 19 anos </option>
                    <option value="sen"> Séniores: 20 a 34 anos </option>
                    <option value="vet35"> Veteranos 35: dos 35 aos 39 anos </option>
                    <option value="vet40"> Veteranos 40: dos 40 aos 49 anos </option>
                    <option value="vet50"> Veteranos 50: dos 50 aos 59 anos </option>
                    <option value="vet60+"> Veteranos 60+: dos 60 anos em diante </option>
                </select>

                <br>
                <p><input id="submitButton" class="formButton" type="submit" value="Submeter"> <button type="button" class="formButton" onclick="resetForm();">Reset</button></p> 
            </form>

            <a href="${pageContext.request.contextPath}/"><button class="backButton">Voltar atrás</button></a>
        </fieldset>

        <p id="creditos">Made by: José Santos<br> <a href="mailto: l43017@alunos.uevora.pt">l43017@alunos.uevora.pt</a></p>

        <script>

            let eventosDropDown;

            $(document).ready(function(){

                // Buscar todos os eventos que ainda não aconteceram
                $.post("/getEventos",function(todosEventos){

                    let eventosPorAcontecer = filterFuturos(todosEventos);
                    eventosDropDown = eventosPorAcontecer;

                    // Se existem eventos por acontecer, colocá-los na dropdown
                    if( eventosPorAcontecer.length > 0 ){

                        // Dado um array de eventos coloca cada um como opção de uma dropDown
                        $("#eventoDiv").html(eventoParaDropdown(eventosPorAcontecer));
                    }
                    else{ // Nenhum evento por acontecer

                        $("#eventoDiv").html("Nenhum evento por acontecer<br>Por favor tente mais tarde".bold());
                        $("#submitButton").prop('disabled', true); 
                    }
                });
            

                // Fazer o POST do formulário e receber a resposta com o número do dorsal
                $("#participantRegistration").on("submit", function(event){

                    event.preventDefault();

                    if(validateForm()){

                        let e = document.getElementById("evento");
                        let idEvento = e.value;

                        var formValues= $(this).serialize();
                        
                        $.post("/inserirParticipante", formValues, function(dadosServer){

                            let dadosServerJSON = JSON.parse(dadosServer);
                            if(dadosServerJSON.status == "ok"){

                                // RECEBER O EVENTO PELO ID
                                $.post("/getEventoById", {id: idEvento}, function(dadosEvento){

                                    // Receber valor referente ao Eventos
                                    $.post("http://alunos.di.uevora.pt/tweb/t2/mbref4payment",{amount: dadosEvento.preco} ,function(dadosPagamento){
                                        
                                        let mbAmount = dadosPagamento.mb_amount;
                                        let mbEntity = parseInt(dadosPagamento.mb_entity);
                                        let mbReference = parseInt(dadosPagamento.mb_reference);

                                        $.post("/inserirPagemento", 
                                            {email: '${utilizador.getEmail()}', id: idEvento, mbAmount: mbAmount, mbEntity: mbEntity, mbReference: mbReference})
                                        
                                        let stringPagamento = "Entidade: "+mbEntity+
                                            "\nReferência: "+mbReference+
                                            "\nMontante: "+mbAmount+"€";

                                        confirm('Obrigado pela sua inscrição!\n\nOs dados de pagamento são os seguintes \n'+stringPagamento);
                                        
                                        // Reset dos dados
                                        resetForm();
                                    })

                                })

                            }
                            else if (dadosServerJSON.status == "erro"){

                                alert("Já está inscrito nesse evento.");
                            }
                            else{
                                alert("Ocorreu um erro.\n\nSe o problema persistir tente mais tarde.");
                            }     
                        });

                        return true;
                    }

                    alert("Por favor preencha todos os campos.");
                    return false;
                    
                });

            });

            // Fazer reset do form
            function resetForm(){
                $("#evento").val("default");
                $("#precoEvento").html("");
                $("#escalao").val("default");
                $("#masculino").prop("checked", false);
                $("#feminino").prop("checked", false);
            }

            // Atualizar o preco quando o evento muda
            function mudarEvento(select){

                let id = select.value;

                for(let evento of eventosDropDown){
                    if(evento.id == id){
                        $("#precoEvento").html(evento.preco+'€');
                        return;
                    }
                }
            }

            function validateForm(){

                let form = document.forms["participantRegistration"];
                
                let evento = form['evento'];
                let genero = form["genero"].value;
                let escalao = form["escalao"].value;

                if( evento == undefined || evento.value == "default" || genero == "" || escalao == "default" ){

                    return false;
                }
                
                return true;
            }

        </script>
    </body>
</html>
