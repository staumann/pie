package com.home.tool.web.html

import com.home.tool.service.BillService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.text.SimpleDateFormat
import java.util.*

@Controller
@RequestMapping("/show/result")
class ResultController(private val billService: BillService) {

    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    @GetMapping("/menu")
    fun getMenu(model: Model): ModelAndView {
        model["title"] = "Result menu"
        return ModelAndView("result/menu", model.asMap())
    }

    @GetMapping("/calculate")
    fun getResult(@RequestParam("start") start: String, @RequestParam("end") end: String, model: Model): ModelAndView {
        model["title"] = "Result"
        val startCalendar = Calendar.getInstance()
        val endCalendar = Calendar.getInstance()
        startCalendar.time = sdf.parse(start)
        endCalendar.time = sdf.parse(end)
        val bills = billService.getBillsForTimeFrame(startCalendar, endCalendar)
        println("got bills: ")
        println(bills)
        model["bills"] = bills
        return ModelAndView("result/result", model.asMap())
    }
}
