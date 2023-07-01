var formDataPhotos = new FormData;
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

function createAddingForm() {
    removeOtherForm()
    let centerAddingContainer = document.createElement('div')
    centerAddingContainer.id = 'centerAddingContainer'
    document.getElementById('mainColumn').append(centerAddingContainer)
    pasteForm('/adding-form', 'centerAddingContainer')
    // Вызов функции с указанием селектора и времени ожидания
    waitForPhotoSpaceToDisplay('#addImages', 100);
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

//Получить форму по URL контроллера HtmlFormController и вставить в элемент с id = elementId
function pasteForm(url, elementId) {
    let xhr = new XMLHttpRequest()  // Создаём локальную переменную XHR, которая будет объектом XMLHttpRequest
    xhr.open('GET', url)     // Задаём метод запроса и URL  запроса
    xhr.onload = function () {
        // Используем обработчик событий onload, чтобы поймать ответ сервера XMLHttpRequest
        document.getElementById(elementId).innerHTML = xhr.response
    }
    xhr.send()  // Инициирует запрос. Посылаем запрос на сервер.
}

//Ждет пока появится input добавления фото и начинает сделить когда на этот input нажмут
function waitForPhotoSpaceToDisplay(selector, time) {

    if (document.querySelector(selector) != null) {
        let inputPhotosButton = document.querySelector(selector)
        let previewPhotosSpace = document.getElementById('previewImages')
        // Элемент найден, можно начинать с ним работать
        document.querySelector(selector).addEventListener('change', function() {
            console.log("Событие change произошло!");
            // удаляем все ранее созданные элементы с изображениями
            while (previewPhotosSpace.firstChild) {
                previewPhotosSpace.removeChild(previewPhotosSpace.firstChild);
            }

            // получаем выбранные изображения
            const files = inputPhotosButton.files;

            let i = 0
            // создаем элементы с превью изображений
            for (const file of files) {
                formDataPhotos.append('image_' + i, file);
                i++
                const img = document.createElement("img");
                img.className = 'photoElements'
                img.src = URL.createObjectURL(file);
                previewPhotosSpace.appendChild(img);
            }
        });
        return;
    } else {
        setTimeout(function() {
            waitForPhotoSpaceToDisplay(selector, time);
        }, time);
    }
}

function getFormAndPrependTo(url, element) {
    let xhr = new XMLHttpRequest()  // Создаём локальную переменную XHR, которая будет объектом XMLHttpRequest
    xhr.open('GET', url)     // Задаём метод запроса и URL  запроса
    xhr.onload = function () {
        // Используем обработчик событий onload, чтобы поймать ответ сервера XMLHttpRequest
        element.innerHTML = xhr.response  // Содержимое ответа, помещаем внутрь элемент "body"
    }
    xhr.send()  // Инициирует запрос. Посылаем запрос на сервер.
}

//Удалить все формы
function removeOtherForm() {
        [
            'centerAddingContainer', 'filters', 'info', 'container'
        ].forEach(formId => {
        let form = document.getElementById(formId)
        if (form !== null) {
            form.remove()
        }
    })
}

//Удалить все элементы с классом 'className'
function removeElementsWithClassName(className) {
    document.querySelectorAll('.' + className).forEach(item => {
        item.remove()
    })
}

function createApartmetnItem(item) {
    let updateButton = document.createElement('button')
    updateButton.setAttribute('onclick', 'editElement(' + item.id + ')')
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
    //Тут все нормально работает - ковычки не трогать
    deteteButton.setAttribute('onclick', 'deleteElement("delete", ' + item.id + ')')
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
    space.className = 'itemsElement numBlocks'
    space.innerHTML =
        '<img width="24" height="24" class="icon" src="icons/space.svg" alt="icon">' +
        '<p class="title">' + item.space + '</p>' +
        '<p class="title">м²</p>'

    let city = document.createElement('div')
    city.className = 'itemsElement cities'
    city.innerHTML =
        // '<img width="24" height="24" class="icon" src="icons/apartment.svg" alt="icon">' +
        '<p class="title">' + item.city + '</p>'

    let adult = document.createElement('div')
    adult.className = 'itemsElement people'
    adult.innerHTML =
        '<img width="24" height="24" class="icon" src="icons/person.svg" alt="icon">' +
        '<p class="title">' + item.adult + ' / ' + item.children + '</p>'

    let summary = document.createElement('div')
    summary.className = 'itemsElement numBlocks'
    summary.innerHTML =
        '<img width="24" height="24" class="icon" src="icons/money.svg" alt="icon">' +
        '<p class="title">' + item.summary + '</p>' +
        '<p class="title">₽</p>'

    firstRow.prepend( idNumber, name, calendarButton, updateButton, city, space, adult, summary, deteteButton)
    apartment.prepend(firstRow, secondRow)
    document.getElementById('container').prepend(apartment)
}

