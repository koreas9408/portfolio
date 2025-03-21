package com.seunghyun.portfolio.admin.context.experience.controller

import com.seunghyun.portfolio.admin.context.experience.service.AdminExperienceService
import com.seunghyun.portfolio.admin.data.FormElementDTO
import com.seunghyun.portfolio.admin.data.SelectFormElementDTO
import com.seunghyun.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/experience")
class AdminExperienceViewController(
    private val experienceService: AdminExperienceService
) {

    @GetMapping
    fun experience(model: Model): String {

        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO("title", 4),
            TextFormElementDTO("description", 8),
            SelectFormElementDTO("startYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("startMonth", 2, (1..12).toList()),
            SelectFormElementDTO("endYear", 3, (2010..2030).toList()),
            SelectFormElementDTO("endMonth", 2, (1..12).toList()),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )

        model.addAttribute("formElements", formElements)

        val detailFormElements = listOf<FormElementDTO>(
            TextFormElementDTO("content", 10),
            SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("detailFormElements", detailFormElements)

        val table = experienceService.getExperienceTable()
        model.addAttribute("table", table)

        val detailTable = experienceService.geExperienceDetailTable(null)
        model.addAttribute("detailTable", detailTable)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Experience"),
            Pair("pageName", table.name),
            Pair("editable", true),
            Pair("deletable", false),
            Pair("hasDetails", false),
        )
        model.addAttribute("pageAttributes", pageAttributes)

        return "admin/page-table"
    }
}