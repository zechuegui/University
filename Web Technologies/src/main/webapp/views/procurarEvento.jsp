<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <title> Procurar eventos </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../static/css/GetYourRace.css">
    <script src="../static/js/script.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body class="formBody">

    <!-- Navigation Bar -->
    <jsp:include page="navBar.jsp">
        <jsp:param name="utilizador" value="${utilizador}"/>
    </jsp:include>

    <h2>Procurar evento</h2>

    <fieldset>

        <br>
        <div style="display: inline-block;">
            <p>Procurar evento</p>
            <input type="text" id="textBoxEvento" oninput="filterTableEventos()">
        </div>
        <div style="display: inline-block;">
            <p>Procurar data</p>
            <input type="date" id="dataEvento" oninput="filterTableEventos()">
        </div>
        <br>

        <div id="informacao"></div>
        <table id="table">
            <thead>
                <tr>
                    <th id="evento_th">Evento</th>
                    <th id="data_th">Data</th>
                    <th id="participantes_th">Participantes</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <br>
        <a href="${pageContext.request.contextPath}/"><button class="backButton">Voltar atrás</button></a>

    </fieldset>


    <script>

        let todosEventos;

        $(document).ready(function(){

            // Buscar todos os eventos
            $.post("/getEventos",function(dadosServer){

                todosEventos = dadosServer.sort(sortData);

                // Se existem eventos por acontecer, popular a tabela
                if( dadosServer.length > 0 ){

                    addTable(todosEventos);
                }
                else{ // Nenhum evento por acontecer

                    $("#table").remove();
                    $("#informacao").html("Não existe nenhum evento<br>Por favor tente mais tarde".bold());
                    $("#dataEvento").prop('disabled', true); 
                    $("#textBoxEvento").prop('disabled', true); 
                }
            });
        });

        function checkTextoEvento(textoEvento, splitTextoEvento, evento){
            return textoEvento!="" ? auxIncludes(evento.nomeEvento, splitTextoEvento): true
        }

        function checkData(data, evento){
            return data!=""? evento.data == data : true;
        }

        function filterTableEventos(){
            let data = $("#dataEvento").val();
            let textoEvento = $("#textBoxEvento").val();

            let splitTextoEvento = textoEvento.split(" ");

            addTable(
                todosEventos.filter((evento)=>{

                    return (checkTextoEvento(textoEvento, splitTextoEvento, evento) && 
                            checkData(data, evento));
                })
            )
        }

        function addTable(informacaoTabela){

            resetTable();

            // Guardar o número de eventos inseridas para adicionar no fim
            let rowNumber = 0;

            // Adicionar no body da tabela
            let table = $("#table tbody")[0];

            informacaoTabela.forEach((informacao) => {
                
                let row = table.insertRow(rowNumber);

                row.insertCell(0).innerHTML = informacao.nomeEvento;
                row.insertCell(1).innerHTML = dateToPT(informacao.data);

                // Se o evento já ocorreu, queremos as classificações, se não queremos as inscrições
                row.insertCell(2).innerHTML = ( new Date(informacao.data) < new Date()) || (isToday(new Date(informacao.data))) ?
                    '<form action="/informacaoEvento" method="POST"><button name="nomeEvento" value="'+informacao.nomeEvento+'">Classificações</button>'+
                    '<input type="hidden" name="tipo" value="classificacao"/></form>' : 
                    '<form action="/informacaoEvento" method="POST"><button name="nomeEvento" value="'+informacao.nomeEvento+'">Inscrições</button>'+
                    '<input type="hidden" name="tipo" value="inscricao"/></form>';

                rowNumber ++;
            });

        }

    </script>

</body>
</html>