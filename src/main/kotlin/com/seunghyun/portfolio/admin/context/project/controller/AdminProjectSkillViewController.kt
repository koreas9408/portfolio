package com.seunghyun.portfolio.admin.context.project.controller

import com.seunghyun.portfolio.admin.context.project.service.AdminProjectSkillService
import com.seunghyun.portfolio.admin.data.FormElementDTO
import com.seunghyun.portfolio.admin.data.SelectFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/project/skill")
class AdminProjectSkillViewController(
    private val skillService: AdminProjectSkillService
) {

    @GetMapping
    fun projectSkill(model: Model): String {

        val projectList = skillService.getProjectList()
        val skillList = skillService.getSkillList()

        val formElements = listOf<FormElementDTO>(
            SelectFormElementDTO("project", "8", projectList),
            SelectFormElementDTO("skill", "4", skillList),
        )
        model.addAttribute("formElements", formElements)

        val table = skillService.getProjectSkillTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttributes = mutableMapOf<String, Any>(
            Pair("menuName", "Projects"),
            Pair("pageName", table.name),
            Pair("editable", false),
            Pair("deletable", true),
            Pair("hasDetails", false),
        )
        model.addAttribute("pageAttributes", pageAttributes)

        return "admin/page-table"
    }
}