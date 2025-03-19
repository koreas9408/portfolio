package com.seunghyun.portfolio.admin.context.link.controller

import com.seunghyun.portfolio.admin.context.link.service.AdminLinkService
import com.seunghyun.portfolio.admin.data.FormElementDTO
import com.seunghyun.portfolio.admin.data.SelectFormElementDTO
import com.seunghyun.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/link")
class AdminLinkViewController(
    private val linkService: AdminLinkService
) {

    @GetMapping
    fun link(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO(name = "name", size = 2),
            TextFormElementDTO(name = "content", size = 8),
            SelectFormElementDTO(name = "isActive", size = 2, options = listOf(true.toString(), false.toString()))
        )

        val table = linkService.getLinkTable()
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