package com.home.tool.web.html

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller("/error")
class ErrorController {
    @RequestMapping
    fun error(model: Model): ModelAndView {
        model["title"] = "Bill overview - Error"
        return ModelAndView("error", model.asMap())
    }
}