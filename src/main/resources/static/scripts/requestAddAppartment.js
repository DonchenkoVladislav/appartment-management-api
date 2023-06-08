function save(formData) {

    let tariffList = new Array()

    Array.from(document.getElementsByClassName("tariff")).forEach((item) => {

        var tariff = new Object()

        tariff.startDate = item.querySelector("[name=input_startDate]").value
        tariff.endDate = item.querySelector("[name=input_endDate]").value
        tariff.summaryTariff = item.querySelector("[name=input_summary_tariff]").value

        tariffList.push(tariff)
    })

    $.ajax({
        type: 'POST',
        url: '/appartments-add',
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

    $.ajax({
        type: 'POST',
        url: '/appartments-add-photos',
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
}