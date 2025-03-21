package com.seunghyun.portfolio.admin.context.project.form

import com.seunghyun.portfolio.domain.entity.ProjectDetail
import jakarta.validation.constraints.NotBlank

data class ProjectDetailForm(
    val id: Long,
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val content: String,
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val url: String?,
    val isActive: Boolean,
) {
    fun toEntity(): ProjectDetail {
        return ProjectDetail(
            content = this.content,
            url = this.url,
            isActive = this.isActive
        )
    }
}
