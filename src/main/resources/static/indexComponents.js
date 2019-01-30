function updateListaParticipantes(){
    $.getJSON("/listaParticipantes", function (dadosRetornados) {
        var newRowTable = '';
        dadosRetornados.forEach(function(e){
            e = jQuery.parseJSON(e);

            newRowTable += "<tr>";
            newRowTable += "<td>"+e["nomeParticipante"]+"</td>";
            newRowTable += "<td>"+e["valorPago"]+"</td>";
            newRowTable += "<td>"+e["bebida"]+"</td>";
            newRowTable += "<td>"+e["pago"]+"</td>";
            newRowTable += "<td>"+e["observacao"]+"</td>";
            newRowTable += "</tr>";
        });
        document.querySelector('tbody').innerHTML = newRowTable;
    });
}

$(document).ready(function(){

    updateListaParticipantes(); //Atualizando lista de participantes

    $("#addNewParticipante").submit(function (e){
        e.preventDefault();

        var objectToSend = {};
        $.each($(this).serializeArray(), function(){
            objectToSend[this.name] = this.value;
        });

        $.ajax({
            type: "POST",
            url: "/novoParticipante",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            data: JSON.stringify(objectToSend),
            success: function (dataResponde) {
                console.log(dataResponde);
                updateListaParticipantes();
            }
        });
    });
});