package com.svoi.vkaliningrade.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlFormController {

    /*Контроллер только для отправки html кода, чтоб не писать весь html в одном файле,
    а делать запросы к этому контроллеру и подставлять его в нужные элементы на странице*/

    /*Контроллер для получения формы добавления/изменения объектов*/
    @GetMapping("/adding-form")
    public String getApartmentAddForm() {
        return "forms/adding_form";
    }

    /*Контроллер для получения формы фильтров*/
    @GetMapping("/info-form")
    public String getFilterForm() {
        return "forms/info_form";
    }
}
