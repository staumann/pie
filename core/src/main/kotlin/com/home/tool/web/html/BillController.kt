package com.home.tool.web.html

import com.home.tool.core.BillProcessor
import com.home.tool.model.Shop
import com.home.tool.service.BillService
import com.home.tool.service.ShopService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class BillController(private val billProcessor: BillProcessor, private val shopService: ShopService) {

    @GetMapping("/show/bill")
    fun editBill(@RequestParam("id") id: String, model: Model): ModelAndView {
        model["title"] = "Edit Bill"

        billProcessor.getDisplayInformation(id)?.apply {
            model["bill"] = this
            model["shops"] = getShops(this.shop)
        }
        return ModelAndView("bill/details", model.asMap())
    }

    private fun getShops(shop: Shop?): List<Pair<Shop, Boolean>> =
        shopService.getAllShops().map { Pair(it, it.id == shop?.id) }.toList()
}