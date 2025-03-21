package com.seunghyun.portfolio.admin.context.experience.form

import com.seunghyun.portfolio.domain.entity.ExperienceDetail
import jakarta.validation.constraints.NotBlank

data class ExperienceDetailForm(
    val id: Long,
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val content: String,
    val isActive: Boolean,

    ) {
    fun toEntity(): ExperienceDetail {
        return ExperienceDetail(
            content = this.content,
            isActive = this.isActive
        )
    }
}
