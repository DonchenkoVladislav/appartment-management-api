function getAllApartmentNames() {
        $.ajax({
            type: 'GET',
            url: '/nameList',
            dataType: 'json',
            success: function (nameList) {
                nameList.forEach((item) => {
                    var option = document.createElement('option')
                    option.innerHTML = item
                    document.getElementById('allNames').prepend(option)
                })
            }
        });
}

function waitLoadElementByNameAndFillValue(name, value) {
    function checkElement() {
        let element = document.getElementsByName(name)[0];
        if (element) {
            console.log("Элемент найден");
            // Останавливаем выполнение setInterval
            clearInterval(intervalId);
            // Выполняем действия с элементом
            element.value = value
        }
    }
    // Проверяем наличие элемента с таймаутом timeout
    let intervalId = setInterval(checkElement, 200);
}

//Создать и заполнить список тарифов в форме adding_form.html
function createTariffsInAddingFrom(name, tariffs) {
    function checkElement() {
        let element = document.getElementsByClassName(name)[0];
        if (element) {
            console.log("Элементы с классом " + name + " найдены");
            // Останавливаем выполнение setInterval
            clearInterval(intervalId);
            tariffs.filter(tariff => tariff.summaryTariff !== 0).forEach(() => {
                console.log("Создаю тариф")
                createTariff()
            })
            tariffsStartDateInputs = document.getElementsByName('input_startDate')
            for (i = 0; i < tariffsStartDateInputs.length - 1; i++) {
                console.log(i)
                tariffsStartDateInputs[i].value = tariffs[i].startDate
            }
            tariffsEndDateInputs = document.getElementsByName('input_endDate')
            for (e = 0; e < tariffsEndDateInputs.length - 1; e++) {
                tariffsEndDateInputs[e].value = tariffs[e].endDate
            }
            tariffsSummaryInputs = document.getElementsByName('input_summary_tariff')
            for (s = 0; s < tariffsSummaryInputs.length - 1; s++) {
                tariffsSummaryInputs[s].value = tariffs[s].summaryTariff
            }
        }
    }
    // Проверяем наличие элемента с таймаутом timeout
    let intervalId = setInterval(checkElement, 200);
}

function findElementByText(selector, text) {
    const elements = document.querySelectorAll(selector);
    for (let i = 0; i < elements.length; i++) {
        if (elements[i].textContent === text) {
            return elements[i];
        }
    }
    return null;
}


