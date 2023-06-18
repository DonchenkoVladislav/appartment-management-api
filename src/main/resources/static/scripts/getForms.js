//Свормировать страницу со списком всех объектов
function createAllApartmentsPage() {
    callForm('/filter-form')
    let data = getJson('/all')
}

//Удалить все формы и получить форму по URL контроллера HtmlFormController
function callForm(url) {
    removeOtherForm()
    getForm(url)
}

//Получить форму по URL контроллера HtmlFormController
function getForm(url) {
    let xhr = new XMLHttpRequest()  // Создаём локальную переменную XHR, которая будет объектом XMLHttpRequest
    xhr.open('GET', url)     // Задаём метод запроса и URL  запроса
    xhr.onload = function () {
        // Используем обработчик событий onload, чтобы поймать ответ сервера XMLHttpRequest
        document.getElementById("mainColumn").innerHTML = xhr.response  // Содержимое ответа, помещаем внутрь элемент "body"
    }
    xhr.send()  // Инициирует запрос. Посылаем запрос на сервер.
}

//Удалить все формы
function removeOtherForm() {
    let addingForm = document.getElementById("addingFormContainer")
    let filterForm = document.getElementById("filters")

    if (addingForm !== null) {
        addingForm.remove()
    }
    if (filterForm !== null) {
        filterForm.remove()
    }
}

function createApartmetnItem(item) {
    let apartment = document.createElement('div')
    apartment.id = 'apartment'

    let firstRow = document.createElement('div')
    let secondRow = document.createElement('div')

    let idNumber = createElement(null, item.id, null)
    let name = createElement(
        '<img width="24" height="24" class="icon" src="icons/pen.svg" alt="icon">',
        item.name,
        null
    )
    let space = createElement(
        '<img width="24" height="24" className="icon" src="icons/space.svg" alt="icon">',
        item.space,
        'м²'
    )
    let adult = createElement(
        '<img width="24" height="24" class="icon" src="icons/person.svg" alt="icon">',
        item.adult + ' / ' + item.kids,
        null
    )
    let summary = createElement(
        '<img width="24" height="24" class="icon" src="icons/money.svg" alt="icon">',
        item.summary,
        '₽'
    )

    firstRow.prepend(idNumber, name, space, adult, summary)
    apartment.prepend(firstRow, secondRow)
    document.getElementById('container').prepend(apartment)
}

function createElement(icon, text, unit) {
    let element = document.createElement("div")
    element.className = 'itemsElement'
    element.innerHTML = icon
    element.innerHTML = text
    element.innerHTML = unit
    return element
}

