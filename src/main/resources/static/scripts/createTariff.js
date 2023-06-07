function createTariff() {
    var newTariff = document.createElement('div')
    newTariff.innerHTML =
        "<div draggable=\"true\" class=\"descriptionElements\">\n" +
        "                    <!--Дата начала-->\n" +
        "                    <div class=\"paramInput\">\n" +
        "                            <span class=\"flexParamInputCenter\">\n" +
        "                                <img width=\"24\" height=\"24\" class=\"icon\" src=\"icons/clock.svg\" alt=\"icon\">\n" +
        "                                <p class=\"text\">Начало</p>\n" +
        "                            </span>\n" +
        "                        <input type=\"text\" pattern=\"\\d\\d-\\d\\d\" class=\"niceInput\" name=\"input_startDate\"\n" +
        "                               preloader=\"дд-мм\">\n" +
        "                    </div>\n" +
        "                    <!--Дата окончания-->\n" +
        "                    <div class=\"paramInput\">\n" +
        "                            <span class=\"flexParamInputCenter\">\n" +
        "                                <img width=\"24\" height=\"24\" class=\"icon\" src=\"icons/clock.svg\" alt=\"icon\">\n" +
        "                                <p class=\"text\">Конец</p>\n" +
        "                            </span>\n" +
        "                        <input type=\"text\" pattern=\"\\d\\d-\\d\\d\" class=\"niceInput\" name=\"input_endDate\" preloader=\"дд-мм\">\n" +
        "                    </div>\n" +
        "                    <!--Стоимость-->\n" +
        "                    <div class=\"paramInput\">\n" +
        "                            <span class=\"flexParamInputCenter\">\n" +
        "                                <img width=\"24\" height=\"24\" class=\"icon\" src=\"icons/money.svg\" alt=\"icon\">\n" +
        "                                <p class=\"text\">Стоимость, ₽/сутки</p>\n" +
        "                            </span>\n" +
        "                        <input type=\"number\" class=\"niceInput\" name=\"input_summary_tariff\" preloader=\"\">\n" +
        "                    </div>\n" +
        "                </div>"

    document.getElementById("tariffContainer").prepend(newTariff)
}