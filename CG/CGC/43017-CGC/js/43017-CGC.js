function main(){    
    
    animate();
}

function cabeça(p){

    p.beginPath();
    
    p.arc(105,54, 30, 0 ,2*Math.PI);  //Mudar coordenadas
    p.closePath();
   
    p.fillStyle = "#FDDCB4";
    p.fill();
    // p.stroke();
}

function camisola(p){

    p.fillStyle = "#FDDCB4";    //Pescoço
    p.fillRect(92,80, 15,20);
    
    p.fillStyle = "#B22222";
    p.beginPath();                                  //Camisola Vermelha
    p.ellipse(100,170,32,80, Math.PI, 0, 2*Math.PI);
    p.fill();
 
}

function braço(){
    let a = document.getElementById("acanvas").getContext("2d");

    a.beginPath();
    a.ellipse(118 , 213 , 13 , 40 , -47 * Math.PI/180 , 0 , 2*Math.PI);
    a.closePath();
    a.fillStyle = "#FDDCB4";
    a.fill();
}

function antebraço(){
    let a = document.getElementById("acanvas").getContext("2d");

    a.fillStyle = "#B22222";
    a.beginPath()
    a.fillRect(88,110, 28, 30);
    a.closePath();

    a.fillStyle = "#FDDCB4";
    a.beginPath();
    a.fillRect(88,140, 28, 55);
    a.closePath();
}

function mao(){
    let a = document.getElementById("acanvas").getContext("2d");

    a.fillStyle = "#FDDCB4";
    a.beginPath();
    a.arc(143, 236 , 11, 0 , 2*Math.PI); //Palma da Mao

    a.ellipse(153, 228, 3, 6, 95*Math.PI/180, 0, 2 * Math.PI);  //Polegar
    a.ellipse(158, 236, 2.5, 10, 115*Math.PI/180, 0, 2 * Math.PI); //Indicador
    a.ellipse(156, 241, 2.5, 13, 115*Math.PI/180, 0, 2 * Math.PI);//médio
    a.ellipse(149, 245.1, 2.5, 13.5, 113*Math.PI/180, 0, 2 * Math.PI);//anelar
    a.ellipse(146, 249, 2, 9, 115*Math.PI/180, 0, 2 * Math.PI);//mindinho


    a.fill();
}

function rotaçaoBraços(aux, direçao){

    let p = document.getElementById("acanvas").getContext("2d");
    
    p.translate(100,110)

    p.rotate(aux * Math.PI / 180);
    if(aux>-40 && Boolean(direçao)){
        aux--;
    }
    else{
        direçao =0;
    }

    if(aux<40 && !Boolean(direçao)){
        aux++;
    }
    else{
        direçao=1;
    }
    
    p.translate(-100,-110);

    return {aux:aux, direçao:direçao};

}
////////////////////////////////////////////////////////////////////////
var auxBraços=0;
var subir = 1;

function braçoDireito(p, x){

    p.save();

    if(x<2800){
        var valores = rotaçaoBraços( auxBraços, subir);
        auxBraços = valores.aux;
        subir = valores.direçao;
    }
        

    antebraço();
    braço();
    mao();
    
    p.restore();
}
////////////////////////////////////////////////////////////////////////


function braçoEsquerdo(p, x){

    p.save();

    if(x<2800){
        rotaçaoBraços( -auxBraços, !subir);
    }

    antebraço();
    braço();
    mao();

    p.restore();
}

////////////////////////////////////////////////////////////////////////////////////////////////
function coxa(){
    let p = document.getElementById("acanvas").getContext("2d");
    p.fillStyle = "#0000CD";
    p.beginPath();
    p.ellipse(115,283,20,40,-25*Math.PI/180,0, 2*Math.PI);//coxa
    p.fill();
}

function perna(){
    let p = document.getElementById("acanvas").getContext("2d");
    p.fillStyle = "#0000CD"
    p.beginPath();
    p.ellipse(130,345,15,42, 175*Math.PI/180,0, 2*Math.PI); //perna
    p.arc(130, 315, 13, 0, 2*Math.PI);    //Joelho
    p.fill();

}

