<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt">
    <head>

        <title>Get your race</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../static/css/GetYourRace.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../static/js/script.js"></script>
        
    </head>

    <div id="eventosAMostrar" neventos="ola"></div>

    <body id="mainPageBody">

        <!-- Navigation Bar -->
        <jsp:include page="navBar.jsp">
            <jsp:param name="utilizador" value="${utilizador}"/>
        </jsp:include>
            
        <div id="frontPageEvents">

            <button class="arrowLeftButton arrow"></button>

            <div id="eventos">
            </div>

            <button class="arrowRightButton arrow"></button>
        </div>

        

        <div id="finishLineDivWithAboutUs">

            <picture id="finishLineImg">
                <source media="(max-width: 479px)" srcset="../static/img/CrossingFinishLine/CrossingFinishLine-320w.jpg">
                <source media="(min-width: 480px) and (max-width: 639px)" srcset="../static/img/CrossingFinishLine/CrossingFinishLine-480w.jpg">
                <source media="(min-width: 640px) and (max-width: 799px)" srcset="../static/img/CrossingFinishLine/CrossingFinishLine-640w.jpg">
                <source media="(min-width: 800px) and (max-width: 899px)" srcset="../static/img/CrossingFinishLine/CrossingFinishLine-800w.jpg">
                <source media="(min-width: 900px)" srcset="../static/img/CrossingFinishLine/CrossingFinishLine-900w.jpg">
                <img src="../static/img/CrossingFinishLine/CrossingFinishLine-800w.jpg" 
                alt="Person crossing finish line">
            </picture>
            
            <div id="aboutUs">
                <h2>Sobre nós</h2>
                <p>
                    Stiar é uma comunidade de atletas que, em companhia ou sozinhos, desafiam-se a correr por novos locais 
                    do país. A partir da Stiar é possível encontrar e registar corridas em eventos relacionados com corrida,
                     como também ver as classificações de corridas anteriores.<br><br>

                    Quebre a rotina, faça desporto.<br><br>

                    2021 © Stiar. Todos os direitos reservados
                                        
                </p>
            </div>

            <div class="sponsors">
                <picture>
                    <source media="(max-width: 1000)" srcset="../static/img/Sponsors/decathlon-50.png">
                    <img src="../static/img/Sponsors/decathlon-100.png" alt="">
                </picture>
                <picture>
                    <source media="(max-width: 1000)" srcset="../static/img/Sponsors/gardengourmet-50w.png">
                    <img src="../static/img/Sponsors/gardengourmet-100w.png" alt="">
                </picture>
                <picture>
                    <source media="(max-width: 1000)" srcset="../static/img/Sponsors/rockinrio-50w.png">
                    <img src="../static/img/Sponsors/rockinrio-100w.png" alt="">
                </picture>
            </div>

        </div>

        <p id="creditos">Made by: José Santos<br> <a href="mailto: l43017@alunos.uevora.pt">l43017@alunos.uevora.pt</a></p>

        <script>

            let currentNEventos;

            $(document).ready(function(){
    
                // Colocar o titulo e descrição dos eventos
                /* {id: 0, 
                    nomeEvento: 'corrida de evora', 
                    descricao: ''', 
                    preco: 10, 
                    data: '2022-01-17'}*/
                $.post("/getEventos",function(eventos){

                    if(Object.keys(eventos).length == 0){ // Não existe nenhum evento
                        // TODO
                    }
                    
                    console.log("eventos - ",eventos)

                    let eventosSorted = eventos.sort(sortData);

                    let stringEventos = "";

                    let i = 1 // TODO
                    currentNEventos = 0;
                    for(let evento of eventosSorted){

                        let data = dateToPT(evento.data);

                        // <div class="img" style="display: inline-block;">
                        console.log("passou aqui");
                        stringEventos += `
                        <div class="img" style="display: inline-block;">
                            <img src="../static/img/Events/event\${i}.jpg" alt="" >
                            <h3>\${evento.nomeEvento}</h3>
                            <p>\${data}</p>
                            <p>\${evento.descricao}</p>
                            <p>Valor: \${evento.preco}€</p>
                        </div>`
                        i++;
                        if(i > 6){
                            i = 1;
                        }
                        currentNEventos ++;
                    }

                    console.log("NUMERO TODAL DE EVENTOS",currentNEventos)
        
                    $("#eventos").html(stringEventos);
                    $(window).resize();
                });
    
                $('.arrowLeftButton').click(function(){
    
                    $('.arrowRightButton').prop('disabled', false);
                
                    let conteudo_imagens = document.getElementsByClassName('img');
                    if( $(conteudo_imagens[0]).css('display') == 'inline-block' ){
                        $('.arrowLeftButton').prop('disabled', true);
                    }
                    else{ 
    
                        let ultimoNone = 0;
    
                        while($(conteudo_imagens[ultimoNone+1]).css('display') == 'none'){
                            ultimoNone++;
                        }
    
                        let ultimoInlineBlock = ultimoNone;
    
                        while($(conteudo_imagens[ultimoInlineBlock+1]).css('display') == 'inline-block'){
                            ultimoInlineBlock++;
                        }
    
                        $(conteudo_imagens[ultimoNone]).css('display', 'inline-block');
                        $(conteudo_imagens[ultimoInlineBlock]).css('display','none');
    
                    }
    
                });
    
                $('.arrowRightButton').click(() => {
    
                    $('.arrowLeftButton').prop('disabled', false);
                    
                    let conteudo_imagens = document.getElementsByClassName('img');
    
                    if( $(conteudo_imagens[conteudo_imagens.length-1]).css('display') == 'inline-block' ){
                        $('.arrowRightButton').prop('disabled', true);
                    }
                    else{ 
                    

                        let primeiroInlineBlock = getPrimeiroInlineBlock();
    
                        let primeiroNone = primeiroInlineBlock;
    
                        while($(conteudo_imagens[primeiroNone]).css('display') == 'inline-block'){
                            primeiroNone++;
                        }
    
                        $(conteudo_imagens[primeiroInlineBlock]).css('display','none');
                        $(conteudo_imagens[primeiroNone]).css('display', 'inline-block');
                        
                    }
                });

                $(window).on('resize', function() {

                    let viewWidth = $(this).width()
                    
                    if (viewWidth > 1459){
                        showEventos(4);
                    }
                    else if(viewWidth > 1119){
                        showEventos(3);
                    }
                    else if(viewWidth > 799){
                        showEventos(2);
                    }
                    else{
                        showEventos(1);
                    }
                });

                // Dependendo do n recebido, mostra esse nº de eventos
                function showEventos(nEventosAMostrar){
                    
                    let nOriginal = nEventosAMostrar;

                    if(nEventosAMostrar == currentNEventos){  // Se já estamos a mostrar o nº correto de eventos, não alterar nada
                        return;
                    }

                    let conteudoEventos = document.getElementsByClassName('img');        

                    let primeiroInlineBlock = getPrimeiroInlineBlock();


                    if(nEventosAMostrar < currentNEventos){   // Vamos retirar eventos da vista

                        let current = primeiroInlineBlock;

                        while(nEventosAMostrar>0){
                            current++;
                            nEventosAMostrar--;
                        }    

                        $(conteudoEventos[current]).css('display', 'none')

                        while($(conteudoEventos[current+1]).css('display') == 'inline-block'){

                            $(conteudoEventos[current+1]).css('display', 'none')
                            current++;
                        }

                        // Como tiramos Eventos da direita, o que está mais à direita na view já não é o último
                        $('.arrowRightButton').prop('disabled', false); 

                    }
                    else{  // Vamos adicionar um evento

                        nEventosAMostrar = nEventosAMostrar - currentNEventos;

                        // Vamos adicionar eventos à direita excepto se já não existirem mais, então adicionamos à esquerda (do primeiroInLineBlock)
                        let nTotalEventos = conteudoEventos.length;
                        let current = primeiroInlineBlock;

                        // Enquanto os da direita estiverem visíveis incrementar
                        while($(conteudoEventos[current+1]).css('display') == 'inline-block' && (current + 1 < nTotalEventos)){
                            current++;                                
                        }

                        // Ou chegamos ao primeiro nao visivel ou chegamos ao evento mais à direita


                        // Enquanto podemos mostrar o evento da direita
                        while(($(conteudoEventos[current+1]).css('display') == 'none') && (current + 1 < nTotalEventos) && (nEventosAMostrar>0)){

                            $(conteudoEventos[current+1]).css('display','inline-block');
                            current++;
                            nEventosAMostrar--;
                        }

                        // Ou não é necessário mostrar mais elementos, ou já mostramos todos à direita, ou não é possível ir mais para a direita

                        if(nEventosAMostrar > 0){  // Não existem mais eventos para mostrar à direita, mostramos à esquerda

                            current = primeiroInlineBlock;

                            // Como sabemos que o evento imediatamente à esquerda do primeiro está none não precisamos de percorrer
                            // Enquanto podemos mostrar o evento da esquerda
                            while($(conteudoEventos[current-1]).css('display') == 'none' && current > 0 && nEventosAMostrar>0){
                                $(conteudoEventos[current-1]).css('display','inline-block');
                                current--;
                                nEventosAMostrar--;
                            }
                        }
                    }
                    currentNEventos = nOriginal - nEventosAMostrar; // caso nao hajam eventos sufeciente para mostrar
                    console.log(currentNEventos);
                }
            });

            // Devolve o evento visível mais à esquerda
            function getPrimeiroInlineBlock(){

                let conteudoEventos = document.getElementsByClassName('img');  
                let primeiroInlineBlock = 0;

                while($(conteudoEventos[primeiroInlineBlock]).css('display') == 'none'){
                    primeiroInlineBlock++;
                }
                return primeiroInlineBlock;
            }

        </script>
    </body>
</html>
