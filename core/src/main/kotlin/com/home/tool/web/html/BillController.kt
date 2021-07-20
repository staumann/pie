package com.home.tool.web.html

import com.home.tool.core.BillProcessor
import com.home.tool.core.CategoryProcessor
import com.home.tool.model.Bill
import com.home.tool.model.Shop
import com.home.tool.service.BillService
import com.home.tool.service.ShopService
import com.home.tool.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.text.SimpleDateFormat
import java.util.*

val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

@Controller
class BillController(
    private val billProcessor: BillProcessor,
    private val shopService: ShopService,
    private val userService: UserService,
    private val categoryProcessor: CategoryProcessor
) {

    @GetMapping("/show/bill")
    fun editBill(@RequestParam("id") id: String, model: Model): ModelAndView {
        model["title"] = "Edit Bill"

        billProcessor.getDisplayInformation(id)?.apply {
            model["bill"] = this
            model["categories"] = categoryProcessor.getAllCategories().map { it to (it.id == this.id) }
            model["users"] = userService.getAll()
        }
        return ModelAndView("bill/details", model.asMap())
    }

    @GetMapping("/show/bill/new")
    fun newBill(model: Model): ModelAndView {
        model["title"] = "Create new Bill"
        model["shops"] = shopService.getAllShops()
        model["users"] = userService.getAll().filter { it.pool }
        return ModelAndView("bill/new", model.asMap())
    }

    @PostMapping("/show/bill/save")
    fun saveNewBill(
        @RequestParam("shopId") shopId: String,
        @RequestParam("payedBy") payedBy: String,
        @RequestParam("date") date: String
    ): ModelAndView {
        val cal: Calendar = Calendar.getInstance()
        cal.time = sdf.parse(date)
        val bill = billProcessor.saveBill(Bill(payedBy = payedBy, shopId = shopId, date = cal))
        return ModelAndView("redirect:/show/bill?id=${bill?.id}")
    }
}