function pe(){
    let p = document.getElementById("acanvas").getContext("2d");

    p.beginPath();
    p.fillStyle = "#FDDCB4";
    p.fillRect(126,381, 15,20);//tornozelo
    p.fill();

    p.fillStyle = "black";

    p.save();

    p.translate(145,400);
    p.rotate(5*Math.PI/180);
    p.translate(-145,-400);

    p.beginPath();
    p.ellipse(145, 401, 6, 20, 90*Math.PI/180, 0, 2*Math.PI);//Sapato
    p.fillRect(124, 396, 16, 13);
    p.fill();
    p.closePath();

    
    p.restore();
}

function rotaçaoPerna(aux, topo){
    
    let p = document.getElementById("acanvas").getContext("2d");

    p.translate(100,250);
    p.rotate(10*Math.PI/180);
    p.rotate(aux * Math.PI / 180);

    if( topo === 0){

        aux-=0.7;
        if(aux<-30){
            topo=1;
        }
    }

    if(topo === 1){

        aux+=0.7;
        if(aux > 30){
            topo=0;
        }
    }
    p.translate(-100,-250);

    return {auxPerna: aux, subirPerna: topo};


}

var direitaTopo = 0;
var auxPerna =0;
function pernaDireita(p, x){

    p.save();

    if(x<2800){
        let auxRotaçao = rotaçaoPerna(auxPerna, direitaTopo);
        auxPerna = auxRotaçao.auxPerna;
        direitaTopo = auxRotaçao.subirPerna;
    }
    coxa();
    perna();
    pe();

    p.restore();
}

function pernaEsquerda(p, x){
    p.save();
    
    if(x<2800){
        rotaçaoPerna(-auxPerna, !direitaTopo);
    }
    

    coxa();
    perna();
    pe();

    p.restore();

}
////////////////////////////////////////////////////////////////////////
function pernas(p, x){
    p.save();
    p.translate(-5,-10);
    pernaEsquerda(p, x),
    pernaDireita(p, x);
    p.restore();
}

function pernasParadas(p, x){
    p.save();
    p.translate(-5,-10);
    pernaEsquerda(p, x),

    p.translate(50,30);
    p.rotate(15*Math.PI/180)
    p.translate(15,-50);
    pernaDireita(p, x);
    p.restore();
}

function pessoa(p, x){

    if(x<2800){
        p.save();
        p.scale(0.65,0.65);
        p.translate(450,270);
    
        cabeça(p);
        braçoEsquerdo(p,x);
        camisola(p);  
        pernas(p,x);
        braçoDireito(p,x);  
    
        p.restore(); 
    }
    else{
        pessoaParada(p,x);
    }
     
    
}

function pessoaParada(p,x){
    p.save();
    p.scale(0.65,0.65);
    p.translate(450,270);

    cabeça(p);
    braçoEsquerdo(p,x);
    camisola(p);  
    pernasParadas(p,x);
    braçoDireito(p,x);  

    p.restore();
}
////////////////////////////////////////////////////////////////////////
function cabecaPato(){

    let cP = document.getElementById("acanvas").getContext("2d");
    

    cP.save();
    
    cP.translate(7,7);
    cP.rotate(-30*Math.PI/180);

    cP.beginPath();
    cP.fillStyle = "orange";
    cP.lineTo(11,7);
    cP.lineTo(7,-7);
    cP.lineTo(0,0);
    cP.fill();

    cP.restore();

    cP.beginPath();
    cP.fillStyle = "white";
    cP.ellipse(0,0, 11, 7.5, 10*Math.PI/180, 0, 2*Math.PI);
    cP.fill();
    
    cP.beginPath();

    cP.fillStyle = "black";
    cP.arc(2, -1, 1.5, 0, 2*Math.PI);
    cP.fill();

}

function pescocoPato(){

    let pP = document.getElementById("acanvas").getContext("2d");

    pP.save();
    

    pP.rotate(-5*Math.PI/180);    

    pP.beginPath();
    
    pP.fillStyle = "white";
    pP.fillRect(-11, 0 ,8, 30);

    pP.restore();
}

