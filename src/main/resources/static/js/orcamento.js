$('.selectClientes').select2({
    minimumInputLength: 3 // only start searching when the user has input 3 or more characters
});

 // função para remover linha
 function removeLinha() {
    $(".botaoRemover").off("click");
    $(".botaoRemover").on("click", function () {
        if ($("tr.linhas").length > 1) {
            $(this).parent().parent().remove();
            calculaTotalGeral();
            recalculaLinhas();
        }
    });
}

// função para adicionar linha
function adicionaLinha() {
    novoCampo = $("tr.linhas:first").clone();
    idLinha = parseInt($("tr.linhas:last").prop("id").split('item_')[1]) + 1;
    novoCampo.find("input").val("");
    novoCampo.insertAfter("tr.linhas:last").attr("id", "item_" + idLinha).find("span").html(idLinha);
    removeLinha();
}

// função para formatar valor
function formatarValor(valor) {
    valor = valor.replace(".", "");
    valor = valor.replace(",", ".");
    return parseFloat(valor);
}


// função para exibir valor
function exibirValor(valor) {
    tam = valor.length;
    if (tam <= 2) {
        return valor;
    }
    if ((tam > 2) && (tam <= 6)) {
        return valor.replace(".", ",");
    }
    if ((tam > 6) && (tam <= 9)) {
        return valor.substr(0, tam - 6) + '.' + valor.substr(tam - 6, 3) + ',' + valor.substr(tam - 2, tam);
    }
    if ((tam > 9) && (tam <= 12)) {
        return valor.substr(0, tam - 9) + '.' + valor.substr(tam - 6, 3) + '.' + valor.substr(tam - 6, 3) + ',' + valor.substr(tam - 2, tam);
    }
}


// função para calcular o valor Total de todas as linhas
function calculaTotalGeral() {
    var valores = 0;
    $('input[name="valorTotal[]"]').each(function () {
        valor = formatarValor($(this).val());
        if (isNaN(valor)) {
            valores += parseInt(0);
        } else {
            valores += valor;
        }

    });
    $("#totalGeral").html(exibirValor(valores.toFixed(2)));
}

// botão adicionar
$("#botaoAdicionar").on("click", adicionaLinha);

// botão remover tudo
$("#botaoRemoverTudo").click(function () {
    // total de linhas
    var totalLinhas = $("tr.linhas").length;
    // loop para remover todas a linhas
    for (var i = 1; i < totalLinhas; i++) {
        $("tr.linhas:last").remove();
    }
    $("tr.linhas").find("input").val("");
    calculaTotalGeral();
});

// calculando valores
$("table").on('keyup', 'input', function () {
    var Linha = $(this).parent().parent().prop("id").split('item_')[1];
    var quantidade = formatarValor($("tr#item_" + Linha).find("#quantidade").val());
    var valorUnitario = formatarValor($("tr#item_" + Linha).find("#valorUnitario").val());
    var valorTotal = (quantidade * valorUnitario);

    if (isNaN(valorTotal)) {
        valorTotal = "0.00"
    } else {
        valorTotal = exibirValor(valorTotal.toFixed(2));
    }
    $("tr#item_" + Linha).find("#valorTotal").val(valorTotal);
    calculaTotalGeral();
});