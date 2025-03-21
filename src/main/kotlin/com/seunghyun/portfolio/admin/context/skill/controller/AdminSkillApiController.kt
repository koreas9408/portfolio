package com.seunghyun.portfolio.admin.context.skill.controller

import com.seunghyun.portfolio.admin.context.skill.form.SkillForm
import com.seunghyun.portfolio.admin.context.skill.service.AdminSkillService
import com.seunghyun.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/skill")
class AdminSkillApiController(
    private val skillService: AdminSkillService
) {

    @PostMapping
    fun postSkill(@RequestBody @Validated form: SkillForm): ResponseEntity<Any> {
        skillService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putSkill(@PathVariable id: Long, @RequestBody @Validated form: SkillForm): ResponseEntity<Any> {
        skillService.update(id, form)

        return ApiResponse.successUpdate()
    }


}