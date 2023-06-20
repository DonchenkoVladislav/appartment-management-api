function save(formData) {

    let tariffList = new Array()

    Array.from(document.getElementsByClassName("tariff")).forEach((item) => {

        var tariff = new Object()

        tariff.startDate = item.querySelector("[name=input_startDate]").value
        tariff.endDate = item.querySelector("[name=input_endDate]").value
        tariff.summaryTariff = item.querySelector("[name=input_summary_tariff]").value

        tariffList.push(tariff)
    })

    let requiredFields = [
        $("[name='input_name']").val(),
        $("[name='input_coordinates']").val(),
        $("[name='input_space']").val(),
        $("[name='input_adult']").val(),
        $("[name='input_beds']").val(),
        $("[name='input_summary']").val()
    ]

    let allrequiredFieldsIsFilled = true

    requiredFields.forEach(element => {
        if (element === "") {
            allrequiredFieldsIsFilled = false
        }
    })

    if (allrequiredFieldsIsFilled) {
        //Отправка нового объекта на сервер
        $.ajax({
            type: 'POST',
            url: '/save',
            dataType: 'json',
            processData: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                "name": $("[name='input_name']").val(),
                "city": $("[name='input_city']").val(),
                "coordinates": $("[name='input_coordinates']").val(),
                "space": $("[name='input_space']").val(),
                "adult": $("[name='input_adult']").val(),
                "children": $("[name='input_children']").val(),
                "beds": $("[name='input_beds']").val(),
                "from": $("[name='input_from']").val(),
                "summary": $("[name='input_summary']").val(),
                "view": $("[name='input_view']").val(),
                "conveniences": $("[name='input_conveniences']").val(),
                "services": $("[name='input_services']").val(),
                "tariffs": tariffList,
            }),
            error: function (request, status, error) {
                if (!request.status === 200) {
                    alert("Упс! Что-то пошло не так. Попробуйте еще раз")
                }
            }
        })

        formData.append("appartmentName", $("[name='input_name']").val())

        //Отправка фотографий нового объекта на сервер
        $.ajax({
            type: 'POST',
            url: '/save-photos',
            dataType: 'json',
            processData: false,
            contentType: 'application/json; charset=utf-8',
            data: formData,
            error: function (request, status, error) {
                if (!request.status === 200) {
                    alert("Не удалось загрузить изображения")
                }
            }
        })
    } else {
        alert("Заполнены не все обязательные поля")
    }
}

function getJson(url, name, city) {
    //Получение объекта для формирования списка со всеми записями
    $.ajax({
        url: url + '?name=' + name + '&city=' + city,
        type: 'GET',
        headers: {
            'Accept': 'application/json'
        },
        success: function(info) {
            removeElementsWithClassName('apartment')

            let countApartments = info.length
            let totalSpace = 0
            let totalPrise = 0
            info.forEach(item => {
                totalSpace += item.space
                totalPrise += item.summary
                createApartmetnItem(item)
            })
            document.getElementById('totalSpace').innerHTML = totalSpace
            document.getElementById('totalApartments').innerHTML = countApartments
            document.getElementById('averagePrice').innerHTML = totalPrise/countApartments
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Обработка ошибки
        }
    });
}

//Функция используется в forming.js. Не удалять
function deleteElement(url, id) {
    return $.ajax({
        url: '/' + url + '?id=' + id,
        type: 'DELETE',
        headers: {
            'Accept': 'application/json'
        },
        success: function() {
            getAllApartmentNames()
            getJson('/info', '', '-')
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Обработка ошибки
        }
    });
}