function corpoPato(){
    let cP = document.getElementById("acanvas").getContext("2d");

    cP.save();

    cP.beginPath();
    

    cP.fillStyle = "white";
    cP.ellipse(-29, 31, 30, 15, -15*Math.PI/180, 0, 2*Math.PI);
    cP.fill();

    cP.restore();

}

function pata(){
    let p = document.getElementById("acanvas").getContext("2d");

    p.fillStyle = "orange";

    p.save();

    p.translate(-30, 45);

    p.fillRect(0,0, 3, 15); //perna

    p.beginPath();


    p.translate(-1,19);
    p.rotate(-90*Math.PI/180);
    p.scale(0.4,0.6);

    p.moveTo(10,0);
    p.lineTo(0,30);         //Pata
    p.lineTo(5,25);
    p.lineTo(10,30);
    p.lineTo(15,25);
    p.lineTo(20,30);
    p.lineTo(10,0);

    p.fill();

    p.restore();

}


function rotacaoPata(auxRP, descerPata){
    let rP = document.getElementById("acanvas").getContext("2d");
    
    rP.translate(-20,20);
    rP.rotate(auxRP*Math.PI/180);
    
    if(descerPata===0){

        auxRP-=0.5;
        if(auxRP<-10){
            descerPata = 1;
        }
    }
    else{

        auxRP+=0.5
        if(auxRP>10){
            descerPata=0;
        }
    }
    rP.translate(20,-20);

    return {auxRP: auxRP, descerPata: descerPata};
}

var auxRP=1;
var descerPata = 0;
function pataDir(p){

    p.save();

    if(x<2900){
        var auxR =rotacaoPata(auxRP, descerPata);
        auxRP = auxR.auxRP;
        descerPata = auxR.descerPata;
    }

    pata();

    p.restore();
}

function pataEsq(p){
    p.save();

    if(x<2900){
        rotacaoPata(-auxRP, !descerPata);
    }

    pata();

    p.restore();
}

function patasPato(p){
    
    pataEsq(p);
    pataDir(p);
}

var continuar=1; //variável auxiliar para o pato continuar a mexer depois de tudo ter parado
function pato(x){

    let p = document.getElementById("acanvas").getContext("2d");
    p.save();

    if(x<2790){
        p.translate(100,380);
    }
    else if(x<2900){

        p.translate(100+continuar, 380);
        continuar++;
    }
    else{
        p.translate(100+continuar, 380);
    }
    
    cabecaPato();
    pescocoPato();

    patasPato(p);

    corpoPato();
    
    
    p.restore();
 
}

////////////////////////////////////////////////////////////////////////
var auxSol = 0; //guarda a posição onde o sol estava no instante em que começa a descer
var auxCurva = 0; //ajuda a realizar o movimento em arco do sol

function sol(x){
    let s = document.getElementById("acanvas").getContext("2d");
    //Guardar posição Y onde o Sol estava antes de começar a descer

    var auxY = 150 - (x/ (8 + auxCurva));
    

    s.save();
    if( (850-(x/5)) <= 500 ){//sol chegou ao centro começar a descer
        
        s.translate(850-(x/5), auxY + 2 *  (auxSol/ (8+auxCurva)));
        auxSol++;
        if(auxCurva>0){
            auxCurva-=0.01;
        }  
    }
    else{
        s.translate(850-(x/5), 150-( x / (8+auxCurva)));
        auxCurva+=0.01;
    }   
    

    var gradienteSol = s.createLinearGradient(0,0,0,40);
    gradienteSol.addColorStop(0, "#FFD700");
    gradienteSol.addColorStop(0.5, "#FFA500");
    gradienteSol.addColorStop(1, "	#FF8C00");

    s.fillStyle = gradienteSol;
    
    s.beginPath();
    
    s.arc(0,0,40,0,2*Math.PI); 
    s.fill();
    s.closePath();

    s.restore();
}

function fundoAzul(p){
    var gradienteCeu = p.createLinearGradient(0, 0, 0, 380);
    gradienteCeu.addColorStop(0, "#004FFF");
    gradienteCeu.addColorStop(1, "#FFFFFF");
    p.fillStyle = gradienteCeu;

    p.fillRect(0, 0, innerWidth, 300);
}

