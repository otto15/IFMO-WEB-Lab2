function processSubmit() {
    let x_value = $('#x_value').val();
    let y_value = $('#y_value').val();
    let r_value = $('#r_value').val();
    console.log(x_value, y_value, r_value);
    const date = new Date();
    const offset = date.getTimezoneOffset();
    if (validate(x_value, y_value, r_value)) {
        $.ajax({
            type: "POST",
            url: "table",
            async: false,
            data: {"x": parseFloat(x_value.trim()), "y": parseFloat(y_value.trim()), "r": parseInt(r_value.trim()), "offset": offset},
            success: function(response_body) {
                $(".info-table tbody").append(getHtmlRowFromJson(response_body));
                let color = response_body.result === true ? "#569E76" : "#B15E79";
                showDot(createDot(response_body.x, response_body.y, response_body.r, color));
            },
            error: function(data) {
                alert(data);
            }
        });
    }
}

function getHtmlRowFromJson(data) {
    return `<tr>
        <td class="x">${data.x}</td>
        <td class="y">${data.y}</td>
        <td class="r">${data.r}</td>
        <td>${data.currentTime}</td>
        <td>${data.executionTime}</td>
        <td>${data.result}</td>
        </tr>`;
}
