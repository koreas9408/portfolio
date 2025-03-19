package com.seunghyun.portfolio.admin.context.achievement.controller

import com.seunghyun.portfolio.admin.context.achievement.service.AdminAchievementService
import com.seunghyun.portfolio.admin.data.DateFormElementDTO
import com.seunghyun.portfolio.admin.data.FormElementDTO
import com.seunghyun.portfolio.admin.data.SelectFormElementDTO
import com.seunghyun.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/achievement")
class AdminAchievementViewController(
    private val achievementService: AdminAchievementService
) {

    @GetMapping
    fun achievement(model: Model): String {
        val formElements = listOf<FormElementDTO>(
            TextFormElementDTO(name = "title", size = 4),
            TextFormElementDTO(name = "description", size = 8),
            DateFormElementDTO(name = "achievedDate", size = 5),
            TextFormElementDTO(name = "host", size = 5),
            SelectFormElementDTO(name = "isActive", size = 2, options = listOf(true.toString(), false.toString()))
        )

        val table = achievementService.getAchievementTable()
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