function ceu(p, x){
    
    fundoAzul(p);
    sol(x);
}

function relva(p){
    var gradienteRelva = p.createLinearGradient(0, 0, 0, 525);
    gradienteRelva.addColorStop(0, "#7CFC00");
    gradienteRelva.addColorStop(1, "#227B00");
    p.fillStyle = gradienteRelva;

    p.fillRect(0,300,innerWidth, innerHeight);

    var gradienteTerra = p.createLinearGradient(1000,20,0, 1150);
    gradienteTerra.addColorStop(0, "#816C5B");
    gradienteTerra.addColorStop(0.5, "#B99C6B");
    gradienteTerra.addColorStop(0.1 , "#816C5B");

    p.fillStyle = gradienteTerra;

    p.fillRect(0,390, innerWidth, 80);

}

function troncoArvore(a){

    var gradienteTronco = a.createLinearGradient(0, 0, -1, -0.6);
    gradienteTronco.addColorStop(0, "#A0522D");
    gradienteTronco.addColorStop(1, "#8B4513");
    a.fillStyle = gradienteTronco;
    
    
    
    a.beginPath();
    a.lineTo(50,0);
    a.lineTo(25,-50);
    a.lineTo(0,0);
    a.closePath();

    a.fill();

    a.fillRect(10,0,30,-100);
}

function gradienteFolhas(a){
    let gradienteF = a.createLinearGradient(100,20,-30,10);
    gradienteF.addColorStop(0, "#32CD32");
    gradienteF.addColorStop(1, "#228B22");
    
    return gradienteF;

}

function escalaVelocidadeArvore(y, deslocacao){ //Variar o tamanho e velocidade das arvores consoante o y

    var aux = y-310;

    var size = 0.4 + (aux*0.02);
    var speed=deslocacao/(5.5 - aux*0.1);

    return {size: size, speed: speed};
}

function arvoreOval(x, y, deslocacao){
    let a = document.getElementById("acanvas").getContext("2d");

    a.save();

    var valores = escalaVelocidadeArvore(y, deslocacao);
    a.translate(x-valores.speed, y);
    a.scale(valores.size,valores.size);
    
    troncoArvore(a);

    a.beginPath();

    a.fillStyle = gradienteFolhas(a);
    a.ellipse(25,-180,50,100, 0,0, 2*Math.PI);
    a.fill();

    a.closePath();
    
    a.restore();

}

function arvoreNatal(x , y, deslocacao){
    let a = document.getElementById("acanvas").getContext("2d");

    a.save();

    var valores = escalaVelocidadeArvore(y, deslocacao);
    
    a.translate(x-valores.speed, y);

    a.scale(valores.size,valores.size);

    troncoArvore(a);

    a.fillStyle = gradienteFolhas(a);
    
    a.beginPath();
    a.moveTo(-50,-70);      //Triângulo mais baixo
    a.lineTo(100,-70);
    a.lineTo(25,-160);
    a.fill();

    a.beginPath();
    a.moveTo(-40 ,-120);       //Triângulo do meio
    a.lineTo(90, -120);
    a.lineTo(25, -210);
    a.fill();

    a.beginPath();
    a.moveTo(-30, -170);
    a.lineTo(80, -170);     //Triângulo do Topo
    a.lineTo(25, -270);
    a.fill();

    a.restore();

}
////////////////////////////////////////////////////////////////////////
function arvores(deslocacao){

    arvoreNatal(380 , 310, deslocacao);
    arvoreNatal(600 , 311, deslocacao);
    arvoreOval(500, 312, deslocacao);
    arvoreOval(450 , 313, deslocacao);
    arvoreNatal(50, 315, deslocacao);
    arvoreNatal(790, 315, deslocacao);  
    arvoreOval(220, 317, deslocacao);
    arvoreNatal(400, 319, deslocacao);
    arvoreOval(300, 320, deslocacao);
    arvoreOval(350 , 320, deslocacao);
    arvoreOval(860, 310, deslocacao);
    arvoreOval(900, 310, deslocacao)
    
    arvoreNatal(825, 320, deslocacao);

    arvoreNatal(730 , 325, deslocacao);
    arvoreNatal(140, 327, deslocacao);
    arvoreOval(640, 330, deslocacao);
    arvoreOval(80, 330, deslocacao);
    arvoreNatal(650, 335, deslocacao);
    
    
    arvoreOval(700, 341, deslocacao);
    arvoreNatal(260, 343, deslocacao);
    arvoreNatal(900,345, deslocacao);
    arvoreNatal(0, 350, deslocacao);
   
    //Depois do Rio

    arvoreOval(1300, 315, deslocacao);
    arvoreOval(1400, 317, deslocacao);
    arvoreNatal(1570, 318, deslocacao);
    
    arvoreNatal(1400,  320, deslocacao);
    
    arvoreOval(1650, 330, deslocacao);
    arvoreNatal(1800, 337, deslocacao);
    arvoreNatal(2000, 340, deslocacao);
    
}

