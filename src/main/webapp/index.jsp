<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="main.css">
    <title>LAB2</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="src/js/init.js"></script>
    <script src="src/js/click.js"></script>
    <script src="src/js/submit.js"></script>
    <script src="src/js/validator.js"></script>
    <script src="src/js/limiter.js"></script>
    <script src="src/js/cleaner.js"></script>
    <script src="src/js/dot.js"></script>
</head>

<body onload="init()">
<header>
    <div class="corner author-info">
        <p>Rakhmatullin Ramazan</p>
        <p>P32131</p>
    </div>
    <div class="project-name">
        <h1>Laboratory work #2</h1>
    </div>
    <div class="corner variant">
        <p>Variant: 13198</p>
    </div>
</header>
<div class="wrapper">
    <div class="wrap-content">
        <div class="sender">
            <div class="in-sender graph">
                <svg id="svg-graph" width="300px" height="300px" xmlns="https://www.w3.org/2000/svg">
                    <!-- Координатные оси -->
                    <line x1="0" x2="300" y1="150" y2="150"></line>
                    <line x1="150" x2="150" y1="0" y2="300"></line>
                    <!--Стрелки-->
                    <polygon points="150,0 145,15 155,15" stroke="black"></polygon>
                    <polygon points="300,150 285,145 285,155" stroke="black"></polygon>
                    <!--Прямоугольник в третьей четверти-->
                    <polygon points="150,150 150,250 200,250 200,150"></polygon>
                    <!--Четверть круга в первой четверти-->
                    <path d="M150,200 A50,50 90 0,1 100,150 L 150,150 Z"></path>
                    <!--Треугольник в четвертой четверти-->
                    <polygon points="50,150 150,50 150,150"></polygon>
                    <!-- Подписи к осям -->
                    <text x="285" y="135">X</text>
                    <text x="160" y="15">Y</text>
                    <!-- Метки для значений R на оси X -->
                    <line x1="50" x2="50" y1="143" y2="157"></line>
                    <line x1="100" x2="100" y1="143" y2="157"></line>
                    <line x1="200" x2="200" y1="143" y2="157"></line>
                    <line x1="250" x2="250" y1="143" y2="157"></line>
                    <!-- Метки для значений R на оси Y -->
                    <line x1="143" x2="157" y1="50" y2="50"></line>
                    <line x1="143" x2="157" y1="100" y2="100"></line>
                    <line x1="143" x2="157" y1="200" y2="200"></line>
                    <line x1="143" x2="157" y1="250" y2="250"></line>
                    <!-- Значения R на оси X -->
                    <text class="-r-g" x="40" y="138">-R</text>
                    <text class="-r2-g" x="85" y="138">-R/2</text>
                    <text class="r2-g" x="190" y="138">R/2</text>
                    <text class="r-g" x="245" y="138">R</text>
                    <!-- Значения R на оси Y -->
                    <text class="r-g" x="162" y="54">R</text>
                    <text class="r2-g" x="162" y="104">R/2</text>
                    <text class="-r2-g" x="162" y="204">-R/2</text>
                    <text class="-r-g" x="162" y="254">-R</text>
                </svg>
            </div>
            <div class="in-sender form-box">
                <form id="form" onsubmit="processSubmit(); return false;">
                    <div id="values x_div">
                        <input type="hidden" name="x" id="x_value" value="">
                        <div class="value-title">
                            <p>Enter X:</p>
                        </div>
                        <div class="buttons x-buttons"></div>
                        <div class="validation-info x-validation-info"></div>
                    </div>
                    <div id="values y_div">
                        <label for="y_value">
                            <div class="value-title">
                                <p>Enter Y:</p>
                            </div>
                            <div class="y">
                                <input type="text" name="y" id="y_value" oninput="limit_length(event, 10)">
                            </div>
                            <div class="validation-info y-validation-info"></div>
                        </label>
                    </div>
                    <div id="values r_div">
                        <div class="value-title">
                            <p>Enter R:</p>
                        </div>
                        <input type="hidden" name="r" id="r_value" value="">
                        <div class="buttons r-buttons"></div>
                        <div class="validation-info r-validation-info"></div>
                    </div>
                    <div class="managing-buttons">
                        <input type="submit" value="Проверить">
                        <input id="reset" type="reset" value="Сбросить" onclick="clean_input()">
                    </div>
                </form>
            </div>
        </div>
        <div class="table-box">
            <div class="clean-button">
                <input type="button" value="Очистить таблицу" onclick="clean_table()">
            </div>
            <div class="info-table">
                <table>
                    <thead>
                    <tr class="table-header">
                        <th>X</th>
                        <th>Y</th>
                        <th>R</th>
                        <th>Текущее время</th>
                        <th>Время выполнения</th>
                        <th>Результат</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>

</html>
