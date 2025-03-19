package com.seunghyun.portfolio.admin.context.skill.controller

import com.seunghyun.portfolio.admin.context.skill.service.AdminSkillService
import com.seunghyun.portfolio.admin.data.FormElementDTO
import com.seunghyun.portfolio.admin.data.SelectFormElementDTO
import com.seunghyun.portfolio.admin.data.TextFormElementDTO
import com.seunghyun.portfolio.domain.constant.SkillType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/skill")
class AdminSkillViewController(
    private val skillService: AdminSkillService
) {

    @GetMapping
    fun skill(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO(name = "name", size = 2),
            SelectFormElementDTO(name = "type", size = 2, options = SkillType.values().map { it.name }.toList()),
            SelectFormElementDTO(name = "isActive", size = 2, options = listOf(true.toString(), false.toString()))
        )

        val table = skillService.getSkillTable()
        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Resume"),
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