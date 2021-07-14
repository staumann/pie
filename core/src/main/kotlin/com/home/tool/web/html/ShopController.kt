package com.home.tool.web.html

import com.home.tool.model.Shop
import com.home.tool.service.CategoryService
import com.home.tool.service.ShopService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/show/shop")
class ShopController(private val shopService: ShopService, private val categoryService: CategoryService) {

    @GetMapping("/overview")
    fun getAllShops(model: Model): ModelAndView {
        model["title"] = "Shop overview"
        model["shops"] = shopService.getAllShops()
        return ModelAndView("shop/overview")
    }

    @GetMapping("/new")
    fun newShop(model: Model): ModelAndView {
        model["title"] = "Create New Shop"
        model["categories"] = categoryService.getAll()
        return ModelAndView("shop/new")
    }

    @PostMapping("/new/store")
    fun storeShop(@RequestParam("name") name: String, @RequestParam("category") category: String): ModelAndView {
        shopService.storeShop(Shop(name = name, category = category))
        return ModelAndView("redirect:/show/shop/overview")
    }
}
