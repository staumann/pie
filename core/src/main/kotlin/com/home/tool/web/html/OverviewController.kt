package com.home.tool.web.html

import com.home.tool.core.BillProcessor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class OverviewController(private val billProcessor: BillProcessor) {

    @GetMapping("/overview")
    fun overview(model: Model): ModelAndView  {
        model["title"] = "Bill overview"
        model["bills"] = billProcessor.getOverViewInformation()
        return ModelAndView("overview", model.asMap())
    }
}
