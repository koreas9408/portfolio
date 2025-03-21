package com.seunghyun.portfolio.admin.context.introduction.controller

import com.seunghyun.portfolio.admin.context.introduction.form.IntroductionForm
import com.seunghyun.portfolio.admin.context.introduction.service.AdminIntroductionService
import com.seunghyun.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/introduction")
class AdminIntroductionApiController(
    private val introductionService: AdminIntroductionService
) {

    @PostMapping
    fun postIntroduction(@RequestBody @Validated form: IntroductionForm): ResponseEntity<Any> {
        introductionService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putIntroduction(@PathVariable id: Long, @RequestBody @Validated form: IntroductionForm): ResponseEntity<Any> {
        introductionService.update(id, form)

        return ApiResponse.successUpdate()
    }


}