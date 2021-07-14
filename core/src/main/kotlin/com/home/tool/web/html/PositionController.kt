package com.home.tool.web.html

import com.home.tool.model.Position
import com.home.tool.service.PositionService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/show/position")
class PositionController(private val positionService: PositionService) {

    @Suppress("LongParameterList")
    @PostMapping("/save")
    fun savePosition(
        @RequestParam("name") name: String,
        @RequestParam("billId") billId: String,
        @RequestParam("targetId") targetId: String,
        @RequestParam("category") category: String,
        @RequestParam("amount") amount: Double,
        @RequestParam(value = "comment", required = false) comment: String
    ): ModelAndView {
        val pos = Position(
            billId = billId,
            amount = amount,
            category = category,
            name = name,
            targetId = targetId,
            comment = comment
        )
        positionService.storePosition(pos)
        return ModelAndView("redirect:/show/bill?id=$billId")
    }

    @GetMapping("/delete")
    fun deletePosition(@RequestParam("id") id: String, billId: String): ModelAndView {
        positionService.deletePosition(id)
        return ModelAndView("redirect:/show/bill?id=$billId")
    }
}
