package com.seunghyun.portfolio.admin.context.link.form

import com.seunghyun.portfolio.domain.entity.Link
import jakarta.validation.constraints.NotBlank

data class LinkForm(
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val name: String,
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val content: String,
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val isActive: Boolean
) {
    fun toEntity(): Link {
        return Link(
            name = this.name,
            content = this.content,
            isActive = this.isActive
        )
    }

    fun toEntity(id: Long): Link {
        val link = this.toEntity()
        link.id = id

        return link
    }
}
