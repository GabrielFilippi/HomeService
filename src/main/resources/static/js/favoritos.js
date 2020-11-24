$("#removerFavorito").on("click", function(e){
    e.preventDefault();

    var idFavorito = $(".divFavoritoEntity").attr("data-id");
    $.ajax({
        url: "/minha-conta/deleteFavorito/"+idFavorito,
        method: "GET",
        success: function (data) {
            location.reload();
        },
        error: function (data) {
            console.log(data);
            return null;
        }
    });
});