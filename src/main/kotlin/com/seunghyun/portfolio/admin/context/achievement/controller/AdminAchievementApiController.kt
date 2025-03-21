package com.seunghyun.portfolio.admin.context.achievement.controller

import com.seunghyun.portfolio.admin.context.achievement.form.AchievementForm
import com.seunghyun.portfolio.admin.context.achievement.service.AdminAchievementService
import com.seunghyun.portfolio.admin.data.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/api/achievement")
class AdminAchievementApiController(
    private val achievementService: AdminAchievementService
) {

    @PostMapping
    fun postAchievement(@RequestBody @Validated form: AchievementForm): ResponseEntity<Any> {
        achievementService.save(form)

        return ApiResponse.successCreate()
    }

    @PutMapping("/{id}")
    fun putAchievement(@PathVariable id: Long, @RequestBody @Validated form: AchievementForm): ResponseEntity<Any> {
        achievementService.update(id, form)

        return ApiResponse.successUpdate()
    }


}