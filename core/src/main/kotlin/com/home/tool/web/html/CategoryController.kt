package com.home.tool.web.html

import com.home.tool.core.CategoryProcessor
import com.home.tool.model.Category
import com.home.tool.service.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class CategoryController(
    private val categoryService: CategoryService,
    private val categoryProcessor: CategoryProcessor
) {

    @GetMapping("/show/category/overview")
    fun showAllCategory(model: Model): ModelAndView {
        model["categories"] = categoryService.getAll()
        return ModelAndView("category/overview", model.asMap())
    }

    @GetMapping("/show/category/new")
    fun getNewCategory(): ModelAndView {
        return ModelAndView("category/new")
    }

    @PostMapping("/show/category/new/store")
    fun storeNewCategory(
        @RequestParam("name") name: String,
        @RequestParam("description") description: String
    ): ModelAndView {
        categoryService.storeCategory(Category(name = name, description = description))
        return ModelAndView("redirect:/show/category/overview")
    }

    @GetMapping("/show/category/details")
    fun getCategory(@RequestParam("id") id: String, model: Model): ModelAndView {
        val category = categoryService.getCategory(id)
        val total = categoryProcessor.getTotalForCategory(id)
        model["title"] = "Category details"
        category?.apply {
            model["category"] = this
        }
        model["total"] = total

        return ModelAndView("category/details")
    }
}