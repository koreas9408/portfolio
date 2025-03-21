package com.seunghyun.portfolio.admin.context.experience.service

import com.seunghyun.portfolio.admin.context.experience.form.ExperienceForm
import com.seunghyun.portfolio.admin.data.ApiResponse
import com.seunghyun.portfolio.admin.data.TableDTO
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/experience")
class AdminExperienceApiController(
    private val experienceService: AdminExperienceService
) {

    @PostMapping
    fun postExperience(@RequestBody @Validated form: ExperienceForm): ResponseEntity<Any> {
        experienceService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putExperience(@PathVariable id: Long, @RequestBody @Validated form: ExperienceForm): ResponseEntity<Any> {
        experienceService.update(id, form)

        return ApiResponse.successUpdate()
    }

    @GetMapping("/{id}/details")
    fun getExperienceDetails(@PathVariable id: Long): TableDTO {
        return experienceService.geExperienceDetailTable(id)
    }

}