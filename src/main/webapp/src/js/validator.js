function validate(x, y, r) {
    let elems;
    let x_validation_info_box = document.querySelector('.x-validation-info');
    let y_validation_info_box = document.querySelector('.y-validation-info');
    let r_validation_info_box = document.querySelector('.r-validation-info');

    x_validation_info_box.innerHTML = "";
    y_validation_info_box.innerHTML = "";
    r_validation_info_box.innerHTML = "";

    let x_validation_success = false;
    let y_validation_success = false;
    let r_validation_success = false;

    if (x === undefined || x === "") {
        x_validation_info_box.innerHTML = "<span>Не выбрано значение X!</span>";
    } else {
        x_validation_success = true;
    }

    if (!(y.trim() === "")) {
        let y_value = Number(y);
        if (isNaN(y_value)) {
            y_validation_info_box.innerHTML = "<span>Координата Y задается числом!</span>";
        } else {
            if ((y_value > -5) && (y_value < 5)) {
                y_validation_success = true;
            } else y_validation_info_box.innerHTML = "<span>Координата Y задается числом в промежутке (-5..5)!</span>";
        }
    } else y_validation_info_box.innerHTML = "<span>Не введена координата Y!</span>";

    if (r === undefined || r === "") {
        r_validation_info_box.innerHTML = "<span>Не выбрано значение R!</span>";
    } else {
        r_validation_success = true;
    }

    if (!x_validation_success) {
        elems = document.querySelectorAll(".x-dot.selected");

        [].forEach.call(elems, function (el) {
            el.classList.remove("selected");
        });

        $('#x_value').val("");
    }

    if (!r_validation_success) {
        elems = document.querySelectorAll(".r-dot.selected");

        [].forEach.call(elems, function (el) {
            el.classList.remove("selected");
        });

        $('#r_value').val("");
    }

    if (!y_validation_success) {
        $('#y_value').val("");
    }

    return x_validation_success && y_validation_success && r_validation_success;
}