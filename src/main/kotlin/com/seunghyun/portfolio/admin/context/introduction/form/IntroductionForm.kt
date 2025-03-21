package com.seunghyun.portfolio.admin.context.introduction.form

import com.seunghyun.portfolio.domain.entity.Introduction
import jakarta.validation.constraints.NotBlank

data class IntroductionForm(
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val content: String,
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val isActive: Boolean
) {
    fun toEntity(): Introduction {
        return Introduction(
            content = this.content,
            isActive = this.isActive
        )
    }

    fun toEntity(id: Long): Introduction {
        val introduction = this.toEntity()
        introduction.id = id

        return introduction
    }
}