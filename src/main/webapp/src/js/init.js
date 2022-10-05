const range = (start, end) => {
    const length = end - start;
    return Array.from({length}, (_, i) => start + i);
}

const circles = {};

const xRange = range(-5, 4);
const rRange = range(1, 6);

function init() {
    printBtns(xRange, ".x-buttons", "x-dot", "x_value");
    printBtns(rRange, ".r-buttons", "r-size", "r_value");
    $(".r-size").each(function () {
            circles[$(this).val()] = []
        }
    );
    getTable();
    chooseFirstRadius();
    $("#svg-graph").on('click', function (event) {
        let target = $("#svg-graph");
        let r = $("#r_value").val();
        let x = Math.round(event.clientX - target.position().left);
        let y = event.clientY - target.position().top;
        let xValue = ((x - 150) / (100 / r)).toFixed(3);
        let yValue = ((y - 150) / (-100 / r)).toFixed(3);
        $('#x_value').val(xValue);
        $('#y_value').val(yValue);
        processSubmit();
    })
}

function printBtns(btnsAr, parent, className, targetClass) {
    for (let i = 0; i < btnsAr.length; i++) {
        const btn = document.createElement("input");
        btn.type = "button";
        btn.value = btnsAr[i];
        const buttons = $(parent);
        btn.classList.add(className);
        btn.id = className + i;
        btn.setAttribute("target", targetClass);
        btn.addEventListener("click", btnClick);
        buttons.append(btn);
    }
}

function chooseFirstRadius() {
    const r_button = $(".r-buttons .r-size:first-child");
    r_button.addClass("selected");
    $("#r_value").val(r_button.val());
    changeRLabels(r_button.val());
}

function getTable() {
    $.ajax({
        type: "GET",
        url: "table",
        success: function (response) {

            response.forEach(el => {
                    $('.info-table table tbody').append(getHtmlRowFromJson(el));
                    let color = el.result === true ? "#569E76" : "#B15E79";
                    createDot(el.x, el.y, el.r, color);
                    recreateDots($("#r_value").val());
                }
            )

        },
        error: function (data) {
            console.log(data);
        }
    });
}

function changeRLabels(value) {
    $(".r-g").each(function () {
            $(this).text(value)
        }
    )
    $(".-r-g").each(function () {
            $(this).text(-value)
        }
    )
    $(".r2-g").each(function () {
            $(this).text(value / 2)
        }
    )
    $(".-r2-g").each(function () {
            $(this).text(-value / 2)
        }
    )
}

