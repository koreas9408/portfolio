package com.seunghyun.portfolio.admin.context.skill.form

import com.seunghyun.portfolio.domain.entity.Skill
import jakarta.validation.constraints.NotBlank

data class SkillForm(
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val name: String,
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val type: String,
    @field:NotBlank(message = "{0}은 필수 값 입니다.")
    val isActive: Boolean
) {
    fun toEntity(): Skill {
        return Skill(
            name = this.name,
            type = this.type,
            isActive = this.isActive
        )
    }

    fun toEntity(id: Long): Skill {
        val skill = this.toEntity()
        skill.id = id

        return skill
    }
}
