
function createTariff() {

    if (document.getElementsByClassName("tariff").length < 8) {
        var newTariff = document.createElement('div')
        newTariff.innerHTML =
            "                        <div draggable=\"true\" class=\"descriptionElements tariff\">\n" +
            "                            <!--Дата начала-->\n" +
            "                            <div class=\"paramInput\">\n" +
            "                            <span class=\"flexParamInputEvently\">\n" +
            "                                <img width=\"24\" height=\"24\" class=\"icon\" src=\"icons/clock.svg\" alt=\"icon\">\n" +
            "                                <p class=\"text\">С</p>\n" +
            "                                <input type=\"text\" pattern=\"\\d\\d-\\d\\d\" class=\"niceInput\" name=\"input_startDate\"\n" +
            "                                       preloader=\"дд-мм\">\n" +
            "                            </span>\n" +
            "                            </div>\n" +
            "                            <!--Дата окончания-->\n" +
            "                            <div class=\"paramInput\">\n" +
            "                            <span class=\"flexParamInputEvently\">\n" +
            "                                <img width=\"24\" height=\"24\" class=\"icon\" src=\"icons/clock.svg\" alt=\"icon\">\n" +
            "                                <p class=\"text\">до</p>\n" +
            "                                <input type=\"text\" pattern=\"\\d\\d-\\d\\d\" class=\"niceInput\" name=\"input_endDate\"\n" +
            "                                       preloader=\"дд-мм\">\n" +
            "                            </span>\n" +
            "                            </div>\n" +
            "                            <!--Стоимость-->\n" +
            "                            <div class=\"paramInput\">\n" +
            "                            <span class=\"flexParamInputEvently\">\n" +
            "                                <img width=\"24\" height=\"24\" class=\"icon\" src=\"icons/money.svg\" alt=\"icon\">\n" +
            "                                <input type=\"number\" class=\"niceInput\" name=\"input_summary_tariff\" preloader=\"\">\n" +
            "                            </span>\n" +
            "                            </div>\n" +
            "                        </div>\n"

        document.getElementById("tariffContainer").prepend(newTariff)
    } else {
        alert("Можно создать только 9 тарифов")
    }
}