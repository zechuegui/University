<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Informação evento</title>
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

        <c:if test="${tipo=='classificacao'}">
            <h2>Classificações - ${nomeEvento}</h2>
        </c:if>
        <c:if test="${tipo=='inscricao'}">
            <h2>Inscrições - ${nomeEvento}</h2>
        </c:if>


        <fieldset>

            <a style="position:absolute; top: 10px; left: 20px;" href="${pageContext.request.contextPath}/procurarEvento"><button class="backButton">Voltar atrás</button></a>

            <!-- Caso o utilizador tente entrar diretamente sem escolher um evento -->
            <c:if test="${empty tipo}">
                <c:redirect url="/procurarEvento"/>
            </c:if>

            <p>Género</p>
            <input id="checkboxHomem" type="checkbox" class="checkbox" onchange="filterTable()" checked disabled><label for="checkboxHomem">Homem</label>
            <input id="checkboxMulher" type="checkbox" class="checkbox" onchange="filterTable()" checked disabled><label for="checkboxMulher">Mulher</label>       
            
            <table id="table">
                <thead>
                    <tr>
                        <th>Dorsal <br>
                            <input type="text" id="procurarDorsal" oninput="filterTable()">
                        </th>
                        <th>Nome <br>
                            <input type="text" id="procurarNome" oninput="filterTable()">  
                        </th>
                        <th>Escalão <br>
                            <select id="escalaoDropDown" onchange="filterTable()" disabled>
                                <option value="todos"> Todos</option>
                                <option value="jun"> Júniores: 18 a 19 anos </option>
                                <option value="sen"> Séniores: 20 a 34 anos </option>
                                <option value="vet35"> Veteranos 35: dos 35 aos 39 anos </option>
                                <option value="vet40"> Veteranos 40: dos 40 aos 49 anos </option>
                                <option value="vet50"> Veteranos 50: dos 50 aos 59 anos </option>
                                <option value="vet60+"> Veteranos 60+: dos 60 anos em diante </option>
                            </select>
                        </th>
                        <c:if test="${tipo=='classificacao'}">
                            <th>Start</th>
                            <th>P1</th>
                            <th>P2</th>
                            <th>P3</th>
                            <th>Finish</th>
                            <th>Tempo total</th>
                        </c:if>
                        <c:if test="${tipo=='inscricao'}">
                            <th>Estado</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
            <h3 id="informacao"></h3>

        </fieldset>

        <script>
            // TODO ARRANJAR COMPLEXIDADE SE HOUVER TEMPO

            let dadosOriginais = [];

            $(document).ready(function(){

                // {dorsal: 1, name: 'jose', genero: 'm', escalao: 'jun', pago: 0}
                $.post("/getCommonInformationByEventId", {id: '${id}'} ,function(commonInformation){

                    if(commonInformation.length > 0){


                        for(let value of commonInformation){
                            dadosOriginais.push(JSON.parse(value));
                        }

                        if('${tipo}'=='inscricao'){
                            addTable(dadosOriginais);
                        }
                        else{ // Se queremos as classificações temos de obter os tempos

                            $.post("/getTempoById", {id: '${id}'}, function(tempos){

                                // Associar os tempos no local ao dorsal
                                for(let ogDados of dadosOriginais){
                                    for(let tempo of tempos){
                                        if(ogDados.dorsal == tempo.dorsal){
                                            ogDados[tempo.ponto] = tempo.tempo;

                                            // Se já existem tempos de start e finish associados a este dorsal, inserimos no Objeto
                                            if(ogDados["start"] !== undefined && ogDados["finish"]!== undefined){  
                                                ogDados["tempoTotal"] = subtrairTempo(ogDados["finish"], ogDados["start"]);
                                            }

                                        }
                                    }
                                }

                                addTable(dadosOriginais.sort(compareTempos));  // Ordenamos por ordem decrescete
                            });
                        }

                        $("#checkboxHomem").prop("disabled", false);
                        $("#checkboxMulher").prop("disabled", false);
                        $("#escalaoDropDown").prop("disabled", false);

                        return;
                    }

                    if('${tipo}'=='inscricao'){

                        $("#informacao").html("Não existe nenhuma inscrição".bold());
                        return;
                    }   

                    $("#informacao").html("Não existe nenhuma classificação".bold());                
                    
                });
            });

            // {dorsal: 1, name: 'jose', genero: 'm', escalao: 'jun', pago: 0}
            function addTable(informacaoTabela){

                console.log("informação a adicionar", informacaoTabela);

                resetTable();

                // Guardar o número de eventos inseridas para adicionar no fim
                let rowNumber = 0;

                // Adicionar no body da tabela
                let table = $("#table tbody")[0];

                informacaoTabela.forEach((informacao) => {

                    $("#informacao").html("");
                    
                    let row = table.insertRow(rowNumber);

                    row.insertCell(0).innerHTML = (informacao.dorsal == 0) ? "" : informacao.dorsal;
                    row.insertCell(1).innerHTML = informacao.name;
                    row.insertCell(2).innerHTML = escalaoExtenso[informacao.escalao];

                    if('${tipo}'=='inscricao'){

                        row.insertCell(3).innerHTML = (informacao.pago == 1) ? "Confirmada" : "Não confirmada";
                    }
                    else {

                        row.insertCell(3).innerHTML = informacao.start ? informacao.start : 'X';
                        row.insertCell(4).innerHTML = informacao.P1 ? informacao.P1 : 'X';
                        row.insertCell(5).innerHTML = informacao.P2 ? informacao.P2 : 'X';
                        row.insertCell(6).innerHTML = informacao.P3 ? informacao.P3 : 'X';
                        row.insertCell(7).innerHTML = informacao.finish ? informacao.finish : 'X';
                        row.insertCell(8).innerHTML = informacao.tempoTotal ? informacao.tempoTotal : 'X';
                    }

                    rowNumber++;
                });

                if(rowNumber==0){
                    $("#informacao").html("Não existe nenhuma correspondência para os filtros introduzidos");
                }

            }

            function checkHomem(homemChecked, pessoa){
                return homemChecked && pessoa.genero == 'm';
            }

            function checkMulher(mulherChecked, pessoa){
                return mulherChecked && pessoa.genero == 'f';
            }

            function checkEscalao(escalao, pessoa){
                return escalao == 'todos' ? true : pessoa.escalao == String(escalao);
            }

            function checkDorsal(dorsal, splitDorsal, pessoa){
                return dorsal!="" ? auxIncludes(pessoa.dorsal.toString(), splitDorsal): true;
            }

            function checkNome(nome, splitNome, pessoa){
                return nome!=""? auxIncludes(pessoa.name, splitNome) : true
            }

            // Filtra a tabela consoante os filtros introduzidos
            function filterTable(){

                let homemChecked = $('#checkboxHomem').prop('checked');
                let mulherChecked = $('#checkboxMulher').prop('checked');
                let escalao = $('#escalaoDropDown').prop('value');

                let dorsal = $('#procurarDorsal').prop('value').toString();
                let nome = $('#procurarNome').prop('value');

                let splitDorsal = dorsal.split(" ");
                let splitNome = nome.split(" ");

                if(homemChecked || mulherChecked){

                    addTable(
                        dadosOriginais.filter((pessoa) => {
                            console.log(pessoa);
                            return (checkHomem(homemChecked, pessoa) || checkMulher(mulherChecked, pessoa)) && 
                                    checkEscalao(escalao, pessoa) &&
                                    checkDorsal(dorsal, splitDorsal, pessoa) && 
                                    checkNome(nome, splitNome, pessoa);
                        })
                    );
                    return;
                }

                addTable([]);
            }

            function compareTempos(t1, t2){
                let t1Array = t1.tempoTotal.split(":");
                let t2Array = t2.tempoTotal.split(":");

                for(let i=0; i<3; i++){

                    if(t1Array[i] > t2Array[i]){
                        return 1;
                    }
                    else if(t1Array[i] < t2Array[i]){
                        return -1;
                    }
                }
                return 0;
            }
        
            function subtrairTempo(final, inicial){

                let finalArray = final.split(":");
                let inicialArray = inicial.split(":");

                let newTempo = [];
                let carry = 0;

                for(let i = 2; i>=0; i--){

                    let value = finalArray[i]-inicialArray[i];

                    // Se o carry for = 1 significa que a subtração anterior foi negativa
                    if(carry == 1){
                        value -= 1;
                        carry = 0;
                    }

                    if(value < 0){ // Se o valor for negativo

                        // adicionamos 60 e colocamos o carry a 1
                        value += 60;
                        carry = 1;                        
                    }

                    value = value.toString();

                    (value.length < 2) && (value = "0" + value);

                    newTempo.unshift(value);  // Shift em vez de push porque queremos adicionar ao início
                }

                return newTempo.join(':');

            }
        </script>
    </body>
</html>
