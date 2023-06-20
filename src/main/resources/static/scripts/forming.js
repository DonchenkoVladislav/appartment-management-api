//Свормировать страницу со списком всех объектов
function createAllApartmentsPage() {
    callForm('/info-form')
    getAllApartmentNames()
    getJson('/info', '', '-')
}

function search() {
    getJson('/info', document.getElementById('filterInput').value, document.getElementById('filterCityInput').value)
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

//Удалить все элементы с классом 'className'
function removeElementsWithClassName(className) {
    document.querySelectorAll('.' + className).forEach(item => {
        item.remove()
    })
}

function createApartmetnItem(item) {
    let updateButton = document.createElement('button')
    updateButton.onclick = ''
    updateButton.type = 'button'
    updateButton.className = 'iconButton'
    updateButton.innerHTML =
        '<img width="20" height="20" class="icon" src="icons/pencil.svg" alt="icon">'

    let calendarButton = document.createElement('button')
    calendarButton.onclick = ''
    calendarButton.type = 'button'
    calendarButton.className = 'iconButton'
    calendarButton.innerHTML =
        '<img width="20" height="20" class="icon" src="icons/calendar.svg" alt="icon">'

    let deteteButton = document.createElement('button')
    deteteButton.onclick = ''
    deteteButton.type = 'button'
    deteteButton.className = 'iconButton'
    deteteButton.innerHTML =
        '<img width="20" height="20" class="icon" src="icons/trash.svg" alt="icon">'

    let apartment = document.createElement('div')
    apartment.className = "apartment"

    let firstRow = document.createElement('div')
    firstRow.className = 'apartmentRow'

    let secondRow = document.createElement('div')

    let idNumber = document.createElement('p')
    idNumber.prepend(item.id)

    let name = document.createElement('div')
    name.className = "itemsElement name"
    name.innerHTML =
        '<p class="title">' + item.name + '</p>'

    let space = document.createElement('div')
    space.className = 'itemsElement'
    space.innerHTML =
        '<img width="24" height="24" class="icon" src="icons/space.svg" alt="icon">' +
        '<p class="title">' + item.space + '</p>' +
        '<p class="title">м²</p>'

    let city = document.createElement('div')
    city.className = 'itemsElement'
    city.innerHTML =
        // '<img width="24" height="24" class="icon" src="icons/apartment.svg" alt="icon">' +
        '<p class="title">' + item.city + '</p>'

    let adult = document.createElement('div')
    adult.className = 'itemsElement'
    adult.innerHTML =
        '<img width="24" height="24" class="icon" src="icons/person.svg" alt="icon">' +
        '<p class="title">' + item.adult + ' / ' + item.children + '</p>'

    let summary = document.createElement('div')
    summary.className = 'itemsElement'
    summary.innerHTML =
        '<img width="24" height="24" class="icon" src="icons/money.svg" alt="icon">' +
        '<p class="title">' + item.summary + '</p>' +
        '<p class="title">₽</p>'

    firstRow.prepend( idNumber, name, calendarButton, updateButton, city, space, adult, summary, deteteButton)
    apartment.prepend(firstRow, secondRow)
    document.getElementById('container').prepend(apartment)
}

