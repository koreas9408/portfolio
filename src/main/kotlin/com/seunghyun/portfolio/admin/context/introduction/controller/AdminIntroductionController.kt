package com.seunghyun.portfolio.admin.context.introduction.controller

import com.seunghyun.portfolio.admin.context.introduction.service.AdminIntroductionService
import com.seunghyun.portfolio.admin.data.FormElementDTO
import com.seunghyun.portfolio.admin.data.SelectFormElementDTO
import com.seunghyun.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/introduction")
class AdminIntroductionController(
    private val introductionService: AdminIntroductionService
) {
    @GetMapping
    fun introduction(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO(name = "content", size = 10),
            SelectFormElementDTO(name = "isActive", size = 2, options = listOf(true.toString(), false.toString()))
        )

        val table = introductionService.getIntroductionTable()
        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Index"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", false),
        )

        model.addAttribute("formElements", formElements)
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}