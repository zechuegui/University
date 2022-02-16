// erro na consola se colocar let,var,const
escalaoExtenso = {
    'jun' : "Júnior",
    'sen': "Sénior",
    'vet35': "Veterano 35",
    'vet40': "Veterano 40",
    'vet50': "Veterano 50",
    'vet60+': "Veterano 60+"
}

// Dado um array de evetos, ordenar a data por ordem crescente
function sortData(a, b){

    return new Date(a.data) > new Date(b.data) ? 1 : -1;
}

// Dado um array de eventos, filtra para ter apenas os futuros
function filterFuturos(dados){
    // Filtrar os eventos recebidos
    return dados.filter((evento) => {
        
        let dataEvento = new Date(evento.data)
        // Data do evento é maior e não é o dia de hoje
        return ( dataEvento > new Date()) && !(isToday(dataEvento));
    });
}

// Dado um array de eventos, filtra para ter apenas os que já aconteceram
function filterDecorridos(dados){

    // Filtrar os eventos recebidos
    return dados.filter((evento) => {
        
        let dataEvento = new Date(evento.data)
        // Data do evento é menor ou é o dia de hoje
        return ( dataEvento < new Date()) || (isToday(dataEvento));
    });

    
}
// Return true se o dia dado é o atual
function isToday(data){
    let hoje = new Date();
    return (data.getDate() === hoje.getDate() &&
            data.getMonth() === hoje.getMonth() &&
            data.getFullYear() === hoje.getFullYear());

}

// Dado um array de eventos, prepara a string html para os colocar numa dropDown
function eventoParaDropdown(eventos){

    let stringEventosOcorridos = '<select id="evento" name="evento" onchange="mudarEvento(this)">';
    stringEventosOcorridos += '<option value="default" disabled selected value> -- selecione uma opção -- </option>'

    eventos.forEach((evento) => {
        stringEventosOcorridos += '<option value="'+evento.id+'">'+evento.nomeEvento+'</option>';
    });

    stringEventosOcorridos += '</select>';

    return stringEventosOcorridos;
}

// Faz o reset da tabela
function resetTable(){
    $("#table tbody tr").remove();
} 

// dado uma data no formato YYYY-MM-DD, tranforma em DD-MM-YYYY
function dateToPT(data){
    let ano = data.substring(0,4);
    let mes = data.substring(4,8); // Mês contém os hifens
    let dia = data.substring(8,10);

    return dia+mes+ano;
}

// Dada uma string e um array, devolve true se os valores do array se encontrarem na String, e false se não
function auxIncludes(string, array){
    // Verifica se o conteúdo do array se encontra na string
    for(let value of array){  // Ciclo for em vez de forEach, para parar caso não encontre
        if(!string.includes(value)){
            return false;
        }
    }
    return true;
}
