function save(method, url) {

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
            type: method,
            url: url,
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
                "description": $("[name='input_description']").val(),
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

        formDataPhotos.append("appartmentName", $("[name='input_name']").val())

        // Отправка фотографий нового объекта на сервер
        $.ajax({
            type: 'POST',
            url: '/save-photos',
            // dataType: 'image/jpeg, image/png',
            processData: false,
            contentType: 'image/jpeg; image/png; charset=utf-8',
            data: formDataPhotos,
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

function editElementPut(id) {
    save('PUT', '/edit?' + id)
}

function getJson(url, name, city) {
    //Получение объекта для формирования списка со всеми записями
    $.ajax({
        url: url + '?name=' + name + '&city=' + city,
        type: 'GET',
        headers: {
            'Accept': 'application/json'
        },
        success: function (info) {
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
            document.getElementById('averagePrice').innerHTML = Math.round(totalPrise / countApartments)
        },
        error: function (jqXHR, textStatus, errorThrown) {
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
        success: function () {
            getAllApartmentNames()
            getJson('/info', '', '-')
        },
        error: function (jqXHR, textStatus, errorThrown) {
            // Обработка ошибки
        }
    });
}

function editElement(id) {

    // удаляем все кнопоки закрытия формы редактирвоания, чтоб они не копились
    deleteElementById('closeFormButton')

    // создаем кнопочку для закрытия формы
    let closeButton = document.createElement('button')
    closeButton.setAttribute('onclick', 'stopEditing()')
    closeButton.type = 'button'
    closeButton.className = 'iconButton'
    closeButton.id = 'closeFormButton'
    closeButton.innerHTML =
        '<img width="20" height="20" class="icon" src="icons/close.svg" alt="icon">'

    deleteEddingForms()

    $.ajax({
        url: '/edit?id=' + id,
        type: 'GET',
        headers: {
            'Accept': 'application/json'
        },
        success: function (edit) {

            //Находим все открытые окошки с формами
            let editContainers = document.querySelectorAll('div.apartment div.animate-height')

            //Закрываем все открытые окошки с формами
            if (editContainers.length > 0) {
                editContainers.forEach(element => {
                    element.className = 'animate-height-none'
                    // element.remove()
                })
            }

            // Определяем для какого элемента открыть окошко с формой
            const apartmentDiv = findElementByText('div.apartment div.apartmentRow p', edit.id.toString())
                .parentElement
                .parentElement;

            // создаем div элемент
            const editContainer = document.createElement('div');

            // добавляем класс "animate-height" к элементу div - окошко для формы mainColumnFullContainers (создается выше)
            editContainer.classList.add('animate-height');

            // добавляем элемент на страницу - открываем это окошко (выше)
            apartmentDiv.appendChild(editContainer);

            // добавляем кнопочку для закрытия формы
            findElementByText('div.apartment div.apartmentRow p', edit.id.toString())
                .parentElement
                .append(closeButton)

            //Заполняем все поля формы mainColumnFullContainers
            setTimeout(function () {
                    getFormAndPrependTo(
                        '/adding-form', document.getElementsByClassName('animate-height')[0]
                    )
                },
                800)
            waitLoadElementByNameAndFillValue('input_name', edit.name)
            waitLoadElementByNameAndFillValue('input_city', edit.city)
            waitLoadElementByNameAndFillValue('input_coordinates', edit.coordinates)
            waitLoadElementByNameAndFillValue('input_space', edit.space)
            waitLoadElementByNameAndFillValue('input_adult', edit.adult)
            waitLoadElementByNameAndFillValue('input_children', edit.children)
            waitLoadElementByNameAndFillValue('input_beds', edit.beds)
            waitLoadElementByNameAndFillValue('input_from', edit.from)
            waitLoadElementByNameAndFillValue('input_summary', edit.summary)
            waitLoadElementByNameAndFillValue('input_view', edit.view)
            waitLoadElementByNameAndFillValue('input_conveniences', edit.conveniences)
            waitLoadElementByNameAndFillValue('input_services', edit.services)
            waitLoadElementByNameAndFillValue('input_description', edit.description)
            createTariffsInAddingFrom('tariff', edit.tariffs)
            gSave('PUT', '/edit?id=', Math.round(edit.id))
            // $(document).on('click', '#saveButton', function () {
            //     save('PUT', '/edit?' + item.id)
            // });
            // setTimeout(() => document.getElementById('saveButton').setAttribute('onclick', 'save("PUT", "/edit?"' + item.id + ')'), 1000)
            // waitLoadElementByIdAndSetAtribute('saveButton', 'onclick', 'save("PUT", "/edit?"' + item.id + ')')

        },
        error: function (jqXHR, textStatus, errorThrown) {
            // Обработка ошибки
        }
    });

    // Вызов функции с указанием селектора и времени ожидания
    // Добавление фотографий
    waitForPhotoSpaceToDisplay('#addImages', 100);
}

function stopEditing() {
    // удаляем все кнопоки закрытия формы редактирвоания, чтоб они не копились
    deleteElementById('closeFormButton')
    deleteEddingForms()
    closeEddingForms()
}

function deleteEddingForms() {
    //находим все формы, которая включит в себя все описание квартиры
    let mainColumnFullContainers = document.getElementsByClassName('mainColumnFullContainer')
    //Удаляем уже открытые формы
    if (mainColumnFullContainers.length > 0) {
        mainColumnFullContainers[0].remove()
    }
}

function closeEddingForms() {
//Находим все открытые окошки с формами
    let editContainers = document.querySelectorAll('div.apartment div.animate-height')

    //Закрываем все открытые окошки с формами
    if (editContainers.length > 0) {
        editContainers.forEach(element => {
            element.className = 'animate-height-none'
            // element.remove()
        })
    }
}

function deleteElementById(elementId) {
    let otherCloseButton = document.getElementById(elementId)
    if (otherCloseButton) {
        otherCloseButton.remove()
    }
}