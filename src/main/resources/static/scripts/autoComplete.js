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