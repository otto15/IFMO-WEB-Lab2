function clean_input() {

    var elems = document.querySelectorAll(".selected");

    [].forEach.call(elems, function (el) {
        el.classList.remove("selected");
    });

    $('#x_value').val(undefined);
    $('#y_value').val("");
    $('#r_value').val(undefined);
}

function clean_table() {
    $.ajax({
        type: "DELETE",
        url: "table",
    });
    $(".r-size").each(function () {
            circles[$(this).val()].forEach(
                dot => {
                    dot.remove();
                }
            );
            circles[$(this).val()] = [];
        }
    );
    $('.info-table table tbody').html("");
}