function lago(x){
    let l = document.getElementById("acanvas").getContext("2d");

    var gradienteLago = l.createLinearGradient(0,0,0,250);
    gradienteLago.addColorStop(0, "#1E90FF");
    gradienteLago.addColorStop(1, "#00BFFF");
    l.fillStyle = gradienteLago;

    l.save();
    l.translate(1150-(x/4.7),335);

    l.beginPath();


    l.ellipse(0,0,200, 30, 0, 0, 2 * Math.PI);

    l.fill();
    l.restore();
}

function chafariz(x){
    let c = document.getElementById("acanvas").getContext("2d");

    let gradienteChafariz = c.createLinearGradient(14,15,0,11);
    gradienteChafariz.addColorStop(0, "#C0C0C0");
    gradienteChafariz.addColorStop(1, "#A9A9A9");
    c.fillStyle = gradienteChafariz;

    c.save();
    
    c.translate(2000-x,305);
    c.rotate(Math.PI);

    c.beginPath();
    c.lineTo(50,0);
    c.lineTo(25,-50); 
    c.lineTo(0,0);
    c.closePath();

    c.fill();


    c.fillRect(15,0, 5,5);
    c.fillRect(10,0,30,-100);

    c.restore();
}

function stopSign(){
    let s = document.getElementById("acanvas").getContext("2d");

    s.save();

    s.beginPath();
    s.translate(24, 35); 
    s.scale(0.5, 1);

    s.beginPath();
    s.fillStyle = "red";
    s.arc(0,0,25,0,2*Math.PI);  //Circulo Vermelho
    s.fill();
    
    s.fillStyle = "white";

    s.scale(0.8,1);
    s.rotate(15*Math.PI/180);
    s.fillRect(-17,-5,35,10);   //Rectângulo Branco
    

    s.restore();
}

function cancela(parar){
    let c = document.getElementById("acanvas").getContext("2d");

    c.save();

    c.beginPath();
    c.translate(3300-parar,320);

    c.fillStyle = "#583A2A";
    c.lineTo(10,3);
    c.lineTo(10, 73);       //Poste da esquerda
    c.lineTo(0, 70);
    c.lineTo(0,0);
    c.fill();

    c.beginPath();
    
    c.fillStyle="#A17B67";
    c.ellipse(25,25, 35, 4, 50*Math.PI/180, 0, Math.PI);
    c.fill();               //Corda

    stopSign(); //Sinal proibida

    c.beginPath();

    c.fillStyle = "#583A2A";
    c.translate(40,49);
    c.lineTo(10,3);
    c.lineTo(10,103);       //Poste da direita
    c.lineTo(0, 100);
    c.lineTo(0,0);

    c.fill();


    c.restore();
}

var parar;
function chao(p, x){

    
    if(x<2800){
        parar = x;
    }

    relva(p);
    lago(parar);
    arvores(parar);
    chafariz(parar);
    cancela(parar);

}

function background(p,x){

    ceu(p, x);
    chao(p, x);
}
////////////////////////////////////////////////////////////////////////
var x=1;
function animate (){
    
    requestAnimationFrame(animate);
    let p = document.getElementById("acanvas").getContext("2d");

    p.clearRect(0,0,  innerWidth+1000, outerHeight+1000);


    background(p, x);

    pato(x);
    pessoa(p,x)

    x++;
}


