package com.seunghyun.portfolio.domain.entity

import com.seunghyun.portfolio.domain.constant.SkillType
import jakarta.persistence.*

@Entity
class Skill(
    name: String,
    type: String,
    isActive: Boolean,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    var id: Long? = null

    var name: String = name

    @Enumerated(value = EnumType.STRING)
    @Column(name = "skill_type")
    var type: SkillType = SkillType.valueOf(type)

    var isActive: Boolean = isActive
}