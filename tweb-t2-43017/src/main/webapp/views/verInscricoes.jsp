<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>As minhas inscricoes</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../static/css/GetYourRace.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../static/js/script.js"></script>

    </head>
    <body>

        <!-- Navigation Bar -->
        <jsp:include page="navBar.jsp">
            <jsp:param name="utilizador" value="${utilizador}"/>
        </jsp:include>
        
        <fieldset>
            <h4 id="informacao_inscrito_nenhum_evento"></h4>
            <table id="table">
                <caption>-- Eventos em que está inscrito -- </caption>
                <thead>
                    <tr>
                        <th id="dorsal_th">Dorsal</th>
                        <th id="evento_th">Evento</th>
                        <th id="dia_th">Dia</th>
                        <th id="pago_th">Pago</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <br>
            <a href="${pageContext.request.contextPath}/"><button class="backButton">Voltar atrás</button></a>
            <br>
        </fieldset>
    <script>

        let informacaoTabela = new Array();

        // Dado um array de pessoas adiciona-as à tabela (SEM TEMPO)
        $(document).ready(function(){

            // Receber todas as inscricoes associadas ao utilizador
            $.post("/getInscricoesByEmail", {email: "${utilizador.getEmail()}"}, function(dadosServer){

                if(dadosServer != null){

                    if(dadosServer.length != 0){

                        $.post("/getEventos", function(eventos){
                            
                            dadosServer.forEach((inscricao) => {

                                informacaoTabela.push(
                                    {   
                                        "dorsal": inscricao.dorsal,
                                        "nomeEvento": eventos[inscricao.id].nomeEvento,
                                        "dia": dateToPT(eventos[inscricao.id].data),
                                        "pago": inscricao.pago,
                                    }
                                )
                            })

                            addTable(informacaoTabela);
                
                        });   
                    }else{
                        $("#table").remove();
                        $("#informacao_inscrito_nenhum_evento")
                            .html("Não está inscrito em nenhum evento".bold());
                    }
                }
                else{
                    alert("Ocorreu um erro!\n\nPor favor tente mais tarde.");
                }

            });

        });
        
        function addTable(informacaoTabela){

            resetTable();

            // Guardar o número de eventos inseridas para adicionar no fim
            let rowNumber = 0;

            // Adicionar no body da tabela
            let table = $("#table tbody")[0];

            informacaoTabela.forEach((informacao) => {
                
                let row = table.insertRow(rowNumber);

                row.insertCell(0).innerHTML = (informacao.dorsal == 0) ? "": informacao.dorsal;
                row.insertCell(1).innerHTML = informacao.nomeEvento;
                row.insertCell(2).innerHTML = informacao.dia;
                row.insertCell(3).innerHTML = (informacao.pago == 0) ? 
                    '<button class="botaoPagar" id="'+informacao.nomeEvento+'" onclick="botaoPagarOnClick(this)">Pagar</button>':
                    "Pago";

                rowNumber ++;
            });

        }

        function botaoPagarOnClick(botao){ // id do botão contém o nome do evento associado

            $.post("/getEventoIdByName", {nomeEvento: botao.id}, function(id){

                $.post("/getDadosPagamento", {email: '${utilizador.getEmail()}', id: id}, function(dadosPagamento){

                    let values = Object.values(dadosPagamento);

                    let mbEntity = values[3];
                    let mbReference = values[4];
                    let mbAmount = values[2];

                    let stringConfirmacao = "Entidade: "+mbEntity+
                                            "\nReferência: "+mbReference+
                                            "\nMontante: "+mbAmount+
                                            "€\n\nDeseja pagar?";

                    let confirma = confirm(stringConfirmacao);

                    if(confirma){
                        $.post("/pagarEvento",{mbReference: parseInt(mbReference), email: '${utilizador.getEmail()}', id: id}, function(respostaPagarEvento){

                            if(respostaPagarEvento != 0){
                                
                                alert("Pagamento realizado com sucesso!\n\nO número do seu dorsal é "+respostaPagarEvento);
                                // Realizado um reload para atualizar a página
                                location.reload();
                                return;
                            }

                            alert("Ocorreu um problema.\n\nPor favor tente mais tarde.");

                        });
                    }
                })
            })
        }
    </script>
    </body>
</html>
